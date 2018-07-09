<template>
    <uni-form-header title="机械基本信息">
        <el-form :model="theForm"
                 :rules="rules"
                 ref="theForm"
                 label-width="100px"
                 class="demo-theForm">

            <el-form-item label="机械名称"
                          prop="machineName">
                <el-input v-model="theForm.machineName"
                          placeholder="请输入"
                          class="long-input"></el-input>
            </el-form-item>
            <el-form-item label="机械类型"
                          prop="machineType">
                <el-cascader placeholder="试试搜索：挖掘机"
                             clearable
                             class="long-input"
                             :show-all-levels="false"
                             expand-trigger="hover"
                             v-model="theForm.machineType"
                             :options="machineTypeOption"
                             filterable></el-cascader>
            </el-form-item>
            <el-form-item label="品牌型号"
                          prop="brandId">
                <el-select v-model="theForm.brandId"
                           class="long-input"
                           placeholder="请选择">
                    <el-option v-for="(item,index) in brandIdOption"
                               :key="index"
                               :label="item.label"
                               :value="item.value"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="机械图片"
                          prop="machineImg">
                <el-upload
                    ref="picupload"
                    accept="image/*"
                    :action="uploadActionUrl"
                    list-type="picture-card"
                    :file-list="attachmentlist"
                    multiple
                    :before-upload="beforeAvatarUpload"
                    :on-preview="handlePictureCardPreview"
                    :on-success="handleAvatarSuccess"
                    :before-remove="beforeRemove"
                    :on-remove="onRemove"
                    :auto-upload="machineId ? true:false">
                    <i class="el-icon-plus"></i>
                     <div slot="tip"
                        class="el-upload__tip">
                        <p>支持jpg、jpeg、gif、png格式上传，</p>
                        <p>建议使用尺寸800*800像素以上，大小不超过2M的正方形图片</p>
                    </div>
                </el-upload>
            </el-form-item>

            <!-- <el-form-item label="机械图片"
                          prop="machineImg">
                <el-upload class="avatar-uploader"
                           accept="image/*"
                           :action="uploadActionUrl"
                           :show-file-list="false"
                           :on-success="handleAvatarSuccess"
                           :before-upload="beforeAvatarUpload">
                    <img v-if="theForm.machineImg"
                         :src="theForm.machineImg"
                         class="avatar">
                    <i v-if="!theForm.machineImg"
                       class="el-icon-plus avatar-uploader-icon"></i>
                    <el-button v-if="theForm.machineImg"
                               @click.native.stop.passive="theForm.machineImg='';remoteImgAddress=''"
                               class="avatar-delete"
                               type="danger"
                               icon="el-icon-delete"
                               circle></el-button>
                    <div slot="tip"
                         class="el-upload__tip">
                        <p>支持jpg、jpeg、gif、png格式上传，</p>
                        <p>建议使用尺寸800*800像素以上，大小不超过2M的正方形图片</p>
                    </div>
                </el-upload>
            </el-form-item> -->


            <el-form-item label="出厂日期"
                          prop="manufactureTime">
                <el-date-picker type="date"
                                placeholder="选择日期"
                                class="long-input"
                                :picker-options="pickerOptions"
                                v-model="theForm.manufactureTime"></el-date-picker>
            </el-form-item>
            <el-form-item label="已使用年限"
                          prop="usedYears">
                <el-col :span="5">
                    <el-input type="number"
                              min="0"
                              class="long-input"
                              :max="maxUsedYears"
                              v-model.number="theForm.usedYears">
                        <template slot="append">年</template>
                    </el-input>
                </el-col>
            </el-form-item>
            <el-form-item label="机械来源"
                          prop="machineSource">
                <el-select v-model="theForm.machineSource"
                           class="long-input"
                           placeholder="请选择">
                    <el-option v-for="(item,index) in machineSourceOption"
                               :key="index"
                               :label="item.label"
                               :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item v-if="isBorrowed"
                          label="所属个体户"
                          prop="machineOwner">
                <el-select v-model="theForm.machineOwner"
                           class="long-input"
                           placeholder="请选择">
                    <el-option v-for="(item,index) in machineOwnerOption"
                               :key="index"
                               :label="item.label"
                               :value="item.value"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item style="margin: 50px 0;">
                <el-button type="primary"
                           class="long-button"
                           @click="submitForm('theForm')">保存</el-button>
                <el-button @click="resetForm('theForm')"
                           class="long-button">重置</el-button>
            </el-form-item>
        </el-form>
    </uni-form-header>
</template>
<script>
    import uniFormHeader from '../../components/uniFormHeader.vue'
    import { standardAsync } from '@/api/async'
    import util from '@/utils/index'
    import apiConfig from '@/api/config'
    import { getUUID } from '@/api/'

    export default {
        components: { uniFormHeader },
        data() {
            return {
                isAddingMode: true, //添加模式/编辑模式
                machineId: this.$route.query.machineId ? this.$route.query.machineId:'',

                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                },
                attachmentlist:[],
                uploadActionUrl:"/mgateway/back/communal/attachmentService/upload?requestId="+ getUUID() + "&appId=" 
                + apiConfig.appId+"&attachmentYwlx=1703&attachmentYwid="+(this.$route.query.machineId?this.$route.query.machineId:''),
                theForm: {
                    machineName: '',//机械名称
                    machineType: [],//机械类型
                    brandId: '',//品牌型号
                    manufactureTime: '',//出厂日期
                    machineImg: '',//机械图片
                    usedYears: '',//已使用年限
                    machineSource: '',//机械来源
                    machineOwner: '',//所属个体户
                },
                remoteImgAddress: '',
                oldTypeId: '',
                asyncWait: 2,

                rules: {
                    machineName: [{ required: true, min: 3, max: 30, message: '机械名称长度至少3个字符，最长30个字符', trigger: 'change' }],
                    machineType: [{ required: true, message: '请选择', trigger: 'change' }],
                    brandId: [{ required: true, message: '请选择品牌型号', trigger: 'change' }],
                    manufactureTime: [{ required: true, message: '请选择出厂日期', trigger: 'change' }],
                    usedYears: [{ required: true, type: 'number', min: 0, max: 99, message: '根据机械设备从开始使用至今的年份数', trigger: 'change' }],
                    machineSource: [{ required: true, message: '请选择', trigger: 'change' }],
                    machineOwner: [{ required: true, message: '请选择', trigger: 'change' }],
                },

                machineTypeOption: [],
                brandIdOption: [],
                machineSourceOption: [],
                machineOwnerOption: [],
            };
        },
        created() {
            this.initData()
            this.machineId = this.$route.query.machineId
            if (this.machineId) {
                this.isAddingMode = false
                standardAsync(this, 'queryMachineInfo', { machineId: this.machineId }, res => {
                    for (const key in this.theForm) {
                        if (this.theForm.hasOwnProperty(key)) {
                            this.theForm[key] = res.body[key]
                        }
                    }
                   
                    this.attachmentlist = res.body.attachmentlist.map(
                        (item)=>{
                            return {
                                    name:item.attachmentName,
                                    url:apiConfig.wxImgvUrl+item.attachmentVfsPath
                                }
                        }
                    );

                    // this.remoteImgAddress = this.theForm.machineImg
                    // if (this.theForm.machineImg)
                    //     this.theForm.machineImg = apiConfig.wxImgvUrl + this.theForm.machineImg
                    this.oldTypeId = this.theForm.machineType
                    this.theForm.machineType = []
                    --this.asyncWait
                })
            }
        },
        computed: {
            // 是租赁
            isBorrowed() {
                let value = this.theForm.machineSource
                let found = this.machineSourceOption.find(item => item.value === value)
                if (found)
                    return found.label === '租赁'
                else
                    return false
            },
            maxUsedYears() {
                let time = this.theForm.manufactureTime
                let maxYear
                if (time) {
                    maxYear = new Date().getFullYear() - new Date(time).getFullYear() + 1
                } else {
                    maxYear = 99
                }
                this.rules.usedYears[0].max = maxYear
                return maxYear
            }
        },
        watch: {
            asyncWait(newVal) {
                if (newVal === 0) {
                    // 设置machineType默认选中
                    for (let i = 0, len = this.machineTypeOption.length; i < len; i++) {
                        if (this.theForm.machineType.length > 0)
                            break
                        let father = this.machineTypeOption[i]
                        let children = father.children
                        for (let j = 0, len2 = children.length; j < len2; j++) {
                            if (children[j].value === this.oldTypeId) {
                                this.theForm.machineType = [father.value, children[j].value]
                                break
                            }
                        }
                    }
                }
            },
            'theForm.machineSource'(newValue, oldValue) {
                if (this.isBorrowed) {
                    this.theForm.machineOwner = ''
                } else {
                    this.theForm.machineOwner = util.sStore.getItem(util.sStore.MACE_SELECED_INS_ID)
                }
            },
        },
        mounted() {
            console.log(this.rules.usedYears)
        },
        methods: {
            initData() {
                const dictMapper = (list, labelName, valueName) => list.map(item => ({
                    label: item[labelName],
                    value: item[valueName],
                }))

                standardAsync(this, 'getDicGroupList', { dcGroup: 'jxgroup' }, res => {
                    this.machineTypeOption = res.body.map(item => ({
                        value: item.dcCodeMain,
                        label: item.dcNameMain,
                        children: dictMapper(item.dcList, 'dcName', 'dcCode')
                    }))
                    --this.asyncWait
                })
                standardAsync(this, 'getDicList', { dcCodeMain: '03' }, res => {
                    this.brandIdOption = dictMapper(res.body, 'dcname', 'dcCode')
                })
                standardAsync(this, 'getDicList', { dcCodeMain: '02' }, res => {
                    this.machineSourceOption = dictMapper(res.body, 'dcname', 'dcCode')
                })
                let param = {
                    insStyle: '1802',
                    currentInsId: util.lStore.getItem(util.lStore.MACE_SELECED_INS_ID),
                }
                standardAsync(this, 'queryInstitutionList', param, res => {
                    this.machineOwnerOption = dictMapper(res.body, 'insName', 'insId')
                })
            },
            handleAvatarSuccess(res, file,fileList) {
                // this.theForm.machineImg = URL.createObjectURL(file.raw);
                // this.remoteImgAddress = res.body[0].fillePath
               // this.attachmentlist = fileList;

            },
            beforeAvatarUpload(file) {
                const isImage = /^image\/(jpe?g|png|gif)$/i.test(file.type)
                if (!isImage) {
                    this.$message.error('上传图片只能是jpg、jpeg、gif、png格式!');
                }
                return isImage;
            },
            beforeRemove(file, fileList){
                this.ayncRemoveFile(file,fileList,this.attachmentInfo)
            },
            async ayncRemoveFile(file,fileList,attachmentInfo){
                await  this.promiseRemoveFile(file,fileList,attachmentInfo)
            },
             promiseRemoveFile(file,fileList,attachmentInfo){
                let attachmentVfsPath = file.url.substr(apiConfig.wxImgvUrl.length)
                return new Promise(resolve => standardAsync(this, 'removeFile', {"attachmentYwid":this.machineId,"attachmentVfsPath":attachmentVfsPath}, res => {
                    if(res.head.errorCode=='0'){
                        return true
                        resolve()
                    }                    
                }))
            },
            onRemove(file, fileList){
                console.log('onRemove',file)
            },
          
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },



            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let param = { ...this.theForm }
                        console.log("this.theForm ", this.theForm);
                        param.machineType = param.machineType[1]
                        // param.manufactureTime = util.toDate(param.manufactureTime)
                        param.machineImg = this.remoteImgAddress
                        console.log("提交param ", param);
                        if (this.isAddingMode) {
                            standardAsync(this, 'addMachine', param, res => {
                                this.uploadActionUrl ="/mgateway/back/communal/attachmentService/upload?requestId="
                                + getUUID() + "&appId=" + apiConfig.appId+"&attachmentYwlx=1703&attachmentYwid=" + res.body;
                                this.$nextTick(()=>{
                                    this.$refs.picupload.submit();
                                    this.$message.success('添加成功');
                                    this.$router.back();
                                }) 
                            })
                        } else {
                            param.machineId = this.machineId
                            standardAsync(this, 'updateMachine', param, res => {
                                this.$message.success('修改成功');
                                this.$router.back()
                            })
                        }
                    } else {
                        this.$message.error('保存失败');
                        return false;
                    }
                });
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
    }
</script>


<style lang="scss"
       scoped>
    @import '~@/styles/vueImport/index.scss';
    .avatar-uploader {
        .avatar-delete {
            position: absolute;
            top: 0;
            right: 0;
        }
    }

</style>
