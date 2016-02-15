DROP TABLE IF EXISTS t_order_detail;  
CREATE TABLE t_order_detail (  
  order_detail_id integer NOT NULL AUTO_INCREMENT,  
  order_id integer NOT NULL,  
  wish_id integer NOT NULL, 
  price DECIMAL(10,2) default 1,
  original_price DECIMAL(10,2) default 1,
  count integer not null,
  created_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  PRIMARY KEY (order_detail_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
