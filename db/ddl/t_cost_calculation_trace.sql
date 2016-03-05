DROP TABLE IF EXISTS t_cost_calculation_trace;  
CREATE TABLE t_cost_calculation_trace (  
  id integer NOT NULL AUTO_INCREMENT, 
  cate_id  integer, 
  class_id  integer, 
  user_id   integer, 
  insert_date  datetime default NOW(),
  update_date  datetime default  NOW(),
  PRIMARY KEY (id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;

