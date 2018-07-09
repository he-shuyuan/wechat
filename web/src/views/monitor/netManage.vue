<template>
    <el-tabs type="border-card" v-model="activeName">
        <el-tab-pane label="入网机械" name="net">
            <mc-table v-if="firstLoadnet" ref="table1" @getCurrentTableInfo="getCurrentTableInfoOfNet" stripe :formOpt="netFormOpt" :columns="netColumns"
                method="queryNetMachine" :params="netParams">
                <template slot-scope="scope" slot="machineImgOFnet">
                    <router-link :to="'/manage/machineManage/detail?machineId=' + scope.row.machineId">
                        <img class="machine-img" :src="imgUrl(scope.row.machineImg)" alt="加载失败">
                    </router-link>
                </template>
                <template slot-scope="scope" slot="machineNameAndIdOfNet">
                    <router-link style="color: #3366FF;" :to="'/manage/machineManage/detail?machineId=' + scope.row.machineId">
                        {{scope.row.machineName}}
                        <br> {{scope.row.machineId}}
                    </router-link>
                </template>
                <span slot="customOperate">
                    共
                    <span style="color:red">{{numOfNet}}</span>台
                </span>
            </mc-table>
        </el-tab-pane>
        <el-tab-pane label="未入网机械" name="outNet">
            <mc-table v-if="firstLoadOutNet" ref="table2" @getCurrentTableInfo="getCurrentTableInfoOfOutNet" stripe :formOpt="outNetFormOpt"
                :columns="outNetColumns" method="queryNotNetMachine" :params="outNetParams">
                <template slot-scope="scope" slot="machineImgOfOutNet">
                    <router-link :to="'/manage/machineManage/detail?machineId=' + scope.row.machineId">
                        <img class="machine-img" :src="imgUrl(scope.row.machineImg)" alt="加载失败">
                    </router-link>
                </template>
                <template slot-scope="scope" slot="machineNameAndIdOfOutNet">
                    <router-link style="color: #3366FF;" :to="'/manage/machineManage/detail?machineId=' + scope.row.machineId">
                        {{scope.row.machineName}}
                        <br> {{scope.row.machineId}}
                    </router-link>
                </template>
                <template slot-scope="scope" slot="machineType">
                    <div>未绑定终端</div>
                </template>
                <span slot="customOperate">
                    共
                    <span style="color:red">{{numOfOutNet}}</span>台
                </span>
            </mc-table>
        </el-tab-pane>
        <el-tab-pane label="硬件终端" name="FTU">
            <mc-table v-if="firstLoadFTU" ref="table3" @getCurrentTableInfo="getCurrentTableInfoOfFTU" stripe :formOpt="FTUFormOpt" :columns="FTUColumns"
                method="queryTerminalAndMace" :params="FTUParams">
                <template slot-scope="scope" slot="operate">
                    <el-button v-if="scope.row.terminalStatus == '0802'" size="small" @click="cancelTerminal(scope.row)">取消关联</el-button>
                </template>
                <template slot-scope="scope" slot="machineNameOfFTU">
                    <router-link style="color: #3366FF;" :to="'/manage/machineManage/detail?machineId=' + scope.row.machineId">
                        {{scope.row.machineName}}
                    </router-link>
                </template>
                <template slot-scope="scope" slot="terminalStatus">
                    <div>{{transformContractType(scope.row.terminalStatus)}}</div>
                </template>
                <span slot="customOperate">
                    共
                    <span style="color:red">{{numOfFTU}}</span>台
                </span>
            </mc-table>
        </el-tab-pane>
    </el-tabs>

</template>

<script>
    import netColumns from './resouce/tableOption/netTableOption'
    import outNetColumns from './resouce/tableOption/outNetTableOption'
    import FTUColumns from './resouce/tableOption/FTUTableOption'
    import netFormOpt from './resouce/formOption/netFormOption'
    import outNetFormOpt from './resouce/formOption/outNetFormOption'
    import FTUFormOpt from './resouce/formOption/FTUFormOption'
    import util from "@/utils/"
    import {
        Message,
        MessageBox
    } from 'element-ui';
    import {
        standardAsync
    } from '@/api/async'
    import {
        lStore
    } from '@/utils/'
    import apiConfig from '@/api/config'
    export default {
        data() {
            return {
                netFormOpt,
                netColumns,
                outNetColumns,
                netParams: {},
                outNetParams: {},
                FTUParams: {},
                outNetFormOpt,
                FTUFormOpt,
                FTUColumns,
                numOfNet: 0,
                numOfOutNet: 0,
                numOfFTU: 0,
                activeName: 'net',
                firstLoadOutNet: false,
                firstLoadFTU: false,
                firstLoadnet:true,
                terminalStatus: '',
                init: {
                    terminalStatusMap: {}
                },
                imgHolder:require('../../assets/img/Default_Pic.jpg')
            }
        },
        filters: {
            filterFun: function (value) {
                if (type == 'dcname') {
                    return dc
                } else if (dc) {

                }
                return value
            }
        },
        watch: {
            activeName(val) {
                if (val == 'outNet') {
                    this.firstLoadOutNet = true
                    this.firstLoadFTU = false
                    this.firstLoadnet = false
                } else if (val == 'FTU') {
                    this.firstLoadFTU = true
                    this.firstLoadOutNet = false
                    this.firstLoadnet = false
                } else if (val == 'net') {
                    this.firstLoadnet = true
                    this.firstLoadFTU = false
                    this.firstLoadOutNet = false                    
                }

            }
        },
        created() {
            this.getProjectSelect()
        },
        mounted() {
            this.getTerminalStatus()
        },
        methods: {
            imgUrl(path) {
                console.log('path',path)
                return path ? apiConfig.wxImgvUrl + path : this.imgHolder
            },
            getCurrentTableInfoOfNet(data, totle) {
                console.log('tableD1', data)
                this.numOfNet = totle ? totle : 0
            },
            getCurrentTableInfoOfOutNet(data, totle) {
                console.log('tableD2', data)
                this.numOfOutNet = totle ? totle : 0
            },
            getCurrentTableInfoOfFTU(data, totle) {
                console.log('tableD3', data)
                this.numOfFTU = totle ? totle : 0
            },
            getStatusName(dcCode) {
                let params = {
                    dcCodeMain: dcCode.slice(0, 2)
                }
                standardAsync(this, 'getDicList', params, res => {
                    for (let item of res.body) {
                        if (item.dcCode == dcCode) {
                            this.terminalStatus = item.dcname
                        }
                    }
                })
            },
            cancelTerminal(row) {
                let params = {
                    machineId: row.machineId,
                    terminalCode: row.terminalCode
                }
                MessageBox.confirm('确定取消关联吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    standardAsync(this, 'cancelTerminal', params, res => {
                        this.$notify({
                            title: '提示',
                            message: '取消成功',
                            type: 'success',
                            duration: 2000
                        });
                        this.$refs.table3.searchHandle()
                    })
                })

            },
            /**
             * 获取项目列表
             * @return {[type]} [description]
             */
            getProjectSelect() {
                standardAsync(this, 'queryCompanyProject', {
                    companyId: util.lStore.getItem(util.lStore.MACE_SELECED_INS_ID)
                }, res => {
                    netFormOpt[1].initData = []
                    res.body.forEach(ob => {
                        netFormOpt[1].initData.push({
                            value: ob.projectId,
                            label: ob.projectName
                        })
                    })
                });
            },
            /**
             * 获取终端状态名称
             * @return {[type]} [description]
             */
            getTerminalStatus() {
                standardAsync(this, 'getDicList', {
                    dcCodeMain: '08'
                }, res => {
                    res.body.forEach(ob => {
                        this.init.terminalStatusMap[ob.dcCode] = ob.dcname;
                    })
                });
            },
            /**
             * 字典转换
             * @param  {[type]} key [description]
             * @return {[type]}     [description]
             */
            transformContractType(key) {
                return this.init.terminalStatusMap[key];
            },
        }
    }
</script>

<style scoped>
    .machine-img {
        width: 60px;
        height: 60px;
        border-radius: 8px;
    }
</style>