DROP TABLE IF EXISTS t_wish_order;  
CREATE TABLE t_wish_order (  
  wish_order_id integer NOT NULL AUTO_INCREMENT,  
  user_id integer NOT NULL,
  price DECIMAL(10,2) default 1,
  original_price DECIMAL(10,2) default 1,
  created_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  PRIMARY KEY (wish_order_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;