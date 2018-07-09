<template>
    <div class="menu-wrapper">
        <template v-for="item in routes">
            <router-link v-if="!item.childFunction"
                         :to="item.url"
                         :key="item.name">
                <el-menu-item :index="item.url"
                              :class="{'submenu-title-noDropdown':!isNest}">
                    <svg-icon v-if="item.imgUrl"
                              className="sidebar-icon"
                              :icon-class="item.imgUrl"></svg-icon>
                    <span v-if="item.name"
                          slot="title">{{item.name}}</span>
                </el-menu-item>
            </router-link>

            <el-submenu v-else
                        :index="item.name||item.url"
                        :key="item.name">
                <template slot="title">
                    <svg-icon v-if="item.imgUrl"
                              :icon-class="item.imgUrl"
                              className="sidebar-icon"></svg-icon>
                    <span v-if="item.name"
                          slot="title">{{item.name}}</span>
                </template>

                <template v-for="child in item.childFunction">
                    <sidebar-item-auth :is-nest="true"
                                  class="nest-menu"
                                  v-if="false"
                                  :routes="child.childFunction"
                                  :key="child.path"></sidebar-item-auth>

                    <router-link v-else
                                 :to="child.url"
                                 :key="child.name">
                        <el-menu-item :index="child.url">
                            <svg-icon v-if="child.imgUrl"
                                      className="sidebar-icon"
                                      :icon-class="child.imgUrl"></svg-icon>
                            <span v-if="child.name"
                                  slot="title">{{child.name}}</span>
                        </el-menu-item>
                    </router-link>
                </template>
            </el-submenu>

        </template>
    </div>
</template>

<script>
    export default {
        name: 'SidebarItemAuth',
        props: {
            routes: {
                type: Array
            },
            isNest: {
                type: Boolean,
                default: false
            }
        },
        methods: {
            hasOneShowingChildren(children) {
                const showingChildren = children.filter(item => {
                    return !item.hidden
                })
                return showingChildren.length === 1
            }
        }
    }
</script>

<style>
    .svg-icon.sidebar-icon {
        width: 1.5em !important;
        height: 1.5em !important;
        margin-right: 5px !important;
        position: relative;
        top: 3px;
    }

    .nest-menu .el-submenu__icon-arrow {
        display: none;
    }

</style>
