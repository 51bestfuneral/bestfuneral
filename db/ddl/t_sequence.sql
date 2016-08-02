DROP TABLE IF EXISTS t_sequence;  
CREATE TABLE t_sequence (
  _id integer NOT NULL AUTO_INCREMENT,
   step integer NOT NULL,
  created_date  datetime default NOW(),
  PRIMARY KEY (_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
