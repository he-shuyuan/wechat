<template>
    <router-view v-if="$route.name !== thisRouteName"></router-view>
    <mc-table v-else
              stripe ref="refCreateTable" 
              :formOpt="formOpt"
              showAdd 
              addBtnText="新增公司" 
              :columns="columns"
              @addEvent="$router.push({name: 'addorModifyCompany', params: {name:'addCompany'}})"
              method="pageQueryAdminInstitutionDTOList" :params="params">
        <template slot-scope="scope" slot="handle">
            <el-button size="mini" type="danger" v-if="isShowButton"
            @click.native="$router.push({name:'userView',params:{depId:scope.row.insId,pathCode:scope.row.pathCode,depName:scope.row.insName}})">用户管理</el-button>
            <el-button size="mini" type="success" @click="editCompany(scope.row)">编辑</el-button>
            <el-button v-if="true" size="mini" type="danger" @click="deleteCompany(scope.row)">删除</el-button>
        </template>
    </mc-table>
</template>

<script>
    import { standardAsync, customAsync } from '@/api/async'
    import { Message, MessageBox} from 'element-ui';
    import formOpt from './resouce/formOption/companyFormOption'
    import columns from './resouce/tableOption/companyTableOption'
    import util from "utils"
    export default {
        data() {
            const _this = this
            return {
                formOpt,
                columns,
                params: {isValid:'Y'},
                thisRouteName: 'companyManage',
                isShowButton:util.sStore.getItem(util.sStore.AUTH_MENU_LIST) && util.sStore.getItem(util.sStore.AUTH_MENU_LIST).indexOf('_user_manage')>-1,
            }
        },
        methods: {
            editCompany(row) {
                this.$router.push({
                    name: 'addorModifyCompany',
                    params: {
                        insId: row.insId,
                        name:'modifyCompany'
                    }
                })
            },
            deleteCompany(row) {
                let _this = this
                let params = {
                    "insId":row.insId,
                    "isValid":"N"
                }
                  MessageBox.confirm('确定删除此记录吗?', '确定', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    standardAsync(_this, 'updateAdminInstitution', params, res => {
                         _this.$notify({
                            title: '提示',
                            message: '删除成功',
                            type: 'success',
                            duration: 2000
                        });
                        _this.$refs.refCreateTable.searchHandle()
                    })
                })
            }
        }
    }
</script>

<style>
</style>