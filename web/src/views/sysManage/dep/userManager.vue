<template>
    <mc-table ref="table" stripe :columns="columns" method="pageQueryAdminUserDTOList" :params="params" showAdd addBtnText="新增用户" 
    :formOpt="formOpt"
    :autoLoad="false"
    :btnData="{btnStyle:'text-align:right;padding-top:20px;margin-right:20px'}"
    @addEvent="$router.push({name:'userAdd',params:{depId:params.depId,insId:params.insId}})">
       <template slot-scope="scope" slot="userName">
      <!--  <router-link :to="{name:'userDetail',params:{data:scope.row}}"> -->
            {{scope.row.userName}}
        <!-- </router-link> -->
       </template> 
        <template slot-scope="scope" slot="userSex">
        {{scope.row.userSex=='1'?'男':'女'}}
       </template> 
       <template slot-scope="scope" slot="status">
            <el-button v-if="scope.row.isValid =='Y'" type="primary" size="mini">已启用</el-button>
            <el-button v-else type="danger" size="mini">已禁用</el-button>
       </template> 
       <template slot-scope="scope" slot="handle">
        <el-button type="primary" size="mini"
         @click.native="$router.push({name:'userRole',params:{depId:params.insId,userId:scope.row.userId,userName:scope.row.userName}})">角色分配</el-button>
        <el-button v-if="scope.row.isValid=='Y'" 
           type="warning" size="mini"  @click.native="changeStatus('N',scope.row.userId)">禁用</el-button>
        <el-button v-else type="warning" size="mini" @click.native="changeStatus('Y',scope.row.userId)">启用</el-button>
        <el-button type="danger" size="mini" @click.native="removeUserDep(scope.row.userId)">移除</el-button>
       </template> 
    </mc-table>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    import util from "@/utils/"
    export default {
        props:{
           depId:null,
           pathCode:'',
           insId:''
        },
        data() {
            return {
                init:{
                   contractTypeMap:{},
                },
                formOpt: [
                  {
                        label: '用户名称',
                        col:7,
                        type: 'text',
                        prop: 'userName',
                        placeholder: '请输入',
                      
                    },
                     {
                        label: '登录名',
                        col:7,
                        type: 'text',
                        prop: 'loginName',
                        placeholder: '请输入',
                      
                    },
                    {
                        label: '手机号',
                        col:7,
                        type: 'text',
                        prop: 'phone',
                        placeholder: '请输入',
                      
                    },
                     {
                        col:3,
                        label: '',
                        type: 'search'
                    },
                    {
                        label: '性别',
                        col:7,
                        type: 'select',
                        prop: 'userSex',
                        placeholder: '请选择',
                        initData: [{label:'男',value:'1'},
                                   {label:'女',value:'0'}]
                    },
                    {
                        label: '范围',
                        col:7,
                        type: 'select',
                        prop: 'optionsRadios',
                        placeholder: '请选择',
                        initData: [{value:'0',label:'仅当前部门'},
                                   {value:'1',label:'当前部门及其下级'}]
                    },
                     {
                        label: '状态',
                        col:7,
                        type: 'select',
                        prop: 'isValid',
                        placeholder: '请选择',
                        initData: [{label:'已启用',value:'Y'},
                                   {label:'已禁用',value:'N'}]
                    },
                    {
                        col:3,
                        label: '',
                        type: 'reset',
                    },
                ],
                columns: [
                     {
                        prop: 'userName',
                        label: '用户名称',
                        minWidth: 100,
                        slotName: 'userName'
                    },
                     {
                        prop: 'loginName',
                        label: '登录名',
                        minWidth: 100
                    },
                     {
                        prop: 'phone',
                        label: '手机号',
                        minWidth: 100
                    },
                    {
                        prop: 'userSex',
                        label: '性别',
                        minWidth: 50,
                        slotName: 'userSex'
                    },
                    {
                        prop: 'isValid',
                        label: '状态',
                        minWidth: 100,
                        slotName: 'status'
                    },
                    {
                        prop: '',
                        label: '操作',
                        width: 300,
                        slotName: 'handle'
                    },
                ],
                params: {
                    depId: '',
                    pathCode:'',
                    insId:''
                }
            }
        },
        watch:{
          'depId':function(nv,ov){
            this.params.depId = nv;
            this.params.pathCode = this.pathCode;
            this.params.insId = this.insId;
             if(this.$refs.table)
               this.$refs.table.searchHandle()
          }
        },
        created(){
        },
        mounted() {
            this.params.depId = this.depId;
            this.params.insId = this.insId;
            this.params.pathCode = this.$route.params.pathCode;
             if(this.$refs.table)
               this.$refs.table.searchHandle()
        },
        methods: {
            /**
             * 移除用户
             * @param  {[type]} data [description]
             * @return {[type]}      [description]
             */
            removeUserDep:function(userId){
                let _this = this;
                MessageBox.confirm('确定删除此记录吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
               standardAsync(_this,'removeAdminDepUser',{userId:userId,depId:_this.params.depId},res=>{
                   _this.$message.success('移除成功')
                   _this.$refs.table.searchHandle()

               });
              });
            },
            /**
             * 改变用户状态
             * @param  {[type]} status [description]
             * @return {[type]}        [description]
             */
            changeStatus:function(status,userId){
                standardAsync(this,'updateAdminUser',{userId:userId,isValid:status},res=>{
                   this.$message.success(status=='N'?'已启用':'已禁用')
                   this.$refs.table.searchHandle()

               });
             },
            }
        }
   

</script>

<style scoped>
  .el-tag:hover{
     cursor:pointer;
    }
</style>
