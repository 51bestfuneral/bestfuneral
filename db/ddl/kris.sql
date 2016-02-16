DROP TABLE IF EXISTS answers;  
CREATE TABLE answers (  
  answer_id integer NOT NULL AUTO_INCREMENT,  
  user_id varchar(20) NOT NULL,  
  ans_list_id varchar(20),  
  question_id varchar(20),  
  answer_desc  varchar(200),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (answer_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS cemeterys;  
CREATE TABLE cemeterys (  
  cemetery_id integer NOT NULL AUTO_INCREMENT,  
  cemetery_name varchar(20) NOT NULL,  
  cemetery_desc varchar(20),  
  price integer,  
  address  varchar(20),
  url  varchar(100),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (cemetery_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS contracts;  
CREATE TABLE contracts (  
  contract_id integer NOT NULL AUTO_INCREMENT,  
  funeral_id varchar(20) NOT NULL,  
  file varchar(20),  
  user_id varchar(20),  
  status  varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (contract_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS funeral_details;  
CREATE TABLE funeral_details (  
  funeral_id integer NOT NULL AUTO_INCREMENT,  
  element varchar(20) NOT NULL,  
  value varchar(20),  
  seq varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (funeral_id, element)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS funeral_templates;  
CREATE TABLE funeral_templates (  
  template_id integer NOT NULL,  
  element varchar(20) NOT NULL,  
  wish_map varchar(20),  
  defaultvalue varchar(20), 
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (template_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS funerals;  
CREATE TABLE funerals (  
  funeral_id integer NOT NULL,  
  user_id varchar(20) NOT NULL,  
  total_price varchar(20),
  wishlist_id varchar(20),
  createdate  datetime default NOW(),
  updateddate  datetime default NOW(),
  PRIMARY KEY (funeral_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS level_wish_types;  
CREATE TABLE level_wish_types (  
  wish_type varchar(20) NOT NULL,  
  wish_type_level integer NOT NULL,    
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_type, wish_type_level)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS messages;  
CREATE TABLE messages (  
  message_id varchar(20) NOT NULL,  
  user_id varchar(20) NOT NULL,  
  content varchar(200),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (message_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS option_rules;  
CREATE TABLE option_rules(  
  option_id  varchar(20) NOT NULL,  
  rule_id varchar(20) NOT NULL,  
  rule_type varchar(20),  
  rule varchar(20),  
  seq  integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (rule_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS options;  
CREATE TABLE options (  
  option_id integer NOT NULL AUTO_INCREMENT,  
  Question_id varchar(20) NOT NULL,  
  Option_desc varchar(200),  
  sequence integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (option_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS t_f_question_age_range;  
CREATE TABLE t_f_question_age_range (  
  range_id integer NOT NULL AUTO_INCREMENT,
  Age_range  varchar(100),
  discription varchar(200),
  create_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  PRIMARY KEY (range_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_service_category;  
CREATE TABLE t_service_category (  
  cate_id integer NOT NULL AUTO_INCREMENT,  
  title  varchar(200), 
  service_dis  varchar(200), 
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  PRIMARY KEY (cate_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t_service_category_class;  
CREATE TABLE t_service_category_class ( 
  _id integer NOT NULL AUTO_INCREMENT,  
  cate_id integer ,
  class_id integer ,
  class_dis  varchar(200), 
  high_price DECIMAL(10,2),
  price DECIMAL(10,2),
  low_price DECIMAL(10,2),
  currency_id integer ,
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS users;  
CREATE TABLE users (  
  user_id integer NOT NULL AUTO_INCREMENT,  
  user_name varchar(20) NOT NULL,  
  user_type varchar(20),  
  user_ref varchar(20),  
  e_mail  varchar(200),  
  address  varchar(200),
  pwd varchar(40),
  phone  varchar(20),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  contactor  varchar(20),
  contactor_mail  varchar(200),
  contactor_phone  varchar(20),
  PRIMARY KEY (user_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS wish_types;  
CREATE TABLE wish_types (  
  wish_type varchar(20) NOT NULL,  
  wish_type_desc varchar(200) NOT NULL,  
  necessary integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_type)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS wishlists;  
CREATE TABLE wishlists (  
  wishlist_id integer NOT NULL AUTO_INCREMENT,
  user_id integer NOT NULL,
  price varchar(20) NOT NULL,  
  ans_list_id varchar(20),  
  comment varchar(20),  
  status  varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wishlist_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS wishlist_details;  
CREATE TABLE wishlist_details (  
  wishlist_id integer NOT NULL,  
  wishlist_detail_id integer NOT NULL AUTO_INCREMENT,  
  wish_type varchar(20),  
  wish_id varchar(20),  
  count  varchar(20),
  price integer,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wishlist_id, wishlist_detail_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS wishs;  
CREATE TABLE wishs (  
  wish_id integer NOT NULL AUTO_INCREMENT,  
  wish_name varchar(20) NOT NULL,  
  wish_type varchar(20),  
  price varchar(20),  
  count  varchar(20),
  wish_desc varchar(500),
  url       varchar(100),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;