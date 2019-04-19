-- 数据库初始化脚本

-- MySQL version: 5.7.21

-- 创建数据库
create database db_seckill;

-- 使用数据库
use db_seckill;

-- 创建秒杀库存表
create table seckill_product (
  `seckill_id`  bigint      not null auto_increment
  comment '秒杀商品ID',
  `name`        varchar(50) not null
  comment '商品名称',
  `number`      int         not null
  comment '库存数量',
  `start_time`  datetime(6) not null
  comment '秒杀开始时间',
  `end_time`    datetime(6) not null
  comment '秒杀结束时间',
  `create_time` datetime(6) not null
  comment '创建时间',
  primary key (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)
  engine = InnoDB
  auto_increment = 1000
  default charset = utf8
  comment = '秒杀库存表';

-- 初始化库存数据
insert into seckill_product (`name`, `number`, `start_time`, `end_time`, create_time)
values ('1000元秒杀手机', 100, '2019-04-16 00:00:00', '2019-04-17 00:00:00', now()),
       ('500元秒杀ipad', 200, '2019-04-16 00:00:00', '2019-04-17 00:00:00', now()),
       ('2000元秒杀笔记本电脑', 300, '2019-04-16 00:00:00', '2019-04-17 00:00:00', now()),
       ('3000元秒杀PC', 400, '2019-04-16 00:00:00', '2019-04-17 00:00:00', now());

-- 秒杀成功明细表
create table success_killed (
  `seckill_id`  bigint      not null
  comment '秒杀商品ID',
  `user_phone`  varchar(50) not null
  comment '用户手机号',
  `state`       tinyint     not null default -1
  comment '状态标识：-1：无效；0：有效；1：已付款',
  `create_time` datetime(6) not null
  comment '创建时间',
  primary key (seckill_id, user_phone),
  key idx_create_time(create_time)
)
  engine = InnoDB
  default charset = utf8
  comment = '秒杀成功明细表';