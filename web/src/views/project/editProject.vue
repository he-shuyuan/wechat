<template>
    <uni-form-header title="项目基本信息">
        <el-form :model="ruleForm"
                :rules="rules"
                ref="projectForm"
                label-width="100px"
                class="demo-ruleForm">

                <el-form-item label="项目名称"
                            prop="projectName" >
                    <el-col :span="12">
                        <el-input v-model="ruleForm.projectName"
                            placeholder="请输入项目名称"
                            class="long-input"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="项目类型"
                            required>
                    <el-col :span="12">
                        <el-form-item prop="projectType">
                            <el-select v-model="ruleForm.projectType"
                                    class="long-input"
                                    placeholder="请选择">
                                <el-option v-for="(item,index) in projectTypeOptions"
                                        :key="index"
                                        :label="item.dcname"
                                        :value="item.dcCode"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-form-item> 
                <el-form-item label="所属区域"
                            required>
                    <el-col :span="12">
                        <el-form-item prop="area">
                            <mc-cascader class="long-input" v-model="ruleForm.area" separator=" " type="area" change-on-select></mc-cascader>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="负责人"
                            prop="manager">
                    <el-col :span="12">
                        <el-input v-model="ruleForm.manager"
                            placeholder="请输入项目负责人姓名"
                            class="long-input"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="负责人电话"
                            prop="managerPhone">
                    <el-col :span="12">
                        <el-input v-model="ruleForm.managerPhone"
                            placeholder="请输入项目负责人电话"
                            class="long-input"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="bz">
                    <el-col :span="12">
                        <el-input type="textarea" v-model="ruleForm.bz" class="long-input"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="附件" v-if="ruleForm.projectId && ruleForm.projectStatus=='0501'">
                    <el-col :span="12">
                        <file-upload 
                            :attachmentInfo="attachmentInfo"
                        >
                        </file-upload>
                    </el-col>               
                </el-form-item>
            <el-form-item style="margin: 50px 0;" >
                <el-button type="primary" v-if="ruleForm.projectStatus == '0501'" 
                        @click="editProject('projectForm')">保存</el-button>
                <el-button v-if="ruleForm.projectStatus == '0501'"
                            @click="resetForm('projectForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </uni-form-header>
    
</template>

<script>
    import api from '@/api/'
    import uniFormHeader from '../../components/uniFormHeader.vue'
    import fileUpload from '../../components/FileUpload.vue'
    import { standardAsync, customAsync } from '@/api/async'
    import apiConfig from '@/api/config'

    export default {
        components: {
            uniFormHeader,fileUpload
        },
        data() {
            var checkPhone = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入手机号码'));
                } else {
                    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
                    if(!myreg.test(value)) { 
                        callback(new Error('请输入有效的手机号码'));
                    }
                    else{
                        callback();
                    }
                }
            };
            return{
                ruleForm: {
                    projectId:'', //项目id
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
                rules: {
                     projectName: [{ required: true, min: 3, max: 30, message: '项目名称长度至少3个字符，最长30个汉字', trigger: 'change' }],
                     projectType: [{ required: true, message: '请选择', trigger: 'change' }],
                     area: [{ required: true, message: '请选择', trigger: 'change' }],
                     manager: [{ required: true, min: 2, max: 6, message: '项目负责人名称长度至少2个字符，最长6个汉字', trigger: 'change' }],
                     //managerPhone: [{ required: true,validator: checkPhone, trigger: 'change' }],
                },
                projectTypeOptions:[],
                areaOptions:[],
                createUserInfo:null,
                attachmentInfo:{
                    listType:"text/picture",
                    attachmentList:[],
                    attachmentYwid:null,
                    attachmentYwlx:"1701"
                }
            }
          
        },
        mounted() {
            this.initProjectType()
            this.ruleForm.projectId = this.$route.query.projectId
            if(this.ruleForm.projectId){
                this.attachmentInfo.attachmentYwid=this.ruleForm.projectId
                this.initProjectInfo()
            }
        },
        methods: {
            handleAvatarSuccess(res, file) {
                this.ruleForm.imageUrl = URL.createObjectURL(file.raw);
            },
            beforeAvatarUpload(file) {
                const isImage = /^image\/(jpe?g|png|gif)$/.test(file.type)
                if (!isImage) {
                    this.$message.error('上传头像图片只能是jpg、jpeg、gif、png格式!');
                }
                return isImage;
            },
            
            submitForm(formName,callback) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if(callback){
                            callback()
                        }
                    } else {
                        return false;
                    }
                });
            },
            editProject(){
                let _this = this
                let method = 'updateProject'
                if(!_this.ruleForm.projectId){
                    method = 'addProject'
                }
                this.ruleForm.areaProvince = this.ruleForm.area[0]?this.ruleForm.area[0]:''
                this.ruleForm.areaCity = this.ruleForm.area[1]?this.ruleForm.area[1]:''

                this.submitForm('projectForm',function(){
                    standardAsync(_this, method, _this.ruleForm, res => {
                        _this.$message.success('保存成功');
                        if(!_this.ruleForm.projectId){
                            _this.resetForm('projectForm')
                        }
                        _this.$router.back()
                })
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            initProjectInfo(){
                standardAsync(this, 'queryProjectInfo', this.ruleForm, res => {
                    this.ruleForm = res.body.projectInfo
                    let areaCity = this.ruleForm.areaCity
                    let areaProvince = this.ruleForm.areaProvince
                    this.ruleForm.area=[areaProvince,areaCity]
                    this.attachmentInfo.attachmentList= res.body.attachmentList.map(function(item){
                        return{
                            name:item.attachmentName,
                            url:apiConfig.wxImgvUrl+item.attachmentVfsPath
                        }
                    })
                    
                    this.createUserInfo = res.body.createUserInfo
                })
            },
            initProjectType(){
                standardAsync(this, 'getDicList', {"dcCodeMain":"04"}, res => {
                    this.projectTypeOptions = res.body
                })
            },
        }
    }

</script>

<style lang="scss"
       scoped>
    @import '~@/styles/vueImport/index.scss';

    .long-input {
        width: 500px;
    }

    .short-input {
        width: 150px;
    }

    .el-button {
        width: 100px;
    }

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        border: 1px solid $border-color;
        font-size: 28px;
        color: #8c939d;
        width: 250px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        border: 1px solid $border-color;
        /* width: 178px; */
        height: 178px;
        display: block;
    }

    .el-upload__tip p {
        line-height: 2;
        margin: 0;
    }

</style>