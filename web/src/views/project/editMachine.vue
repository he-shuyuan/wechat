<template>
    <uni-form-header title="机械编辑">
        <!-- <div style="margin-top: 15px; width:36%;">
            <el-input placeholder="请输入搜索关键词" v-model="searchStr">
                <template slot="append">搜索</template>
            </el-input>
        </div> -->
        <el-row style="margin-top:20px;">
            <el-transfer v-model="value1" 
                filterable 
                :data="transferdata" 
                :titles="['未选中机械', '选中机械']"
                :filter-method="filterMethod"
                :props="{
                    key: 'machineId',
                    label: 'machineName'
                    }">
                 <div slot-scope="{ option }" class="option-content">
                    <div class="machine-img">
                        <img :src="imgUrl(option['machineImg'])">
                    </div>
                    <div class="machine-desc">
                    <span class="machine-name">{{option['machineName']}}</span><br>
                    <span class="machine-id">{{option['machineId']}}</span>
                    </div>
                    <!-- <div class="machine-source">
                        {{option.machineType}}
                    </div> -->
                </div>
            </el-transfer>
        </el-row> 
        <el-row style="margin-top:30px;">
            <el-col :span="3" :offset="7">
                <el-button type="primary" @click="cfgProjectMachine">确定</el-button>    
            </el-col>
            <el-col :span="3">
                <el-button @click="toUrl('/manage/project/detail?activeName=machine&projectId='+projectId)">取消</el-button>
            </el-col>
        </el-row>
    </uni-form-header>
</template>

<script>
    import api from '@/api/'
    import {getUUID} from '@/api/'
    import uniFormHeader from '../../components/uniFormHeader.vue'
    import { standardAsync, customAsync } from '@/api/async'
    import apiConfig from '@/api/config'
    export default {
        components: {
            uniFormHeader,
        },
        data() {
            return{
                projectId:this.$route.query.projectId,
                searchStr:'',
                transferdata: [],
                value1: [],
                imgHolder:require('../../assets/img/100x70.png'),
            }
        },
        mounted() {
             this.queryPorjectUnMachine();
          
        },
        watch:{
            searchStr:function(val){
                console.log(val);

            },
        },
        methods: {
            toUrl(path){
                if(path!=''){
                    this.$router.push(path);
                }
            },
            filterMethod(query, item){
                if(query == ''){
                    return true;
                }else{
                    for(var i in item){
                        if(item[i]!=''&&item[i]!=null && item[i].indexOf(query) > -1){
                            return true;
                        }  
                    }
                }
            },
            queryPorjectUnMachine(){
                var obj = {
                    projectId:this.projectId,
                };
                standardAsync(this,'queryPorjectUnMachine',obj,res=>{
                        if(res.head.errorCode == 0){
                          this.transferdata = res.body;            
                        }     
                })
            },


            cfgProjectMachine(){
                console.log("cfgProjectMachine");
                console.log(this.value1);
                if(this.value1.length == 0){
                     this.$message({
                        message: '请选择要添加的机械',
                        type: 'warning'
                    });
                    return ;
                }
                 var obj = {
                    projectId:this.projectId,
                    machineList:this.value1,
                };
                standardAsync(this,'cfgProjectMachine',obj,res=>{
                        if(res.head.errorCode == 0){
                            var self = this;
                            this.$message({
                                message: '添加成功',
                                type: 'success',
                                duration:1000,
                                onClose:function(){
                                    self.toUrl('/manage/project/detail?activeName=machine&projectId='+self.projectId);
                                }
                            });         
                        }     
                })
            },

            imgUrl(path) {
                return path ? apiConfig.wxImgvUrl + path : this.imgHolder
            },
                
           
        },

    }

</script>

<style lang="scss" scoped>
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
<style>
    .el-transfer-panel{
        width: 36%;
    }

    .el-transfer-panel__item{
        height: 100px;
    }
    .machine-img img{
        width: 50px;
        height:50px;
    }
    .option-content{
        display: flex;
        display: -webkit-flex;
        align-items:center;
        align-content: space-around;

    }
    .option-content>div{
        padding: 0 6px;
    }

    .machine-source{
        word-wrap:break-word;
        word-break:break-all;
    }
    

    .el-transfer-panel__list *{
        white-space:normal ;
    }
</style>
