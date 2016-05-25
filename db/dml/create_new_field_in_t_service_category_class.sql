
alter table t_service_category_class add COLUMN img_url_default VARCHAR(200) DEFAULT NULL; 
alter table t_service_category_class add COLUMN img_url_selected VARCHAR(200) DEFAULT NULL; 
alter table t_contact_info add COLUMN express_method integer DEFAULT 1; 
alter table t_wish_order add COLUMN source_id integer not null;
alter table t_express_info add COLUMN wish_order_id integer NOT NULL;
alter table t_order_detail add COLUMN wish_name VARCHAR(500) ;
alter table t_order add COLUMN wish_order_id integer not null;




