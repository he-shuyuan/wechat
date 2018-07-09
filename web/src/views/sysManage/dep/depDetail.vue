<template>
  
    <el-form :model="ruleForm"
             
             ref="ruleForm"
             label-width="100px"
             class="demo-ruleForm">
        <uni-form-header title="部门基本信息">
            <el-row>
            <el-col :span="12">
            <el-form-item label="上级部门名称"
                          prop="parentDepId">
                <el-input v-model="init.parentName"
                          placeholder="请输入"
                           :disabled="true"></el-input>
            </el-form-item>
            </el-col>
            <el-col :span="12">
             <el-form-item label="所属机构"
                          prop="insId">
                <el-input v-model="init.insName"
                          placeholder="请输入"
                           :disabled="true"></el-input>
            </el-form-item>
            </el-col>
            </el-row>
             <el-form-item label="部门名称"
                          prop="depName" required>
                <el-input v-model="ruleForm.depName"
                          placeholder="请输入"
                          class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="邮箱"
                          prop="email" >
                <el-input v-model="ruleForm.email"
                          placeholder="请输入"
                          class="long-input"></el-input>
            </el-form-item>
             <el-form-item label="电话"
                          prop="phone" >
                <el-input v-model="ruleForm.phone"
                          placeholder="请输入"
                          class="long-input"></el-input>
            </el-form-item>
             <el-form-item label="地址"
                          prop="address" >
                <el-input v-model="ruleForm.address"
                          placeholder="请输入"
                          class="long-input"></el-input>
            </el-form-item>
                <el-form-item label="备注"
                          prop="remark">
                <textarea class="long-input"
                   style="height:70px"
                   v-model="ruleForm.remark">
                </textarea>
            </el-form-item>
            <el-row>
            <el-col :span="12">
              <el-input v-model="ruleForm.parentDepId"
                       placeholder="请输入"
                       type="hidden"></el-input>
            </el-col>
            <el-col :span="12">
              <el-input v-model="ruleForm.insId"
                      placeholder="请输入"
                      type="hidden"></el-input>
            </el-col>
            </el-row>
             <el-form-item style="">
                <el-button type="primary"
                           class="long-button"
                           @click="submitForm()">{{init.op}}</el-button>
                <el-button type="default" class="long-button" @click="$router.push({name:'userManage'})">取消</el-button>
            </el-form-item>

        </uni-form-header>
    </el-form>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import uniFormHeader from '../../../components/uniFormHeader.vue' 
    import util from "@/utils/"
    import apiConfig from '@/api/config'
    export default {
      components: { uniFormHeader },
        data() {
            return {
              init:{
                 parentName:'',
                 insName:'',
                 op:'新增',
                 currentTreeNode:''
              },
                ruleForm:{
                 insId:'',
                 parentDepId:'',
                 pathCode:'',
                 email:'',
                 phone:'',
                 address:'',
                 remark:''
                },
                rules:[{}],
            }
           
        },
        beforeRouteEnter (to, from, next) {
          next(vm => {
           /* if(!to.params.node){
              return;
            }*/
            if(!to.params.depId){
              console.log(to.params)
              vm.init.op = '新增'
              to.meta.title='新增部门'
              vm.ruleForm.insId = to.params.data.insId;
              vm.init.insName = to.params.data.insName;
              vm.ruleForm.parentDepId = to.params.data.depId;
              vm.init.parentName = to.params.data.depName;
              vm.ruleForm.pathCode = to.params.data.pathCode;
              //vm.init.currentTreeNode = to.params.node;
            }else{
              vm.init.op = '修改'
              to.meta.title='修改部门'
              vm.init.insName = to.params.data.insName;
              debugger;
              if(to.params.parentData){
              vm.init.parentName = to.params.parentData.depName;
              }else{
                vm.init.parentName = '无上级'  
              }
              vm.queryDepDetailById(to.params.depId);
            }
          })
        },
        created(){
          
        },
        mounted() {
        
        },
        methods: {
          /**
           * 提交表单
           * @return {[type]} [description]
           */
          submitForm:function(){
             if(this.ruleForm.depId){//修改
               this.modifyDepartment();
             }else{//新增
               this.addDepartment();
             }
          },
          /**
           * 添加部门
           */
          addDepartment:function(){
              standardAsync(this,'addDepartment',this.ruleForm,res=>{
                     this.$message.success("添加成功"); 
                     this.$router.push({name:'userManage'})
              });
          },
          /**
           * 修改部门
           * @return {[type]} [description]
           */
           modifyDepartment:function(){
              standardAsync(this,'modifyDepartment',this.ruleForm,res=>{
                     this.$message.success("修改成功"); 
                     this.$router.push({name:'userManage'})
              });
          },
          /**
           * 通过id获取部门信息
           * @return {[type]} [description]
           */
          queryDepDetailById:function(id){
                standardAsync(this,'querySecDepartmentById',{depId:id},res=>{
                this.ruleForm = res.body;
              });
          },
        
          }
        }
   

</script>

<style scoped>
.el-tag{
  width:150px;
  text-align: center;
}
.el-tag:hover{
  background-color: green
}
._header{
 height:48px;
 width:100%;
 text-align: right;
}
._edit_link{
  display:block;
  margin:10px 10px 10px 10px;
} 
._body table td,._body table th{
  border-right: 1px solid #ebeef5;
  border-bottom:  1px solid #ebeef5;
  text-align: center
}
._body table{
    width:100%;
    height:500px;
    margin-bottom: 50px;
    border-top: 1px solid #ebeef5;
    border-left: 1px solid #ebeef5;
}
._body table td:nth-child(odd){
  width:15%;
}
._body table td:nth-child(even){
  width:35%;
}
._title_header{
  width:100%;
  padding: 18px;
  text-align: center;
  border-bottom: 1px solid #fff;
  background: #e4e7ed;
  font-weight: bold;
}

</style>
