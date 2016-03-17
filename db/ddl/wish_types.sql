DROP TABLE IF EXISTS wish_types;  
CREATE TABLE wish_types (  
  wish_type varchar(20) NOT NULL,  
  wish_type_desc varchar(200) NOT NULL,  
  necessary integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_type)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


