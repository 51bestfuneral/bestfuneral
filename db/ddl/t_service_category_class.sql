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
  img_url_default VARCHAR(200) DEFAULT NULL,
  img_url_selected VARCHAR(200) DEFAULT NULL,
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


select _id , cate_id, class_id, img_url_default from  t_service_category_class;

update t_service_category_class set img_url_selected='/pages/html/customizatonPages/assets/images/costCalcu/basic_.png' where _id=11; 