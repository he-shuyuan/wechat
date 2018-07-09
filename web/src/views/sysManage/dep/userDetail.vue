<template>
  <div class="_con_body">
  <div class="_header">
      <el-tag @click.native="$router.go(-1)">返回</el-tag>
      </div>
      <div class="_body">
         <div class="_title_header">
          用户信息详情
        </div>
         <table>
           <tr>
             <td>昵称</td>
             <td>{{$route.params.data.ucUserDTO.nickName}}
             ( <span v-if="$route.params.data.ucUserDTO.status=='N'" style="color:green">已启用</span>
                <span v-else style="color:red">已禁用</span>
             )
             </td>
              <td>用户编号</td>
             <td>{{$route.params.data.ucUserDTO.userId}}</td>
           </tr>
            <tr>
             <td>工号</td>
             <td>{{$route.params.data.ucUserDTO.empNum}}</td>
             <td>性别</td>
             <td>{{$route.params.data.ucUserDTO.sex}}</td>
           </tr>
            <tr>
             <td>生日</td>
             <td>{{toDatetime($route.params.data.ucUserDTO.birthday,'yyyy-MM-dd')}}</td>
             <td></td>
             <td></td>
           </tr>
            <tr>
             <td>头像</td>
             <td colspan="2">{{$route.params.data.ucUserDTO.userImage}}</td>
             <td></td>
           </tr>
            <tr>
             <td>备注</td>
             <td colspan="3">{{$route.params.data.ucUserDTO.remark}}</td>
           </tr>
         </table>
        </div>
     <div class="_title_header">
           登录方式列表
       </div>
        <el-table 
           :header-cell-style="{'background-color':'#FFEFCE'}"
           :data="$route.params.data.ucUserLoginDTOList"
          style="width: 100%;margin-bottom:50px">
       <el-table-column
        type="index"
        label="序号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="loginAccount"
        label="登录账号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="loginType"
        label="登录方式"
        width="200">
        <template slot-scope="scope">
           {{loginType(scope.row.loginType)}}
        </template>
       </el-table-column>
      <el-table-column
        prop="password"
        label="密码">
         <template slot-scope="scope">
           <span v-if="scope.row.loginType=='accountPassword'">********</span>
           <span v-else>无</span>
        </template>
      </el-table-column>
       <el-table-column
         prop="status"
        label="状态">
         <template slot-scope="scope">
            <el-tag v-if="scope.row.status=='Y'">已禁用</el-tag>
          <el-tag v-else>已启用</el-tag>
        </template>
      </el-table-column>
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
                
              },
                ruleForm:{
                
                },
                rules:[{}],
            }
           
        },
        beforeRouteEnter (to, from, next) {
          next(vm => {
           
          })
        },
        created(){
          
        },
        mounted() {
        
        },
        methods: {
          toDatetime:util.toDatetime,
          /**
           * 登录方式
           * @param  {[type]} type [description]
           * @return {[type]}      [description]
           */
          loginType:function(type){
             switch(type){
               case 'accountPassword' : return '账密登录';
               case 'wechatAutoLogin' : return '微信自动登录';
               case 'emailVcode' : return '邮箱验证码登录';
               case 'phoneVcode' : return '手机验证码登录';
               default : return '其他方式登录';
             }      
          },
         
          },
        }
   

</script>

<style scoped>
.el-tag{
  width:150px;
  text-align: center;
}
.el-tag:hover{
  background-color: green;
  cursor:pointer;
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
