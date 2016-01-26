DROP TABLE IF EXISTS t_service_category_class;  
CREATE TABLE t_service_category_class ( 
  _id integer NOT NULL AUTO_INCREMENT,  
  cate_id integer ,
  class_id integer ,
  class_dis  varchar(200), 
  high_price DECIMAL(10,2),
  price DECIMAL(10,2),
  low_price DECIMAL(10,2),
  currency_id integer ,
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;



insert into t_service_category_class values('1','1','0','不需要','0.00','0.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('2','1','1','需要基本净身穿衣','0.00','100.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('3','1','2','需要入殓师服务','0.00','200.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('4','1','3','需要高级入殓师服务','0.00','1000.00','0.00','1',NOW(),NOW());

insert into t_service_category_class values('5','2','0','不需要','0.00','0.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('6','2','1','簡單鮮花','0.00','100.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('7','2','2','中檔鮮花','0.00','200.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('8','2','3','高級鮮花','0.00','1000.00','0.00','1',NOW(),NOW());

insert into t_service_category_class values('9','3','0','不需要','0.00','0.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('10','3','1','簡單音響播放','0.00','100.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('11','3','2','中檔樂隊','0.00','200.00','0.00','1',NOW(),NOW());
insert into t_service_category_class values('12','3','3','高檔樂隊演奏','0.00','1000.00','0.00','1',NOW(),NOW());