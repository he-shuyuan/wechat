<template>
    <div>
        <el-tabs type="border-card">
            <el-tab-pane label="组织机构详情">
                <div style="text-align:right;margin:10px 15px;">
                    <el-button @click="$router.push({path:'/manage/companyManage/modifyCompany',query:{insId:$route.query.insId}})">编辑组织信息</el-button>
                </div>
                <el-card shadow="never" class="modify">
                    <div slot="header">
                        <center class="conopany-name">{{headInfo.insName}}</center>
                        <div class="conopany-summary">
                            <el-row>
                                <el-col :span='7'>
                                    <div class="label">统一信用代码：</div>
                                </el-col>
                                <el-col :span='5'>
                                    <div class="label">{{headInfo.organizationCode}}</div>
                                </el-col>
                                <el-col :span='5'>
                                    <div class="label">登记日期：</div>
                                </el-col>
                                <el-col :span='7'>
                                    <div class="label">{{toDatetime(headInfo.openDate,'yyyy-MM-dd')}}</div>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span='7'>
                                    <div class="label">法人代表：</div>
                                </el-col>
                                <el-col :span='5'>
                                    <div class="label">{{headInfo.legal}}</div>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                    <div class="company-info">
                        <el-row v-for="(item,index) in companyInfoList" :key="index">
                            <el-col :span='4'>
                                <div class="label">{{item.label}}：</div>
                            </el-col>
                            <el-col :span='20'>
                                <div class="label">{{item.info}}</div>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import sessionStorageUtil from "@/utils/sessionStorageUtil"
    import {
        standardAsync
    } from '@/api/async'
    import utils from '@/utils/'
    export default {
        data() {
            return {
                bankNo: '91440239484626402M',
                companyInfoList: [{
                        label: '组织类型',
                        key: 'insStyle',
                        info: ''
                    },
                    {
                        label: '经营地址',
                        key: 'address',
                        info: ''
                    },
                    {
                        label: '联系人',
                        key: 'contact',
                        info: ''
                    },
                    {
                        label: '联系电话',
                        key: 'phone',
                        info: ''
                    },
                    {
                        label: '开户名称',
                        key: 'openName',
                        info: ''
                    },
                    {
                        label: '开户银行',
                        key: 'openBank',
                        info: ''
                    },
                    {
                        label: '银行账号',
                        key: 'bankNo',
                        info: ''
                    },
                    {
                        label: '录入员',
                        key: 'userName',
                        info: ''
                    },
                    {
                        label: '录入日期',
                        key: 'createDate',
                        info: ''
                    },
                ],
                headInfo: {
                    insName: '',
                    legal: '',
                    openDate: '',
                    organizationCode: ''
                }
            }
        },
        created() {
            this.getCompanyInfo()
        },
        methods: {
            getCompanyInfo() {
                let userInfo = sessionStorageUtil.getItem(sessionStorageUtil.MCE_USER_INFO)
                let params = {}
                params.insId = this.$route.query.insId
                standardAsync(this, 'queryInstitutionById', params, res => {
                    let result = {}
                    result.userName = userInfo.nickName
                    Object.assign(result, res.body)
                    for (let key in result) {
                        for (let _item of this.companyInfoList) {
                            if (key == 'insName' || key == 'legal' || key == 'openDate' || key ==
                                'organizationCode') {
                                this.headInfo[key] = result[key]
                            } else if (key == _item.key) {
                                if (key == 'createDate') {
                                    _item.info = this.toDatetime(result[key], 'yyyy-MM-dd')
                                } else if (key == 'insStyle') {
                                    this.getDicList(result[key])
                                } else {
                                    _item.info = result[key]
                                }
                            }
                        }
                    }
                })
            },
            getDicList(dcCode) {
                let params = {
                    dcCodeMain: dcCode.slice(0, 2)
                }
                standardAsync(this, 'getDicList', params, res => {
                    for (let item of res.body) {
                        if (item.dcCode == dcCode) {
                             this.companyInfoList[0].info = item.dcname
                        }
                    }
                })
            },
            toDatetime: utils.toDatetime
        }
    }
</script>

<style scoped lang="scss">
    .conopany-name {
        color: #3366CC;
        font-weight: normal;
        font-size: 22px;
    }

    .conopany-summary {
        font-weight: normal;
        margin-top: 26px;
        margin-bottom: -15px;
        .el-row {
            margin-bottom: 15px;

            .el-col {
                &:nth-child(odd) {
                    .label {
                        text-align: right;
                    }
                }
                &:nth-child(even) {
                    .label {
                        color: #3366CC;
                    }
                }
            }
        }
    }

    .company-info {
        margin: -20px;
        .el-row {
            padding: 18px 0;
            border-bottom: 1px solid #f1f1f1;
            &:last-child {
                border-bottom: 0;
            }
            .el-col {
                &:nth-child(odd) {
                    .label {
                        text-align: right;
                    }
                }
                &:nth-child(even) {
                    .label {
                        color: #3366CC;
                    }
                }
            }
        }
    }
</style>