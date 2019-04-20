// JS模块-秒杀商品
var seckill = {
    // 封装秒杀相关ajax的url
    URL: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        execution: function (seckillId, md5) {
            return "/seckill/" + seckillId + "/" + md5 + "/execution";
        }
    },
    // 验证手机号
    validatePhone: function (phone) {
        return phone && phone.length === 11 && !isNaN(phone);
    },
    // 时间判断
    countdown: function (seckillId, nowTime, stateTime, endTime) {
        var seckillBox = $("#seckill-box");

        // 时间判断
        if (nowTime > endTime) {
            // 秒杀结束
            seckillBox.hide().html('秒杀结束').show(300);
        } else {
            if (nowTime < stateTime) {
                // 秒杀未开始，开始倒计时
                // 计时事件绑定
                // 秒杀时间加一秒，防止时间偏移
                var killTime = new Date(stateTime + 1000);
                seckillBox.countdown(killTime, function (event) {
                    // 时间格式
                    var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');

                    seckillBox.html(format).show();
                }).on('finish.countdown', function () {
                    // 时间完成后回调事件，控制显示逻辑，执行秒杀
                    seckill.handlerSeckill(seckillId, seckillBox);
                });
            } else {
                // 秒杀开始
                seckill.handlerSeckill(seckillId, seckillBox);
            }
        }
    },
    handlerSeckill: function (seckillId, node) {
        // 时间完成后回调事件，控制显示逻辑，执行秒杀
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');

        // 获取秒杀地址
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            // 在回调函数中执行交互流程

            if (result && result.success) {
                var exposer = result.data;

                // 判断是否开启秒杀
                if (exposer.exposed) {
                    // 开启秒杀
                    // 获取秒杀地址
                    var killUrl = seckill.URL.execution(seckillId, exposer.md5);
                    console.log("killUrl:" + killUrl);

                    // 绑定一次点击事件
                    $("#killBtn").one('click', function () {
                        // 绑定执行秒杀请求的操作
                        // 禁用按钮
                        $(this).addClass('disabled');

                        // 发送秒杀请求，执行秒杀
                        $.post(killUrl, {}, function (result) {
                            if (result && result.success) {
                                var seckillResult = result.data;

                                var state = seckillResult.state;
                                var stateInfo = seckillResult.stateInfo;

                                // 显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');

                            } else {
                                console.log('result:' + JSON.stringify(result));
                            }
                        }, 'JSON');
                    });

                    node.show();
                } else {
                    // 未开启秒杀
                    seckill.countdown(seckillId, exposer.now, exposer.startTime, exposer.endTime);
                }
            } else {
                console.log('result:' + JSON.stringify(result));
            }
        }, 'json');

    },
    // 详情页秒杀逻辑
    details: {
        // 详情页初始化
        init: function (params) {
            // 用户手机验证和登录，计时交互
            // 规划交互流程
            // 从cookie中查找手机号
            var killPhone = $.cookie("killPhone");

            var seckillId = params.seckillId;
            var startTime = params.startTime;
            var endTime = params.endTime;

            // 验证手机号
            if (!seckill.validatePhone(killPhone)) {
                // 绑定手机号
                // 控制输出
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    // 显示弹出层
                    show: true,
                    // 禁止位置关闭
                    backdrop: 'static',
                    // 关闭键盘事件
                    keyboard: false
                });

                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhoneKey").val();

                    if (seckill.validatePhone(inputPhone)) {
                        // 写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});

                        // 验证通过，刷新页面
                        window.location.reload();
                    } else {
                        $("#killPhoneMessage").hide().html('<label class="label label-danger">请填写有效的手机号！</label>').show(300);
                    }

                });
            } else {
                // 手机号验证通过

                // 计时
                $.getJSON(seckill.URL.now(), '', function (result) {
                    if (result && result.success) {
                        var nowTime = result.data;

                        // 时间判断，进行计时交互
                        seckill.countdown(seckillId, nowTime, startTime, endTime);
                    } else {
                        console.log('result:' + JSON.stringify(result));
                    }
                });
            }
        }
    }
};