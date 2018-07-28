<template>
  <el-container>
  <el-header>
   <span>当前机构：{{$route.params.depName}}</span>    
   <el-tag @click.native="$router.go(-1)" class="_back">返回</el-tag>
  </el-header>
  <el-main>
      <user-manager :depId="$route.params.depId"
         :pathCode="$route.params.pathCode"
         :insId="$route.params.depId"></user-manager>
  </el-main>
  </el-container>
</template>
<script>
import { standardAsync, customAsync } from '@/api/async'
import userManager from './userManager';
import util from "@/utils/"
export default {
  components:{
      userManager
  },
    data() {
        return {
          init:{
             depId:'',
          }
        }
    },
    mounted(){
     console.log(this.$route.params.pathCode,this.$route.params.depId)
    },
    methods:{
   

    }
};
</script>
<style scoped>
.el-header{
  border-bottom:solid 1px #e4e7ed; 
  padding: 18px;
  text-align: center;
  border-bottom: 1px solid #fff;
  background: #e4e7ed;
  font-weight: bold;
}
._back{
  width:120px;
  text-align: center;
  cursor:pointer;
  float:right;
}
._back:hover{
  background-color: green;
}
</style>