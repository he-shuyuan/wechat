import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
    { path: '/login', component: () =>
            import ('@/views/login/index'), hidden: true },
    { path: '/404', component: () =>
            import ('@/views/404'), hidden: true },
    { path: '/401', component: () =>
            import ('@/views/401'), hidden: true },
    { path: '*', redirect: '/404', hidden: true },

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        name: 'Dashboard',
        hidden: true,
        children: [{
            path: 'dashboard',
            name: 'dashboard',
            component: () =>
                import ('@/views/dashboard/index'),
            meta: { title: '首页', requiresAuth: true }
        }, ],
    },

    {
        path: '/monitor',
        component: Layout,
        redirect: 'noredirect',
        name: 'monitor',
        meta: { title: '监测', icon: 'dashboard' },
        children: [{
            path: 'tree1',
            name: 'tree1',
            component: () =>
                import ('@/views/tree/index'),
            meta: { title: '监测概览', requiresAuth: true },
        }, {
            path: 'tree2',
            name: 'tree2',
            component: () =>
                import ('@/views/tree/index'),
            meta: { title: '机械位置', requiresAuth: true },
        }, {
            path: 'netManage',
            name: 'netManage',
            component: () =>
                import ('@/views/monitor/netManage'),
            meta: { title: '入网管理', requiresAuth: true, auth: true },
        }, ],
    },

    {
        path: '/reports',
        component: Layout,
        redirect: 'noredirect',
        name: 'reports',
        meta: { title: '报表', icon: 'form2' },
        children: [{
            path: 'tree4',
            name: 'tree4',
            component: () =>
                import ('@/views/tree/index'),
            meta: { title: '综合查询', requiresAuth: true },
        }, {
            path: 'tree5',
            name: 'tree5',
            component: () =>
                import ('@/views/tree/index'),
            meta: { title: '监测统计', requiresAuth: true },
        }, {
            path: 'tree6',
            name: 'tree6',
            component: () =>
                import ('@/views/tree/index'),
            meta: { title: '统计报表', requiresAuth: true },
        }, ],
    },

    {
        path: '/manage',
        component: Layout,
        redirect: 'noredirect',
        name: 'manage',
        meta: { title: '管理', icon: 'manage' },
        children: [
            {
                path: 'machineManage',
                name: 'machineManage',
                component: () => import('@/views/manage/machineManage'),
                meta: { title: '机械管理',requiresAuth:true,auth:true},
                children:[
                    {
                        path: 'detail',
                        name: 'machineDetail',
                        component: () => import('@/views/machineDetail/index'),
                        meta: { title: '机械详情',requiresAuth:true },
                        hidden: true,
                    },
                    {
                        path: 'networkAccessInfo',
                        name: 'networkAccessInfo',
                        component: () => import('@/views/machineDetail/networkAccessInfo'),
                        meta: { title: '机械入网信息',requiresAuth:true },
                        hidden: true,
                    },
                    {
                        path: 'edit',
                        name: 'editMachine',
                        component: () => import('@/views/machineDetail/editMachine'),
                        meta: { title: '编辑机械信息',requiresAuth:true },
                        hidden: true,
                    },
                ]
            },
            {
                path: 'project',
                name: 'project',
                component: () => import('@/views/project/index'),
                meta: { title: '项目管理',requiresAuth:true ,auth:true},
                children: [
                    {
                        path: 'edit',
                        name: 'editProject',
                        component: () => import('@/views/project/editProject'),
                        meta: { title: '项目编辑',requiresAuth:true },
                        hidden: true,
                    },
                    {
                        path: 'detail',
                        name: 'projectDetail',
                        component: () => import('@/views/project/projectDetail'),
                        meta: { title: '项目详情',requiresAuth:true },
                        hidden: true,
                    },
                    {
                        path: 'editM',
                        name: 'editM',
                        component: () => import('@/views/project/editMachine'),
                        meta: { title: '机械编辑',requiresAuth:true },
                        hidden: true,
                    },
                ]
            },
            {
                path: 'contract',
                name: 'contract',
                component: () => import('@/views/manage/contract/index'),
                meta: { title: '合同管理',requiresAuth:true ,auth:true},
                children:[
                            {
                                path: 'op/:op',
                                name: 'addOrUpdate',
                                component: () => import('@/views/manage/contract/addOrUpdate'),
                                meta: { title: '修改合同',requiresAuth:true },
                            },
                            {
                                path: 'detail',
                                name: 'detail',
                                component: () => import('@/views/manage/contract/detail'),
                                meta: { title: '合同详情' },
                            },
                             {
                                path: 'progress',
                                name: 'progress',
                                component: () => import('@/views/manage/contract/progress'),
                                meta: { title: '进度款申报',requiresAuth:true },
                            },
                        ]
            },
            {
                path: 'companyManage',
                name: 'companyManage',
                component: () => import('@/views/manage/company/companyManage'),
                meta: { title: '公司管理',requiresAuth:true ,auth:true},
                children:[
                    {
                        path: 'addorModifyCompany',
                        name: 'addorModifyCompany',
                        component: () => import('@/views/manage/company/editCompany'),
                        meta: { title: '',requiresAuth:true },
                    },
                ]
            },
            {
                path: 'FTUregister',
                name: 'FTUregister',
                component: () => import('@/views/manage/FTUregister'),
                meta: { title: '终端注册',requiresAuth:true ,auth:true},
            },
        ],
    },
    {
        path: '/sysManage',
        component: Layout,
        name: 'sysManage',
        redirect: 'noredirect',
        meta: { title: '系统管理', icon: 'manage' },
        children: [{
            path: 'roleManage',
            name: 'roleManage',
            component: () =>
                import ('@/views/sysManage/role/index'),
            meta: { title: '角色管理', requiresAuth: true, auth: true },
            children: [{
                path: 'detail',
                name: 'roleDetail',
                component: () =>
                    import ('@/views/sysManage/role/detail'),
                meta: { title: '新增角色', requiresAuth: true },
            }, {
                path: 'userRole',
                name: 'userRole',
                component: () =>
                    import ('@/views/sysManage/role/userRole'),
                meta: { title: '分配角色' },
            }, 
             {
                path: 'insRole',
                name: 'insRole',
                component: () =>
                    import ('@/views/sysManage/role/insRole'),
                meta: { title: '机构角色' },
            },{
                path: 'showMenu',
                name: 'showMenu',
                component: () =>
                    import ('@/views/sysManage/role/functionList'),
                meta: { title: '查看菜单', requiresAuth: true },
            }, ]
        }, {
            path: 'functionManage',
            name: 'functionManage',
            component: () =>
                import ('@/views/sysManage/function/index'),
            meta: { title: '菜单管理', requiresAuth: true, auth: true },
            children: [

            ]
        }, {
            path: 'userManage',
            name: 'userManage',
            component: () =>
                import ('@/views/sysManage/dep/index'),
            meta: { title: '部门管理', requiresAuth: true, auth: true },
            children: [{
                path: 'depDetail',
                name: 'depDetail',
                component: () =>
                    import ('@/views/sysManage/dep/depDetail'),
                meta: { title: '部门详情', requiresAuth: true },
            }, {
                path: 'userDetail',
                name: 'userDetail',
                component: () =>
                    import ('@/views/sysManage/dep/userDetail'),
                meta: { title: '用户详情', requiresAuth: true },
            }, {
                path: 'userAdd',
                name: 'userAdd',
                component: () =>
                    import ('@/views/sysManage/dep/userAdd'),
                meta: { title: '新增用户', requiresAuth: true },
            }, {
                path: 'userView',
                name: 'userView',
                component: () =>
                    import ('@/views/sysManage/dep/userView'),
                meta: { title: '用户管理', requiresAuth: true },
            }, ]
        }],
    },
]
const router = new Router({
    // mode: 'history', //后端支持可开
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap,
})

import sessionStorageUtil from '@/utils/sessionStorageUtil'
import { routerParamsStore } from '@/utils/routerSession'
router.beforeEach((to, from, next) => {
    /*if (to.matched.some(record => record.meta.requiresAuth)) {
        let userId = sessionStorageUtil.getItem(sessionStorageUtil.MCE_USER_ID) || ''
        if (userId == "") {
            next({
                path: '/login'
            })
        }
        if (to.meta.auth) {
            let list = sessionStorageUtil.getItem(sessionStorageUtil.AUTH_MENU_LIST)
            if (list && list.indexOf(to.name) == -1) {
                next({
                    path: '/401'
                })
            }
        }
    }*/
    routerParamsStore(to)
    next();
})
export default router
