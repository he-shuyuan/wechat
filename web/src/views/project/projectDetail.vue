<template>
    <el-tabs type="border-card" v-model="activeName">
        <el-tab-pane label="项目详情" name="detail">
            <div style="text-align:right;margin:15px">
                <el-button @click="$router.push({path:'/manage/project/edit',query:{projectId:$route.query.projectId}})">编辑项目信息</el-button>
            </div>
            <div class="uni-form-header">
            <el-card shadow="never"
                    class="modify">
                <div slot="header">
                    <center>项目信息概览</center>
                </div>
                <slot class="uni-form-header-wrap">
                    <div class="company-info">
                        <el-row>
                            <el-col :span='4'>
                                <div class="label">项目编号</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center style="font-weight:bold;">{{ruleForm.projectId}}</center></div>
                            </el-col>
                            <el-col :span='4'>
                                <div class="label">项目状态</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center>{{ruleForm.projectStatusName}}</center></div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span='4'>
                                <div class="label">项目名称</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center style="font-weight:bold;">{{ruleForm.projectName}}</center></div>
                            </el-col>
                            <el-col :span='4'>
                                <div class="label">项目类型</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center>{{ruleForm.projectTypeName}}</center></div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span='4'>
                                <div class="label">所属区域</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center>{{areaLabel(ruleForm.area)}}</center></div>
                            </el-col>
                            <el-col :span='4'>
                                <div class="label"></div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center></center></div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span='4'>
                                <div class="label">负责人</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center>{{ruleForm.manager}}</center></div>
                            </el-col>
                            <el-col :span='4'>
                                <div class="label">负责人电话</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center>{{ruleForm.managerPhone}}</center></div>
                            </el-col>
                        </el-row><el-row>
                            <el-col :span='4'>
                                <div class="label">录入人员</div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center>{{ruleForm.createUserName}}</center></div>
                            </el-col>
                            <el-col :span='4'>
                                <div class="label"></div>
                            </el-col>
                            <el-col :span='8'>
                                <div class="label"><center></center></div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span='4'>
                                <div class="label">备注</div>
                            </el-col>
                            <el-col :span='20'>
                                <div class="label"><center>{{ruleForm.bz}}</center></div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span='4'>
                                <div class="label">附件</div>
                            </el-col>
                            <el-col :span='20'>
                                <ol>
                                    <li v-for="item in attachmentList">
                                    <a :href="item.url">{{item.name}}</a>
                                    </li>
                                </ol>
                            </el-col>
                        </el-row>
                    </div>
                </slot>
            </el-card>
            </div>
        </el-tab-pane>
        <el-tab-pane label="项目机械" name="machine">
            <mc-table 
              ref="table"
              deletBtnText="批量删除"
              @getCurrentTableInfo="getCurrentTableInfo"
              @deleteEvent="deleteMachine"
              type="selection"
              stripe
              :columns="columns"
              method="queryPorjectMachine"
              :params="params">
           
            <template slot-scope="scope" slot="machineImg">
                <img class="machine-img" :src="imgUrl(scope.row.machineImg)" 
                style="cursor: pointer;"
                @click.stop.prevent="routeToDetail(scope.row.machineId)" 
                alt="">
            </template>
            <template slot-scope="scope"
                  slot="machineNameAndId">
                <div style="cursor: pointer;"
                    @click.stop.prevent="routeToDetail(scope.row.machineId)">
                    <span style="color: #3366CC;font-weight: bold;"> {{scope.row.machineName}}</span>
                    <br> {{scope.row.machineId}}
                </div>
            </template>
            <template slot-scope="scope"
                slot="handle">
            <svg-icon style="cursor: pointer;font-size:20px"
                    @click.native="deleteMachine([scope.row])"
                    icon-class="ljx" />
            </template>

            <div slot="customOperate" style="display:flex;display:-webkit-flex;align-items:center;justify-content:space-between;margin:0 15px 10px;;">
                    <div>
                        <!-- <span>共<span style="color:red">{{numOfNet}}</span>台</span> -->
                    </div>
                    <div>
                        <el-button @click="$router.push({path:'/manage/project/editM',query:{projectId:$route.query.projectId}})">编辑项目机械</el-button>
                    </div>
                </div>
            </mc-table>

        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import toDatetime,{toDate} from '@/utils/toDatetime'
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    import uniFormHeader from '../../components/uniFormHeader.vue'
    import apiConfig from '@/api/config'
    export default {
        components: {
            uniFormHeader
        },
        data() {
            return {
                activeName:this.$route.query.activeName ?this.$route.query.activeName : 'detail',
                thisRouteName: 'projectDetail',
                imgHolder: require('../../assets/img/100x70.png'),
                ruleForm: {
                    projectId:this.$route.query.projectId, //项目id
                    projectName: '',//项目名称
                    projectType: '',//项目类型
                    area: [],//区域,
                    areaProvince:'',
                    areaCity:'',
                    manager: '', //负责人姓名
                    managerPhone:'',//负责人电话
                    bz: '',//备注,
                    projectStatus:'0501',//已开工:0501  已完工:0502，
                    imageUrl:null
                },
                projectType:[],
                projectStatus:[],
                areas:[],
                attachmentList:[],
                columns: [{
                    prop: 'machineImg',
                    label: '',
                    align: 'right',
                    width: 100,
                    slotName: 'machineImg'
                },
                {
                    prop: '',
                    label: '机械名称',
                    align: 'left',
                    slotName:'machineNameAndId',
                    minWidth: 150,
                },
                {
                    prop: 'machineType',
                    label: '机械类型',
                    minWidth: 60,
                },
                {
                    prop: 'brandId',
                    label: '品牌型号',
                    minWidth: 100,
                },
                {
                    prop: '',
                    label: '地理位置',
                    minWidth: 100,
                    render: row => {
                        return '---'
                    }
                },
                {
                    prop: 'machineSource',
                    label: '机械来源',
                    minWidth: 100,
                },
                {
                    prop: '',
                    label: '操作',
                    width: 80,
                    slotName: 'handle'
                },
                ],
                params:{
                    projectId:this.$route.query.projectId,
                    pageIndex:1,
                    pageSize:10,
                },
            }
        },
        mounted() {
             //this.initProjectType()
             //this.initProjectStatus()
             this.initArea()
             this.initProjectInfo()
        },
        methods: {
            getCurrentTableInfo(){
                console.log("getCurrentTableInfo");
            },

            deleteMachine(selection) { //删除解绑机械
            if (selection) {
                this.$confirm('是否确认解绑?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                   let param = { machineList: selection.map(item => item.machineId),projectId:selection[0].projectId }
                    standardAsync(this, 'cancelProjectMachine', param, res => {
                        this.$refs.table.searchHandle()
                        this.$message({
                            type: 'success',
                            message: '解绑成功!'
                        });
                    })
                }).catch(() => {

                });

            }
            },



    
            initArea(){
                standardAsync(this, 'getAreaList', {}, res => {
                    this.areas = res.body.map(function(item){
                        if(item.subArea && item.subArea.length>0){
                            item.subArea=item.subArea.map(function(subItem){
                                return {
                                    value:subItem.areaCode,
                                    label:subItem.areaName,
                                }
                            })
                        }
                        return {
                            value:item.areaCode,
                            label:item.areaName,
                            children:item.subArea
                        }
                    })
                })
            },
            initProjectInfo(){
                this.ruleForm.projectId = this.$route.query.projectId
                standardAsync(this, 'queryProjectInfo', this.ruleForm, res => {
                    this.ruleForm = res.body.projectInfo
                    this.attachmentList = res.body.attachmentList.map(function(item){
                        return{
                            name:item.attachmentName,
                            url:apiConfig.wxImgvUrl+item.attachmentVfsPath
                        }
                    })
                    let areaCity = this.ruleForm.areaCity
                    let areaProvince = this.ruleForm.areaProvince
                    this.ruleForm.area=[areaProvince,areaCity]
                })
            },
            projectTypeLabel(){
                let _ruleForm = this.ruleForm
                let findItem = this.projectType.filter(function(item){
                                return item.value==_ruleForm.projectType
                })
                return findItem&&findItem[0]&&findItem[0].label?findItem[0].label:_ruleForm.projectType
            },
            projectStatusLabel(){
                let _ruleForm = this.ruleForm
                let findItem = this.projectStatus.filter(function(item){
                                return item.value==_ruleForm.projectStatus
                })
                return findItem&&findItem[0]&&findItem[0].label?findItem[0].label:_ruleForm.projectStatus
            },
            areaLabel(){
                let _ruleForm = this.ruleForm
                let findItem = this.areas.filter(function(item){
                    return item.value==_ruleForm.areaProvince
                })
                let areaProvinceLabel = findItem&&findItem[0]&&findItem[0].label?findItem[0].label:_ruleForm.areaProvince
                if(!findItem || !findItem[0] || !findItem[0].children){
                    return _ruleForm.areaProvince+_ruleForm.areaCity
                }
                let findSubItem = findItem[0].children.filter(function(subItem){
                    return subItem.value==_ruleForm.areaCity
                })
                let areaCityLabel = findSubItem&&findSubItem[0]&&findSubItem[0].label?findSubItem[0].label:_ruleForm.areaCity
                return areaProvinceLabel+areaCityLabel
            },

            routeToDetail(machineId) {
                this.$router.push('/manage/machineManage/detail?machineId=' + machineId)
            },
            imgUrl(path) {
                return path ? apiConfig.wxImgvUrl + path : this.imgHolder
            },
        }
    }

</script>

<style lang="scss"
       scoped>
  .machine-img {
        max-width: 100px;
        max-height: 70px;
    }
    .company-info {
        margin: -20px -20px;
        .el-row {
           // padding: 18px 0;
            border-bottom: 1px solid #f1f1f1;
            
            &:last-child {
                border-bottom: 0;
            }
            .el-col {
                &:nth-child(odd) {
                    .label {
                        text-align: right;
                    }
                    border-right: 1px solid #f1f1f1;
                    padding: 22px 15px
                }
                &:nth-child(even) {
                    .label {
                        color: #3366CC;
                    }
                    padding: 22px 15px
                }
            }
        }
    }   
</style>