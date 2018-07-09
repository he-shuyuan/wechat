<template>
    <router-view v-if="$route.name !== thisRouteName"></router-view>
    <mc-table v-else ref="table" stripe :formOpt="formOpt" :columns="columns" method="queryContract" :params="params" showAdd addBtnText="新增合同" 
    @addEvent="$router.push({name:'addOrUpdate',params:{op:'add'}})">
         <template slot-scope="scope" slot="contractName">
           <router-link style="color: #3366FF;" :to="{name:'detail',params:{contractId:scope.row.contractId}}">
                       {{scope.row.contractName}} </br> {{scope.row.contractId}}
           </router-link>
       </template>
      <template slot-scope="scope" slot="handle">
            <el-button type="danger" size="mini" @click.native="delContract(scope.row.contractId)" >删除</el-button>
              <el-button v-if="scope.row.contractType !='0601'" type="success" size="mini"
              @click.native="$router.push({name:'progress',params:{contractId:scope.row.contractId}})">
                进度款申报
              </el-button>
       </template> 
    </mc-table>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    import util from "@/utils/"
    export default {
        data() {
            return {
                thisRouteName:'contract',
                init:{
                   contractTypeMap:{},
                },
                formOpt: [{
                        label: '合同类型',
                        col:8,
                        type: 'select',
                        prop: 'contractType',
                        placeholder: '请选择合同类型',
                        initData: [],
                    },
                    {
                        label: '所属项目',
                        col:8,
                        type: 'select',
                        prop: 'projectId',
                        placeholder: '请选择所属项目',
                        initData: []
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
                        label: '合同名称',
                        col:8,
                        type: 'text',
                        placeholder: '请输入合同名称',
                        prop: 'contractName',
                    },
                    {
                        label: '合同编号',
                        col:8,
                        type: 'text',
                        placeholder: '请输入合同编号',
                        prop: 'contractId',
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
                columns: [{
                        prop: '',
                        label: '合同名称',
                        minWidth: 200,
                        slotName: 'contractName'
                    },
                    {
                        prop: 'contractType',
                        label: '合同类型',
                        minWidth: 100,
                           render: row => {
                             return this.transformContractType(row.contractType)
                           }
                    },
                    {
                        prop: 'projectName',
                        label: '所属项目',
                        minWidth: 200,
                        
                    },
                    {
                        prop: '',
                        label: '操作',
                        align:'left',
                        width: 300,
                        slotName: 'handle'
                    },
                ],
                params: {
                    brandId: '1'
                }
            }
        },
        created(){
          
        },
        mounted() {
            this.getContractType();
            this.getProjectSelect();
        },
        methods: {
            /**
             * 获取项目类型
             * @return {[type]} [description]
             */
            getContractType:function(){
              standardAsync(this,'getDicList',{dcCodeMain:'06'},res=>{
                res.body.forEach(ob=>{
                    this.init.contractTypeMap[ob.dcCode]=ob.dcname;
                    this.formOpt[0].initData.push({value:ob.dcCode,label:ob.dcname})
                })
              });
            },
            /**
             * 获取项目列表
             * @return {[type]} [description]
             */
            getProjectSelect:function(){
                standardAsync(this,'queryCompanyProject'
                    ,{companyId:util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)}
                    ,res=>{
                 res.body.forEach(ob=>{
                    this.formOpt[1].initData.push({value:ob.projectId,label:ob.projectName})
                })
              });
            },
            /**
             * 字典转换
             * @param  {[type]} key [description]
             * @return {[type]}     [description]
             */
            transformContractType:function(key){
               return this.init.contractTypeMap[key];
            },
            /**
             * 删除合同
             * @param  {[type]} id [description]
             * @return {[type]}    [description]
             */
            delContract:function(id){
             let _this = this;
              MessageBox.confirm('确定删除此记录吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    standardAsync(_this, 'delContract', {"contractId":id}, res => {
                         _this.$notify({
                            title: '提示',
                            message: '删除成功',
                            type: 'success',
                            duration: 2000
                        });
                        _this.$refs.table.searchHandle()
                    })
                })
            },
            }
        }
   

</script>

<style scoped>
  .el-tag:hover{
     cursor:pointer;
    }
</style>
