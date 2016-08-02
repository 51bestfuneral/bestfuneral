DROP TABLE IF EXISTS t_cart;  
CREATE TABLE t_cart (
  cart_id integer NOT NULL AUTO_INCREMENT,
  user_id integer NOT NULL,
  price DECIMAL(10,2) default 1,
  original_price DECIMAL(10,2) default 1,
  created_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  PRIMARY KEY (cart_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
