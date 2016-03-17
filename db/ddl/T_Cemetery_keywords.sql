DROP TABLE IF EXISTS T_Cemetery_keywords;  
CREATE TABLE T_Cemetery_keywords (  
  _id integer NOT NULL AUTO_INCREMENT, 
  cemetery_id  integer, 
  keyword   varchar(50),
  img_url     varchar(200),
  description   varchar(1000), 
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


