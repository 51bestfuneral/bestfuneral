DROP TABLE IF EXISTS wishlists;  
CREATE TABLE wishlists (  
  wishlist_id integer NOT NULL AUTO_INCREMENT,
  user_id integer NOT NULL,
  price varchar(20) NOT NULL,
  original_price DECIMAL(10,2),
  ans_list_id varchar(20),  
  comment varchar(20),  
  status  varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wishlist_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;