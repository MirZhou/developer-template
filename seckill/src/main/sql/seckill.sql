-- 存储过程：秒杀执行

DELIMITER $$-- console ; 转换为 $$
-- 定义存储过程
-- 参数：in 输入参数； out 输出参数
-- row_count()：返回上一条修改类型sql(delete, insert, update)的影响行数
-- row_count: 0 未修改数据； > 0 表示修改的行数； < 0 sql错误/未执行修改sql
CREATE PROCEDURE `db_seckill`.`execute_seckill` (
	-- 秒杀对象ID
	IN v_seckill_id BIGINT,
	-- 用户手机号
	IN v_phone VARCHAR ( 50 ),
	-- 秒杀时间
	IN v_kill_time DATETIME,
	-- 存储过程执行结果
	OUT r_result INT
	) BEGIN
	-- 变量：接收修改类型sql(delete, insert, update)的影响行数
	DECLARE insert_count INT DEFAULT 0;

	-- 开启事务
	START TRANSACTION;

	-- 插入秒杀记录
	INSERT IGNORE INTO success_killed ( seckill_id, user_phone, create_time )
	VALUES
		( v_seckill_id, v_phone, v_kill_time );

	-- 获取插入的记录数
	SELECT ROW_COUNT( ) INTO insert_count;
	IF ( insert_count = 0 ) THEN
		  -- 未执行插入，主键重复
			ROLLBACK;
		  SET r_result =- 1;

  ELSEIF ( insert_count < 0 ) THEN
    -- 未执行插入，未知异常
		ROLLBACK;
		SET r_result =- 2;
  ELSE
    -- 减库存
    UPDATE db_seckill.seckill_product
		SET number = number - 1
		WHERE
			seckill_id = v_seckill_id
			AND v_kill_time BETWEEN start_time AND end_time
			AND number > 0;
		SELECT row_count( ) INTO insert_count;
		IF ( insert_count = 0 ) THEN
		  -- 减库存操作失败。可能原因：库存不足；秒杀活动未开始或已结束
      ROLLBACK;

			SET r_result = 0;

    ELSEIF ( insert_count < 0 ) THEN
		  -- 减库存操作失败。未知异常
			ROLLBACK;

			SET r_result =- 2;
    ELSE
      -- 减库存成功
      COMMIT;

			SET r_result = 1;

		END IF;

	END IF;

END $$ -- 存储过程定义结束
DELIMITER;