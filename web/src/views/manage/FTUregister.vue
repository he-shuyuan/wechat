<template>
    <div>
        <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane name="discrete" label="非连续终端">
                <mc-form :rules="rules" ref="form1" label-width="150px" :slotProp="[{terminalCode:Array}]" :formOpt="formOpt1">
                    <template slot-scope="scope" slot="after">
                        <el-form-item prop="terminalCode" :label="i==1?'物联网终端编码':''" v-for='i in terminalCodeNum' :key="i">
                            <div style="display:none">{{discrete = discreteCodeNum(scope.row)}}</div>
                            <el-row>
                                <el-col :span="12" style="position:relative;">
                                    <el-input style='width:80%' v-model="scope.row.terminalCode[i-1]" placeholder="请输入物联网终端编码"></el-input>
                                    <div style="position:absolute;display:inline-block;right:30px;top:-4px;">
                                        <svg-icon v-if="i==1" style="cursor: pointer;font-size:40px" @click.native="addTerminalCode()" icon-class="plus" />
                                        <svg-icon v-if="i>1" style="cursor: pointer;font-size:36px" @click.native="cutTerminalCode(scope.row.terminalCode,i)" icon-class="minus"
                                        />
                                    </div>
                                </el-col>
                            </el-row>
                        </el-form-item>
                    </template>
                </mc-form>
            </el-tab-pane>
            <el-tab-pane name="continuous" label="连续终端">
                <mc-form ref="form2" :rules="rules" labelWidth="150px" :slotProp="[{terminalCode:Array}]" :formOpt="formOpt2">
                    <template slot-scope="scope" slot="after">
                        <el-form-item prop="terminalCode" label="物联网终端编码">
                            <el-row>
                                <el-col :span="16" style="position:relative;">
                                    <el-input @blur="continuousCodeNum(scope.row)" style='width:40%' v-model="scope.row.terminalCode[0]" placeholder="请输入物联网终端开始编码"></el-input>
                                    <span>&nbsp;&nbsp;至&nbsp;&nbsp;</span>
                                    <el-input @blur="continuousCodeNum(scope.row)" style='width:40%' v-model="scope.row.terminalCode[1]" placeholder="请输入物联网终端结束编码"></el-input>
                                </el-col>
                            </el-row>
                        </el-form-item>
                    </template>
                </mc-form>
            </el-tab-pane>
        </el-tabs>
        <div class="FTU-register-bottom">
            共
            <span style="color:red">{{activeName=='discrete'?discrete:continuous}}</span>
            个终端
            <el-row class="resgister">
                <el-button @click="validateChangeHandler" type="success">注册</el-button>
            </el-row>
        </div>
    </div>
</template>

<script>
    import createForm from '../../components/mainTpl/createForm.vue'
    import {
        standardAsync
    } from '@/api/async'
    export default {
        components: {
            createForm
        },
        data() {
            var empty = (rule, value, callback) => {
                if (value.length > 0) {
                    for (let item of value) {
                        if (item) {
                            callback()
                        }
                    }
                    callback(new Error('请填写物联网终端编码'));
                } else {
                    callback(new Error('请填写物联网终端编码'));
                }
            }
            return {
                formOpt1: [{
                        label: '终端类型',
                        col: 12,
                        type: 'select',
                        prop: 'terminalType',
                        initData: [{
                            value: '类型一',
                            label: '类型一'
                        }, {
                            value: '类型二',
                            label: '类型二'
                        }, {
                            value: '类型三',
                            label: '类型三'
                        }]
                    },
                    {
                        type: 'hidden',
                        col: 12
                    }
                ],
                formOpt2: [{
                        label: '终端类型',
                        col: 12,
                        type: 'select',
                        prop: 'terminalType',
                        initData: [{
                            value: '类型一',
                            label: '类型一'
                        }, {
                            value: '类型二',
                            label: '类型二'
                        }, {
                            value: '类型三',
                            label: '类型三'
                        }]
                    },
                    {
                        type: 'hidden',
                        col: 12
                    }
                ],
                terminalCodeNum: 1,
                discrete: 0,
                continuous: 0,
                activeName: 'discrete',
                rules: {
                    terminalType: [{
                        required: true,
                        message: '请选择终端类型',
                        trigger: 'change',
                        validator: empty,
                    }],
                    terminalCode: [{
                        required: true,
                        trigger: 'change',
                        validator: empty,
                    }],
                },
                errorPrefix: false
            }
        },
        mounted() {},
        methods: {
            addTerminalCode() {
                this.terminalCodeNum++
            },
            cutTerminalCode(list, i) {
                this.terminalCodeNum--
                    list.splice(i - 1, 1)
            },
            discreteCodeNum(row) {
                let num = row.terminalCode.length
                for (let i of row.terminalCode) {
                    if (!i) {
                        num--;
                    }
                }
                return num
            },
            continuousCodeNum(row) {
                let codeOne = this.getCodeTailNumber(row.terminalCode[0], '0')
                let codeTwo = this.getCodeTailNumber(row.terminalCode[1], '1')
                let num = parseInt(codeTwo) - parseInt(codeOne)
                this.continuous = num.toString() == 'NaN' ? 0 : Math.abs(num) + 1
            },
            getCodeTailNumber(code, i) {
                if (!code) {
                    this.prefix = ''
                    return
                }
                let tailNum = code.match(/\d+$/) ? code.match(/\d+$/)[0] : ''
                let prefix = code.replace(tailNum, '')
                if (this.prefix) {
                    if (this.prefix !== prefix || code.length !== this.verifyCode.length) {
                        this.errorPrefix = true
                    } else {
                        this.errorPrefix = false
                    }
                } else if(i==='0'){
                    this.prefix = prefix
                    this.verifyCode = code
                    this.tailNumLength = tailNum.length
                }
                return tailNum
            },
            register() {
                let formData = {}
                if (this.activeName == 'discrete') {
                    formData = this.$refs.form1.search()
                } else {
                    formData = {
                        ...this.$refs.form2.search()
                    }
                    if (!formData.terminalCode[0] || !formData.terminalCode[1]) {
                        this.$alert('<div style="font-size:16px;">请填写完整物联网终端的起止编码</div></br>', '提示', {
                            confirmButtonText: '确定',
                            dangerouslyUseHTMLString: true
                        });
                        return
                    }
                    if (this.errorPrefix) {
                        this.$alert('该组编码不为连续终端编码，请检查编码前缀是否一致', '提示', {
                            confirmButtonText: '确定',
                        });
                        return
                    }
                    let codeOne = this.getCodeTailNumber(formData.terminalCode[0])
                    let codeTwo = this.getCodeTailNumber(formData.terminalCode[1])
                    if (!codeOne || !codeTwo) {
                        this.$alert('请填写正确的连续编码', '提示', {
                            confirmButtonText: '确定',
                        });
                        return
                    }
                    let beginCode = parseInt(codeOne) < parseInt(codeTwo) ? parseInt(codeOne) :
                        parseInt(codeTwo)
                    let endCode = parseInt(codeOne) > parseInt(codeTwo) ? parseInt(codeOne) :
                        parseInt(codeTwo)
                    if (endCode - beginCode > 99) {
                        this.$alert('连续添加一次最多不能超过100台', '提示', {
                            confirmButtonText: '确定',
                        });
                        return
                    }
                    formData.terminalCode = []
                    let tailCode
                    for (let i = 0; i <= (endCode - beginCode); i++) {
                        if((beginCode + i).toString().length < this.tailNumLength) {
                            tailCode = beginCode + i
                            for(let j=0;j< this.tailNumLength - (beginCode + i).toString().length;j++) {
                               tailCode = "0" + tailCode
                            }
                        } else {
                            tailCode = beginCode + i
                        }
                        formData.terminalCode.push(this.prefix + tailCode)
                    }
                }
                formData.terminalStatus = '0801'
                standardAsync(this, 'addTerminal', formData, res => {
                    this.$alert('添加成功', '提示', {
                        confirmButtonText: '确定'
                    });
                })
            },
            validateChangeHandler() {
                if (this.activeName == 'discrete') {
                    this.validate('form1')
                } else {
                    this.validate('form2')
                }
            },
            validate(form) {
                this.$refs[form].formEvent.validate((valid) => {
                    if (valid) {
                        this.register()
                    } else {
                        return false;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .resgister.el-row {
        text-align: center;
    }

    .el-button {
        margin-top: 30px;
        padding: 12px 60px;
    }

    .FTU-register-bottom {
        margin: 25px 40px;
    }

    .error-prefix-tip {
        color: #f56c6c;
        font-size: 12px;
    }
</style>