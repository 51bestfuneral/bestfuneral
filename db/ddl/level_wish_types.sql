DROP TABLE IF EXISTS level_wish_types;  
CREATE TABLE level_wish_types (  
  wish_type varchar(20) NOT NULL,  
  wish_type_level integer NOT NULL,    
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (wish_type, wish_type_level)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


insert into level_wish_types('wish_type','wish_type_level') values('电话，网站','1' );
insert into level_wish_types('wish_type','wish_type_level') values('专人咨询','1' );
