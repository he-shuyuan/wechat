<template>
<div>
    <el-card class="box-card">
 
    <div class="text item">
         <el-tabs>
            <el-tab-pane label="合同进度款申报">
                <el-form ref="form"  label-width="200px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="合同编号:">
                                {{contractId}}
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="合同名称:">
                                {{contractName}}
                            </el-form-item>
                        </el-col>
                    </el-row>   
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="所属项目:">
                                {{bcompanyName}}
                            </el-form-item>
                        </el-col>
                    </el-row>   

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="合同金额:">
                                {{'￥'+contractMoney}}
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="项目单位:">
                                {{acompanyName}}
                            </el-form-item>
                        </el-col>
                    </el-row> 
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="申报名称:" required="true">
                                <el-input v-model="declareName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="开票单位:">
                               {{bcompanyName}}
                            </el-form-item>
                        </el-col>
                    </el-row> 
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="进度款开票金额:" required="true">
                                <el-input v-model="issuingMoney"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="开票金额(大写):">
                               {{ issuingMoneyUp}}
                            </el-form-item>
                        </el-col>
                    </el-row> 
                    <!-- <el-row>
                        <el-col :span="12">
                            <el-form-item label="填报人">
                                阿萨辛
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="填报日期">
                               2018-03-31
                            </el-form-item>
                        </el-col>
                    </el-row>   -->
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="合同附件:">
                                暂无附件
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row type="flex" class="row-bg" justify="space-around">
                        <el-button type="success"  @click="declareContractPay" style="width:200px;">提交</el-button>
                    </el-row>      
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </div>
    </el-card>

 </div>
</template>

<script>
    import { moneyChineseStr } from '@/utils/moneyFormat'
    import { standardAsync, customAsync } from '@/api/async'
    export default {
        data() {
            return {
                contractId:this.$route.params.contractId ? this.$route.params.contractId:'' ,//'HT20180606b5rF6',//合同编号
                contractName:'',//合同名称
                bcompanyName:'',
                contractMoney:'',
                acompanyName:'',
                declareName:'',//
                bcompanyId:'',
                bcompanyName:'',
                issuingMoney:'',//
                issuingMoneyUp:'零元整',


            }
        },
        beforeRouteEnter (to, from, next) {
        next(vm => {
           
        })
        },
        created(){
         
        },
        mounted() {
            this.queryContractInfo();
        },
        filters:{ },
        methods: {

            //获取申报信息
            queryContractInfo(){
                standardAsync(this,'queryContractInfo',{contractId:this.contractId},res=>{
                    console.log(res); 
                    if(res.head.errorCode == 0){
                        this.contractName = res.body.contractName; 
                        this.bcompanyName = res.body.bcompanyName;
                        this.contractMoney = res.body.contractMoney;
                        this.acompanyName = res.body.acompanyName;
                        this.bcompanyId = res.body.bcompanyId;
                        this.bcompanyName = res.body.bcompanyName;
                    }
                    
                })
            },



             //保存申报
            declareContractPay(){ 
                var self = this;
                if(this.declareName ==""){
                    this.$message("申报名称不能为空");
                    return;

                }else if(this.issuingMoney == ""){
                    this.$message("进度款开票金额不能为空")
                    return;
                }
                console.log("declareContractPay测试啊啊");
                 var obj = {
                     contractId:this.contractId,
                     declareName:this.declareName,
                     issuingMoney:this.issuingMoney,
                     issuingOffice:this.bcompanyId,
                 }
                  standardAsync(this,'declareContractPay',obj,res=>{
                      console.log(res);
                      if(res.head.errorCode == 0){
                            this.$message({
                                showClose: true,
                                message: '申报成功',
                                type: 'success',
                                onClose:()=>{
                                    self.$router.push('/manage/contract');
                                },
                            });
                      }
                      
                })
            },
            
        },

        watch:{
            issuingMoney(val){
                if(val != ''){
                  this.issuingMoneyUp = moneyChineseStr(val);  
                }else{
                    this.issuingMoneyUp = '零元整';
                }
                
            },
        }
    }
   

</script>

<style scoped>

</style>
