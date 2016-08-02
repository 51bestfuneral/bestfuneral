DROP TABLE IF EXISTS T_Cemetery_Epigraph_Zone;  
CREATE TABLE T_Cemetery_Epigraph_Zone (  
  _id integer NOT NULL AUTO_INCREMENT, 
  cemetery_id  integer, 
  zone  varchar(100), 
  description   varchar(1000), 
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;


insert into T_Cemetery_Epigraph_Zone (_id,cemetery_id,zone,description)values(4,2,'圣灵一区','圣灵一区');
insert into T_Cemetery_Epigraph_Zone (_id,cemetery_id,zone,description)values(5,2,'海王星二区','海王星二区');
insert into T_Cemetery_Epigraph_Zone (_id,cemetery_id,zone,description)values(6,2,'海王星十六区','海王星十六区');
insert into T_Cemetery_Epigraph_Zone (_id,cemetery_id,zone,description)values(7,2,'海王星十九区','海王星十九区');
insert into T_Cemetery_Epigraph_Zone (_id,cemetery_id,zone,description)values(8,2,'海王星二十区','海王星二十区');