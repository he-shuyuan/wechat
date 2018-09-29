<template>
    <div class="app-header-wrapper">
      <div class="app-header">
        <img :src="require('../../../assets/img/logo.png')">
        <p>智能管理平台</p>

        <img :src="require('../../../assets/img/u89.png')"
             class="app-header-img-middle">
        <el-select  v-model="state.insId" placeholder="" id="ins_select">
         <el-option 
               v-for="item in sStore.getDepList()"
               :key="item.insId"
               :label="item.insName"
               :value="item.insId">
         </el-option>
        </el-select>
        <el-dropdown class="avatar-container"
                     trigger="click">
            <div class="avatar-wrapper">
                <img class="user-avatar"
                     :src="require('../../../assets/img/u648.png')">
                <span class="user-name">{{sStore.getUserInfo().userName}}</span>
                <i class="el-icon-caret-bottom"></i>
            </div>
            <el-dropdown-menu class="user-dropdown"
                              slot="dropdown">
                <el-dropdown-item>
                    <span @click="logout()"
                          style="display:block;">退出登录</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
       </div>
    </div>
</template>


<script>
    import { standardAsync, customAsync } from '@/api/async'
    import sessionStorageUtil from "@/utils/sessionStorageUtil"
    export default {
        name: 'AppHeader',
        components: {

        },
        data() {
            return {
                state:{
                    insId: sessionStorageUtil.getInsId(),
                },
                sStore:sessionStorageUtil,
            }
        },
        watch:{
           'state.insId':function(nv){
              this.insIdChange();
           }
        },
        created() {
           this.insIdChange();
        },
        mounted() {

        },
        methods: {
          /**
           * 切换企业
           * @return {[type]} [description]
           */
          insIdChange:function(){
            sessionStorageUtil.setInsId(this.state.insId);
            standardAsync(this,'queryUserRoleFunctionList',{insId:this.state.insId,busTypeId:'1'},res=>{
                 
            },null,null,error=>{
              console.log(error)
            });
        },
        /**
         * 退出登录
         * @return {[type]} [description]
         */
        logout:function(){
           sessionStorageUtil.removeAll();
           this.$router.replace('/login');
        }
    }
    }

</script>

<style>
    .app-header .el-input input{
        background:#EC7700;
        border: none;
        color:#FFF;
        font-size: 18px;
         font-weight: bold;
    }
</style>
<style lang="scss"
       scoped>
   
    .app-header {
        background: #EC7700;
        color: #fff;
        font-weight: bold;
        font-size: 18px;
        position: fixed;
        top: 0;
        right: 0;
        left: 0;
        z-index: 1001;
        height: 70px;
        display: flex;
        align-items: center;

        img {
            height: 40px;
            margin: 0 10px;
        }
        p {
            margin-right: 50px
        }
        .app-header-img-middle {
            height: 18px;
        }

        .avatar-container {
            height: 50px;
            position: absolute;
            right: 30px;

            .avatar-wrapper {
                cursor: pointer;
                margin-top: 5px;
                position: relative;
                .user-avatar {
                    width: 40px;
                    height: 40px;
                    border-radius: 50%;
                    background: #fff;
                }
                .user-name {
                    color: #fff;
                    font-size: 18px;
                    position: relative;
                    top: -10px;
                    right: 8px;
                }
                .el-icon-caret-bottom {
                    position: absolute;
                    right: -15px;
                    top: 30%;
                    font-size: 18px;
                    color: #fff;
                }
            }
        }
    }
    
    .app-header-wrapper {
        height: 70px;
    }
</style>
