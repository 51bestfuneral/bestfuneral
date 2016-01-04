DROP TABLE IF EXISTS t_f_question;  
CREATE TABLE t_f_question (  
  qusetion_id integer NOT NULL AUTO_INCREMENT,  
  title varchar(500) NOT NULL,
  question_content varchar(500) NOT NULL,
  priority_id integer,
  module_id integer,
  Age_range  integer,
  gender_range integer,
  answer_cate integer,
  status_id integer,
  mandatory_flag integer,
  display_form integer,
  display_order integer,
  options_size integer,
  option_form integer,
  creator_id integer,
  updator_id integer,
  operator_id integer,
  create_date  datetime default NOW(),
  updated_date  datetime default  NOW(),

  PRIMARY KEY (qusetion_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

