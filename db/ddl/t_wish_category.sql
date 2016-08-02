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


