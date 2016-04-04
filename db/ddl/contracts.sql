DROP TABLE IF EXISTS contracts;  
CREATE TABLE contracts (  
  contract_id integer NOT NULL AUTO_INCREMENT,  
  funeral_id varchar(20) NOT NULL,  
  file varchar(20),  
  user_id varchar(20),  
  status  varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (contract_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;