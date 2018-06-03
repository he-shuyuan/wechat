
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
   dep_user_id          varchar(32)                    not null,
   user_id              varchar(32)                    not null,
   dep_id               varchar(32)                    not null,
   is_valid             char(1)                        not null default 'Y',
   remark               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_DEP_USER primary key (dep_user_id)
);

comment on table t_admin_dep_user is 
'部门人员关系 一个人属多个部门';

comment on column t_admin_dep_user.is_valid is 
'是否有效，取值N/Y，缺省值是Y';

comment on column t_admin_dep_user.remark is 
'描述';

comment on column t_admin_dep_user.create_user is 
'创建人';

comment on column t_admin_dep_user.create_date is 
'创建日期';

comment on column t_admin_dep_user.update_user is 
'修改人';

comment on column t_admin_dep_user.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_department                                    */
/*==============================================================*/
create table t_admin_department 
(
   dep_id               varchar(32)                    not null,
   dep_name             varchar(200)                   not null,
   ins_id               varchar(32)                    not null,
   parent_dep_Id        VARCHAR(32)                    not null,
   path_code            VARCHAR(12)                    not null,
   address              varchar(200)                   null,
   contactor            varchar(32)                    null,
   area                 varchar(32)                    null,
   longitude            varchar(50)                    null,
   latitude             varchar(50)                    null,
   Is_valid             varchar(1)                     not null default 'Y',
   REMARK               VARCHAR(4000)                  null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_DEPARTMENT primary key (dep_id)
);

comment on column t_admin_department.parent_dep_Id is 
'根机构的parent_id为000';

comment on column t_admin_department.path_code is 
'每层由三位数字构成，每层由001开始计数';

comment on column t_admin_department.address is 
'部门地址';

comment on column t_admin_department.contactor is 
'联系人';

comment on column t_admin_department.area is 
'所在地区';

comment on column t_admin_department.Is_valid is 
'Y:是，N:否';

comment on column t_admin_department.REMARK is 
'描述';

comment on column t_admin_department.create_user is 
'创建人';

comment on column t_admin_department.create_date is 
'创建日期';

comment on column t_admin_department.update_user is 
'修改人';

comment on column t_admin_department.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_function                                      */
/*==============================================================*/
create table t_admin_function 
(
   function_id          varchar(32)                    not null,
   bus_type_id          varchar(32)                    not null,
   name                 VARCHAR(20)                    not null,
   code                 varchar(20)                    not null,
   function_type        varchar(10)                    not null,
   url                  VARCHAR(255)                   null,
   parent_id            VARCHAR(32)                    not null,
   sort_no              int                            null,
   image_url            VARCHAR(20)                    null,
   is_context_relative  char(1)                        not null default 'Y',
   target               VARCHAR(32)                    not null,
   is_valid             char(1)                        not null default 'Y',
   remark               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_FUNCTION primary key (function_id)
);

comment on table t_admin_function is 
'功能树:每一个业务类型一棵功能树';

comment on column t_admin_function.name is 
'菜单按钮名称';

comment on column t_admin_function.code is 
'menu_xxx；button_xxx';

comment on column t_admin_function.function_type is 
'可能的取值包括：功能分类、菜单、按钮、工作流程节点';

comment on column t_admin_function.url is 
'菜单访问地址';

comment on column t_admin_function.parent_id is 
'上级菜单ID。根结点为0';

comment on column t_admin_function.sort_no is 
'菜单显示时的排列次序，按照其值的大小进行排序，序号小的显示时排在前面，序号不必是连续的';

comment on column t_admin_function.image_url is 
'菜单图标';

comment on column t_admin_function.is_context_relative is 
'是否相对路径';

comment on column t_admin_function.target is 
'菜单路径所在框架名，_blank、_self、_parent、_top';

comment on column t_admin_function.is_valid is 
'Y：是；N：否';

comment on column t_admin_function.remark is 
'描述';

comment on column t_admin_function.create_user is 
'创建人';

comment on column t_admin_function.create_date is 
'创建日期';

comment on column t_admin_function.update_user is 
'修改人';

comment on column t_admin_function.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_institution                                   */
/*==============================================================*/
create table t_admin_institution 
(
   ins_id               varchar(32)                    not null,
   ins_name             varchar(200)                   not null,
   ins_short_name       varchar(100)                   not null,
   organization_code    varchar(200)                   null,
   type_id              varchar(32)                    not null,
   bus_type_id          varchar(32)                    not null,
   industry_type        char(2)                        null,
   legal                varchar(100)                   null,
   contact              varchar(100)                   null,
   phone                varchar(20)                    null,
   ins_manager          varchar(100)                   null,
   ins_manager_phone    varchar(20)                    null,
   address              varchar(200)                   null,
   addr_web             varchar(200)                   null,
   ins_logo_backgroud   varchar(200)                   null,
   ins_logo             varchar(200)                   null,
   remark               VARCHAR(100)                   null,
   is_valid             char                           null default 'Y',
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_INSTITUTION primary key (ins_id)
);

comment on column t_admin_institution.ins_id is 
'机构id';

comment on column t_admin_institution.ins_name is 
'机构的名称  金财互联、服务商、普通客户的名称';

comment on column t_admin_institution.ins_short_name is 
'机构简称';

comment on column t_admin_institution.organization_code is 
'组织机构代码（社会停信用代码）';

comment on column t_admin_institution.industry_type is 
'行业类型（01计算机软件02计算机硬件）';

comment on column t_admin_institution.legal is 
'企业法人';

comment on column t_admin_institution.contact is 
'联系人';

comment on column t_admin_institution.phone is 
'联系人手机号码';

comment on column t_admin_institution.ins_manager is 
'机构管理员';

comment on column t_admin_institution.ins_manager_phone is 
'管理员电话';

comment on column t_admin_institution.address is 
'企业地址';

comment on column t_admin_institution.addr_web is 
'企业网址';

comment on column t_admin_institution.ins_logo_backgroud is 
'背景图片';

comment on column t_admin_institution.ins_logo is 
'机构logo';

comment on column t_admin_institution.remark is 
'描述';

comment on column t_admin_institution.is_valid is 
'是否启用';

comment on column t_admin_institution.create_user is 
'创建人';

comment on column t_admin_institution.create_date is 
'创建日期';

comment on column t_admin_institution.update_user is 
'修改人';

comment on column t_admin_institution.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_institution_type                              */
/*==============================================================*/
create table t_admin_institution_type 
(
   id                   varchar(32)                    not null,
   name                 varchar(20)                    null,
   remark               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_INSTITUTION_TYPE primary key (id)
);

comment on table t_admin_institution_type is 
'组织类型分：金财互联、服务商、普通客户';

comment on column t_admin_institution_type.name is 
'金财互联、服务商、普通客户';

comment on column t_admin_institution_type.remark is 
'描述';

comment on column t_admin_institution_type.create_user is 
'创建人';

comment on column t_admin_institution_type.create_date is 
'创建日期';

comment on column t_admin_institution_type.update_user is 
'修改人';

comment on column t_admin_institution_type.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_role                                          */
/*==============================================================*/
create table t_admin_role 
(
   role_id              varchar(32)                    not null,
   bus_type_id          varchar(32)                    not null,
   ins_type_id          varchar(32)                    not null,
   role_code            varchar(20)                    not null,
   role_name            varchar(20)                    not null,
   is_valid             char(1)                        not null default 'Y',
   remark               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_ROLE primary key (role_id)
);

comment on table t_admin_role is 
'角色';

comment on column t_admin_role.is_valid is 
'是否启用,默认Y';

comment on column t_admin_role.remark is 
'描述';

comment on column t_admin_role.create_user is 
'创建人';

comment on column t_admin_role.create_date is 
'创建日期';

comment on column t_admin_role.update_user is 
'修改人';

comment on column t_admin_role.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_role_fun                                      */
/*==============================================================*/
create table t_admin_role_fun 
(
   id                   varchar(32)                    not null,
   role_id              varchar(32)                    not null,
   function_id          varchar(32)                    not null,
   remark               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_ROLE_FUN primary key (id)
);

comment on table t_admin_role_fun is 
'角色-功能树';

comment on column t_admin_role_fun.remark is 
'描述';

comment on column t_admin_role_fun.create_user is 
'创建人';

comment on column t_admin_role_fun.create_date is 
'创建日期';

comment on column t_admin_role_fun.update_user is 
'修改人';

comment on column t_admin_role_fun.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_admin_user                                          */
/*==============================================================*/
create table t_admin_user 
(
   user_id              varchar(32)                    not null,
   user_name            varchar(20)                    not null,
   user_sex             varchar(1)                     not null,
   user_head            varchar(50)                    null,
   user_birtd           datetime                       null,
   is_valid             varchar(1)                     null,
   remark               varchar(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(0)                     null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_USER primary key clustered (user_id)
);

comment on column t_admin_user.user_id is 
'id';

comment on column t_admin_user.user_name is 
'用户名';

comment on column t_admin_user.user_sex is 
'性别';

comment on column t_admin_user.user_head is 
'头像';

comment on column t_admin_user.user_birtd is 
'出生年月';

comment on column t_admin_user.is_valid is 
'是否有效';

comment on column t_admin_user.remark is 
'备注';

/*==============================================================*/
/* Table: t_admin_user_login                                    */
/*==============================================================*/
create table t_admin_user_login 
(
   user_id              varchar(32)                    not null,
   login_name           varchar(10)                    not null,
   phone                varchar(11)                    null,
   password             varchar(32)                    null,
   salt                 varchar(32)                    null,
   remark               varchar(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_USER_LOGIN primary key clustered (user_id)
);

comment on column t_admin_user_login.user_id is 
'用户id';

comment on column t_admin_user_login.login_name is 
'登录账号';

comment on column t_admin_user_login.phone is 
'手机号';

comment on column t_admin_user_login.password is 
'密码';

comment on column t_admin_user_login.salt is 
'密码盐';

comment on column t_admin_user_login.remark is 
'备注';

comment on column t_admin_user_login.create_user is 
'创建人';

comment on column t_admin_user_login.create_date is 
'创建时间';

comment on column t_admin_user_login.update_user is 
'修改人';

comment on column t_admin_user_login.update_date is 
'修改时间';

/*==============================================================*/
/* Table: t_admin_user_role                                     */
/*==============================================================*/
create table t_admin_user_role 
(
   id                   varchar(32)                    not null,
   user_id              varchar(32)                    not null,
   role_id              varchar(32)                    not null,
   remake               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_ADMIN_USER_ROLE primary key (id)
);

comment on table t_admin_user_role is 
'岗位-角色';

comment on column t_admin_user_role.user_id is 
'用户id';

comment on column t_admin_user_role.remake is 
'描述';

comment on column t_admin_user_role.create_user is 
'创建人';

comment on column t_admin_user_role.create_date is 
'创建日期';

comment on column t_admin_user_role.update_user is 
'修改人';

comment on column t_admin_user_role.update_date is 
'修改日期';

/*==============================================================*/
/* Table: t_sec_business                                        */
/*==============================================================*/
create table t_sec_business 
(
   bus_type_id          varchar(32)                    not null,
   bus_type_name        varchar(20)                    not null,
   remark               VARCHAR(100)                   null,
   create_user          varchar(32)                    null,
   create_date          datetime                       null,
   update_user          varchar(32)                    null,
   update_date          datetime                       null,
   constraint PK_T_SEC_BUSINESS primary key (bus_type_id)
);

comment on table t_sec_business is 
'业务类型：金财互联、服务商、普通客户';

comment on column t_sec_business.remark is 
'描述';

comment on column t_sec_business.create_user is 
'创建人';

comment on column t_sec_business.create_date is 
'创建日期';

comment on column t_sec_business.update_user is 
'修改人';

comment on column t_sec_business.update_date is 
'修改日期';

alter table t_admin_dep_user
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (user_id)
      references t_admin_user (user_id)
      on update restrict
      on delete restrict;

alter table t_admin_dep_user
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (dep_id)
      references t_admin_department (dep_id)
      on update restrict
      on delete restrict;

alter table t_admin_department
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (ins_id)
      references t_admin_institution (ins_id)
      on update restrict
      on delete restrict;

alter table t_admin_function
   add constraint FK_T_ADMIN__REFERENCE_T_SEC_BU foreign key (bus_type_id)
      references t_sec_business (bus_type_id)
      on update restrict
      on delete restrict;

alter table t_admin_institution
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (type_id)
      references t_admin_institution_type (id)
      on update restrict
      on delete restrict;

alter table t_admin_institution
   add constraint FK_T_ADMIN__REFERENCE_T_SEC_BU foreign key (bus_type_id)
      references t_sec_business (bus_type_id)
      on update restrict
      on delete restrict;

alter table t_admin_role
   add constraint FK_T_ADMIN__REFERENCE_T_SEC_BU foreign key (bus_type_id)
      references t_sec_business (bus_type_id)
      on update restrict
      on delete restrict;

alter table t_admin_role_fun
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (role_id)
      references t_admin_role (role_id)
      on update restrict
      on delete restrict;

alter table t_admin_role_fun
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (function_id)
      references t_admin_function (function_id)
      on update restrict
      on delete restrict;

alter table t_admin_user_role
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (user_id)
      references t_admin_user (user_id)
      on update restrict
      on delete restrict;

alter table t_admin_user_role
   add constraint FK_T_ADMIN__REFERENCE_T_ADMIN_ foreign key (role_id)
      references t_admin_role (role_id)
      on update restrict
      on delete restrict;
