<template>
    <router-view v-if="$route.name !== thisRouteName"></router-view>
    <div v-else
         class="reset">
        <div class="top">
            <el-card shadow="always">
                <el-row :gutter="20">
                    <el-col :span="8"
                            style="display: flex;align-items: center;height: 200px;justify-content: center;">
                        <img class="machine-img"
                             :src="imgUrl">
                    </el-col>
                    <el-col :span="8">
                        <el-form class="short-info">
                            <p class="name">{{MachineInfo.machineName}}</p>
                            <el-form-item label="机械编号:">{{MachineInfo.machineId}}</el-form-item>
                            <el-form-item label="机械类型:">{{getMachineType}}</el-form-item>
                            <el-form-item label="品牌型号:">{{getNameFromDict('brandDict',MachineInfo.brandId)}}</el-form-item>
                            <el-form-item label="机械来源:">{{getNameFromDict('sourceDict',MachineInfo.machineSource)}}</el-form-item>
                        </el-form>
                    </el-col>
                    <el-col :span="6">
                        <div class="wrapper">
                            <el-button class="qrcode">
                                <svg-icon icon-class="qrcode"
                                          className="svg-qrcode"></svg-icon>机械二维码</el-button>

                            <el-button class="netAuth"
                                       :style="{
                                            color: MachineInfo.terminalCode?'#3366FF':'#FF3300',
                                            borderColor: MachineInfo.terminalCode?'#94CAE4':'#FFAE5E'
                                        }">{{MachineInfo.terminalCode?'已入网':'未入网'}}</el-button>
                        </div>
                    </el-col>
                </el-row>
            </el-card>
        </div>

        <div class="bottom">
            <el-card shadow="always">

                <el-tabs v-model="activeName">
                    <el-tab-pane label="概览"
                                 name="zero">
                        <overview :machineId="$route.query.machineId"></overview>
                    </el-tab-pane>
                    <el-tab-pane label="机械信息"
                                 name="first">
                        <info :prop="assembleDataForInfo"></info>
                    </el-tab-pane>
                    <el-tab-pane label="定位监控"
                                 name="second">
                        <position></position>
                    </el-tab-pane>
                    <el-tab-pane label="工作情况"
                                 name="third">
                        <status></status>
                    </el-tab-pane>
                    <el-tab-pane label="机械日志"
                                 name="fourth">
                        <log></log>
                    </el-tab-pane>
                    <el-tab-pane label="机械人员"
                                 name="fifth">
                        <mechanic :prop="MachineInfo.machinePersonlist"></mechanic>
                    </el-tab-pane>
                </el-tabs>
            </el-card>

        </div>

    </div>

</template>


<script>
    import overview from './overview.vue'
    import info from './info.vue'
    import position from './position.vue'
    import status from './status.vue'
    import log from './log.vue'
    import mechanic from './mechanic.vue'

    import { standardAsync } from '@/api/async'
    import apiConfig from '@/api/config'
    import util from '@/utils/index'

    export default {
        components: { overview, info, position, status, log, mechanic },
        data() {
            return {
                thisRouteName: 'machineDetail',
                activeName: 'zero',
                imgHolder: require('../../assets/img/100x70.png'),

                MachineInfo: {
                    machinePersonlist: [],
                    machineWorklist: [],
                },
                brandDict: [],
                sourceDict: [],
                machineTypeDict: [],
                machineOwnerDict: [],
                projectDict: [],
            }
        },
        created() {
            const dictMapper = (list, labelName, valueName) => list.map(item => ({
                label: item[labelName],
                value: item[valueName],
            }))
            let machineId = this.$route.query.machineId
            standardAsync(this, 'queryMachineInfo', { machineId }, res => {
                this.MachineInfo = res.body
            })

            standardAsync(this, 'getDicGroupList', { dcGroup: 'jxgroup' }, res => {
                this.machineTypeDict = res.body.map(item => ({
                    value: item.dcCodeMain,
                    label: item.dcNameMain,
                    children: dictMapper(item.dcList, 'dcName', 'dcCode')
                }))
            })
            standardAsync(this, 'getDicList', { dcCodeMain: '02' }, res => {
                this.sourceDict = dictMapper(res.body, 'dcname', 'dcCode')
            })
            standardAsync(this, 'getDicList', { dcCodeMain: '03' }, res => {
                this.brandDict = dictMapper(res.body, 'dcname', 'dcCode')
            })
            let param = {
                insStyle: '1802',
                currentInsId: util.sStore.getItem(util.sStore.MCE_INS_INFO),
            }
            standardAsync(this, 'queryInstitutionList', param, res => {
                this.machineOwnerDict = dictMapper(res.body, 'insName', 'insId')
            })
            standardAsync(this, 'queryProjectByCompanyId', {}, res => {
                this.projectDict = dictMapper(res.body, 'projectName', 'projectId')
            })
        },
        methods: {
            getNameFromDict(dict, id) {
                let found = this[dict].find(item => item.value === id)
                return found ? found.label : '未知类型'
            },
        },
        computed: {
            imgUrl() {
                return this.MachineInfo.attachmentlist&&this.MachineInfo.attachmentlist.length>0 ?
                    apiConfig.wxImgvUrl + this.MachineInfo.attachmentlist[0].attachmentVfsPath :
                    this.imgHolder
            },
            getMachineType() {
                let foundFather = this.machineTypeDict.find(item => {
                    return new RegExp('^' + item.value).test(this.MachineInfo.machineType)
                })
                if (foundFather) {
                    let foundChild = foundFather.children.find(item => {
                        return item.value === this.MachineInfo.machineType
                    })
                    return foundChild ? foundChild.label : '未知类型'
                }
                return '未知类型'
            },
            assembleDataForInfo() {
                let projectName = this.getNameFromDict('projectDict', this.MachineInfo.projectId)
                if (projectName === '未知类型')
                    projectName = '无'

                let currentInsId = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)
                let machineOwner = (this.MachineInfo.machineOwner && this.MachineInfo.machineOwner !== currentInsId) ?
                    this.getNameFromDict('machineOwnerDict', this.MachineInfo.machineOwner) :
                    undefined

                return {
                    machineName: this.MachineInfo.machineName,
                    machineType: this.getMachineType,
                    brandName: this.getNameFromDict('brandDict', this.MachineInfo.brandId),
                    machineSource: this.getNameFromDict('sourceDict', this.MachineInfo.machineSource),
                    machineOwner,
                    manufactureTime: util.toDate(this.MachineInfo.manufactureTime),
                    usedYears: this.MachineInfo.usedYears,
                    projectName,

                    machineId: this.MachineInfo.machineId,
                    createUserName: this.MachineInfo.createUserName,
                    createTime: util.toDatetime(this.MachineInfo.createTime),
                    updateUserName: this.MachineInfo.updateUserName,
                    updateTime: util.toDatetime(this.MachineInfo.updateTime),
                }
            }
        },
    }

</script>


<style lang="scss"
       scoped>
    .el-card {
        margin: 10px 0;
    }

    .top {
        position: relative;
        .machine-img {
            display: block;
            max-width: 100%;
            max-height: 200px;
        }
        .el-form {
            .name {
                color: #EC7700;
                font-size: 22px;
                font-weight: bold;
                margin: 0;
                margin-bottom: 10px;
            }
            .el-form-item {
                margin-bottom: 0;
            }
        }
        .short-info {
            .el-form-item {
                width: 100%;
            }
        }
        .wrapper {
            height: 150px;
            position: relative;
            .qrcode {
                color: #585858;
                border: 1px solid #585858;
                .svg-qrcode {
                    width: 1.5em;
                    height: 1.5em;
                }
            }
            .netAuth {
                position: absolute;
                left: 0;
                bottom: 0;
                margin-left: 0;
            }
        }
    }

</style>
