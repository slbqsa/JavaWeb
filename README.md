```sql
create table user_info
(
	id int auto_increment,
	name varchar(64) not null,
	gender tinyint default 0 not null,
	age int not null,
	phonenum varchar(36) not null,
	means varchar(256) not null,
	id_three varchar(64) not null,
	constraint user_info_pk
		primary key (id)
);
```
#### 字段含义：
用户id,用户姓名,性别,年龄,手机号码,注册方式,第三方登录账号

###　账户密码单独建表
```sql
create table passwd
(
	id int auto_increment,
	encrpt_passwd varchar(128) not null,
	user_id int not null,
	constraint passwd_pk
		primary key (id)
);
```
#### 又手动设置了一次编码方式
```sql
alter table user_info change name name varchar(64) character set utf8;
```
### 基本流程
dataobject层：数据的存储和到service层的传输
### 用户信息管理
使用otp短信获取注册和登录
```sql
create table item_stock
(
  id int auto_increment,
  stock int default 0 not null,
  item_id int default 0 null,
  constraint item_stock_pk
    primary key (id)
);
```
```sql

create table item
(
	id int not null,
	title varchar(64) default '' not null,
	price decimal default 0 not null,
	description varchar(500) default '' not null,
	sales int not null,
	img_url varchar(0) default '' not null,
	constraint item_pk
		primary key (id)
);
```