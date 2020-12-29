--create table user (id integer not null, dob timestamp, name varchar(255), primary key (id))

insert into user values(1001,sysdate(),'bala');
insert into user values(1002,sysdate(),'krishna');
insert into user values(1003,sysdate(),'krishna');

insert into posts values(10011,'posted by user 1001',1001);
insert into posts values(10012,'posted by user 1001',1001);
