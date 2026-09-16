-- archv 档案馆库房环境监测与虫霉防治 -- schema (liu-015)
-- 列名与基线实体契约（@TableName/@TableField）逐列对齐，改列必须同步实体。
-- 库：liu_015

CREATE TABLE IF NOT EXISTS t_arv_depot (
  id bigint NOT NULL COMMENT '主键',
  depot_code varchar(32) DEFAULT NULL COMMENT '档案馆编号',
  depot_name varchar(64) DEFAULT NULL COMMENT '档案馆名称',
  status int DEFAULT NULL COMMENT '档案状态 0在用 1已停用',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='档案馆档案';

CREATE TABLE IF NOT EXISTS t_arv_env_rule (
  id bigint NOT NULL COMMENT '主键',
  rule_code varchar(32) DEFAULT NULL COMMENT '规则编号',
  rule_name varchar(64) DEFAULT NULL COMMENT '规则名称',
  th1_max decimal(6,2) DEFAULT NULL COMMENT '第一档上限',
  th2_max decimal(6,2) DEFAULT NULL COMMENT '第二档上限',
  th3_max decimal(6,2) DEFAULT NULL COMMENT '第三档上限',
  eff_start datetime DEFAULT NULL COMMENT '管用起始时刻',
  eff_end datetime DEFAULT NULL COMMENT '管用截止时刻(不含)',
  priority int DEFAULT NULL COMMENT '优先次序(数值越大越优先)',
  status int DEFAULT NULL COMMENT '规则状态 0启用 1停用',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库房环境限值规则';

CREATE TABLE IF NOT EXISTS t_arv_env_task (
  id bigint NOT NULL COMMENT '主键',
  item_no varchar(64) DEFAULT NULL COMMENT '条目编号',
  due_at datetime DEFAULT NULL COMMENT '到期时刻',
  amount decimal(12,2) DEFAULT NULL COMMENT '计量值',
  status int DEFAULT NULL COMMENT '状态 0待处理 1已处理 2失败',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='温湿度超限待办';

CREATE TABLE IF NOT EXISTS t_arv_fumigate (
  id bigint NOT NULL COMMENT '主键',
  bill_no varchar(64) DEFAULT NULL COMMENT '单据编号',
  node_no int DEFAULT NULL COMMENT '当前关口 0..2',
  sign_mode int DEFAULT NULL COMMENT '签批模式 0或签 1会签',
  need_count int DEFAULT NULL COMMENT '本关口应签人数',
  sign_count int DEFAULT NULL COMMENT '本关口已签票数',
  status int DEFAULT NULL COMMENT '单据结果 0审批中 1已通过 2已否决',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='熏蒸作业单';

CREATE TABLE IF NOT EXISTS t_arv_mon_item (
  id bigint NOT NULL COMMENT '主键',
  batch_no varchar(64) DEFAULT NULL COMMENT '批次号',
  row_no int DEFAULT NULL COMMENT '原始行号',
  item_code varchar(64) DEFAULT NULL COMMENT '测点编码',
  qty decimal(12,2) DEFAULT NULL COMMENT '测得数值',
  status int DEFAULT NULL COMMENT '行状态 0待处理 1成功 2失败',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='环境监测批次明细';

CREATE TABLE IF NOT EXISTS t_arv_room (
  id bigint NOT NULL COMMENT '主键',
  room_no varchar(64) DEFAULT NULL COMMENT '库房档案编号',
  room_name varchar(64) DEFAULT NULL COMMENT '库房名称',
  room_type varchar(32) DEFAULT NULL COMMENT '库房类型 纸档/胶片/特藏/综合',
  depot_id bigint DEFAULT NULL COMMENT '所属档案馆ID',
  depot_code varchar(32) DEFAULT NULL COMMENT '所属档案馆编号(冗余，以档案为准)',
  area decimal(10,2) DEFAULT NULL COMMENT '库房面积(平方米)',
  review_due datetime DEFAULT NULL COMMENT '复核时限',
  remain_days int DEFAULT NULL COMMENT '距复核时限剩余天数',
  keeper varchar(64) DEFAULT NULL COMMENT '库房管理员',
  room_status int DEFAULT NULL COMMENT '库房状态 0在用 1已停用',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库房档案';

CREATE TABLE IF NOT EXISTS t_arv_treat (
  id bigint NOT NULL COMMENT '主键',
  biz_no varchar(64) DEFAULT NULL COMMENT '单据编号',
  stage int DEFAULT NULL COMMENT '当前环节 0..3',
  status int DEFAULT NULL COMMENT '流转状态 0待发起 1在办 2已办结',
  content varchar(255) DEFAULT NULL COMMENT '处置情况备注',
  last_action varchar(64) DEFAULT NULL COMMENT '最近一次流转动作',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='虫霉防治处置单';

-- 初始档案数据（验收测试依赖 id=1 启用 / id=2 停用）
INSERT INTO t_arv_depot (id, depot_code, depot_name, status, del_flag, create_by, create_time)
VALUES (1, 'AR-001', '市综合档案馆', 0, 0, 'sys', NOW()),
       (2, 'AR-002', '市档案馆老馆(停用)', 1, 0, 'sys', NOW());
