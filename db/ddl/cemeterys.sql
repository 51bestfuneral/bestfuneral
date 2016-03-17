DROP TABLE IF EXISTS cemeterys;  
CREATE TABLE cemeterys (  
  cemetery_id integer NOT NULL AUTO_INCREMENT,  
  cemetery_name varchar(20) NOT NULL,  
  cemetery_desc varchar(1000),  
  price integer,  
  address  varchar(50),
  district  varchar(1000),
  feature  varchar(1000),
  url  varchar(100),
  map_url  varchar(100),
  traffic_Info  varchar(1000),
  desc_img_url VARCHAR(200) DEFAULT NULL,
  feature_img_url VARCHAR(200) DEFAULT NULL,
  location_img_url VARCHAR(200) DEFAULT NULL,
  createdate  datetime default NOW(),
  updateddate  datetime default  NOW(),
  PRIMARY KEY (cemetery_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;





insert into cemeterys (cemetery_id,cemetery_name,cemetery_desc,price,address,district,feature,map_url,traffic_Info)values(2,'汇龙园
','上海汇龙园陵园有限公司是2006年上海市民政局根据城市建设发展、人口动迁布局的新特点，在上海东部规划建设800余亩的一座大型景观陵园。它位于航空港和深水港之间，毗邻东海边独具荷兰田园风光的上海鲜花港，是难得的福祉。 陵园用 "太阳"、"地球"等天文名称定义，营造一种"天人合一"的意境；直径约100米的圆形太阳广场格外醒目。在这里，我们一起迎接初升的太阳，一起相伴看日落的夕阳，那缕缕金色的光芒让人倍感生生不息的希望。
',10000,'上海市浦东新区朝阳农场军港公路388号
','浦东新区
','以天文“星座”命名，体现了汇龙园中西合璧、超越时空的文化内涵。以星空图布局的汇龙园陵园以"圆"为支点，处处体现着人文关怀的理念。
','/funeral/js/images/cemeteryMap.png','" 
从人民广场出发：公交车，地铁2号线转16号线，换乘三港专线再转乘老港1路即可，全程约3小时；自驾，沿南北高架路，进入济阳路沿外环高速公路到沪芦高速公路，进入申嘉高速公路到终点"
');




