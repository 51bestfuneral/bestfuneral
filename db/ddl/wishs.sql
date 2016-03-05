DROP TABLE IF EXISTS wishs;  
CREATE TABLE wishs (  
  wish_id integer NOT NULL AUTO_INCREMENT,  
  wish_name varchar(20) NOT NULL,  
  wish_type varchar(20),
  price varchar(20),  
  count  varchar(20),
  wish_desc varchar(500),
  url       varchar(100),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
   wish_cata varchar(20),
  feature integer default 0
  PRIMARY KEY (wish_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


insert into wishs (wish_id,wish_name,wish_type,price,count,wish_desc,wish_cata,feature) values(8,'寿衣','clothe','100',1,'shouyi','product',1);