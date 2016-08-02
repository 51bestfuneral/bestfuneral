DROP TABLE IF EXISTS t_cart_detail;  
CREATE TABLE t_cart_detail (
  cart_detail_id integer NOT NULL AUTO_INCREMENT,
  cart_id integer NOT NULL,
  price DECIMAL(10,2) default 1,
  original_price DECIMAL(10,2) default 1,
  selected_price DECIMAL(10,2) DEFAULT 0,
  count integer not null,
  created_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  source_id    integer,
  wish_id    integer,
  selected    integer default 0,
  wish_type varchar(100),
  PRIMARY KEY (cart_detail_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;



