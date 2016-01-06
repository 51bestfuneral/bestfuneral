DROP TABLE IF EXISTS t_f_question_age_range;  
CREATE TABLE t_f_question_age_range (  
  range_id integer NOT NULL AUTO_INCREMENT,
  Age_range  varchar(100),
  discription varchar(200),
  create_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  PRIMARY KEY (range_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

