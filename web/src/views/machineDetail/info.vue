<template>
    <div>
        <el-card shadow="always">
            <div slot="header"
                 class="clearfix">
                <span style="line-height: 35px">基本信息</span>
                <el-button style="float: right;padding:10px 20px"
                           @click="$router.push('/manage/machineManage/edit?machineId='+prop.machineId)"
                           type="primary"
                           plain>编辑</el-button>
            </div>
            <el-form inline
                     label-width="90px">
                <el-form-item label="机械名称">
                    {{prop.machineName}}
                </el-form-item>
                <br>
                <el-form-item label="机械类型">
                    {{prop.machineType}}
                </el-form-item>
                <el-form-item label="品牌型号">
                    {{prop.brandName}}
                </el-form-item>
                <el-form-item label="出厂日期">
                    {{prop.manufactureTime}}
                </el-form-item>
                <el-form-item label="已使用年限">
                    {{prop.usedYears}}
                </el-form-item>
            </el-form>

            <el-form inline
                     label-width="90px"
                     class="border-line">
                <el-form-item label="机械来源">
                    {{prop.machineSource}}
                </el-form-item>
                <el-form-item v-if="prop.machineOwner"
                              label="所属个体户">
                    {{prop.machineOwner}}
                </el-form-item>
            </el-form>

            <el-form inline
                     label-width="90px">
                <el-form-item label="所属项目">
                    {{prop.projectName}}
                </el-form-item>
            </el-form>
        </el-card>

        <el-card shadow="always"
                 style="margin-top: 20px">
            <div slot="header"
                 class="clearfix">
                <span style="line-height: 35px">系统信息</span>
            </div>
            <el-form inline
                     label-width="100px">
                <el-form-item label="机械编号">
                    {{prop.machineId}}
                </el-form-item>
                <br>
                <el-form-item label="创建人">
                    {{prop.createUserName}}
                </el-form-item>
                <el-form-item label="创建时间">
                    {{prop.createTime}}
                </el-form-item>
                <el-form-item label="修改人">
                    {{prop.updateUserName}}
                </el-form-item>
                <el-form-item label="最近修改时间">
                    {{prop.updateTime}}
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>


<script>
    export default {
        props: {
            prop: {
                type: Object,
                required: true
            },
        },
        data() {
            return {

            }
        },
        created() {

        },
        mounted() {

        },
        methods: {

        },
    }

</script>


<style lang="scss"
       scoped>
    @import '~@/styles/vueImport/index.scss';

    .border-line {
        border-bottom: 1px solid $border-color;
        border-top: 1px solid $border-color;
    }

</style>
