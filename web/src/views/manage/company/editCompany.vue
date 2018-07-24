<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="140px" class="demo-ruleForm">
        <uni-form-header title="公司基本信息">
            <el-form-item label="统一社会信用代码" prop="organizationCode">
                <el-input v-model="ruleForm.organizationCode" placeholder="请输入统一社会信用代码" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="公司名称" prop="insName">
                <el-input v-model="ruleForm.insName" placeholder="请输入公司名称" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="简称" prop="insShortName">
                <el-input v-model="ruleForm.insShortName" placeholder="请输入公司简称" class="long-input"></el-input>
            </el-form-item>
             <el-form-item label="业务类型" prop="busTypeId">
                <mc-select class="long-input" v-model="ruleForm.busTypeId" placeholder="请选择业务类型" 
                  :propParams="{method:'queryBusTypeList',label:'busTypeName',value:'busTypeId'}"></mc-select>       
            </el-form-item>
            <el-form-item label="组织类型" prop="typeId">
                <mc-select class="long-input" v-model="ruleForm.typeId" placeholder="请选择组织类型" 
                  :propParams="{method:'queryInsTypeList',label:'name',value:'id'}"></mc-select>                
            </el-form-item>
            <el-form-item label="经营地址" prop="address">
                <el-input v-model="ruleForm.address" placeholder="请输入公司地址" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="法人代表" prop="legal">
                <el-input v-model="ruleForm.legal" placeholder="请输入法人代表" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="联系人" prop="contact">
                <el-input v-model="ruleForm.contact" placeholder="请输入联系人" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="phone">
                <el-input v-model="ruleForm.phone" placeholder="请输入联系电话" class="long-input"></el-input>
            </el-form-item>
             <el-form-item label="备注" prop="remark">
                 <el-input type="textarea" :autosize="{ minRows: 3,maxRows:5}" class="long-input" placeholder="备注" v-model="ruleForm.remark">
                </el-input>
            </el-form-item>
            <el-form-item style="margin: 50px 0;">
                <el-button type="primary" class="long-button" @click="submitForm('ruleForm')">保存</el-button>
                <el-button @click="()=>$router.go(-1)" class="long-button">返回</el-button>
            </el-form-item>
        </uni-form-header>
    </el-form>
</template>

<script>
    import uniFormHeader from '../../../components/uniFormHeader.vue'
    import util from "@/utils/"
    import {
        standardAsync,
        customAsync
    } from '@/api/async'
    export default {
        components: {
            uniFormHeader
        },
        data() {
            return {
                ruleForm: {
                    organizationCode: '',
                    insName: '',
                    address: '',
                    remark: '',
                    openDate: '',
                    legal: '',
                    contact: '',
                    phone: '',
                    openName: '',
                    openBank: '',
                    bankNo: '',
                    typeId:'',
                    busTypeId:''
                },
                rules: {
                    organizationCode: [{
                        required: true,
                        message: '请填写统一社会信用代码',
                        trigger: 'change'
                    }],
                    insName: [{
                        required: true,
                        message: '请填写公司名称',
                        trigger: 'change'
                    }],
                    typeId: [{
                        required: true,
                        message: '请选择组织类型',
                        trigger: 'change'
                    }],
                    busTypeId: [{
                        required: true,
                        message: '请选择业务类型',
                        trigger: 'change'
                    }],
                    openDate: [{
                        required: true,
                        message: '请选择登记日期',
                        trigger: 'change'
                    }],
                    legal: [{
                        required: true,
                        message: '请填写法人代表',
                        trigger: 'change'
                    }],
                    contact: [{
                        required: true,
                        message: '请填写联系人',
                        trigger: 'change'
                    }],
                    phone: [{
                        required: true,
                        message: '请填写联系电话',
                        trigger: 'change'
                    }],
                },
            }
        },
        created() {},
        methods: {
            submitForm(formName) {
                console.log('this.ruleForm',this.ruleForm)
                let paramsName = this.$route.params.name
                let params = this.ruleForm
                //params.currentInsId = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (paramsName == "addCompany") {
                            standardAsync(this, 'addInsAndDep', params, res => {
                                this.$message.success('提交成功');
                                this.$router.go(-1)
                            })
                        } else {
                            params.insId = this.$route.params.insId
                            console.log('form',this.ruleForm)
                            standardAsync(this, 'updateAdminInstitution', params, res => {
                                this.$message.success('提交成功');
                                this.$router.go(-1)
                            })
                        }
                    } else {
                        this.$message.error('提交失败');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            getCompanyInfo() {
                let params = {}
                params.insId = this.$route.params.insId
                standardAsync(this, 'queryAdminInstitutionById', {insId:this.$route.params.insId}, res => {
                    Object.assign(this.ruleForm,res.body)
                })
            }
        },
        beforeRouteEnter(to, from, next) {
            // ...
            next(vm => {
                if (to.params.name == "modifyCompany") {
                    to.meta.title = "修改公司信息"
                    vm.getCompanyInfo()
                } else {
                    to.meta.title = "新增公司"
                }
            })
        }
    }
</script>

<style lang="scss" scoped>
    @import '~@/styles/vueImport/index.scss';
</style>