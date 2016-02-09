DROP TABLE IF EXISTS t_service_category;  
CREATE TABLE t_service_category (  
  cate_id integer NOT NULL AUTO_INCREMENT,  
  title  varchar(200), 
  service_dis  varchar(200), 
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  PRIMARY KEY (cate_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

