DROP TABLE IF EXISTS questions;  
CREATE TABLE questions (  
  qusetion_id integer NOT NULL AUTO_INCREMENT,  
  option_id varchar(20) NOT NULL,
  sequence integer,
  question_content varchar(500) NOT NULL,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (qusetion_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

