
alter table cemeterys add COLUMN head_img VARCHAR(200) DEFAULT NULL; 
alter table cemeterys add COLUMN original_price DECIMAL(10,2) default 0.00;
alter table wishlists add COLUMN original_price DECIMAL(10,2) default 0.00;
alter table wishlist_details add COLUMN original_price DECIMAL(10,2) default 0.00;
alter table wishlists add COLUMN level Integer default 0;
alter table wish_types add COLUMN level Integer default 0;
alter table cemeterys add COLUMN type integer default 1;





