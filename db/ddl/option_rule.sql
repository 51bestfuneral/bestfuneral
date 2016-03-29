DROP TABLE IF EXISTS option_rules;  
CREATE TABLE option_rules(  
  option_id  varchar(20) NOT NULL,  
  rule_id varchar(20) NOT NULL,  
  rule_type varchar(20),  
  rule varchar(20),  
  seq  integer,
  level integer,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (rule_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;