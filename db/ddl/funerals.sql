DROP TABLE IF EXISTS funerals;  
CREATE TABLE funerals (  
  funeral_id integer NOT NULL,  
  user_id varchar(20) NOT NULL,  
  total_price varchar(20),
  wishlist_id varchar(20),
  createdate  datetime default NOW(),
  updateddate  datetime default NOW(),
  PRIMARY KEY (funeral_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;