<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
        <uni-form-header title="用户基本信息">
            <el-form-item label="用户名称" prop="userName">
                <el-input v-model="ruleForm.userName" placeholder="请输入" class="long-input"></el-input>
            </el-form-item>
             <el-form-item label="登录账号" prop="loginName">
                <el-input v-model="ruleForm.loginName" placeholder="请输入" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
                <el-input v-model="ruleForm.phone" placeholder="请输入" class="long-input"></el-input>
            </el-form-item>
            <el-form-item prop="depName" label="所在部门">
                <input-tree v-model="ruleForm.depName" class="long-input" @nodeSelect="nodeSelect" :load="reloadTree" placeholder="请选择所在部门"
                    :props="defaultProps" lazy node-key="depId"></input-tree>
            </el-form-item>
             <el-form-item prop="userSex" label="性别">
               <el-radio v-model="ruleForm.userSex" label="1" border>男</el-radio>
               <el-radio v-model="ruleForm.userSex" label="0" border>女</el-radio>
            </el-form-item>
            <el-form-item label="备注" prop="remark">
                <textarea class="long-input" style="height:70px" v-model="ruleForm.remark">
                </textarea>
            </el-form-item>
            <el-form-item style="margin: 50px 0;">
                <el-button type="primary" class="long-button" @click="submitForm('ruleForm')">保存</el-button>
                <el-button type="default" class="long-button" @click="$router.go(-1)">取消</el-button>
            </el-form-item>
        </uni-form-header>
    </el-form>
</template>

<script>
    import {
        standardAsync,
        customAsync
    } from '@/api/async'
    import uniFormHeader from '../../../components/uniFormHeader.vue'
    import util from "@/utils/"
    import inputTree from "./component/inputOfTree"
    export default {
        components: {
            uniFormHeader,
            inputTree
        },
        data() {
            return {
                init: {
                    busTypeList: [],
                    depIdList: [],
                    currentOp: ''
                },
                ruleForm: {
                    userName: '',
                    loginName:'',
                    phone: '',
                    userSex: '',
                    depId: '',
                    remark: '',
                    depName: ''
                },
                rules: {
                    userName: [{
                        required: true,
                        message: '用户名称不能为空',
                        trigger: 'change'
                    }],
                    loginName: [{
                        required: true,
                        min: 1,
                        max: 10,
                        message: '登录名称不能为空,少于10个字符',
                        trigger: 'change'
                    }],
                    phone: [{
                        required: true,
                        message: '手机号不能为空',
                        trigger: 'change'
                    }],
                    depName: [{
                        required: true,
                        message: '所在部门不能为空',
                        trigger: 'change'
                    }]
                },
                defaultProps: {
                    label: 'depName',
                    isLeaf: 'leaf'
                }
            }

        },
        beforeRouteEnter(to, from, next) {
            next(vm => {
              console.log(to.params)
            })
        },
        created() {

        },
        mounted() {
            
        },
        methods: {
            /**
             * 表单提交
             * @param  {[type]} key [description]
             * @return {[type]}     [description]
             */
            submitForm: function (key) {
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        this.ruleForm.insId = this.$route.params.insId;
                        standardAsync(this, 'addAdminUser', this.ruleForm, res => {
                            this.$message.success('保存成功');
                            this.$router.back()
                        });
                    } else {
                        console.log('error')
                        return false;
                    }
                });
            },
            /**
             * 加载数据节点
             * @param  {[type]} node    [description]
             * @param  {[type]} resolve [description]
             * @return {[type]}         [description]
             */
            reloadTree(node, resolve) {
                standardAsync(this, 'queryAdminDepTreeDTOList', {
                    parentDepId: node.data ? node.data.depId : '',
                    depId: this.$route.params.depId,
                    notShowLoading: true
                }, res => {
                    res.body.forEach(ob => {
                        if (ob.childNum > 0) {
                            ob.childNum = false;
                        } else {
                            ob.childNum = true;
                        }
                    })
                    resolve(res.body);
                });
            },
            /**
             * 部门ID及名称获取
             * @param  {[type]} data    [description]
             * @return {[type]}         [description]
             */
            nodeSelect(data) {
                this.ruleForm.depId = data.depId
                this.ruleForm.depName = data.depName
            }
        }
    }
</script>

<style scoped>
</style>