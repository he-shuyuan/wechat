<template>
    <div class="app-header-wrapper">
      <div class="app-header">
        <img :src="require('../../../assets/img/logo.png')">
        <p>机械智能手</p>

        <img :src="require('../../../assets/img/u89.png')"
             class="app-header-img-middle">

        <el-dropdown v-if="insName"
                     trigger="click"
                     style="color: #fff;font-size: 16px;cursor: pointer;"
                     @command="handleCommand"
                     >
            <span class="el-dropdown-link">
                {{insName?insName:''}}
                <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item :command="item" v-for="(item,index) in insInfo" :key="index">{{item.insName}}</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <el-dropdown class="avatar-container"
                     trigger="click">
            <div class="avatar-wrapper">
                <img class="user-avatar"
                     :src="require('../../../assets/img/u648.png')">
                <span class="user-name">{{username}}</span>
                <i class="el-icon-caret-bottom"></i>
            </div>
            <el-dropdown-menu class="user-dropdown"
                              slot="dropdown">
                <el-dropdown-item>
                    <span @click="logout"
                          style="display:block;">退出登录</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
       </div>
    </div>
</template>


<script>
    import { standardAsync, customAsync } from '@/api/async'
    import loginUtil from "@/utils/loginUtil"
    import sessionStorageUtil from "@/utils/sessionStorageUtil"
    import localStorageUtil from "@/utils/localStorageUtil"
    export default {
        name: 'AppHeader',
        components: {

        },
        data() {
            return {
                username: '',
                insInfo: '',
                insName: ''

            }
        },
        created() {
            //debugger
            this.insInfo=sessionStorageUtil.getItem(sessionStorageUtil.MCE_INS_INFO)
            this.insName=sessionStorageUtil.getItem(sessionStorageUtil.MACE_SELECED_INS_NAME)
            let userInfo = sessionStorageUtil.getItem(sessionStorageUtil.MCE_USER_INFO)
            if (userInfo && userInfo.nickName) {
                this.username = userInfo.nickName
            }
        },
        mounted() {

        },
        methods: {
            handleCommand(command){
            //debugger
            let selectedInsId=sessionStorageUtil.getItem(sessionStorageUtil.MACE_SELECED_INS_ID)
            if(command.insId==selectedInsId){

                return ;
            }
            this.insName=command.insName
            let selectIns={userId:sessionStorageUtil.getItem(sessionStorageUtil.MCE_USER_ID),insId:command.insId,insName:command.insName,depId:command.depId}
            loginUtil.changeIns(selectIns)
            let params={insId:command.insId}
            standardAsync(this, 'changeLoginIns', params, res => {
                  let param={};
                  param.router=this.$router;
                  param.functionList=res.body.functionList;
                  param.funCodeList=res.body.funCodeList;
                  this.$store.dispatch('changeIns', param);
                })

            },
            logout() {

                const loading = this.$loading({
                    lock: true,
                    text: '退出登录中...',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });

                standardAsync(this, 'logout', {}, res => {
                    loginUtil.logout();
                    loading.close();
                    this.$router.push("/login");
                })

            }
        },
    }

</script>


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
