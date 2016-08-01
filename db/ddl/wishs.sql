DROP TABLE IF EXISTS wishs;  
CREATE TABLE wishs (  
  wish_id integer NOT NULL AUTO_INCREMENT,  
  wish_name varchar(200) ,
  wish_type varchar(20) ,
  status_id integer default 0,
  feature integer default 0,
  general_code varchar(20),
  general_cate varchar(20),
  sub_cate_code varchar(40),
  sub_category varchar(200),
  material varchar(500),
  model varchar(50),
  supplier varchar(200),
  wish_desc varchar(500),
  size varchar(200),
  url  varchar(500),
  unit varchar(20),
  procurement_cost DECIMAL(10,2) default 0,
  selling_price DECIMAL(10,2) default 0,
  xianen_price  DECIMAL(10,2),
  xianen_diff_price varchar(20),
  xianghe_total_price DECIMAL(10,2),
  xianghe_diff_price varchar(20),
  kaimo_fee DECIMAL(10,2),
  kaimo_time integer default 0,
  other_feeA DECIMAL(10,2),
  other_feeB DECIMAL(10,2),
  other_feeC DECIMAL(10,2),
  book_price1 DECIMAL(10,2),
  book_count1 integer default 0,
  book_price2 DECIMAL(10,2),
  book_count2 integer default 0,
  book_price3 DECIMAL(10,2),
  book_count3 integer default 0,
  purchase_time integer default 0,
  purchase_capacity integer default 0,
  return_count integer default 0,
  sales_volume integer default 0,
  sales_income DECIMAL(10,2),
  total_procurement_cost DECIMAL(10,2),
  commission_rate DECIMAL(4,2),
  commission DECIMAL(10,2),
  bad_debt DECIMAL(10,2),
  gross_profit  DECIMAL(10,2),
  holding_cost  DECIMAL(10,2),
  net_profit DECIMAL(10,2),
  profit_loss_rate DECIMAL(10,2),
  holding_count integer default 0,
  sales_channel integer default 0,
  img_url varchar(200),
  pd_source varchar(200),
  operator_id integer default 0,
  remark varchar(500),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  show_in_front integer default 0,
  filter1 varchar(10),
  filter2 varchar(10),
  filter3 varchar(10),
  tab varchar(12)
  PRIMARY KEY (wish_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


ALTER TABLE wishs CHANGE size size varchar(200);

alter table wishs add pd_source varchar(200);


update wishs set xianen_price=0.1 where wish_id=2558;
