DROP TABLE IF EXISTS T_USER;  
CREATE TABLE T_USER (  
  user_id integer NOT NULL AUTO_INCREMENT,  
  user_name varchar(200) NOT NULL,  
  email varchar(200) NOT NULL,  
  phone varchar(200) NOT NULL, 
  user_type integer NOT NULL ,  
  pass_word varchar(200) NOT NULL,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (user_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;