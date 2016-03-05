DROP TABLE IF EXISTS t_wish_category_sub;  
CREATE TABLE t_wish_category_sub (  
  wish_id integer NOT NULL AUTO_INCREMENT,  
  cate_id integer,
  wish_name varchar(50) NOT NULL,  
  wish_desc varchar(500),
  price DECIMAL(10,2),
  display_price DECIMAL(10,2),
  valid_flag integer,
  url01 varchar(100),
  url02 varchar(100),
  url03 varchar(100),
  url04 varchar(100),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;



insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(13,6,'一般','一般','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(14,6,'高档','高档','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(15,6,'高档订制','高档订制','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(16,7,'普通灵车','普通灵车','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(17,7,'高档灵车','高档灵车','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(18,8,'纸花绢花为主','纸花绢花为主','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(19,8,'鲜花为主','鲜花为主','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(20,8,'精品鲜花','精品鲜花','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(21,9,'全套代订','全套代订','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(22,9,'场地代订','场地代订','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(23,10,'温馨祥和','温馨祥和','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(24,10,'爱的永恒','爱的永恒','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(25,11,'传统','传统','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(26,11,'个性化','个性化','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(27,12,'普通','普通','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(28,12,'专业团队全程服务','专业团队全程服务','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(29,13,'守灵','守灵','100','100',1);
insert into t_wish_category_sub(wish_id,cate_id,wish_name,wish_desc,price,display_price,valid_flag) values(30,13,'全程','全程','100','100',1);





update t_wish_category_sub set url01='/funeral/js/images/tick.jpg' where wish_id in (13,16,18,21,25,27);
update t_wish_category_sub set url02='/funeral/js/images/tick.jpg' where wish_id in (13,16,19,21,25,28);
update t_wish_category_sub set url03='/funeral/js/images/tick.jpg' where wish_id in (14,17,20,22,23,26,27,29);
update t_wish_category_sub set url04='/funeral/js/images/tick.jpg' where wish_id in (15,17,20,22,24,26,28,30);

update t_wish_category_sub set url03='' where wish_id in (15,17,20,22,24,26,28,30);


