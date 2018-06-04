drop table if exists t_admin_dep_user;

drop table if exists t_admin_department;

drop table if exists t_admin_function;

drop table if exists t_admin_institution;

drop table if exists t_admin_institution_type;

drop table if exists t_admin_role;

drop table if exists t_admin_role_fun;

drop table if exists t_admin_user;

drop table if exists t_admin_user_login;

drop table if exists t_admin_user_role;

drop table if exists t_sec_business;

/*==============================================================*/
/* Table: t_admin_dep_user                                      */
/*==============================================================*/
create table t_admin_dep_user
(
   dep_user_id          varchar(32) not null,
   user_id              varchar(32) not null,
   dep_id               varchar(32) not null,
   is_valid             char(1) not null default 'Y' comment '�Ƿ���Ч��ȡֵN/Y��ȱʡֵ��Y',
   remark               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (dep_user_id)
);

alter table t_admin_dep_user comment '������Ա��ϵ��һ������������ţ�';

/*==============================================================*/
/* Table: t_admin_department                                    */
/*==============================================================*/
create table t_admin_department
(
   dep_id               varchar(32) not null,
   dep_name             varchar(200) not null,
   ins_id               varchar(32) not null,
   parent_dep_Id        varchar(32) not null comment '��������parent_idΪ000',
   path_code            varchar(12) not null comment 'ÿ������λ���ֹ��ɣ�ÿ����001��ʼ����',
   address              varchar(200) comment '���ŵ�ַ',
   contactor            varchar(32) comment '��ϵ��',
   area                 varchar(32) comment '���ڵ���',
   longitude            varchar(50),
   latitude             varchar(50),
   Is_valid             varchar(1) not null default 'Y' comment 'Y:�ǣ�N:��',
   REMARK               varchar(4000) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (dep_id)
);

/*==============================================================*/
/* Table: t_admin_function                                      */
/*==============================================================*/
create table t_admin_function
(
   function_id          varchar(32) not null,
   bus_type_id          varchar(32) not null,
   name                 varchar(20) not null comment '�˵���ť����',
   code                 varchar(20) not null comment 'menu_xxx��button_xxx',
   function_type        varchar(10) not null comment '���ܵ�ȡֵ���������ܷ��ࡢ�˵�����ť���������̽ڵ�',
   url                  varchar(255) comment '�˵����ʵ�ַ',
   parent_id            varchar(32) not null comment '�ϼ��˵�ID�������Ϊ0',
   sort_no              int comment '�˵���ʾʱ�����д��򣬰�����ֵ�Ĵ�С�����������С����ʾʱ����ǰ�棬��Ų�����������',
   image_url            varchar(20) comment '�˵�ͼ��',
   is_context_relative  char(1) not null default 'Y' comment '�Ƿ����·��',
   target               varchar(32) not null comment '�˵�·�����ڿ������_blank��_self��_parent��_top',
   is_valid             char(1) not null default 'Y' comment 'Y���ǣ�N����',
   remark               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (function_id)
);

alter table t_admin_function comment '������:ÿһ��ҵ������һ�ù�����';

/*==============================================================*/
/* Table: t_admin_institution                                   */
/*==============================================================*/
create table t_admin_institution
(
   ins_id               varchar(32) not null comment '����id',
   ins_name             varchar(200) not null comment '���������ƣ���ƻ����������̡���ͨ�ͻ������ƣ�',
   ins_short_name       varchar(100) not null comment '�������',
   organization_code    varchar(200) comment '��֯�������루���ͣ���ô��룩',
   type_id              varchar(32) not null,
   bus_type_id          varchar(32) not null,
   industry_type        char(2) comment '��ҵ���ͣ�01��������02�����Ӳ����',
   legal                varchar(100) comment '��ҵ����',
   contact              varchar(100) comment '��ϵ��',
   phone                varchar(20) comment '��ϵ���ֻ�����',
   ins_manager          varchar(100) comment '��������Ա',
   ins_manager_phone    varchar(20) comment '����Ա�绰',
   address              varchar(200) comment '��ҵ��ַ',
   addr_web             varchar(200) comment '��ҵ��ַ',
   ins_logo_backgroud   varchar(200) comment '����ͼƬ',
   ins_logo             varchar(200) comment '����logo',
   remark               varchar(100) comment '����',
   is_valid             char(1) default 'Y' comment '�Ƿ�����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (ins_id)
);

/*==============================================================*/
/* Table: t_admin_institution_type                              */
/*==============================================================*/
create table t_admin_institution_type
(
   id                   varchar(32) not null,
   name                 varchar(20) comment '��ƻ����������̡���ͨ�ͻ�',
   remark               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (id)
);

alter table t_admin_institution_type comment '��֯���ͷ֣���ƻ����������̡���ͨ�ͻ�';

/*==============================================================*/
/* Table: t_admin_role                                          */
/*==============================================================*/
create table t_admin_role
(
   role_id              varchar(32) not null,
   bus_type_id          varchar(32) not null,
   ins_type_id          varchar(32) not null,
   role_code            varchar(20) not null,
   role_name            varchar(20) not null,
   is_valid             char(1) not null default 'Y' comment '�Ƿ�����,Ĭ��Y',
   remark               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (role_id)
);

alter table t_admin_role comment '��ɫ';

/*==============================================================*/
/* Table: t_admin_role_fun                                      */
/*==============================================================*/
create table t_admin_role_fun
(
   id                   varchar(32) not null,
   role_id              varchar(32) not null,
   function_id          varchar(32) not null,
   remark               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (id)
);

alter table t_admin_role_fun comment '��ɫ-������';

/*==============================================================*/
/* Table: t_admin_user                                          */
/*==============================================================*/
create table t_admin_user
(
   user_id              varchar(32) not null comment 'id',
   user_name            varchar(20) not null comment '�û���',
   user_sex             varchar(1) not null comment '�Ա�',
   user_head            varchar(50) comment 'ͷ��',
   user_birtd           datetime comment '��������',
   is_valid             varchar(1) comment '�Ƿ���Ч',
   remark               varchar(100) comment '��ע',
   create_user          varchar(32),
   create_date          datetime,
   update_user          varchar(0),
   update_date          datetime,
   primary key (user_id)
);

/*==============================================================*/
/* Table: t_admin_user_login                                    */
/*==============================================================*/
create table t_admin_user_login
(
   user_id              varchar(32) not null comment '�û�id',
   login_name           varchar(10) not null comment '��¼�˺�',
   phone                varchar(11) comment '�ֻ���',
   password             varchar(32) comment '����',
   salt                 varchar(32) comment '������',
   remark               varchar(100) comment '��ע',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '����ʱ��',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�ʱ��',
   primary key (user_id)
);

/*==============================================================*/
/* Table: t_admin_user_role                                     */
/*==============================================================*/
create table t_admin_user_role
(
   id                   varchar(32) not null,
   user_id              varchar(32) not null comment '�û�id',
   role_id              varchar(32) not null,
   remake               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (id)
);

alter table t_admin_user_role comment '��λ-��ɫ';

/*==============================================================*/
/* Table: t_sec_business                                        */
/*==============================================================*/
create table t_sec_business
(
   bus_type_id          varchar(32) not null,
   bus_type_name        varchar(20) not null,
   remark               varchar(100) comment '����',
   create_user          varchar(32) comment '������',
   create_date          datetime comment '��������',
   update_user          varchar(32) comment '�޸���',
   update_date          datetime comment '�޸�����',
   primary key (bus_type_id)
);

alter table t_sec_business comment 'ҵ�����ͣ���ƻ����������̡���ͨ�ͻ�';

alter table t_admin_dep_user add constraint FK_Reference_11 foreign key (user_id)
      references t_admin_user (user_id) on delete restrict on update restrict;

alter table t_admin_dep_user add constraint FK_Reference_8 foreign key (dep_id)
      references t_admin_department (dep_id) on delete restrict on update restrict;

alter table t_admin_department add constraint FK_Reference_25 foreign key (ins_id)
      references t_admin_institution (ins_id) on delete restrict on update restrict;

alter table t_admin_function add constraint FK_Reference_28 foreign key (bus_type_id)
      references t_sec_business (bus_type_id) on delete restrict on update restrict;

alter table t_admin_institution add constraint FK_Reference_23 foreign key (type_id)
      references t_admin_institution_type (id) on delete restrict on update restrict;

alter table t_admin_institution add constraint FK_Reference_24 foreign key (bus_type_id)
      references t_sec_business (bus_type_id) on delete restrict on update restrict;

alter table t_admin_role add constraint FK_Reference_29 foreign key (bus_type_id)
      references t_sec_business (bus_type_id) on delete restrict on update restrict;

alter table t_admin_role_fun add constraint FK_Reference_2 foreign key (role_id)
      references t_admin_role (role_id) on delete restrict on update restrict;

alter table t_admin_role_fun add constraint FK_Reference_3 foreign key (function_id)
      references t_admin_function (function_id) on delete restrict on update restrict;

alter table t_admin_user_role add constraint FK_Reference_10 foreign key (user_id)
      references t_admin_user (user_id) on delete restrict on update restrict;

alter table t_admin_user_role add constraint FK_Reference_4 foreign key (role_id)
      references t_admin_role (role_id) on delete restrict on update restrict;
