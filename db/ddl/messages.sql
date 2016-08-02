DROP TABLE IF EXISTS messages;  
CREATE TABLE messages (  
  message_id integer NOT NULL AUTO_INCREMENT,  
  wish_id varchar(20) NOT NULL,  
  content varchar(10000),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (message_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;