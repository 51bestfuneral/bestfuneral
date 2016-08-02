DROP TABLE IF EXISTS t_order;  
CREATE TABLE t_order (  
  order_id integer NOT NULL AUTO_INCREMENT,  
  order_no varchar(80) NOT NULL,  
  subject varchar(500) NOT NULL, 
  user_id varchar(20) NOT NULL,  
  payable_amount DECIMAL(10,2),
  currency_id integer default 1,
  currency_rate DECIMAL(10,2) default 1,
  status_id integer not null,
  description varchar(500),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (order_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;