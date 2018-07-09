<template>
    <el-row :gutter="20"
            style="display: flex;">
        <el-col :span="16">
            <status></status>
        </el-col>
        <el-col :span="8">
            <router-link :to="'/manage/machineManage/networkAccessInfo?machineId='+machineId">
                <div class="netInfo">
                    <span>入网信息</span>
                    <i class="el-icon-arrow-right"></i>
                </div>
            </router-link>
            <el-card shadow="never"
                     class="netStatus">
                <div slot="header"
                     class="header-slot">
                    <span>入网状态</span>
                </div>
                <div class="netStatus-main">
                    <i class="el-icon-warning"></i>
                    <div class="label">机械未入网，无法获取运行信息，如果您需要入网，请购买入网硬件设备。</div>
                </div>
            </el-card>
        </el-col>
    </el-row>
</template>


<script>
    import status from './status.vue'
    export default {
        components: { status },
        props: {
            machineId: {
                type: String,
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

    .netInfo {
        padding: 15px 20px;
        position: relative;
        border: 1px solid #ebeef5;
        border-radius: 4px;
        i {
            position: absolute;
            right: 20px;
        }

        &:hover {
            background: rgb(253, 241, 230);
            cursor: pointer;
        }
    }

    .netStatus {

        &-main {
            display: flex;
            align-items: center;
            min-height: 100px;

            i {
                margin: 0 10px;
                color: #FF9831;
                font-size: 40px;
            }
            .label {
                color: #929292;
                font-size: 14px;
            }
        }
    }

</style>
