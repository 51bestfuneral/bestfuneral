DROP TABLE IF EXISTS t_express_info;  
CREATE TABLE t_express_info (  
  express_id integer NOT NULL AUTO_INCREMENT,  
  user_id integer NOT NULL ,
  receiver_name varchar(200) NOT NULL,  
  status_id integer NOT NULL,
  province varchar(200),  
  city varchar(200),  
  detail_address  varchar(500),
  phone  varchar(200),
  delivery_method integer NOT NULL,
  express_fee DECIMAL(10,2),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (express_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

