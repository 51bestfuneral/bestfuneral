DROP TABLE IF EXISTS t_cemetery_more_imgs;  
CREATE TABLE t_cemetery_more_imgs (  
  id integer NOT NULL AUTO_INCREMENT, 
  cemetery_id  integer, 
  description   varchar(200),
  img_url     varchar(200),
  status integer,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;



