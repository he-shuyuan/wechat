<template>
  <el-container>
  <el-header>
   <span>当前用户：{{$route.params.userName}}</span>    
   <el-tag @click.native="$router.go(-1)" class="_back">返回</el-tag>
  </el-header>
  <el-main>
    <el-tabs type="border-card">
      <el-tab-pane label="已分配">
       <mc-table ref="recyleTable" stripe :formOpt="formOpt" :columns="columns" 
       type="selection"
       method="pageQuerySecUserRoleList" :params="{isDist:'0'
       ,depId:$route.params.depId,
       userId:$route.params.userId}" showAdd addBtnText="批量回收"
        :btnData="{addBtnStyle:'background-color:#f56c6c'}"
        @addEvent="recyleRole(init.recyleSelectList)"
        @selection-change="recyleSelectList"
        >
        <template slot-scope="scope" slot="handle">
           <el-button type="danger" size="mini" @click.native="recyleRole([scope.row.roleId])">回收</el-button> 
     </template> 
    </mc-table>
      </el-tab-pane>
      <el-tab-pane label="未分配">
          <mc-table ref="addTable" stripe :formOpt="formOpt" :columns="columns" 
           type="selection"
       method="pageQuerySecUserRoleList" :params="{isDist:'1',depId:$route.params.depId,
       userId:$route.params.userId}" showAdd addBtnText="批量分配"
       @addEvent="addRole(init.addSelectList)"
       @selection-change="addSelectList"> 
      <template slot-scope="scope" slot="handle">
           <el-button type="primary" size="mini" 
           @click.native="addRole([scope.row.roleId])">分配</el-button> 
       </template> 
    </mc-table>
      </el-tab-pane>
   </el-tabs>
   </el-main>
   </el-container>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    export default {
        data() {
            return {
                thisRouteName:'roleManage',
                init:{
                   recyleSelectList:[],
                   addSelectList:[]
                },
                formOpt: [
                    {
                        label: '角色名称',
                        col:8,
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'roleName',
                    },
                    {
                        label: '角色编码',
                        col:8,
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'roleCode',
                    },
                    {
                        col:2,
                        label: '',
                        type: 'hidden',
                    },
                    {
                        col:2,
                        label: '',
                        type: 'search',
                    },
                    {
                        col:2,
                        label: '',
                        type: 'reset',
                    },
                ],
                columns: [{
                        prop: 'roleName',
                        label: '角色名称',
                        minWidth: 200
                    },
                    {
                        prop: 'roleCode',
                        label: '角色编码',
                        minWidth: 200,
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
             * 批量回收角色
             * @param  {[type]} list [description]
             * @return {[type]}      [description]
             */
            recyleRole:function(list){
                if(!list || list.length == 0){
                    this.$message.warning('请先选择角色');
                    return;
                }
                let _this = this;
                  MessageBox.confirm('确定要回收当前所选角色吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                     standardAsync(_this,'banthRecycleUserRole',{userId:_this.$route.params.userId,
                      roleIdList:list},res=>{
                         _this.$message.success('回收成功');
                         _this.$refs.recyleTable.searchHandle();
                         _this.$refs.addTable.searchHandle();
                     });
                });
            },
            /**
             * 批量分配角色
             * @param  {[type]} list [description]
             * @return {[type]}      [description]
             */
            addRole:function(list){
                if(!list || list.length == 0){
                    this.$message.warning('请先选择角色');
                    return;
                }
                let _this = this;
                  MessageBox.confirm('确定要分配当前所选角色吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                     standardAsync(_this,'banthAddUserRole',{userId:_this.$route.params.userId,
                      roleIdList:list},res=>{
                         _this.$message.success('分配成功');
                         _this.$refs.addTable.searchHandle();
                         _this.$refs.recyleTable.searchHandle();
                     });
                });
            },
            /**
             * 回收选择
             * @param  {[type]} data [description]
             * @return {[type]}      [description]
             */
            recyleSelectList:function(data){
               this.init.recyleSelectList = [];
               data.forEach((ob)=>{
                this.init.recyleSelectList.push(ob.roleId);
               });
            },
            addSelectList:function(data){
                this.init.addSelectList = [];
               data.forEach((ob)=>{
                this.init.addSelectList.push(ob.roleId);
               });
            },
            }
        }
   

</script>

<style scoped>
.el-tag:hover{
     cursor:pointer;
}
.el-header{
  border-bottom:solid 1px #e4e7ed; 
  padding: 18px;
  text-align: center;
  border-bottom: 1px solid #fff;
  background: #e4e7ed;
  font-weight: bold;
}
._back{
  width:120px;
  text-align: center;
  cursor:pointer;
  float:right;
}
._back:hover{
  background-color: green;
}
</style>
