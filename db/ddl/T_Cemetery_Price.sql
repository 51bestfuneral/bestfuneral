DROP TABLE IF EXISTS T_Cemetery_Price;  
CREATE TABLE T_Cemetery_Price (  
  _id integer NOT NULL AUTO_INCREMENT, 
  cemetery_id  integer, 
  graveStyle_id  integer, 
  epigraphStyle_id  integer, 
  price DECIMAL(10,2),
  description   varchar(1000), 
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;




