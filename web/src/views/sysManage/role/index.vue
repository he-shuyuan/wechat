<template>
    <router-view v-if="$route.name !== thisRouteName"></router-view>
    <mc-table v-else ref="table" stripe :formOpt="formOpt" :columns="columns" 
    method="pageQueryAdminRoleDTOList" :params="params" showAdd addBtnText="新增角色" 
    @addEvent="$router.push({name:'roleDetail',params:{name:'add'}})">
     <template slot-scope="scope" slot="isValid">
        <el-button v-if="scope.row.isValid =='Y'" type="primary" size="mini">已启用</el-button>
        <el-button v-else type="danger" size="mini">已禁用</el-button>
     </template> 
      <template slot-scope="scope" slot="handle">
        <div v-if="scope.row.isValid =='Y'">
           <el-button type="danger" size="mini" @click.native="showMenu(scope.row)">查看菜单</el-button> 
           <el-button type="warning" size="mini"
              @click.native="changeStatus(scope.row.roleId,'N')">禁用</el-button> 
           <el-button type="primary" size="mini" @click.native="modifyRole(scope.row)">修改
           </el-button> 
        </div>
        <div v-else>
           <el-button type="primary" size="mini"
           @click.native="changeStatus(scope.row.roleId,'Y')">启用</el-button> 
           <el-button type="danger" size="mini" @click.native="delRole(scope.row.roleId)"
             >删除</el-button> 
        </div>
       </template> 
    </mc-table>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    export default {
        data() {
            return {
                thisRouteName:'roleManage',
                init:{
                   contractTypeMap:{},
                },
                formOpt: [{
                        label: '业务类型',
                        col:10,
                        type: 'select',
                        prop: 'busTypeId',
                        placeholder: '请选择',
                        method:'queryBusTypeList',
                        params:{},
                        valueName:'busTypeId',
                        labelName:'busTypeName'
                    },
                     {
                        label: '角色名称',
                        col:10,
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'roleName',
                    },
                     {
                        col:2,
                        label: '',
                        type: 'reset',
                    },
                    {
                        label: '角色编码',
                        col:10,
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'roleCode',
                    },
                       {
                        label: '是否启用',
                        col:10,
                        type: 'select',
                        placeholder: '请输入',
                        prop: 'isValid',
                        initData:[
                        {value:'Y',label:'启用'},
                        {value:'N',label:'禁用'}]
                    },
                    {
                        col:2,
                        label: '',
                        type: 'search',
                    },

                ],
                columns: [{
                        prop: 'roleName',
                        label: '角色名称',
                        minWidth: 150
                    },
                    {
                        prop: 'roleCode',
                        label: '角色编码',
                        minWidth: 150,
                    },
                    {
                        prop: 'busTypeName',
                        label: '业务类型',
                        minWidth: 100,
                        
                    },
                    { 
                        prop:'isValid',
                        label: '状态',
                        slotName:'isValid'
                    },
                    {
                        prop: '',
                        label: '操作',
                        width: 300,
                        slotName: 'handle'
                    },
                ],
                params: {
                   
                }
            }
        },
        created(){
          
        },
        mounted() {
           
        },
        methods: {
            /**
             * 修改角色
             * @param  {[type]} row [description]
             * @return {[type]}     [description]
             */
            modifyRole:function(row){
                this.$router.push({name:'roleDetail',params:{roleId:row.roleId}});
            },
            /**
             * 改变状态
             * @param  {[type]} id     [description]
             * @param  {[type]} status [description]
             * @return {[type]}        [description]
             */
            changeStatus:function(id,status){
               standardAsync(this,'updateAdminRoleDTO',{roleId:id,isValid:status},res=>{
                     this.$message.success("状态改变成功")
                     this.$refs.table.searchHandle()
                 });
            },
            /**
             * 删除角色
             * @param  {[type]} id [description]
             * @return {[type]}    [description]
             */
             delRole:function(id){
                  let _this = this;
                  MessageBox.confirm('确定删除此记录吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                    }).then(() => {
                     standardAsync(_this,'delAdminRoleDTO',{roleId:id},res=>{
                     _this.$message.success("删除成功")
                     _this.$refs.table.searchHandle()
                         });
                })
             },
             /**
              * 展现菜单
              * @param  {[type]} data [description]
              * @return {[type]}      [description]
              */
             showMenu:function(data){
               this.$router.push({name:'showMenu',
                params:{roleId:data.roleId,
                         busTypeId:data.busTypeId,
                       roleName:data.roleName}})
             },
            }
        }
   

</script>

<style scoped>
  .el-tag:hover{
     cursor:pointer;
    }
</style>
