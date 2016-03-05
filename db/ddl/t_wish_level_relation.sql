DROP TABLE IF EXISTS t_wish_level_relation;  
CREATE TABLE t_wish_level_relation ( 
  relation_id integer NOT NULL AUTO_INCREMENT,  
  level_id integer NOT NULL ,  
  wish_id integer NOT NULL ,  
  valid_flag integer,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (relation_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;