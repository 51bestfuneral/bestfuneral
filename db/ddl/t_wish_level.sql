DROP TABLE IF EXISTS t_wish_level;  
CREATE TABLE t_wish_level (  
  level_id integer NOT NULL AUTO_INCREMENT,  
  level_name varchar(50) NOT NULL,  
  level_desc varchar(500),
  price DECIMAL(10,2),
  display_price varchar(50),
  valid_flag integer,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (level_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;