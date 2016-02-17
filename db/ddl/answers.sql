DROP TABLE IF EXISTS answers;  
CREATE TABLE answers (  
  answer_id integer NOT NULL AUTO_INCREMENT,  
  user_id varchar(20) NOT NULL,  
  ans_list_id varchar(20),  
  question_id varchar(20),  
  answer_desc  varchar(200),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  option_id integer,
  PRIMARY KEY (answer_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;