DROP TABLE IF EXISTS t_user;  
CREATE TABLE t_user (  
  user_id integer NOT NULL AUTO_INCREMENT,  
  user_name varchar(200) ,  
  user_ref varchar(200) ,  
  e_mail varchar(200) ,  
  phone varchar(200) , 
  user_type integer NOT NULL ,  
  status integer NOT NULL , 
  last_loginIp varchar(200),
  last_loginTime datetime default NOW(),
  invalid_loginTimes integer default 0,
  pwd varchar(200) NOT NULL,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (user_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;