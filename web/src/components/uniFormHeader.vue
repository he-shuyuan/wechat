<template>
    <div class="uni-form-header">
        <el-card shadow="never"
                 class="modify">
            <div slot="header">
                {{title}}
            </div>
            <slot class="uni-form-header-wrap"></slot>
        </el-card>
    </div>
</template>

<script>
    export default {
        props: {
            title: String,
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

</style>
