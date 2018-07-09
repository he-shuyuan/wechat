<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
        <uni-form-header title="用户基本信息">
            <el-form-item label="用户名称" prop="nickName">
                <el-input v-model="ruleForm.nickName" placeholder="请输入" class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phone">
                <el-input v-model="ruleForm.phone" placeholder="请输入" class="long-input"></el-input>
            </el-form-item>
            <el-form-item prop="empNum" label="工号">
                <el-input v-model="ruleForm.empNum" placeholder="请输入" class="long-input"></el-input>
            </el-form-item>
            <el-form-item prop="depName" label="所在部门">
                <input-tree v-model="ruleForm.depName" class="long-input" @nodeSelect="nodeSelect" :load="reloadTree" placeholder="请选择所在部门"
                    :props="defaultProps" lazy node-key="depId"></input-tree>
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
                    title: '新增合同',
                    busTypeList: [],
                    depIdList: [],
                    currentOp: ''
                },
                ruleForm: {
                    nickName: '',
                    phone: '',
                    empNum: '',
                    depId: '',
                    remark: '',
                    depName: ''
                },
                rules: {
                    nickName: [{
                        required: true,
                        message: '用户名称不能为空',
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
                    isLeaf: 'childNum'
                }
            }

        },
        beforeRouteEnter(to, from, next) {
            next(vm => {

            })
        },
        created() {

        },
        mounted() {
            this.initSelect();
        },
        methods: {
            /**
             * 初始化下拉框
             * @return {[type]} [description]
             */
            initSelect: function () {
                standardAsync(this, 'queryBusinessList', {}, res => {
                    this.init.busTypeList = res.body;
                });
                standardAsync(this, 'queryInsTypeList', {}, res => {
                    this.init.insTypeList = res.body;
                });
            },
            /**
             * 表单提交
             * @param  {[type]} key [description]
             * @return {[type]}     [description]
             */
            submitForm: function (key) {
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        this.ruleForm.insId = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID);
                        standardAsync(this, 'addUser', this.ruleForm, res => {
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
             * 获取角色详情
             * @param  {[type]} id [description]
             * @return {[type]}    [description]
             */
            queryRoleById: function (id) {
                standardAsync(this, 'queryRoleById', {
                    roleId: id
                }, res => {
                    this.ruleForm = res.body;
                });
            },
            /**
             * 加载数据节点
             * @param  {[type]} node    [description]
             * @param  {[type]} resolve [description]
             * @return {[type]}         [description]
             */
            reloadTree(node, resolve) {
                standardAsync(this, 'querySecDepartmentTree', {
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