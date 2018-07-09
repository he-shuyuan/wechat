<template>
    <router-view v-if="$route.name !== thisRouteName"></router-view>
    <div v-else >

    <createTable ref="refCreateTable"
        method="queryProject" :params="params" 
        stripe :formOpt="formOpt" :columns="columns" 
        type="selection"
            showAdd addBtnText="新增项目" @addEvent="editProject"
            >
        <template slot-scope="scope" slot="productName">
            <router-link style="color: #3366FF;" :to="{path: '/manage/project/detail',query:{projectId:scope.row.projectId}}">
                        {{scope.row.projectName}} </br> {{scope.row.projectId}}
            </router-link>
        </template>  
        <template slot-scope="scope" slot="projectStatusSlot">
            <!-- <el-tag v-if="scope.row.projectStatus == '0502'" style="color:#fff" color="#005792">已完工</el-tag> -->
            <span v-if="scope.row.projectStatus == '0502'" style="color:#f09233">已完工</span>
            <span v-if="scope.row.projectStatus == '0501'" style="color:#005792">已开工</span>
            <!-- <el-tag v-if="scope.row.projectStatus == '0501'" color="#fff">已开工</el-tag> -->
        </template>  
        <template slot-scope="scope" slot="handle">
            <el-button v-if="false && scope.row.projectStatus == '0501'" size="mini" type="success"  @click="editProject(scope.row)">编辑</el-button>
            <el-button v-if="false" size="mini" type="danger"  @click="deleteProject(scope.row)">删除</el-button>
            <el-button v-if="scope.row.projectStatus == '0501'" type="danger" size="mini"  @click="finishProject(scope.row)">完工</el-button>
        </template>
    </createTable>
    <el-dialog title="完工" :visible.sync="dialogFormVisible">
    <el-form >
        <el-form-item label="完工日期" >
            <div class="block">
                <span class="demonstration">默认</span>
                <el-date-picker
                v-model="finishTime"
                type="date"
                placeholder="选择日期">
                </el-date-picker>
            </div>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="doFinishProject">确 定</el-button>
    </div>
    </el-dialog>
    
    </div>    
</template>

<script>
    // import createForm from '@/components/mainTpl/createForm.vue'
    import toDatetime,{toDate} from '@/utils/toDatetime'
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    import createTable from '@/components/mainTpl/createTable.vue'
    import utils from 'utils'
    export default {
        components: {
            // createForm,
            createTable
        },
        data() {
            return {
                thisRouteName: 'project',
                dialogFormVisible:false,
                finishTime:null,
                finishProjectId:null,
                formOpt: [{
                        label: '项目类型',
                        col:8,
                        type: 'select',
                        prop: 'projectType',
                        placeholder: '请选择项目类型',
                        initData: []
                    },{
                        label: '项目状态',
                        col:8,
                        type: 'select',
                        prop: 'projectStatus',
                        placeholder: '请选择项目状态',
                        initData: []
                    },
                    {
                        label: '项目区域',
                        col:8,
                        type: 'area',
                        style:'width:auto',
                        placeholder: '请选择项目区域',
                        changeOnSelect:true,
                        initData: []
                    },
                    {
                        label: '项目名称',
                        col:8,
                        type: 'text',
                        placeholder: '请输入项目名称',
                        prop: 'projectName',
                    },
                    {
                        label: '负责人',
                        col:8,
                        type: 'text',
                        placeholder: '请输入负责人',
                        prop: 'manager',
                    },
                    {
                        col:2,
                        label:'',
                        type: 'hidden',
                    },
                    {
                        col:2,
                        label: '',
                        type: 'search',
                    },
                    {
                        col:2,
                        label: '',
                        type: 'reset',
                    }
                ],
                columns: [{
                        prop: 'projectName',
                        label: '项目名称',
                        align:"left",
                        minWidth: 240,
                        slotName: 'productName'
                    },
                    {
                        prop: 'productName',
                        label: '负责人',
                        minWidth: 150,
                        render: row => {
                            return row.manager + "</br>" + row.managerPhone
                        }
                    },
                    {
                        prop: 'projectStatus',
                        label: '项目状态',
                        minWidth: 100,
                        slotName:'projectStatusSlot',
                    },
                    {
                        prop: 'projectType',
                        label: '项目类型',
                        minWidth: 100,
                        render: row => {
                            let findItem = this.formOpt[0].initData.filter(function(item){
                                return item.value==row.projectType
                            })
                            return findItem&&findItem[0]&&findItem[0].label?findItem[0].label:row.projectType
                        }
                    },
                    {
                        prop: 'areaCity',
                        label: '项目区域',
                        minWidth: 100,
                        render: row => {
                            let findItem = this.formOpt[2].initData.filter(function(item){
                                return item.value==row.areaProvince
                            })
                            let areaProvinceLabel = findItem&&findItem[0]&&findItem[0].label?findItem[0].label:row.areaProvince
                            if(!findItem || !findItem[0] || !findItem[0].children){
                                return row.areaProvince+row.areaCity
                            }
                            let findSubItem = findItem[0].children.filter(function(subItem){
                                return subItem.value==row.areaCity
                            })
                            let areaCityLabel = findSubItem&&findSubItem[0]&&findSubItem[0].label?findSubItem[0].label:row.areaCity
                            return areaCityLabel !=='全部'?areaProvinceLabel+areaCityLabel : areaProvinceLabel
                        }
                    },
                    {
                        prop: 'finishTime',
                        label: '实际完成时间',
                        minWidth: 200,
                        render: row => {
                             return row.finishTime? toDate(row.finishTime) : '------'
                        }
                    },
                    {
                        prop: '',
                        label: '操作',
                        width: 220,
                        slotName: 'handle'
                    },
                ],
                params:{
                    delStatus:'N'
                }
            }
        },
        mounted() {
            console.log('route',this.$route)
            this.initProjectType()
            this.initProjectStatus()
            this.initArea()
        },
        methods: {
            editProject(row) {
                this.$router.push({
                    path: '/manage/project/edit',
                    query: {
                        projectId: row?row.projectId:''
                    }
                })

            },
            deleteProject(row){
                if(!row){
                    return;
                }
                let _this = this
                MessageBox.confirm('确定删除此记录吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    standardAsync(_this, 'delProject', {"projectId":row.projectId}, res => {
                         _this.$notify({
                            title: '提示',
                            message: '删除成功',
                            type: 'success',
                            duration: 2000
                        });
                        _this.$refs.refCreateTable.searchHandle()
                    })
                })
            },
            finishProject(row){
                if(!row){
                    return;
                }
                this.dialogFormVisible=true
                this.finishProjectId = row.projectId
            },
            doFinishProject(){
                standardAsync(this, 'finishProject', {"projectId":this.finishProjectId,"finishTime":this.finishTime}, res => {
                    this.$notify({
                        title: '提示',
                        message: '操作成功',
                        type: 'success',
                        duration: 2000
                    });
                    this.dialogFormVisible = false
                    this.$refs.refCreateTable.searchHandle()
                });
            },
            onCancel() {
                this.$message({
                    message: 'cancel!',
                    type: 'warning'
                })
            },
            initProjectType(){
                standardAsync(this, 'getDicList', {"dcCodeMain":"04"}, res => {
                    this.formOpt[0].initData = res.body.map(function(item){
                        return {
                            value:item.dcCode,
                            label:item.dcname,
                        }
                    })
                })
            },
            initProjectStatus(){
                standardAsync(this, 'getDicList', {"dcCodeMain":"05"}, res => {
                    this.formOpt[1].initData = res.body.map(function(item){
                        return {
                            value:item.dcCode,
                            label:item.dcname,
                        }
                    })
                })
            },
            initArea(){
                standardAsync(this, 'getAreaList', {}, res => {
                    this.formOpt[2].initData = utils.areaDataHandler(res.body)
                })
            }
        }
    }

</script>

<style>


</style>