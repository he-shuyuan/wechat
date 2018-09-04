<template>
  <div class="login-container">
    <el-form class="login-form"
             autoComplete="on"
             :model="loginForm"
             :rules="loginRules"
             ref="loginForm"
             label-position="left">
      <h3 class="title">智能管理平台</h3>
      <el-form-item prop="phone">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="phone" />
        </span>
        <el-input name="phone"
                  type="text"
                  v-model="loginForm.phone"
                  autoComplete="on"
                  placeholder="phone" />
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"></svg-icon>
        </span>
        <el-input name="password"
                  :type="pwdType"
                  @keyup.enter.native="handleLogin"
                  v-model="loginForm.password"
                  autoComplete="on"
                  placeholder="password"></el-input>
        <span class="show-pwd"
              @click="showPwd">
          <svg-icon icon-class="eye" />
        </span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   style="width:100%;"
                   :loading="loading"
                   @click.native.prevent="handleLogin">
          登　录
        </el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
  import { isvalidPhone } from '@/utils/validate'
  import { standardAsync, customAsync } from '@/api/async'
  import config from '@/api/config'
  import sessionStorageUtil from "@/utils/sessionStorageUtil"
  import localStorageUtil from "@/utils/localStorageUtil"
   
  export default {
    name: 'login',
    data() {
      const isvalidPhones = (rule, value, callback) => {

        if (!isvalidPhone(value)) {
          callback(new Error('手机号码格式不正确'))
        } else {
          callback()
        }
      }
      const validatePass = (rule, value, callback) => {
        if (value.length < 5) {
          callback(new Error('密码不能小于5位'))
        } else {
          callback()
        }
      }
      return {
        loginForm: {
          phone: '',
          password: ''
        },
        loginRules: {
         // phone: [{ required: true, trigger: 'blur', validator: isvalidPhones }],
          password: [{ required: true, trigger: 'blur', validator: validatePass }]
        },
        loading: false,
        pwdType: 'password'
      }
    },
    methods: {
      showPwd() {
        if (this.pwdType === 'password') {
          this.pwdType = ''
        } else {
          this.pwdType = 'password'
        }
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            let params={account:this.loginForm.phone,password:this.loginForm.password,type:"pass"}
              standardAsync(this,'login',params,res=>{
                 sessionStorageUtil.setToken(res.body.token);
                 sessionStorageUtil.setUserInfo(res.body);
                 standardAsync(this,'queryUserInsInfoByUserId',{userId:res.body.userId,busTypeId:'1'},res2=>{
                       if(!res2.body.depList || res2.body.depList.length == 0){
                            this.$message('该用户没有部门');
                            return;
                       }
                       sessionStorageUtil.setDepList(res2.body.depList);
                       this.initFirstInsId(res2.body.depList);
                       this.$router.push('manage/companyManage');
                 });
              });
             this.loading = false;
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      /**
       * 初始化第一个insId
       * @param  {[type]} list [description]
       * @return {[type]}      [description]
       */
      initFirstInsId:function(list){
         let insId = localStorageUtil.getInsId();
         if(insId){
            for(let ob in list){
                if(insId == ob.insId){
                   sessionStorageUtil.setInsId(ob.insId);
                   return;
                }
             }
         }
           sessionStorageUtil.setInsId(list[0].insId);
      },
    }
  }
</script>

<style rel="stylesheet/scss"
       lang="scss">
  $bg:#2d3a4b;
  $light_gray:#eee;

  /* reset element-ui css */

  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        &:-webkit-autofill {
          -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: #fff !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }

</style>

<style rel="stylesheet/scss"
       lang="scss"
       scoped>
  $bg:#2d3a4b;
  $dark_gray:#889aa4;
  $light_gray:#eee;
  .login-container {
    background-image: url('../../assets/img/u0.jpg');
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: $bg;
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 520px;
      padding: 35px 35px 15px 35px;
      margin: 120px auto;
    }
    /* .tips {
      position: absolute;
      bottom:0;
      font-size: 14px;
      color: #fff;
     margin-bottom: 10px;
      div {
        text-align: center;
       &:first-of-type {
         margin-right: 16px;
       }
      }
    } */
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
      &_login {
        font-size: 20px;
      }
    }
    .title {
      font-size: 26px;
      font-weight: 400;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }

</style>
