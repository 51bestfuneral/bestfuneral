DROP TABLE IF EXISTS t_contact_info;  
CREATE TABLE t_contact_info (  
  contact_id integer NOT NULL AUTO_INCREMENT,  
  user_id integer NOT NULL ,
  contact_name varchar(200) NOT NULL,  
  gender integer ,  
  status_id integer NOT NULL,
  province varchar(200),  
  city varchar(200),  
  detail_address  varchar(500),
  phone  varchar(200),
  backup_name  varchar(200),
  backup_phone  varchar(200),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (contact_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

