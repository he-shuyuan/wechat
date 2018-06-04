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
   is_valid             char(1) not null default 'Y' comment '是否有效，取值N/Y，缺省值是Y',
   remark               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (dep_user_id)
);

alter table t_admin_dep_user comment '部门人员关系（一个人属多个部门）';

/*==============================================================*/
/* Table: t_admin_department                                    */
/*==============================================================*/
create table t_admin_department
(
   dep_id               varchar(32) not null,
   dep_name             varchar(200) not null,
   ins_id               varchar(32) not null,
   parent_dep_Id        varchar(32) not null comment '根机构的parent_id为000',
   path_code            varchar(12) not null comment '每层由三位数字构成，每层由001开始计数',
   address              varchar(200) comment '部门地址',
   contactor            varchar(32) comment '联系人',
   area                 varchar(32) comment '所在地区',
   longitude            varchar(50),
   latitude             varchar(50),
   Is_valid             varchar(1) not null default 'Y' comment 'Y:是，N:否',
   REMARK               varchar(4000) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (dep_id)
);

/*==============================================================*/
/* Table: t_admin_function                                      */
/*==============================================================*/
create table t_admin_function
(
   function_id          varchar(32) not null,
   bus_type_id          varchar(32) not null,
   name                 varchar(20) not null comment '菜单按钮名称',
   code                 varchar(20) not null comment 'menu_xxx；button_xxx',
   function_type        varchar(10) not null comment '可能的取值包括：功能分类、菜单、按钮、工作流程节点',
   url                  varchar(255) comment '菜单访问地址',
   parent_id            varchar(32) not null comment '上级菜单ID。根结点为0',
   sort_no              int comment '菜单显示时的排列次序，按照其值的大小进行排序，序号小的显示时排在前面，序号不必是连续的',
   image_url            varchar(20) comment '菜单图标',
   is_context_relative  char(1) not null default 'Y' comment '是否相对路径',
   target               varchar(32) not null comment '菜单路径所在框架名，_blank、_self、_parent、_top',
   is_valid             char(1) not null default 'Y' comment 'Y：是；N：否',
   remark               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (function_id)
);

alter table t_admin_function comment '功能树:每一个业务类型一棵功能树';

/*==============================================================*/
/* Table: t_admin_institution                                   */
/*==============================================================*/
create table t_admin_institution
(
   ins_id               varchar(32) not null comment '机构id',
   ins_name             varchar(200) not null comment '机构的名称（金财互联、服务商、普通客户的名称）',
   ins_short_name       varchar(100) not null comment '机构简称',
   organization_code    varchar(200) comment '组织机构代码（社会停信用代码）',
   type_id              varchar(32) not null,
   bus_type_id          varchar(32) not null,
   industry_type        char(2) comment '行业类型（01计算机软件02计算机硬件）',
   legal                varchar(100) comment '企业法人',
   contact              varchar(100) comment '联系人',
   phone                varchar(20) comment '联系人手机号码',
   ins_manager          varchar(100) comment '机构管理员',
   ins_manager_phone    varchar(20) comment '管理员电话',
   address              varchar(200) comment '企业地址',
   addr_web             varchar(200) comment '企业网址',
   ins_logo_backgroud   varchar(200) comment '背景图片',
   ins_logo             varchar(200) comment '机构logo',
   remark               varchar(100) comment '描述',
   is_valid             char(1) default 'Y' comment '是否启用',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (ins_id)
);

/*==============================================================*/
/* Table: t_admin_institution_type                              */
/*==============================================================*/
create table t_admin_institution_type
(
   id                   varchar(32) not null,
   name                 varchar(20) comment '金财互联、服务商、普通客户',
   remark               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (id)
);

alter table t_admin_institution_type comment '组织类型分：金财互联、服务商、普通客户';

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
   is_valid             char(1) not null default 'Y' comment '是否启用,默认Y',
   remark               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (role_id)
);

alter table t_admin_role comment '角色';

/*==============================================================*/
/* Table: t_admin_role_fun                                      */
/*==============================================================*/
create table t_admin_role_fun
(
   id                   varchar(32) not null,
   role_id              varchar(32) not null,
   function_id          varchar(32) not null,
   remark               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (id)
);

alter table t_admin_role_fun comment '角色-功能树';

/*==============================================================*/
/* Table: t_admin_user                                          */
/*==============================================================*/
create table t_admin_user
(
   user_id              varchar(32) not null comment 'id',
   user_name            varchar(20) not null comment '用户名',
   user_sex             varchar(1) not null comment '性别',
   user_head            varchar(50) comment '头像',
   user_birtd           datetime comment '出生年月',
   is_valid             varchar(1) comment '是否有效',
   remark               varchar(100) comment '备注',
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
   user_id              varchar(32) not null comment '用户id',
   login_name           varchar(10) not null comment '登录账号',
   phone                varchar(11) comment '手机号',
   password             varchar(32) comment '密码',
   salt                 varchar(32) comment '密码盐',
   remark               varchar(100) comment '备注',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建时间',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改时间',
   primary key (user_id)
);

/*==============================================================*/
/* Table: t_admin_user_role                                     */
/*==============================================================*/
create table t_admin_user_role
(
   id                   varchar(32) not null,
   user_id              varchar(32) not null comment '用户id',
   role_id              varchar(32) not null,
   remake               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (id)
);

alter table t_admin_user_role comment '岗位-角色';

/*==============================================================*/
/* Table: t_sec_business                                        */
/*==============================================================*/
create table t_sec_business
(
   bus_type_id          varchar(32) not null,
   bus_type_name        varchar(20) not null,
   remark               varchar(100) comment '描述',
   create_user          varchar(32) comment '创建人',
   create_date          datetime comment '创建日期',
   update_user          varchar(32) comment '修改人',
   update_date          datetime comment '修改日期',
   primary key (bus_type_id)
);

alter table t_sec_business comment '业务类型：金财互联、服务商、普通客户';

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
