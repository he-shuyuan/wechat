<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="140px" class="demo-ruleForm">
        <uni-form-header title="公司基本信息">
            <el-form-item label="统一社会信用代码" prop="organizationCode">
                <el-input v-model="ruleForm.organizationCode" placeholder="请输入统一社会信用代码" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="公司名称" prop="insName">
                <el-input v-model="ruleForm.insName" placeholder="请输入公司名称" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="组织类型" prop="insStyle">
                <mc-select class="long-input" v-model="ruleForm.insStyle" placeholder="请选择组织类型" dcCodeMain="18"></mc-select>                
            </el-form-item>
            <el-form-item label="经营地址" prop="address">
                <el-input v-model="ruleForm.address" placeholder="请输入经营地址" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="经营范围" prop="remark">
                <el-input type="textarea" :autosize="{ minRows: 3,maxRows:5}" class="long-input" placeholder="请输入经营范围" v-model="ruleForm.remark">
                </el-input>
            </el-form-item>
            <el-form-item label="登记日期" prop="openDate">
                <el-date-picker class="long-input" v-model="ruleForm.openDate" type="date" placeholder="选择日期">
                </el-date-picker>
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
            <el-form-item label="开户名称" prop="openName">
                <el-input v-model="ruleForm.openName" placeholder="请输入开户名称" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="开户银行" prop="openBank">
                <el-input v-model="ruleForm.openBank" placeholder="请输入开户银行" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="银行账号" prop="bankNo">
                <el-input v-model="ruleForm.bankNo" placeholder="请输入银行账号" class="long-input"></el-input>
            </el-form-item>
            <el-form-item style="margin: 50px 0;">
                <el-button type="primary" class="long-button" @click="submitForm('ruleForm')">保存</el-button>
                <el-button @click="resetForm('ruleForm')" class="long-button">重置</el-button>
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
            var checkPhone = (rule, value, callback) => {
                if (value) {
                    var isPhone = /^([0-9]{3,4}(-)?)?[0-9]{7,8}$/;
                    var isMob = /^1[34578]\d{9}$/; 
                    if (isMob.test(value) || isPhone.test(value)) {
                        callback();
                    } else {
                        callback(new Error('请填写正确的号码格式'));
                    }
                } else {
                    callback(new Error('请填写联系电话'));
                }
            }
            var checkBankNo = (rule, value, callback) => {
                if (value) {
                    var pattern = /^\d{13,19}$/
                    if (pattern.test(value)) {
                        callback();
                    } else {
                        callback(new Error('请填写正确的银行账号'));
                    }
                } else {
                    callback(new Error('请填写银行账号'));
                }
            }
            return {
                ruleForm: {
                    organizationCode: '',
                    insName: '',
                    insStyle: '',
                    address: '',
                    remark: '',
                    openDate: '',
                    legal: '',
                    contact: '',
                    phone: '',
                    openName: '',
                    openBank: '',
                    bankNo: '',
                    typeId:'3',
                    busTypeId:'2'
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
                    insStyle: [{
                        required: true,
                        message: '请选择组织类型',
                        trigger: 'change'
                    }],
                    address: [{
                        required: true,
                        message: '请填写经营地址',
                        trigger: 'change'
                    }],
                    remark: [{
                        required: true,
                        max: 3000,
                        message: '填写公司/个体户经营范围，最长不能超过3000个字符',
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
                        validator: checkPhone,
                        // message: '请填写联系电话',
                        trigger: 'change'
                    }],
                    openName: [{
                        required: true,
                        message: '请填写开户名称',
                        trigger: 'change'
                    }],
                    openBank: [{
                        required: true,
                        message: '请填写开户银行',
                        trigger: 'change'
                    }],
                    bankNo: [{
                        required: true,
                        validator: checkBankNo,
                        // message: '请填写银行账号',
                        trigger: 'change'
                    }]
                },
            }
        },
        created() {},
        methods: {
            submitForm(formName) {
                console.log('this.ruleForm',this.ruleForm)
                let paramsName = this.$route.params.name
                let params = this.ruleForm
                params.currentInsId = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (paramsName == "addCompany") {
                            standardAsync(this, 'addInstitution', params, res => {
                                this.$message.success('提交成功');
                                this.$router.go(-1)
                            })
                        } else {
                            params.insId = this.$route.query.insId
                            standardAsync(this, 'modifyInstitution', params, res => {
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
                params.insId = this.$route.query.insId
                standardAsync(this, 'queryInstitutionById', params, res => {
                    Object.assign(this.ruleForm,res.body)
                })
            }
        },
        beforeRouteEnter(to, from, next) {
            // ...
            next(vm => {
                console.log(to)
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