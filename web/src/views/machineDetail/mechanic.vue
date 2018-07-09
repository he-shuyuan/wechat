<template>
    <div>
        <el-card shadow="always">
            <div slot="header"
                 class="header-slot">
                <span>机械人员</span>
                <span style="float: right;">共{{prop.length}}人</span>
            </div>
            <div class="machine-block"
                 style="display: block;">
                <el-row class="mechanic"
                        v-for="(item,index) in prop"
                        :key="index">
                    <el-col :span="2"
                            class="avatar">{{item.machineUserName.substr(0,2)}}</el-col>
                    <el-col :span="8"
                            class="name">{{item.machineUserName}}</el-col>
                    <el-col :span="8"
                            class="title">{{item.machineUserType}}</el-col>
                    <el-col :span="6"
                            class="phone">{{item.machineUserPhone}}</el-col>
                </el-row>
            </div>
        </el-card>
    </div>
</template>


<script>

    export default {
        props: {
            prop: {
                type: Array,
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

    .mechanic {
        display: flex;
        align-items: center;
        margin-bottom: 1em;
        padding-bottom: .5em;
        border-bottom: 1px solid $border-color;
        .avatar {
            background: #FFA953;
            border-radius: 50%;
            width: 2.5em;
            height: 2.5em;
            line-height: 2.5em;
            text-align: center;
            color: #fff;
            font-size: .9em;
        }
        .name {
            padding-left: 1em;
        }
    }

</style>
