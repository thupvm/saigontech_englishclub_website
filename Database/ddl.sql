/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     03/11/2018 11:25:36 CH                       */
/*==============================================================*/


alter table CLIPS 
   drop foreign key FK_CLIPS_FK_CLIP_ADMINS;

alter table CLIPS 
   drop foreign key FK_CLIPS_FK_CLIP_T_CLIP_TYP;

alter table FILES 
   drop foreign key FK_FILES_FK_FILE_MATERIAL;

alter table MATERIALS 
   drop foreign key FK_MATERIAL_FK_MATERI_ADMINS;

alter table MATERIALS 
   drop foreign key FK_MATERIAL_FK_MATERI_MATERIAL;

alter table NEWS 
   drop foreign key FK_NEWS_FK_NEWS_ADMINS;

alter table NEWS 
   drop foreign key FK_NEWS_FK_NEWS_T_NEWS_TYP;

alter table TIPS 
   drop foreign key FK_TIPS_FK_TIP_ADMINS;

alter table TIPS 
   drop foreign key FK_TIPS_FK_TIP_TY_TIP_TYPE;

drop table if exists ADMINS;


alter table CLIPS 
   drop foreign key FK_CLIPS_FK_CLIP_ADMINS;

alter table CLIPS 
   drop foreign key FK_CLIPS_FK_CLIP_T_CLIP_TYP;

drop table if exists CLIPS;

drop table if exists CLIP_TYPES;


alter table FILES 
   drop foreign key FK_FILES_FK_FILE_MATERIAL;

drop table if exists FILES;


alter table MATERIALS 
   drop foreign key FK_MATERIAL_FK_MATERI_ADMINS;

alter table MATERIALS 
   drop foreign key FK_MATERIAL_FK_MATERI_MATERIAL;

drop table if exists MATERIALS;

drop table if exists MATERIAL_TYPES;


alter table NEWS 
   drop foreign key FK_NEWS_FK_NEWS_ADMINS;

alter table NEWS 
   drop foreign key FK_NEWS_FK_NEWS_T_NEWS_TYP;

drop table if exists NEWS;

drop table if exists NEWS_TYPES;


alter table TIPS 
   drop foreign key FK_TIPS_FK_TIP_ADMINS;

alter table TIPS 
   drop foreign key FK_TIPS_FK_TIP_TY_TIP_TYPE;

drop table if exists TIPS;

drop table if exists TIP_TYPES;

/*==============================================================*/
/* Table: ADMINS                                                */
/*==============================================================*/
create table ADMINS
(
   ID                   int not null auto_increment  comment '',
   USERNAME             varchar(50) not null  comment '',
   PASSWORD             varchar(50) not null  comment '',
   FULLNAME             varchar(255) not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: CLIPS                                                 */
/*==============================================================*/
create table CLIPS
(
   ID                   int not null auto_increment  comment '',
   TITLE                varchar(100)  comment '',
   UPLOAD_PERIOD        datetime not null  comment '',
   DESCRIPTION          varchar(1000)  comment '',
   LINK                 varchar(10000) not null  comment '',
   ADMIN_ID             int not null  comment '',
   CLIP_TYPE_ID         int not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: CLIP_TYPES                                            */
/*==============================================================*/
create table CLIP_TYPES
(
   ID                   int not null auto_increment  comment '',
   NAME                 varchar(100) not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: FILES                                                 */
/*==============================================================*/
create table FILES
(
   ID                   int not null auto_increment  comment '',
   NAME                 varchar(1000) not null  comment '',
   MATERIAL_ID          int not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: MATERIALS                                             */
/*==============================================================*/
create table MATERIALS
(
   ID                   int not null auto_increment  comment '',
   TITLE                varchar(100) not null  comment '',
   TITLE_PICTURE        varchar(255)  comment '',
   UPLOAD_PERIOD        datetime not null  comment '',
   CONTENT              varchar(10000)  comment '',
   ADMIN_ID             int not null  comment '',
   MATERIAL_TYPE_ID     int not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: MATERIAL_TYPES                                        */
/*==============================================================*/
create table MATERIAL_TYPES
(
   ID                   int not null auto_increment  comment '',
   NAME                 varchar(100) not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: NEWS                                                  */
/*==============================================================*/
create table NEWS
(
   ID                   int not null auto_increment  comment '',
   TITLE                varchar(100) not null  comment '',
   TITLE_PICTURE        varchar(255) not null  comment '',
   UPLOAD_PERIOD        datetime not null  comment '',
   CONTENT              varchar(100000) not null  comment '',
   ADMIN_ID             int not null  comment '',
   NEWS__TYPE_ID        int not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: NEWS_TYPES                                            */
/*==============================================================*/
create table NEWS_TYPES
(
   ID                   int not null auto_increment  comment '',
   NAME                 varchar(100) not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: TIPS                                                  */
/*==============================================================*/
create table TIPS
(
   ID                   int not null auto_increment  comment '',
   TITLE                varchar(100) not null  comment '',
   TITLE_PICTURE        varchar(255) not null  comment '',
   UPLOAD_PERIOD        datetime not null  comment '',
   CONTENT              varchar(100000) not null  comment '',
   ADMIN_ID             int not null  comment '',
   TIP_TYPE_ID          int not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

/*==============================================================*/
/* Table: TIP_TYPES                                             */
/*==============================================================*/
create table TIP_TYPES
(
   ID                   int not null auto_increment  comment '',
   NAME                 varchar(100) not null  comment '',
   STATUS               bool not null  comment '',
   primary key (ID)
);

alter table CLIPS add constraint FK_CLIPS_FK_CLIP_ADMINS foreign key (ADMIN_ID)
      references ADMINS (ID) on delete restrict on update restrict;

alter table CLIPS add constraint FK_CLIPS_FK_CLIP_T_CLIP_TYP foreign key (CLIP_TYPE_ID)
      references CLIP_TYPES (ID) on delete restrict on update restrict;

alter table FILES add constraint FK_FILES_FK_FILE_MATERIAL foreign key (MATERIAL_ID)
      references MATERIALS (ID) on delete restrict on update restrict;

alter table MATERIALS add constraint FK_MATERIAL_FK_MATERI_ADMINS foreign key (ADMIN_ID)
      references ADMINS (ID) on delete restrict on update restrict;

alter table MATERIALS add constraint FK_MATERIAL_FK_MATERI_MATERIAL foreign key (MATERIAL_TYPE_ID)
      references MATERIAL_TYPES (ID) on delete restrict on update restrict;

alter table NEWS add constraint FK_NEWS_FK_NEWS_ADMINS foreign key (ADMIN_ID)
      references ADMINS (ID) on delete restrict on update restrict;

alter table NEWS add constraint FK_NEWS_FK_NEWS_T_NEWS_TYP foreign key (NEWS__TYPE_ID)
      references NEWS_TYPES (ID) on delete restrict on update restrict;

alter table TIPS add constraint FK_TIPS_FK_TIP_ADMINS foreign key (ADMIN_ID)
      references ADMINS (ID) on delete restrict on update restrict;

alter table TIPS add constraint FK_TIPS_FK_TIP_TY_TIP_TYPE foreign key (TIP_TYPE_ID)
      references TIP_TYPES (ID) on delete restrict on update restrict;

