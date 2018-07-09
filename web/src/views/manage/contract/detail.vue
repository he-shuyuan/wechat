<template>
<div class="_con_body">
<div class="_header">
<router-link  class="_edit_link"
:to="{name:'addOrUpdate',params: {op:'edit'},params:{contractId:init.contractId}}">
      <el-tag >编辑合同信息</el-tag>
      </router-link>
      </div>
      <div class="_body">
         <div class="_title_header">
   项目合同信息概览
 </div>
         <table>
           <tr>
             <td>合同编号</td>
             <td>{{init.contractId}}</td>
              <td>合同编号</td>
             <td>{{init.contractName}}</td>
           </tr>
            <tr>
             <td>所属项目</td>
             <td>{{init.projectName}}</td>
              <td>合同类型</td>
             <td>{{dic.contractTypeMap[init.contractType]}}</td>
           </tr>
            <tr>
             <td>合同金额</td>
             <td>￥{{toMoney(init.contractMoney)}}</td>
             <td></td>
             <td></td>
           </tr>
            <tr>
             <td>甲方单位</td>
             <td>{{init.acompanyName}}</td>
              <td></td>
             <td></td>
           </tr>
            <tr>
             <td>乙方单位</td>
             <td>{{init.bcompanyName}}</td>
              <td></td>
             <td></td>
           </tr>
            <tr>
             <td>录入人员</td>
             <td>{{init.createUserName}}</td>
              <td>录入时间</td>
             <td>{{toDatetime(init.createTime,'yyyy-MM-dd')}}</td>
           </tr>
            <tr>
             <td>备注</td>
             <td colspan="3">{{init.bz}}</td>
           </tr>
             <tr>
             <td>合同附件</td>
             <td colspan="3" style="text-align: left;padding:0;margin:0">
               <ol>
                <li v-for="item in attachmentList">
                <a :href="item.url">{{item.name}}</a>
                </li>
               </ol>
             </td>
           </tr>
         </table>
        </div>
 <div class="_title_header">
进度款申请清单
 </div>
        <el-table 
        header-cell-style="background-color:#FFEFCE;"
    :data="contractpaytList"
    style="width: 100%;margin-bottom:50px">
    <el-table-column
      type="index"
      label="序号">
    </el-table-column>
    <el-table-column
      prop="declareName"
      label="申报名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="issuingOffice"
      label="开票单位"
      width="180">
    </el-table-column>
    <el-table-column
      prop="issuingMoney"
      label="开票金额">
    </el-table-column>
     <el-table-column
      prop="createUser"
      label="填报人">
    </el-table-column>
     <el-table-column
      prop="createTime"
      label="填报日期">
      <template slot-scope="scope">
        {{ toDatetime(scope.row.createTime,'yyyy-MM-dd') }}</span>
      </template>
    </el-table-column>
  </el-table>
 </div>

</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import uniFormHeader from '../../../components/uniFormHeader.vue' 
    import util from "@/utils/"
    import apiConfig from '@/api/config'
    export default {
      components: { uniFormHeader },
        data() {
            return {
              init:{
                contractId:'',
                contractName:'',
                projectName:'',
                contractType:'',
                contractId:'',
                acompanyId:'',
                bcompanyId:'',
                createUserId:'',
                createDate:''
              },
              dic:{
                contractTypeMap:{},
              },
              contractpaytList:[],
              attachmentList:[],
            }
           
        },
        beforeRouteEnter (to, from, next) {
          next(vm => {
           if(to.params.contractId){
               vm.init.contractId = to.params.contractId;
               vm.getContractType();
               vm.queryContractInfo();
           }
          })
        },
        created(){
          
        },
        mounted() {
            //this.queryContractInfo();
        },
        methods: {
          toMoney:util.toMoney,
          toDatetime:util.toDatetime,
          /**
           * 获取合同详情
           * @return {[type]} [description]
           */
          queryContractInfo:function(){
            standardAsync(this,'queryContractInfo'
                    ,{contractId:this.init.contractId}
                    ,res=>{
               this.init=res.body;
               this.contractpaytList = res.body.contractpaytList;
               this.attachmentList = res.body.attachmentList.map(function(item){
                        return{
                            name:item.attachmentName,
                            url:apiConfig.wxImgvUrl+item.attachmentVfsPath
                        }
                    });
                })
            },
            getContractType:function(){
              standardAsync(this,'getDicList',{dcCodeMain:'06'},res=>{
                res.body.forEach(ob=>{
                    this.dic.contractTypeMap[ob.dcCode]=ob.dcname;
                })
              });
            },
          }
        }
   

</script>

<style scoped>
.el-tag{
  width:150px;
  text-align: center;
}
.el-tag:hover{
  background-color: green
}
._header{
 height:48px;
 width:100%;
 text-align: right;
}
._edit_link{
  display:block;
  margin:10px 10px 10px 10px;
} 
._body table td,._body table th{
  border-right: 1px solid #ebeef5;
  border-bottom:  1px solid #ebeef5;
  text-align: center
}
._body table{
    width:100%;
    height:500px;
    margin-bottom: 50px;
    border-top: 1px solid #ebeef5;
    border-left: 1px solid #ebeef5;
}
._body table td:nth-child(odd){
  width:15%;
}
._body table td:nth-child(even){
  width:35%;
}
._title_header{
  width:100%;
  padding: 18px;
  text-align: center;
  border-bottom: 1px solid #fff;
  background: #e4e7ed;
  font-weight: bold;
}
._con_body{
  width:94%;
  margin:0px 3% 20px 3%;
}
ol{
  margin:0;
}
</style>
