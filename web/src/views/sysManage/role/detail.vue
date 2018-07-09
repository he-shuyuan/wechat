<template>
    <el-form :model="ruleForm"
             :rules="rules"
             ref="ruleForm"
             label-width="120px"
             class="demo-ruleForm">
        <uni-form-header title="角色基本信息">
            <el-form-item label="角色名称"
                          prop="roleName"
                          >
                <el-input v-model="ruleForm.roleName"
                          placeholder="请输入"
                          class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="角色编码"
                          prop="roleCode">
                        <el-input v-model="ruleForm.roleCode"
                          placeholder="请输入"
                          class="long-input"></el-input>
            
            </el-form-item>
                    <el-form-item prop="busTypeId" label="业务类型">
                        <el-select v-model="ruleForm.busTypeId"
                                  placeholder="请选择">
                           <el-option v-for="(item,index) in init.busTypeList"
                                :key="index"
                                :label="item.busTypeName"
                                :value="item.busTypeId"></el-option>
                       </el-select> 
                    
                    </el-form-item>
                     <el-form-item prop="insTypeId" label="所属机构类型">
                        <el-select v-model="ruleForm.insTypeId"
                                  placeholder="请选择">
                           <el-option v-for="(item,index) in init.insTypeList"
                                :key="index"
                                :label="item.name"
                                :value="item.id"></el-option>
                       </el-select> 
                    </el-form-item>
                <el-form-item label="备注"
                          prop="remark">
                <textarea class="long-input"
                style="height:70px"
                v-model="ruleForm.remark">
                   </textarea>
            </el-form-item>
             <el-form-item style="margin: 50px 0;">
                <el-button type="primary"
                           class="long-button"
                           @click="submitForm('ruleForm')">保存</el-button>
                <el-button v-if="init.currentOp!='add'" type="default"
                           class="long-button"
                           @click="$router.push({name:'roleManage'})">取消</el-button>
            </el-form-item>
        </uni-form-header>
    </el-form>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import uniFormHeader from '../../../components/uniFormHeader.vue'
    import util from "@/utils/"
    export default {
        components: { uniFormHeader },
        data() {
            return {
                init:{
                    title:'新增合同',
                    busTypeList:[],
                    insTypeList:[],
                    currentOp:''
                },
                ruleForm:{
                  roleName:'',
                  roleId:'',
                  busTypeId:'',
                  insTypeId:'',
                  isValid:'',
                  remark:'',
                  roleCode:''
                },
                rules:{
                    roleName: [{ required: true,
                     message: '角色名称不能为空', trigger: 'change' }],
                    roleCode: [{ required: true,
                     message: '角色编码不能为空', trigger: 'change' }],
                    busTypeId:[{required:true,
                      message:'业务类型不能为空',trigger: 'change' }],
                    insTypeId: [{ required: true, 
                      message: '机构类型不能为空', trigger: 'change' }]
                }
            }
           
        },
        beforeRouteEnter (to, from, next) {
        next(vm => {
            if(to.params.roleId){
               vm.queryRoleById(to.params.roleId);
               vm.init.currentOp = 'edit';
               to.meta.title='修改角色'
            }else{
               vm.init.currentOp = 'add';
               to.meta.title='新增角色'
            }
        })
        },
        created(){
          
        },
        mounted() {
            this.initSelect();
        },
        methods: {
           /**
            * 初始化下拉框
            * @return {[type]} [description]
            */
           initSelect:function(){
            standardAsync(this,'queryBusinessList',{},res=>{
              this.init.busTypeList = res.body;
            });
            standardAsync(this,'queryInsTypeList',{},res=>{
              this.init.insTypeList = res.body;
            });
           },
           /**
            * 表单提交
            * @param  {[type]} key [description]
            * @return {[type]}     [description]
            */
           submitForm:function(key){
              if(this.init.currentOp == 'add'){
                this.ruleForm.roleId = null;
                standardAsync(this,'addRole',this.ruleForm,res=>{
                    this.$message.success('保存成功');
                    this.$router.push({name:'roleManage'})
                 });
              }else{
                  standardAsync(this,'modifyRole',this.ruleForm,res=>{
                    this.$message.success('修改成功');
                    this.$router.push({name:'roleManage'})
                 });
              }
           },
           /**
            * 获取角色详情
            * @param  {[type]} id [description]
            * @return {[type]}    [description]
            */
           queryRoleById:function(id){
            standardAsync(this,'queryRoleById',{roleId:id},res=>{
                 this.ruleForm = res.body;
                 }); 
           }
        }
        }
   

</script>

<style scoped>

</style>
