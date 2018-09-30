<template>
    <el-scrollbar wrapClass="scrollbar-wrapper">
        <el-menu ref="sibeMenu"
                 mode="vertical"
                 :show-timeout="200"
                 :default-active="$route.path"
                 background-color="#304156"
                 text-color="#bfcbd9"
                 active-text-color="#d08770">
            >
            <sidebar-item-auth :routes="routes"></sidebar-item-auth>
        </el-menu>
    </el-scrollbar>
</template>

<script>
    import { mapGetters } from 'vuex'
    import SidebarItemAuth from './SidebarItemAuth'

    export default {
        components: { SidebarItemAuth},
        computed: {
            ...mapGetters([
                'sidebar',
                'menuList'
            ]),

            routes() {
               return this.menuList;
            },
            isCollapse() {
                return !this.sidebar.opened
            }
        },
        mounted() {
            const menu = this.$refs.sibeMenu
           /* menu.open('monitor')
            menu.open('reports')
            menu.open('manage')*/
        }
    }
</script>
