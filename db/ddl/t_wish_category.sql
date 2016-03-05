DROP TABLE IF EXISTS t_wish_category;  
CREATE TABLE t_wish_category (  
  cate_id integer NOT NULL AUTO_INCREMENT,  
  cate_name varchar(50) NOT NULL,  
  cate_desc varchar(500),
  valid_flag integer,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (cate_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(1,'流程咨询','流程咨询',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(2,'专人服务指导','专人服务指导',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(3,'骨灰盒','骨灰盒',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(4,'寿衣','寿衣',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(5,'净身穿衣','净身穿衣',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(6,'搭建灵堂','搭建灵堂',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(7,'遗体骨灰接运','遗体骨灰接运',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(8,'花圈，花篮','花圈，花篮',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(9,'追悼会','追悼会',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(10,'告别式订制','告别式订制',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(11,'落葬方式','落葬方式',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(12,'落葬仪式','落葬仪式',1);
insert into t_wish_category(cate_id,cate_name,cate_desc,valid_flag) values(13,'法事超度','法事超度',1);