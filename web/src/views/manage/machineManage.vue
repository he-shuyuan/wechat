<template>
    <router-view v-if="$route.name !== thisRouteName"></router-view>
    <mc-table v-else ref="table" showAdd showDelet deletBtnText="批量删除" @getCurrentTableInfo="getCurrentTableInfo" @addEvent="$router.push('/manage/machineManage/edit')"
        @deleteEvent="deleteMachine" type="selection" stripe :formOpt="formOpt" :columns="columns" method="queryMachine" :params="{}">
        <template slot-scope="scope" slot="machineImg">
            <img class="machine-img" :src="imgUrl(scope.row.machineImg)" alt="图片加载失败" style="cursor: pointer;" @click.stop.prevent="routeToDetail(scope.row.machineId)">
        </template>
        <template slot-scope="scope" slot="machineNameAndId">
            <div style="cursor: pointer;color: #3366FF;" @click.stop.prevent="routeToDetail(scope.row.machineId)">
                <span style="font-weight: bold;"> {{scope.row.machineName}}</span>
                </br> {{scope.row.machineId}}
            </div>
        </template>
        <template slot-scope="scope" slot="terminalCode">
            <div :style="{
                color: scope.row.terminalCode?'#f09233':'#005792',
                borderColor: scope.row.terminalCode?'#f09233':'#005792',
                borderWidth:'0px',
                borderStyle:'solid',
                borderRadius:'0px',
                padding:'0px'
            }">
                {{scope.row.terminalCode?'已入网':'未入网'}}
            </div>
        </template>

        <template slot-scope="scope" slot="handle">
            <el-button size="mini" type="danger" @click.native.stop="deleteMachine([scope.row])">删除</el-button>
        </template>
    </mc-table>
</template>

<script>
    import {
        standardAsync
    } from '@/api/async'
    import apiConfig from '@/api/config'
    export default {
        data() {
            return {
                imgHolder: require('../../assets/img/Default_Pic.jpg'),
                thisRouteName: 'machineManage',
                formOpt: [{
                        col: 8,
                        label: '机械类型',
                        type: 'cascader',
                        dcGroup: 'jxgroup',
                        prop: 'machineType',
                        showAllLevels: false,
                        filterable: true,
                        placeholder: '试试搜索：挖掘机',
                        expandTrigger: 'hover',
                        initData:[]
                    }, {
                        col: 8,
                        label: '所属项目',
                        type: 'select',
                        prop: 'projectId',
                        placeholder: '请选择所属项目',
                        method: 'queryProjectByCompanyId',
                        params: {},
                        valueName: 'projectId',
                        labelName: 'projectName'
                    }, {
                        col: 8,
                        label: '',
                        type: 'search',
                    },
                    {
                        col: 8,
                        label: '机械名称',
                        type: 'text',
                        placeholder: '请输入机械名称',
                        prop: 'machineName',
                    },
                    {
                        col: 8,
                        label: '入网状态',
                        type: 'select',
                        prop: 'internetStatus',
                        placeholder: '请选择入网状态',
                        method: 'getDicList',
                        params: {
                            dcCodeMain: '07'
                        },
                        valueName: 'dcCode',
                        labelName: 'dcname'
                    },
                    {
                        col: 8,
                        label: '',
                        type: 'reset',
                    }
                ],

                columns: [{
                        prop: 'machineImg',
                        label: '',
                        align: 'right',
                        width: 100,
                        slotName: 'machineImg'
                    },
                    {
                        prop: '',
                        label: '机械名称 / 编码',
                        align: 'left',
                        minWidth: 150,
                        slotName: 'machineNameAndId'
                    },
                    {
                        prop: 'terminalCode',
                        label: '入网状态',
                        minWidth: 60,
                        slotName: 'terminalCode'
                    },
                    {
                        prop: 'machineType',
                        label: '机械类型',
                        minWidth: 100,
                        render: row => {
                            let id = row.machineType
                            let foundFather = this.machineTypeDict.find(item => new RegExp('^' + item.value).test(
                                id))
                            if (foundFather) {
                                let foundChild = foundFather.children.find(item => item.value === id)
                                return foundChild ? foundChild.label : '未知类型'
                            }
                            return '未知类型'
                        }
                    },
                    {
                        prop: 'brandId',
                        label: '品牌类型',
                        minWidth: 100,
                        render: row => {
                            let id = row.brandId
                            let found = this.brandDict.find(item => item.value === id)
                            return found ? found.label : '未知类型'
                        }
                    },
                    {
                        prop: 'projectName',
                        label: '所属项目',
                        minWidth: 100,
                    },
                    {
                        prop: '',
                        label: '操作',
                        width: 150,
                        slotName: 'handle'
                    },
                ],
                machineTypeDict: [],
                brandDict: [],
            }
        },
        created() {
            const dictMapper = (list, labelName, valueName) => list.map(item => ({
                label: item[labelName],
                value: item[valueName],
            }))
            standardAsync(this, 'getDicGroupList', {
                dcGroup: 'jxgroup'
            }, res => {
                this.machineTypeDict = res.body.map(item => ({
                    value: item.dcCodeMain,
                    label: item.dcNameMain,
                    children: dictMapper(item.dcList, 'dcName', 'dcCode')
                }))
                this.formOpt[0].initData = this.machineTypeDict
            })
            standardAsync(this, 'getDicList', {
                dcCodeMain: '03'
            }, res => {
                this.brandDict = dictMapper(res.body, 'dcname', 'dcCode')
            })
        },
        mounted() {

        },
        methods: {
            imgUrl(path) {
                return path ? apiConfig.wxImgvUrl + path : this.imgHolder
            },
            getCurrentTableInfo(data, totle) {

            },
            deleteMachine(selection) {
                if (selection) {
                    this.$confirm('是否确认删除?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let param = {
                            machineId: selection.map(item => item.machineId)
                        }
                        standardAsync(this, 'delMachine', param, res => {
                            this.$refs.table.searchHandle()
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        })
                    }).catch(() => {

                    });

                }
            },
            routeToDetail(machineId) {
                this.$router.push('/manage/machineManage/detail?machineId=' + machineId)
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