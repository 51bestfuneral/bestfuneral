DROP TABLE IF EXISTS wishlist_details;  
CREATE TABLE wishlist_details (  
  wishlist_detail_id integer NOT NULL AUTO_INCREMENT,  
  wishlist_id integer NOT NULL,  
  wish_type varchar(20),  
  wish_id integer,  
  source_id integer,  
  count  varchar(20),
  price DECIMAL(20,2) DEFAULT 0,
  recommend  integer,
  selected integer DEFAULT 0,
  original_price DECIMAL(20,2) DEFAULT 0,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY ( wishlist_detail_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

alter table wishlist_details add COLUMN selected integer DEFAULT 0; 
alter table wishlist_details add COLUMN selected_price integer DEFAULT 0; 
alter table wishlist_details change sourceId recommend integer;