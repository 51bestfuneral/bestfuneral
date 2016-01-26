DROP TABLE IF EXISTS options;  
CREATE TABLE options (  
  option_id integer NOT NULL AUTO_INCREMENT,  
  Question_id varchar(20) NOT NULL,  
  Option_desc varchar(200),  
  sequence integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (option_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;