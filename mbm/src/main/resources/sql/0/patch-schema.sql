drop table cart_items if exists;
drop table carts if exists;
drop table contact_method_details if exists;
drop table contact_method_secondary_details if exists;
drop table customers if exists;
drop table customers_contact_method_details if exists;
drop table item_field_details if exists;
drop table item_field_secondary_details if exists;
drop table items if exists;
drop table items_item_field_details if exists;
create table cart_items (quantity integer not null, item_id bigint, cart_id bigint, primary key (cart_id, item_id));
create table carts (id bigint generated by default as identity unique, primary key (id));
create table contact_method_details (id bigint generated by default as identity unique, primary_detail varchar(255), primary key (id));
create table contact_method_secondary_details (contact_method_id bigint not null, secondary_detail varchar(255));
create table customers (id bigint generated by default as identity unique, open_id varchar(255), uuid varchar(255) not null, cart_fk bigint, primary key (id));
create table customers_contact_method_details (customers_id bigint not null, contactMethodMap_id bigint not null, contactMethodMap_KEY binary(255), primary key (customers_id, contactMethodMap_KEY), unique (contactMethodMap_id));
create table item_field_details (id bigint generated by default as identity unique, content varchar(255) not null, locale_key varchar(255) not null, primary key (id));
create table item_field_secondary_details (item_field_id bigint not null, content varchar(255) not null, locale_key varchar(255) not null, primary key (item_field_id, content, locale_key));
create table items (id bigint generated by default as identity unique, gtin varchar(255), sku varchar(255) not null, primary key (id));
create table items_item_field_details (items_id bigint not null, itemFieldMap_id bigint not null, itemFieldMap_KEY binary(255), primary key (items_id, itemFieldMap_KEY), unique (itemFieldMap_id));
alter table cart_items add constraint FK8907EDE14A4AD4B1 foreign key (cart_id) references carts;
alter table cart_items add constraint FK8907EDE1D2AE3E84 foreign key (item_id) references items;
alter table contact_method_secondary_details add constraint FK1734DD87B031493 foreign key (contact_method_id) references contact_method_details;
alter table customers add constraint FK600E7C554A4AD45B foreign key (cart_fk) references carts;
alter table customers_contact_method_details add constraint FKD51AD20DDC637F9C foreign key (customers_id) references customers;
alter table customers_contact_method_details add constraint FKD51AD20DC2930BD8 foreign key (contactMethodMap_id) references contact_method_details;
alter table item_field_secondary_details add constraint FK94F58D467C282F1A foreign key (item_field_id) references item_field_details;
alter table items_item_field_details add constraint FKDB6E56B070F12453 foreign key (itemFieldMap_id) references item_field_details;
alter table items_item_field_details add constraint FKDB6E56B09AC8F6B7 foreign key (items_id) references items;