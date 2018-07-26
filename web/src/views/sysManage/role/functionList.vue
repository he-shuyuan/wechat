<template>
 
<el-container  class="_body">
  <!-- <el-header>当前角色:<span style="color:red">{{init.roleName}}</span></el-header> -->
  <el-container>
  <el-aside style="width:80%;height:400px">  
  <el-tag style="width:100%;text-align:center">权限菜单</el-tag>
   <el-tree 
    show-checkbox 
    node-key="functionId" 
    :load="reloadTree"
    ref="tree"
    check-strictly
    :highlight-current="true"
    lazy
    :default-expanded-keys="defaultExpand"
    :default-checked-keys="defaultSelect"
    :props="defaultProps">
    </el-tree>
    </el-aside>
     <el-footer><el-button type="danger" @click.native="confirmDist">确认授权</el-button></el-footer>
     </el-container>
   <el-main>当前角色:<span style="color:red">{{$route.params.roleName}}</span>
   </el-main>
</el-container>
</template>
<script>
import { standardAsync, customAsync } from '@/api/async'
import { Message, MessageBox} from 'element-ui';
import config from '@/api/config'
import util from "@/utils/"
export default {
    data() {
        return {
            init:{
                roleId:'',
                roleName:'未选择',
            },
            defaultSelect:[],
            defaultExpand:[],
            defaultProps: { 
                label: 'functionName',
                isLeaf:'leaf'
            }
        };
    },
    beforeRouteEnter (to, from, next) {
          next(vm => {
         /*   vm.init.roleId = to.params.roleId;
            vm.init.roleName = to.params.roleName;*/
          })
      },
    mounted(){

    },
    methods:{
         /**
         * 加载数据节点
         * @param  {[type]} node    [description]
         * @param  {[type]} resolve [description]
         * @return {[type]}         [description]
         */
        reloadTree(node,resolve){
           standardAsync(this,'queryAdminRoleFunDTOTreeList',
            {busTypeId:this.$route.params.busTypeId
            ,parentId:node.data?node.data.functionId:"",
            roleId:this.$route.params.roleId
            ,notShowLoading:true},res=>{
               res.body.forEach(ob=>{
                  if(!ob.leaf){
                     this.defaultExpand.push(ob.functionId)
                  }
                  if(ob.hasSelected){
                    this.defaultSelect.push(ob.functionId);
                  }
                })
                resolve(res.body);
           })
        },
        /**
         * 确认授权
         * @return {[type]} [description]
         */
        confirmDist:function(){
            let _this = this;
              MessageBox.confirm('确定要改变角色菜单权限吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
            standardAsync(_this,'addAuthFunctionList',{roleId:_this.$route.params.roleId,functionIdList:_this.$refs.tree.getCheckedKeys()},res=>{
                 _this.$message.success('授权成功')
                _this.$router.push({name:'roleManage'})
            });
          });
        }
    }
};
</script>
<style scoped>
.el-header{
  border-bottom:solid 1px #e4e7ed; 
  padding: 18px;
  text-align: center;
  border-bottom: 1px solid #fff;
  font-weight: bold;
  background: #e4e7ed;
}
._body{
    margin:0 3% 0 3%;
}
.el-tree{
  max-height: 400px;
  border: 1px solid #e4e7ed;
}
.el-main{
  border:solid 1px #e4e7ed;
  text-align: center;
} 
</style>