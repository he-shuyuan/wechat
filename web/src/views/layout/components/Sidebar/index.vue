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
            <sidebar-item-auth :routes="menuList"></sidebar-item-auth>
        </el-menu>
    </el-scrollbar>
</template>

<script>
    import { mapGetters } from 'vuex'
    import SidebarItem from './SidebarItem'
    import SidebarItemAuth from './SidebarItemAuth'

    export default {
        components: { SidebarItem,SidebarItemAuth },
        computed: {
            ...mapGetters([
                'sidebar',
                'menuList'
            ]),

            routes() {
               // console.log(this.$router.options.routes)
                //return this.$router.options.routes;
            return [{imgUrl:"manage",name:"系统管理",functionType:"menu",url:"/sysManage",parentId:"2",functionId:"212333",childNum:2,childFunction:[{imgUrl:null,name:"菜单管理",functionType:"menu",url:"functionManage",parentId:"212333",functionId:"21321",childNum:0,childFunction:null},{imgUrl:null,name:"角色管理",functionType:"menu",url:"roleManage",parentId:"212333",functionId:"212345",childNum:0,childFunction:null}]}];
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
