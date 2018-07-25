<template>
    <el-container class="_body" @click.native="outsideClick">
        <el-header>菜单管理</el-header>
        <el-container>
            <el-aside width="200px">
                <el-tag style="width:100%;text-align:center" class="_menu_op">菜单列表</el-tag>
                <el-tag style="width:100%;" class="_menu_op">
                    <span style="color:black">已选择：</span>
                    {{($refs.tree && $refs.tree.getCurrentNode())?$refs.tree.getCurrentNode().name:(init.defaultName || '未选择')}}</el-tag>
                <div class="_menu_op">
                    <el-button type="primary" size="mini" @click.native="addMenu">新增</el-button>
                    <el-button type="danger" size="mini" @click.native="delMenu">删除</el-button>
                </div>
                <el-tree :load="reloadTree" :props="defaultProps" ref="tree" lazy node-key="functionId" highlight-current @node-click="selectNode">
                </el-tree>
            </el-aside>
            <el-main>
                <el-form :model="ruleForm" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="上级菜单" prop="parentFunctionName">
                                <el-input v-model="status.currentParentName" placeholder="请输入" class="long-input" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="busTypeId" label="业务类型">
                                <el-select v-model="ruleForm.busTypeId" placeholder="请选择">
                                    <el-option v-for="(item,index) in init.busTypeList" :key="index" :label="item.busTypeName" :value="item.busTypeId"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="菜单名称" prop="name">
                                <el-input v-model="ruleForm.name" placeholder="请输入" class="long-input"></el-input>

                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="functionType" label="类型">
                                <el-select v-model="ruleForm.functionType" placeholder="请选择">
                                    <el-option v-for="(item,index) in init.functionTypeList" :key="index" :label="item.name" :value="item.id"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="菜单编号" prop="name">
                                <el-input v-model="ruleForm.code" placeholder="请输入"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="排序编号" prop="sortNo">
                                <el-input v-model="ruleForm.sortNo" placeholder="请输入" type="number"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label="菜单地址" prop="url">
                        <el-input v-model="ruleForm.url" placeholder="请输入" class="long-input"></el-input>
                    </el-form-item>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item prop="imgurl" label="图标">
                                <div class="_svg_select">
                                    <div @click="showSvgSelectView = true" id="svgSelectBtn">
                                        <svg-icon v-if="ruleForm.imageUrl" :icon-class="ruleForm.imageUrl" style="font-size: 20px"></svg-icon>
                                        <svg-icon icon-class="down" style="font-size: 10px"></svg-icon>
                                    </div>
                                    <div class="arrow" v-show="showSvgSelectView"></div>
                                    <div class="icon-wrapper" v-if="showSvgInit" v-show="showSvgSelectView" id="svgWapper">
                                        <div class="icon-tool">
                                            <div class="icon-tool-left">
                                                <span @click="pageIndex>1 && goTo(--pageIndex)" :style="pageIndex==1?'color: #c0c4cc;':''">
                                                    <i class="el-icon el-icon-arrow-left"></i>
                                                </span>
                                                <span>&nbsp;&nbsp;{{currentPageIndex}}&nbsp;&nbsp;</span>
                                                <span @click="pageIndex < Math.ceil(svgList.length / 24) && goTo(++pageIndex)" :style="pageIndex == Math.ceil(svgList.length / 24)?'color: #c0c4cc;':''">
                                                    <i class="el-icon el-icon-arrow-right"></i>
                                                </span>
                                                <input v-model="pageIndex" type="text" @keydown="listenEnter($event,pageIndex)" @blur="goTo(pageIndex)">&nbsp;页
                                            </div>
                                            <div class="icon-tool-right">
                                                <el-button size="mini" @click="resetSvg" type="primary">清空</el-button>
                                            </div>
                                        </div>
                                        <el-row>
                                            <el-col :span="3" v-for="(svgName,index) in svgPageList" @click.native="svgSelet(svgName)" :key="index">
                                                <svg-icon :icon-class="svgName" style="font-size: 20px"></svg-icon>
                                            </el-col>
                                        </el-row>
                                    </div>
                                </div>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item prop="target" label="展现方式">
                                <el-select v-model="ruleForm.target" placeholder="请选择">
                                    <el-option v-for="(item,index) in init.targetList" :key="index" :label="item.name" :value="item.id"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label="备注" prop="remark">
                        <textarea class="long-input" style="height:70px;width:100%" v-model="ruleForm.remark">
                        </textarea>
                    </el-form-item>
                    <el-form-item style="margin: 50px 0;">
                        <el-button type="primary" class="long-button" @click.native="saveFunction">保存</el-button>
                        <!--                 <el-button v-if="init.currentOp!='add'" type="default"
           class="long-button"
           >取消</el-button> -->
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </el-container>
</template>``
<script>
    import {
        standardAsync,
        customAsync
    } from '@/api/async'
    import config from '@/api/config'
    import Clickoutside from '@/utils/clickoutside'
    export default {

        data() {
            let svgList = []
            const requireAll = requireContext => {
                let svgPathList = requireContext.keys()
                for (let item of svgPathList) {
                    item = item.replace('.svg', '').replace('./', '')
                    svgList.push(item)
                }
            }
            const req = require.context('../../../icons/svg', false, /\.svg$/)
            requireAll(req)
            return {
                init: {
                    targetList: [{
                        name: '路由',
                        id: '_router'
                    }],
                    functionTypeList: [{
                        name: '菜单',
                        id: 'menu'
                    }, {
                        name: '按钮',
                        id: 'button'
                    }],
                    busTypeList: [{
                        busTypeId: null,
                        busTypeName: '全业务'
                    }],
                    defaultName: null,
                },
                status: {
                    currentNode: '',
                    currentNodeId: '',
                    currentNodeName: '',
                    currentParentId: '',
                    currentParentName: '',
                },
                ruleForm: {

                },
                rules: [],
                defaultProps: {
                    label: 'name',
                    isLeaf: 'isLeaf'
                },
                svgList,
                showSvgSelectView: false,
                pageIndex: 1,
                svgPageList: [],
                currentPageIndex: 1,
                showSvgInit:false
            };
        },
        created() {
            this.goTo(1);
        },
        mounted() {
            this.initSelect();
        },
        methods: {
            /**
             * 选择节点
             * @param  {[type]} a [description]
             * @param  {[type]} b [description]
             * @param  {[type]} c [description]
             * @return {[type]}   [description]
             */
            selectNode: function (a, b, c) {
                this.status.currentNode = b;
                if (this.status.currentNodeId != a.functionId) {
                    this.queryFunctionById(a.functionId);
                }
                this.status.currentNodeId = a.functionId;
                this.status.currentNodeName = a.name;

                if (b.parent && b.parent.data) {
                    this.status.currentParentId = b.parent.data.functionId;
                    this.status.currentParentName = b.parent.data.name;
                } else {
                    this.status.currentParentId = '';
                    this.status.currentParentName = '无上级';
                }

            },
            /**
             * 加载数据节点
             * @param  {[type]} node    [description]
             * @param  {[type]} resolve [description]
             * @return {[type]}         [description]
             */
            reloadTree(node, resolve) {
                standardAsync(this, 'queryAdminFunctionList', {
                    busTypeId: '1',
                    parentId: node.data ? node.data.functionId : "",
                    notShowLoading: true
                }, res => {
                    (!node.data) && this.initTreeSelect();
                    resolve(res.body);
                })
            },
            /**
             * 初始化业务类型
             * @return {[type]} [description]
             */
            initSelect: function () {
                standardAsync(this, 'queryBusTypeList', {
                     notShowLoading: true
                 }, res => {
                     this.init.busTypeList = this.init.busTypeList.concat(res.body);
                 });
            },
            /**
             * 通过id查找菜单
             * @param  {[type]} id [description]
             * @return {[type]}    [description]
             */
            queryFunctionById: function (id) {
                standardAsync(this, 'queryAdminFunctionById', {
                        functionId: id,
                        notShowLoading: true
                    },
                    res => {

                        this.ruleForm = res.body;
                    });
            },
            /**
             * 新增菜单
             */
            addMenu: function () {
                if (!this.status.currentNode) {
                    this.$message.warning("请先选择一个菜单");
                    return;
                }
                this.status.currentParentId = this.status.currentNodeId;
                this.status.currentParentName = this.status.currentNodeName;
                this.ruleForm = {};
            },
            /**
             * 保存菜单
             * @return {[type]} [description]
             */
            saveFunction: function () {
                if (this.ruleForm.functionId) { //update
                    standardAsync(this, 'updateAdminFunction', this.ruleForm, res => {
                        //this.ruleForm.functionId = res.body;
                        Object.assign(this.status.currentNode.data, this.ruleForm)
                        this.$message.success("修改成功");
                    });
                } else { //add
                    this.ruleForm.parentId = this.status.currentParentId;
                    standardAsync(this, 'addAdminFunction', this.ruleForm, res => {
                        this.ruleForm.functionId = res.body.functionId;
                        let param = JSON.parse(JSON.stringify(this.ruleForm));
                        param.isLeaf = true;
                        this.append(this.status.currentNode,param);
                        this.$message.success("新增成功");
                    });
                }
            },
            /**
             * 添加节点
             * @param  {[type]} node [description]
             * @param  {[type]} data [description]
             * @return {[type]}      [description]
             */
            append(node, data) {
                data.childNum = true;
                this.$refs.tree.append(data, node);
                node.isLeaf = false;
            },
            /**
             * 删除
             * @return {[type]} [description]
             */
            delMenu: function () {
                if (!this.status.currentNode) {
                    this.$message.warning("请先选择一个菜单");
                    return;
                }
                if (!this.status.currentNode.isLeaf) {
                    this.$message.warning("请先删除子节点");
                    return;
                }
                standardAsync(this, 'delAdminFunction', {
                    functionId: this.status.currentNodeId
                }, res => {
                    this.$refs.tree.remove(this.status.currentNode);
                    this.ruleForm = {};
                    this.$message.success("删除成功");
                });
            },
            /**
             * 初始化树选择
             * @return {[type]} [description]
             */
            initTreeSelect: function () {
                setTimeout(() => {
                    this.$refs.tree && this.$refs.tree.setCurrentKey("1")
                    if (this.$refs.tree && this.$refs.tree.getCurrentNode()) {
                        this.init.defaultName = this.$refs.tree.getCurrentNode().name;
                        this.queryFunctionById(this.$refs.tree.getCurrentNode().functionId);
                        this.status.currentParentId = '';
                        this.status.currentParentName = '无上级';
                        this.status.currentNode = this.$refs.tree.getNode(this.$refs.tree.getCurrentNode());
                    }
                }, 10);
            },
            svgSelet(svgName) {
                this.ruleForm.imageUrl = svgName
                this.showSvgSelectView = false
            },
            outsideClick(e) {
                let _this = this;
                var divTop1 = document.querySelectorAll('#svgWapper'); // 设置目标区域
                var divTop2 = document.querySelectorAll('#svgSelectBtn'); // 设置目标区域
                if (_this.isTargerContain(divTop1, e.target) && _this.isTargerContain(divTop2, e.target)) {
                    this.showSvgSelectView = false
                }
            },
            /**
             * 判断target是否在list里
             * @param  {[type]}  list   [description]
             * @param  {[type]}  target [description]
             * @return {Boolean}        [description]
             */
            isTargerContain: function (list, target) {
                let temp = 0;
                list.forEach((o) => {
                    if (o.contains(target)) {
                        temp = 1;
                    }
                });
                if (temp == 1) {
                    return false;
                }
                return true;
            },
            goTo(pageIndex) {
                
                if (this.showSvgInit && (pageIndex == null || pageIndex < 0 || this.currentPageIndex == pageIndex)) {
                    return
                }
                if(!this.showSvgInit) {
                    this.showSvgInit = true
                }
                this.svgPageList = this.svgList.slice(24 * (pageIndex - 1), 24 * pageIndex)
                this.currentPageIndex = pageIndex
            },
            listenEnter(e, pageIndex) {
                if (e.keyCode == '13') {
                    let _pageIndex = pageIndex
                    if (pageIndex < 1) {
                        _pageIndex = this.pageIndex = this.currentPageIndex ? this.currentPageIndex : 1
                    }
                    if (pageIndex > Math.ceil(this.svgList.length / 24)) {
                        _pageIndex = this.pageIndex = Math.ceil(this.svgList.length / 24)
                    }
                    this.goTo(_pageIndex)
                }
            },
            resetSvg() {
                this.ruleForm.imageUrl = ''
            }
        }
    };
</script>
<style scoped>
    .el-header {
        border-bottom: solid 1px #e4e7ed;
        padding: 18px;
        text-align: center;
        border-bottom: 1px solid #fff;
        background: #e4e7ed;
        font-weight: bold;
    }

    ._body {
        margin: 0 3% 0 3%;
    }

    .el-aside {
        border: solid 1px #e4e7ed;
        height: 580px;
    }

    .el-main {
        border: solid 1px #e4e7ed;
    }

    .el-button--mini {
        padding: 4px 10px;
    }

    ._input_width {
        width: 70%;
    }

    ._svg_select {
        border: solid 1px #e4e7ed;
        width: 50px;
        text-align: center;
        position: relative;
    }

    .icon-wrapper {
        position: absolute;
        top: -40px;
        left: 60px;
        width: 300px;
        overflow: auto;
        z-index: 1000;
        border: 1px solid #e4e7ed;
        border-radius: 6px;
        background: #fff;
    }

    .arrow {
        position: absolute;
        top: 10px;
        left: 40px;
        z-index: 1001;
        border: 10px solid transparent;
        border-right-color: #e4e7ed;
    }

    .arrow::after {
        content: "";
        position: absolute;
        top: -10px;
        left: -9px;
        z-index: 1001;
        border: 10px solid transparent;
        border-right-color: #fff;
    }

    .svg_icon {
        border: solid 1px #e4e7ed;
    }

    ._svg_select:hover {
        color: #333;
        background-color: #e6e6e6;
        border: dotted 1px #adadad;
        cursor: pointer;
    }

    ._menu_op {
        width: 100%;
        border: solid 1px #e4e7ed;
        height: 48px;
        line-height: 48px;
        text-align: center;
        background-color: #fff;
    }

    .icon-tool {
        border-bottom: 1px solid #f1f1f1;
        height: 40px;
        line-height: 40px;
        text-align: left;
        padding: 0 8px;
        overflow: hidden;
    }

    .icon-tool input {
        width: 30px;
        text-align: center;
        font-size: 14px;
        margin-left: 6px;
    }

    .icon-tool-left {
        float: left;
    }

    .icon-tool-right {
        float: right;
    }
</style>