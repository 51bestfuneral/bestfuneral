DROP TABLE IF EXISTS t_reservation;  
CREATE TABLE t_reservation (  
  reserv_id integer NOT NULL AUTO_INCREMENT,  
  user_id integer NOT NULL,
  phone_number varchar(20),
  cemetery_id integer,
  reserv_dte varchar(20),
  created_date  datetime default NOW(),
  updated_date  datetime default  NOW(),
  PRIMARY KEY (reserv_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;