DROP TABLE IF EXISTS wishlist_details;  
CREATE TABLE wishlist_details (  
  wishlist_id integer NOT NULL,  
  wishlist_detail_id integer NOT NULL AUTO_INCREMENT,  
  wish_type varchar(20),  
  wish_id integer,  
  source_id integer,  
  count  varchar(20),
  price DECIMAL(20,2),
  sourceId integer,
  original_price DECIMAL(20,2),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wishlist_id, wishlist_detail_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;