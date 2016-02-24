DROP TABLE IF EXISTS wishs;  
CREATE TABLE wishs (  
  wish_id integer NOT NULL AUTO_INCREMENT,  
  wish_name varchar(20) NOT NULL,
  wish_type varchar(20),
  price varchar(20),
  cost_price DECIMAL(10,2),
  selling_price DECIMAL(10,2),
status_id integer default 0,
  general_code varchar(20),
  general_cate varchar(20),
  sub_cate_code varchar(40),
  supplier varchar(200),
  img_url varchar(200),
  unit varchar(20),
  model varchar(40),
  category varchar(200),
  material varchar(500),
  count  varchar(20),
  wish_desc varchar(500),
  url       varchar(100),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  feature integer default 0,
  PRIMARY KEY (wish_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


