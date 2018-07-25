<template>
    <mc-table ref="table" stripe :columns="columns" method="pageQueryAdminUserDTOList" :params="params" showAdd addBtnText="新增用户" 
    :btnData="{btnStyle:'text-align:right;padding-top:20px;margin-right:20px'}"
    @addEvent="$router.push({name:'userAdd',params:{depId:params.depId}})">
       <template slot-scope="scope" slot="nickName">
       <router-link :to="{name:'userDetail',params:{data:scope.row}}">
            {{scope.row.ucUserDTO.nickName}}
        </router-link>
       </template> 
       <template slot-scope="scope" slot="empNum">
        {{scope.row.ucUserDTO.empNum}}
       </template> 
        <template slot-scope="scope" slot="sex">
        {{scope.row.ucUserDTO.sex}}
       </template> 
       <template slot-scope="scope" slot="status">
          <el-tag v-if="scope.row.ucUserDTO.status=='Y'">已禁用</el-tag>
          <el-tag v-else>已启用</el-tag>
       </template> 
       <template slot-scope="scope" slot="handle">
        <el-button type="primary" size="mini"
         @click.native="$router.push({name:'userRole',params:{depId:params.depId,userId:scope.row.ucUserDTO.userId,userName:scope.row.ucUserDTO.nickName}})">角色分配</el-button>
        <el-button v-if="scope.row.ucUserDTO.status=='Y'" 
           type="warning" size="mini"  @click.native="changeStatus('N',scope.row.ucUserDTO.userId)">启用</el-button>
        <el-button v-else type="warning" size="mini" @click.native="changeStatus('Y',scope.row.ucUserDTO.userId)">禁用</el-button>
        <el-button type="danger" size="mini" @click.native="removeUserDep(scope.row.ucUserDTO.userId)">移除</el-button>
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
           isDist:'0'
        },
        data() {
            return {
                init:{
                   contractTypeMap:{},
                },
                formOpt: [{
                        label: '用户名称',
                        col:8,
                        type: 'text',
                        prop: 'nickName',
                        placeholder: '请输入',
                      
                    },
                    {
                        label: '性别',
                        col:8,
                        type: 'select',
                        prop: 'sex',
                        placeholder: '请选择',
                        initData: [{label:'男',value:'男'},
                                   {label:'女',value:'女'}]
                    },
                     {
                        col:2,
                        label: '',
                        type: 'hidden'
                    },
                    {
                        col:6,
                        label: '',
                        type: 'search'
                    },
                    {
                        label: '工号',
                        col:8,
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'empNum',
                    },
                    {
                        label: '范围',
                        col:8,
                        type: 'select',
                        prop: 'optionsRadios',
                        placeholder: '请选择',
                        initData: [{value:'0',label:'仅当前部门'},
                                   {value:'1',label:'当前部门及其下级'}]
                    },
                    {
                        col:2,
                        label: '',
                        type: 'hidden'
                    },
                    {
                        col:6,
                        label: '',
                        type: 'reset',
                    },
                ],
                columns: [
                     {
                        prop: 'nickName',
                        label: '用户名称',
                        minWidth: 200,
                        slotName: 'nickName'
                    },
                     {
                        prop: 'empNum',
                        label: '工号',
                        minWidth: 200,
                        slotName: 'empNum'
                        
                    },
                    {
                        prop: 'sex',
                        label: '性别',
                        minWidth: 50,
                        slotName: 'sex'
                    },
                    {
                        prop: 'status',
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
                    depId: util.sStore.getItem(util.sStore.MACE_SELECED_DEP_ID),
                    isValid:'Y',
                    pathCode:'',
                    isDist:'0'
                }
            }
        },
        watch:{
          'depId':function(nv,ov){
            this.params.depId = nv;
             if(this.$refs.table)
               this.$refs.table.searchHandle()
          }
        },
        created(){
            this.params.depId = this.depId;
            this.params.pathCode = this.$route.params.pathCode;
             if(this.$refs.table)
               this.$refs.table.searchHandle()
        },
        mounted() {
           
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
               standardAsync(_this,'removeUserDep',{userId:userId,depId:_this.params.depId},res=>{
                   _this.$message.success('移除成功')
                   _this.$refs.table.searchHandle()

               });
              });
            },
            /**
             * 改变机械状态
             * @param  {[type]} status [description]
             * @return {[type]}        [description]
             */
            changeStatus:function(status,userId){
                standardAsync(this,'modifyUser',{userId:userId,status:status},res=>{
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
