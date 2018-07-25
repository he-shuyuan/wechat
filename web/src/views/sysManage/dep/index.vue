<template>
<router-view v-if="$route.name !== thisRouteName"></router-view>
<el-container v-else class="_body">
  <el-header>用户管理</el-header>
  <el-container>
    <el-aside width="200px">
      <el-tag class="_menu_op">部门层级</el-tag>
      <el-tag  class="_menu_op"><span style="color:black">已选择：</span>
      {{($refs.tree && $refs.tree.getCurrentNode())?$refs.tree.getCurrentNode().depName:(init.defaultName || '未选择')}}</el-tag>
      <div  class="_menu_op">
      <el-button type="primary" size="mini" @click.native="addDep">新增</el-button>
      <el-button type="warning" size="mini" @click.native="modifyDep">修改</el-button>
      <el-button type="danger" size="mini" @click.native="delDep">删除</el-button>
      </div>
      <el-tree 
       :load="reloadTree"
       :props="defaultProps"
       ref="tree"
       lazy
       node-key="depId"
       highlight-current
       :default-checked-keys="init.defaultDepId"
       @node-click="selectNode">
    </el-tree>
    </el-aside>
    <el-main style="padding:0">
         <user-manager :depId="init.depId" ></user-manager>
    </el-main>
  </el-container>
</el-container>
</template>
<script>
import { standardAsync, customAsync } from '@/api/async'
import { Message, MessageBox} from 'element-ui';
import userManager from './userManager';
import config from '@/api/config'
import util from "@/utils/"
export default {
  components:{
      userManager
  },
    data() {
        return {
          thisRouteName:'userManage',
          init:{
             depId:util.sStore.getItem(util.sStore.MACE_SELECED_DEP_ID),
             defaultName:null,
          },
          status:{
             currentNode:'',
             currentNodeName:'无'
          },
          defaultProps: {
                label: 'depName',
                isLeaf:'leaf'
          }
        };
    },
    beforeRouteEnter (to, from, next) {
        next(vm => {
          vm.status.currentNode='';
          vm.status.currentNodeName = '无'
        })
    },
    mounted(){
     
    },
    methods:{
        /**
         * 选择节点
         * @param  {[type]} a [description]
         * @param  {[type]} b [description]
         * @param  {[type]} c [description]
         * @return {[type]}   [description]
         */
        selectNode:function(data,node,c){
          this.status.currentNode = node;
          if(node.data){
            this.status.currentNodeName = node.data.depName;
            console.log(node.data.depId)
            this.init.depId = node.data.depId;
           } 
        },
        /**
         * 加载数据节点
         * @param  {[type]} node    [description]
         * @param  {[type]} resolve [description]
         * @return {[type]}         [description]
         */
        reloadTree(node,resolve){
           standardAsync(this,'queryAdminDepTreeDTOList',
            {parentDepId:node.data?node.data.depId:'',
            depId:'1'
            ,notShowLoading:true},res=>{
                if(!node.data){
                  this.initSelect();
                }
                resolve(res.body);
           });
        },
        /**
         * 修改部门
         * @return {[type]} [description]
         */
        modifyDep:function(){
          console.log('modify',this.$refs.tree.getNode(this.$refs.tree.getCurrentNode()))
          if(!this.status.currentNode.data){
              this.$message.warning("请先选择一个部门");
              return;
            }
            this.$router.push({name:'depDetail',
              params:{depId:this.status.currentNode.data.depId,
                      data:this.status.currentNode.data,
                      parentData:this.status.currentNode.parentNode?this.status.currentNode.parentNode.data:''}})
        },
         /**
          * 初始化业务类型
          * @return {[type]} [description]
          */
         initSelect:function(){
            setTimeout(()=>{
                this.$refs.tree && this.$refs.tree.setCurrentKey("1")

                if(this.$refs.tree && this.$refs.tree.getCurrentNode()){
                   this.init.defaultName = this.$refs.tree.getCurrentNode().depName;
                   this.status.currentNode=this.$refs.tree.getNode(this.$refs.tree.getCurrentNode());
                   this.init.depId = this.$refs.tree.getCurrentNode().depId;
                }
               },10);
          },
          /**
           * 新增部门
           */
          addDep:function(){
            if(!this.status.currentNode.data){
              this.$message.warning("请先选择一个部门");
              return;
            }
            this.$router.push({name:'depDetail',
              params:{data:this.status.currentNode.data,
                      parentData:this.status.currentNode.parentNode?this.status.currentNode.parentNode.data:''}})
          },
          /**
           * 添加节点
           * @param  {[type]} node [description]
           * @param  {[type]} data [description]
           * @return {[type]}      [description]
           */
         append(node,data) {
          data.childNum=true;
          this.$refs.tree.append(data,node);
          node.isLeaf = false;
          },
          /**
           * 删除
           * @return {[type]} [description]
           */
          delDep:function(){
             if(!this.status.currentNode){
              this.$message.warning("请先选择一个部门");
              return;
            }
            if(!this.status.currentNode.isLeaf){
              this.$message.warning("请先删除子部门");
              return;
            }
             let _this = this;
              MessageBox.confirm('确定删除该部门吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
            standardAsync(_this,'delDepartment',
              {depId:_this.status.currentNode.data.depId},res=>{
                 _this.$refs.tree.remove(_this.status.currentNode);
                 _this.$message.success("删除成功");
                 _this.status.currentNode='';
                 _this.status.currentNodeName = '无'
              });
          });
         },

    }
};
</script>
<style scoped>
.el-header{
  border-bottom:solid 1px #e4e7ed; 
  padding: 18px;
  text-align: center;
  border-bottom: 1px solid #fff;
  background: #e4e7ed;
  font-weight: bold;
}
._body{
    margin:0 3% 0 3%;
}
.el-aside{
 border:solid 1px #e4e7ed;
 height:450px;
}
.el-main{
  border:solid 1px #e4e7ed;
  height:450px;
}
._menu_op{
  width:100%;
  border:solid 1px #e4e7ed;
  height:48px;
  line-height: 48px;
  text-align:center;
  background-color: #fff;
}
.el-button--mini {
    padding: 4px 10px;
}
._input_width {
  width:70%;
}
</style>