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