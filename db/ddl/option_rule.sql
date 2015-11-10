DROP TABLE IF EXISTS option_rule;  
CREATE TABLE option_rule(  
  option_id  varchar(20) NOT NULL,  
  rule_id varchar(20) NOT NULL,  
  rule_type varchar(20),  
  rule varchar(20),  
  seq  integer,  
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (option_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;