DROP TABLE IF EXISTS cemeterys;  
CREATE TABLE cemeterys (  
  cemetery_id integer NOT NULL AUTO_INCREMENT,  
  cemetery_name varchar(20) NOT NULL,  
  cemetery_desc varchar(1000),  
  price DECIMAL(10,2),
  original_price DECIMAL(10,2),
  address  varchar(50),
  district  varchar(1000),
  feature  varchar(1000),
  url  varchar(100),
  type integer ,
  map_url  varchar(100),
  traffic_Info  varchar(1000),
  desc_img_url VARCHAR(200) DEFAULT NULL,
  feature_img_url VARCHAR(200) DEFAULT NULL,
  location_img_url VARCHAR(200) DEFAULT NULL,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  filter1 varchar(20),
  filter2 varchar(20),
  filter3 varchar(20),
  PRIMARY KEY (cemetery_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
