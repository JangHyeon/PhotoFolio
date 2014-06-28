CREATE TABLE member (
id varchar(15) NOT NULL,
pwd varchar(50) NOT NULL,
lvl int(1) unsigned DEFAULT '0',
nickname varchar(8) NOT NULL UNIQUE,
email varchar(50) NOT NULL,
phone int(12) NOT NULL,
address varchar(100),
profileimg char(40),
memo varchar(1000),
joindate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id),
UNIQUE (nickname)
) DEFAULT CHARSET=utf8;

alter table member modify phone varchar(12);
commit

desc member


drop table member;

select * from member;

insert into member(id,pwd,nickname,email,phone) values('admin','admin','운영자','seonghyeonzzing@naver.com','112119114');
insert into member(id,pwd,nickname,email,phone) values('user','1004','사용자','이메일123@naver.com','15885588');
insert into member(id,pwd,lvl,nickname,email,phone) values('creator','1004','1','크리에이터','이메일123@naver.com','15888292');

update member set lvl='99' where id='admin';


CREATE TABLE article (
idx int unsigned AUTO_INCREMENT NOT NULL,
id varchar(20) NOT NULL,
subject varchar(50) NOT NULL,
content varchar(5000) NOT NULL,
writedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
acount int unsigned DEFAULT '0',
alike int unsigned DEFAULT '0' ,
report int unsigned DEFAULT '0' ,
tag varchar(200),
secret INT unsigned DEFAULT '0',
PRIMARY KEY (idx),
FOREIGN KEY(id) REFERENCES member(id)
) DEFAULT CHARSET=utf8;


alter table article add thumbnail varchar(40);
alter table article modify thumbnail varchar(40) not null;
alter table article drop tag;
commit;

desc article

select * from article;

insert into article(id,subject,content,tag) values('admin','제목','본문','봄봄, 여름');




CREATE TABLE reply (
reply_idx int unsigned AUTO_INCREMENT NOT NULL,
idx int unsigned not null,
id varchar(20) NOT NULL,
content varchar(5000) NOT NULL,
writedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
report int unsigned DEFAULT '0' ,
PRIMARY KEY (reply_idx),
FOREIGN KEY(id) REFERENCES member(id),
FOREIGN KEY(idx) REFERENCES article(idx)
) DEFAULT CHARSET=utf8;

insert into reply(idx,id,content) values(2,'admin','답글');

select * from reply;


CREATE TABLE image (
image_idx int unsigned AUTO_INCREMENT NOT NULL,
idx int unsigned not null,
ref int unsigned DEFAULT '0',
ori_name varchar(40) NOT NULL,
file_name varchar(40) NOT NULL,
file_size int unsigned NOT NULL,
PRIMARY KEY (image_idx),
FOREIGN KEY(idx) REFERENCES article(idx)
) DEFAULT CHARSET=utf8;

alter table image add tag varchar(200);
alter table image add subject varchar(100) not null;
alter table image add content varchar(1000) not null;

alter table image drop thumbnail;

commit

insert into image(idx,ref,ori_name,file_name,file_size) values(2,1,'람보르기니.jpg','039b420de36e4316b7b11a158b2f6739.jpg',59856);

select * from image;

desc image


drop table notice;

CREATE TABLE notice (
id varchar(15) NOT NULL,
idx int unsigned NOT NULL,
stat int unsigned NOT NULL,
updatedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
chk int unsigned DEFAULT '0',
FOREIGN KEY(id) REFERENCES member(id),
FOREIGN KEY(idx) REFERENCES article(idx)
) DEFAULT CHARSET=utf8;

-- chk 값
-- 0 = 안읽음
-- 1 = 읽음


insert into notice(id,idx,stat) values('admin','2',1);

select * from notice;


CREATE TABLE creator(
id varchar(15) NOT NULL,
homepage varchar(20),
cmemo varchar(2000),
history varchar(2000),
FOREIGN KEY(id) REFERENCES member(id)
) DEFAULT CHARSET=utf8;

select * from creator;

insert into creator values('creator','www.naver.com','cmemomo','history');

insert into creator(id) values('admin');
delete from creator where lvl='99'

delete from creator where id='creator'

select m.id, pwd, lvl,nickname,email,phone,address,memo, joindate, homepage, cmemo,history from member as m join creator as c on m.id=c.id;
commit

drop table interest;
CREATE TABLE interest (
id varchar(15) not null,
interest varchar(15) not null,
FOREIGN KEY(id) REFERENCES member(id),
FOREIGN KEY(interest) REFERENCES member(id)
) DEFAULT CHARSET=utf8;

insert into interest values('admin','user');

select * from interest;


drop table emblem;
CREATE TABLE emblem(
emblem_no int unsigned AUTO_INCREMENT NOT NULL,
emblem varchar(50) not null,
eimg varchar(40) not null,
PRIMARY KEY (emblem_no)
) DEFAULT CHARSET=utf8;

insert into emblem(emblem,eimg) values('봄봄','bom.jpg');

select * from emblem;


drop table emblem_storage;

CREATE TABLE emblem_storage (
id varchar(15) not null,
emblem_no int unsigned not null,
idx int unsigned not null,
winner int unsigned DEFAULT '0',
FOREIGN KEY(id) REFERENCES member(id),
FOREIGN KEY(emblem_no) REFERENCES emblem(emblem_no),
FOREIGN KEY(idx) REFERENCES article(idx)
) DEFAULT CHARSET=utf8;

insert into Emblem_Storage(id,emblem_no,idx) values('user',1,2);

select * from emblem_storage;



CREATE TABLE challenge (
chall_index int unsigned AUTO_INCREMENT NOT NULL,
emblem_no int unsigned not null,
subject varchar(50) not null,
content varchar(1000) not null,
writedate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
startdate TIMESTAMP not null,
enddate TIMESTAMP not null,
activation int unsigned DEFAULT '0',
PRIMARY KEY (chall_index),
FOREIGN KEY(emblem_no) REFERENCES emblem(emblem_no)
) DEFAULT CHARSET=utf8;



