<template>
    <el-upload
        class="upload-demo"
        drag
        :action="uploadActionUrl"
        :file-list="attachmentInfo.attachmentList"
        :list-type="attachmentInfo.listType"
        :before-upload="beforeUpload"
        :on-success="onSuccess"
        :on-remove="onRemove"
        :on-error="onError"
        :on-preview="onPreview"
        :before-remove="beforeRemove"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">可长传合同及其他资料文件（word、excel、ppt、pdf 等，大小限制：≤10M）</div>
    </el-upload> 
</template>

<script>
    import {getUUID} from '@/api/'
    import { standardAsync, customAsync } from '@/api/async'
    import apiConfig from '@/api/config'
    import { Loading, Message } from 'element-ui'

    export default {
        props: {
            attachmentInfo: {
                type: Object,
                required: true
            },
        },
        data() {
            return {
                uploadActionUrl:''
            }
        },
        created() {

        },
        mounted() {
            this.uploadActionUrl="/mgateway/back/communal/attachmentService/upload?requestId="
                    +getUUID()+"&appId=" + apiConfig.appId
                    +"&attachmentYwlx=1701&attachmentYwid="+this.attachmentInfo.attachmentYwid
        },
        methods: {
            //附件处理
            beforeUpload(file){
                console.log('beforeUpload',file)
            },
            onRemove(file, fileList){
                console.log('onRemove',file)
            },
            onSuccess(response, file){
                console.log('onSuccess',file,response)
                if(response.head.errorCode=='0'){
                    file.url=apiConfig.wxImgvUrl+response.body[0].fillePath
                }
                let foundIndex = this.attachmentInfo.attachmentList.findIndex(item=>{
                      return item.name==file.name && item.uid!=file.uid
                })
                if(foundIndex>-1){
                    this.attachmentInfo.attachmentList.splice(foundIndex,1)
                }
                console.log('this.attachmentInfo.attachmentList',this.attachmentInfo.attachmentList)
                this.attachmentInfo.attachmentList.push(file)
            },
            onError(err, file, fileList){
                console.log('onError',file,err) 
                console.log(err)
                console.log(err.message)
                Message.error({ message: err.message })
            },
            onPreview(file){
                console.log('onPreview',file)
            },
            beforeRemove(file, fileList){
                this.ayncRemoveFile(file,fileList,this.attachmentInfo)
            },
            async ayncRemoveFile(file,fileList,attachmentInfo){
                await  this.promiseRemoveFile(file,fileList,attachmentInfo)
            },
            promiseRemoveFile(file,fileList,attachmentInfo){
                let attachmentVfsPath = file.url.substr(apiConfig.wxImgvUrl.length)
                return new Promise(resolve => standardAsync(this, 'removeFile', {"attachmentYwid":attachmentInfo.attachmentYwid,"attachmentVfsPath":attachmentVfsPath}, res => {
                    if(res.head.errorCode=='0'){
                        let foundIndex = attachmentInfo.attachmentList.findIndex(item=>{
                            return item.name==file.name && item.uid==file.uid
                        })
                        if(foundIndex>-1){
                            attachmentInfo.attachmentList.splice(foundIndex,1)
                        }
                        return true
                        resolve()
                    }                    
                }))
            }
        },
    }

</script>


<style lang="scss"
       scoped>
    @import '~@/styles/vueImport/index.scss';

</style>
