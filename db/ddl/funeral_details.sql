DROP TABLE IF EXISTS funeral_details;  
CREATE TABLE funeral_details (  
  funeral_id integer NOT NULL AUTO_INCREMENT,  
  element varchar(20) NOT NULL,  
  value varchar(20),  
  seq varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (funeral_id, element)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;