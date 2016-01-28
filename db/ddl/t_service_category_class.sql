DROP TABLE IF EXISTS t_service_category_class;  
CREATE TABLE t_service_category_class ( 
  _id integer NOT NULL AUTO_INCREMENT,  
  cate_id integer ,
  class_id integer ,
  class_dis  varchar(200), 
  high_price DECIMAL(10,2),
  price DECIMAL(10,2),
  low_price DECIMAL(10,2),
  currency_id integer ,
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


