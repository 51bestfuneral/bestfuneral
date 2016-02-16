DROP TABLE IF EXISTS cemeterys;  
CREATE TABLE cemeterys (  
  cemetery_id integer NOT NULL AUTO_INCREMENT,  
  cemetery_name varchar(20) NOT NULL,  
  cemetery_desc varchar(20),  
  price integer,  
  address  varchar(20),
  url  varchar(100),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (cemetery_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;