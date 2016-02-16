DROP TABLE IF EXISTS users;  
CREATE TABLE users (  
  user_id integer NOT NULL AUTO_INCREMENT,  
  user_name varchar(20) NOT NULL,  
  user_type varchar(20),  
  user_ref varchar(20),  
  e_mail  varchar(200),  
  address  varchar(200),
  pwd varchar(40),
  phone  varchar(20),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  contactor  varchar(20),
  contactor_mail  varchar(200),
  contactor_phone  varchar(20),
  PRIMARY KEY (user_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

