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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS cemeterys;  
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS comments;  
CREATE TABLE comments (  
  comment_id integer NOT NULL AUTO_INCREMENT,  
  wish_id varchar(20) NOT NULL,  
  user_id varchar(20),  
  score varchar(20),  
  comment  varchar(200),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (comment_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS contracts;  
CREATE TABLE contracts (  
  contract_id integer NOT NULL AUTO_INCREMENT,  
  funeral_id varchar(20) NOT NULL,  
  file varchar(20),  
  user_id varchar(20),  
  status  varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (contract_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS funerals;  
CREATE TABLE funerals (  
  funeral_id integer NOT NULL,  
  user_id varchar(20) NOT NULL,  
  total_price varchar(20),
  wishlist_id varchar(20),
  createdate  datetime default NOW(),
  updateddate  datetime default NOW(),
  PRIMARY KEY (funeral_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS funeral_details;  
CREATE TABLE funeral_details (  
  funeral_id integer NOT NULL AUTO_INCREMENT,  
  element varchar(20) NOT NULL,  
  value varchar(20),  
  seq varchar(20),  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (funeral_id, element)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS funeral_templates;  
CREATE TABLE funeral_templates (  
  template_id integer NOT NULL,  
  element varchar(20) NOT NULL,  
  wish_map varchar(20),  
  defaultvalue varchar(20), 
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (template_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS options;  
CREATE TABLE options (  
  option_id integer NOT NULL AUTO_INCREMENT,  
  Question_id varchar(20) NOT NULL,  
  Option_desc varchar(200),  
  sequence integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (option_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS option_rule;  
CREATE TABLE option_rule(  
  option_id  varchar(20) NOT NULL,  
  rule_id varchar(20) NOT NULL,  
  rule_type varchar(20),  
  rule varchar(20),  
  seq  integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (option_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS questions;  
CREATE TABLE questions (  
  qusetion_id integer NOT NULL AUTO_INCREMENT,  
  option_id varchar(20) NOT NULL,  
  seq integer,    
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (qusetion_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS users;  
CREATE TABLE users (  
  user_id integer NOT NULL AUTO_INCREMENT,  
  user_name varchar(20) NOT NULL,  
  user_type varchar(20),  
  user_ref varchar(20),  
  e_mail  varchar(20),  
  address  varchar(20),
  pwd varchar(40),
  phone  varchar(20),
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  contactor  varchar(20),
  contactor_mail  varchar(20),
  contactor_phone  varchar(20),
  PRIMARY KEY (user_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS wishlists;  
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS wishlist_details;  
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS wishs;  
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;DROP TABLE IF EXISTS wish_types;  
CREATE TABLE wish_types (  
  wish_type varchar(20) NOT NULL,  
  wish_type_desc varchar(200) NOT NULL,  
  necessary integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_type)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;