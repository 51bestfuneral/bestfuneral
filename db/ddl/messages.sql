DROP TABLE IF EXISTS messages;  
CREATE TABLE messages (  
  message_id varchar(20) NOT NULL,  
  user_id varchar(20) NOT NULL,  
  content varchar(200),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (message_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;