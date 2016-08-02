DROP TABLE IF EXISTS T_Cemetery_Epigraph_Style;  
CREATE TABLE T_Cemetery_Epigraph_Style (  
  _id integer NOT NULL AUTO_INCREMENT, 
  cemetery_id  integer, 
  style  varchar(50),
  description   varchar(1000), 
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;




