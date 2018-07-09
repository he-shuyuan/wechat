<template>

    <el-form :model="ruleForm"
             :rules="rules"
             ref="ruleForm"
             label-width="100px"
             class="demo-ruleForm">
        <uni-form-header title="合同基本信息">
            <el-form-item label="合同名称"
                          prop="contractName"
                          required>
                <el-input v-model="ruleForm.contractName"
                          placeholder="请输入合同名称"
                          class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="所属项目"
                          required
                          prop="projectId">
                        <el-select v-model="ruleForm.projectId"
                                   class="long-input"
                                   placeholder="请选择所属项目">
                           <el-option v-for="(item,index) in init.projectList"
                                       :key="index"
                                       :label="item.projectName"
                                       :value="item.projectId"></el-option>
                        </el-select>
            
            </el-form-item>
                    <el-form-item prop="contarctType" label="合同分类">
                    
                        <el-select v-model="ruleForm.contractType"
                                   class="long-input"
                                   placeholder="请选择合同类型" @change="contractTyleChange">
                            <el-option v-for="(item,index) in init.contractTypeList"
                                 :key="index"
                                 :label="item.dcname"
                                 :value="item.dcCode"></el-option>
                        </el-select>
                    
                    </el-form-item>
                     <el-form-item prop="contractMoney" label="合同金额">
                        <el-col :span="5">
                        <el-input v-model="ruleForm.contractMoney"
                                type="number"
                                class="long-input"
                                min="0"
                                placeholder="请输入金额">
                      <template slot="append">元</template>
                        </el-input>
                        </el-col>
                    </el-form-item>
            <el-form-item label="甲方单位"
                          prop="acompanyId">
                <el-select v-model="ruleForm.acompanyId"
                           class="long-input"
                           placeholder="请选择甲方单位"
                           clearable>
                   <el-option v-for="(item,index) in init.acompanyList"
                           :key="index"
                           :label="item.insName"
                           :value="item.insId"></el-option>
                </el-select>
            </el-form-item>
              <el-form-item label="对方单位"
                          prop="bcompanyId">
                <el-select v-model="ruleForm.bcompanyId"
                           class="long-input"
                           placeholder="请选择对方单位"
                           clearable>
                   <el-option v-for="(item,index) in init.bcompanyList"
                           :key="index"
                           :label="item.insName"
                           :value="item.insId"></el-option>
                </el-select>
            </el-form-item>
                <el-form-item label="备注"
                          prop="bz">
                <textarea class="long-input"
                style="height:70px"
                v-model="ruleForm.bz">
                            </textarea>
            </el-form-item>
            <el-form-item label="合同附件" v-if="init.isShowAttach">
            <File-upload :attachmentInfo="attachmentInfo"></File-upload>    
            </el-form-item>
             <el-form-item style="margin: 50px 0;">
                <el-button type="primary"
                           class="long-button"
                           @click="submitForm('ruleForm')">{{init.op}}</el-button>
            </el-form-item>
        </uni-form-header>
    </el-form>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import uniFormHeader from '../../../components/uniFormHeader.vue'
    import FileUpload from '../../../components/FileUpload.vue'
    import util from "@/utils/"
    import apiConfig from '@/api/config'
    export default {
        components: { uniFormHeader ,FileUpload},
        data() {
            return {
                init:{
                    title:'新增合同',
                    op:'保存',
                    projectList:[],
                    contractTypeList:[],
                    acompanyList:[],
                    bcompanyList:[],
                    isShowAttach:false
                },
                ruleForm:{
                  contractName:'',
                  projectId:'',
                  contractType:'',
                  contractMoney:'',
                  acompanyId:'',
                  bcompanyId:'',
                  bz:'',
                  companyId:''
                },
                attachmentInfo:{
                  attachmentList:[],
                  listType:'text/picture',
                  attachmentYwid:'',
                },
                rules:{
                    contractName: [{ required: true, min: 3, max: 30, message: '合同名称长度至少3个字符，最长30个字符', trigger: 'change' }],
                    contractMoney: [{ required: true, message: '根据项目合同的金额填写，价格必须是0.01-99999999999之间的数字。', trigger: 'change' }],
                    projectId:[{required:true,message:'项目不能为空',trigger: 'change' }],
                    acompanyId: [{ required: true, message: '请选择,甲方单位是指项目合同的业主方、总包方名称。甲方单位长度至少3个字符，最长100个字符', trigger: 'change' }],
                    bcompanyId: [{ required: true, message: '请选择对方单位长度至少3个字符，最长100个字符', trigger: 'change' }],
                }
            }
           
        },
        beforeRouteEnter (to, from, next) {
        next(vm => {
            vm.getProjectSelect();
            vm.getContractType();
            vm.queryInstitutionList();
           if(to.params.op && to.params.op == 'add'){
                 to.meta.title="新增合同"
                 vm.init.isShowAttach = false;
                 vm.init.op = "保存";
            }else{
                 to.meta.title="修改合同"
                 vm.init.isShowAttach = true;
                 vm.attachmentInfo.attachmentYwid=to.params.contractId;
                 vm.queryContractInfo(to.params.contractId);
                 vm.init.op = "提交修改";
                 vm.contractTyleChange();
            }
            vm.init.title = to.meta.title;
            
            
        })
        },
        created(){
          
        },
        mounted() {
            
        },
        methods: {
          /**
           * 添加合同
           * @return {[type]} [description]
           */
            addContract:function(){
                  this.ruleForm.companyId = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID);
                  standardAsync(this,'addContract'
                    ,this.ruleForm
                    ,res=>{
                    this.$message.success('保存成功');
                    this.$router.push({name:'contract'})
                })
            },
            /**
             * 修改合同
             * @return {[type]} [description]
             */
           updateContract:function(){
                  this.ruleForm.companyId = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID);
                  standardAsync(this,'updateContract'
                    ,this.ruleForm
                    ,res=>{
                    this.$message.success('修改成功');
                    this.$router.push({name:'detail',
                      query:{contractId:this.ruleForm.contractId}})
                })
            },
            /**
             * 获取项目列表
             * @return {[type]} [description]
             */
          getProjectSelect:function(){
                standardAsync(this,'queryCompanyProject'
                    ,{companyId:util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)}
                    ,res=>{
                      this.init.projectList = res.body || [];
              });
            },
          /**
             * 获取项目类型
             * @return {[type]} [description]
             */
          getContractType:function(){
              standardAsync(this,'getDicList',{dcCodeMain:'06'},res=>{
                this.init.contractTypeList = res.body || [];
              });
          },
          /**
           * 查询企业列表
           * @return {[type]} [description]
           */
          queryInstitutionList:function(type){
           if(this.ruleForm.contractType =='0601'){
              standardAsync(this,'queryInstitutionList',
                {currentInsId:util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)
                  ,insStyle:'1801'},res=>{
                 this.init.acompanyList = res.body || [];
              });
           }else{
              standardAsync(this,'queryInstitutionList',
                {currentInsId:util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)},res=>{
                 this.init.bcompanyList = res.body || [];
              });
           }
             
          },
          /**
           * 合同类型变化
           * @return {[type]} [description]
           */
          contractTyleChange:function(){
            this.ruleForm.acompanyId = '';
            this.ruleForm.bcompanyId = '';
            if(this.ruleForm.contractType =='0601'){//总包
                this.init.bcompanyList = [
                {insId:util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID),
                insName:util.sStore.getItem(util.sStore.MACE_SELECED_INS_NAME)}]
            }else{//分包、租赁
                 this.init.acompanyList = [
                 {insId:util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID),
                  insName:util.sStore.getItem(util.sStore.MACE_SELECED_INS_NAME)}]
          
            }
            this.queryInstitutionList(this.ruleForm.contractType);
          },
          /**
           * 提交
           * @param  {[type]} key [description]
           * @return {[type]}     [description]
           */
          submitForm:function(key){
               this.$refs[key].validate((valid) => {
                  if (valid) {
                        if(this.init.op == '保存'){
                         this.addContract();
                        }else{
                          this.updateContract();
                        }
                    } else {
                        this.$message.error('提交失败');
                    }
                });
          },
          /**
           * 获取合同详情
           * @return {[type]} [description]
           */
          queryContractInfo:function(id){
            standardAsync(this,'queryContractInfo'
                    ,{contractId:id}
                    ,res=>{
               this.ruleForm=res.body;
               this.attachmentInfo.attachmentList=res.body.attachmentList.map(function(item){
                        return{
                            name:item.attachmentName,
                            url:apiConfig.wxImgvUrl+item.attachmentVfsPath
                        }
                    });
                })
            },
        }
        }
   

</script>

<style scoped>

</style>
