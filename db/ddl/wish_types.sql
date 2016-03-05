DROP TABLE IF EXISTS wish_types;  
CREATE TABLE wish_types (  
  wish_type varchar(20) NOT NULL,  
  wish_type_desc varchar(200) NOT NULL,  
  necessary integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_type)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


insert into wish_types(wish_type,wish_type_desc,necessary) values('礼仪','礼仪','2');
insert into wish_types(wish_type,wish_type_desc,necessary) values('专人服务指导','专人服务指导','2');
insert into wish_types(wish_type,wish_type_desc,necessary) values('骨灰盒','骨灰盒','1');
