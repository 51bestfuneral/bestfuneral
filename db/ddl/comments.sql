DROP TABLE IF EXISTS comments;  
CREATE TABLE comments (  
  comment_id integer NOT NULL AUTO_INCREMENT,  
  wish_id varchar(20) NOT NULL,  
  user_id varchar(20),
  type varchar(20),
  comment  varchar(200),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (comment_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;