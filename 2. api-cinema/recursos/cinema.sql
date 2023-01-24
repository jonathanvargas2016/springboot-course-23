/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     1/18/2023 10:01:00 PM                        */
/*==============================================================*/


drop table if exists APPLICATION;

drop table if exists "RELEASE";

drop table if exists TICKET;

/*==============================================================*/
/* Table: APPLICATION                                           */
/*==============================================================*/
create table APPLICATION
(
   ID_APP               bigint not null auto_increment,
   NAME                 varchar(100) not null,
   DESCRIPTION_APP      varchar(200) not null,
   APPTYPE              varchar(20) not null,
   primary key (ID_APP)
);

/*==============================================================*/
/* Table: "RELEASE"                                             */
/*==============================================================*/
create table "RELEASE"
(
   ID_RELEASE           bigint not null auto_increment,
   VERSION              varchar(10) not null,
   RELEASE_DATE         date not null,
   DESCRIPTION_RELEASE  varchar(200) not null,
   primary key (ID_RELEASE)
);

/*==============================================================*/
/* Table: TICKET                                                */
/*==============================================================*/
create table TICKET
(
   ID_TICKET            bigint not null auto_increment,
   ID_APP               bigint,
   ID_RELEASE           bigint not null,
   TITLE                varchar(100) not null,
   DESCRIPTION_TITLE    varchar(200),
   STATUS               varchar(20) not null,
   primary key (ID_TICKET)
);

alter table TICKET add constraint FK_RELATIONSHIP_1 foreign key (ID_APP)
      references APPLICATION (ID_APP) on delete restrict on update restrict;

alter table TICKET add constraint FK_RELATIONSHIP_3 foreign key (ID_RELEASE)
      references "RELEASE" (ID_RELEASE) on delete restrict on update restrict;

