<template>
    <div class="createTable-main">


        <createForm v-if="formOpt" 
                    :labelWidth="labelWidth" 
                    :formOpt="formOpt" 
                    @searchHandle="searchHandle"
                    :submitHandler="submitHandler"
                    :rules="rules"
                    ></createForm>


        <div class="table-wrpper">
            <div class="operate-line" :style="btnData.btnStyle?btnData.btnStyle:''" >
                <el-button v-if="showAdd" :type="btnData.addBtnType?btnData.addBtnType:'primary'" :style="btnData.addBtnStyle" @click="()=>emitEventHandler('addEvent')">{{btnData.addBtnText?btnData.addBtnText:addBtnText || '新增机械'}}</el-button>
                <el-button v-if="showDelet" :type="btnData.delBtnType?btnData.delBtnType:''" :style="btnData.delBtnStyle" @click="isDelete">{{btnData.delBtnText?btnData.delBtnText:deletBtnText || '删除机械'}} </el-button>
                <slot name="customOperate"></slot>
            </div>
            <el-table v-loading.lock="loading" ref="table" :data="tableData" :border="border" 
                :stripe="stripe" :height="height" 
                :max-height="maxHeight"
                :fit="fit" :show-header="showHeader" 
                :highlight-current-row="highlightCurrentRow" 
                :current-row-key="currentRowKey" 
                :row-class-name="rowClassName" 
                :row-style="rowStyle" 
                :row-ket="rowKey" 
                :empty-text="emptyText" 
                :default-expand-all="defaultExpandAll"
                :expand-row-keys="expandRowKeys" 
                :default-sort="defaultSort" 
                :tooltip-effect="tooltipEffect" 
                :show-summary="showSummary" 
                :sum-text="sumText" 
                :summary-method="summaryMethod" 
                style="width: 100%;margin-top:20px;" 
                :header-row-style="{background:'#e2e2e2'}"
                header-cell-class-name="machine-table-deep" 
                @select="(selection, row) => emitEventHandler('select', selection, row)"
                @select-all="selection => emitEventHandler('select-all', selection)" 
                @selection-change="handleSelectionChange"
                @cell-mouse-enter="(row, column, cell, event) => emitEventHandler('cell-mouse-enter', row, column, cell, event)"
                @cell-mouse-leave="(row, column, cell, event) => emitEventHandler('cell-mouse-leave', row, column, cell, event)"
                @cell-click="(row, column, cell, event) => emitEventHandler('cell-click', row, column, cell, event)" 
                @cell-dblclick="(row, column, cell, event) => emitEventHandler('cell-dblclick', row, column, cell, event)"
                @row-click="(row, event, column) => emitEventHandler('row-click', row, event, column)"
                @row-dblclick="(row, event) => emitEventHandler('row-dblclick', row, event)"
                @row-contextmenu="(row, event) => emitEventHandler('row-contextmenu', row, event)" 
                @header-click="(column, event) => emitEventHandler('header-click', column, event)"
                @sort-change="args => emitEventHandler('sort-change', args)" 
                @filter-change="filters => emitEventHandler('filter-change', filters)"
                @current-change="(currentRow, oldCurrentRow) => emitEventHandler('current-change', currentRow, oldCurrentRow)"
                @header-dragend="(newWidth, oldWidth, column, event) => emitEventHandler('header-dragend', newWidth, oldWidth, column, event)"
                @expand-change="(row, expanded) => emitEventHandler('expand-change', row, expanded)">

                <slot name="prepend" />
                <el-table-column v-if="type=='selection'" type="selection" width="55">
                </el-table-column>
                <el-table-column v-for="(column, columnIndex) in columns" :key="columnIndex" 
                    :column-key="column.columnKey" 
                    :prop="column.prop"
                    :label="column.label" 
                    :width="column.minWidth ? '-' : (column.width || 140)" 
                    :minWidth="column.minWidth || column.width || 140"
                    :fixed="column.fixed" 
                    :render-header="column.renderHeader" 
                    :sortable="column.sortable" 
                    :sort-method="column.method"
                    :resizable="column.resizable" 
                    :formatter="column.formatter" 
                    :show-overflow-tooltip="column.showOverflowTooltip"
                    :align="column.align || 'center'" 
                    :header-align="column.headerAlign || column.align" 
                    :label-class-name="column.labelClassName"
                    :selectable="column.selectable" 
                    :reserve-selection="column.reserveSelection" 
                    :filters="column.filters" 
                    :filter-placement="column.filterPlacement"
                    :filter-multiple="column.filterMultiple" 
                    :filter-method="column.filterMethod" 
                    :filtered-value="column.filteredValue">
                    <template slot-scope="scope" :scope="newSlotScope ? 'scope' : false ">
                        <span v-if="column.filter">
                            {{ Vue.filter(column['filter'])(scope.row[column.prop]) }}
                        </span>
                        <span v-else-if="column.slotName">
                            <slot :name="column.slotName" :row="scope.row" :$index="scope.$index" />
                            </slot>
                        </span>
                        <span v-else>
                            <span v-html="column.render ? column.render(scope.row) : scope.row[column.prop]"></span>
                        </span>
                    </template>
                </el-table-column>

                <slot name="append" />

            </el-table>

            <div v-if="showPagination" style="margin-top: 10px;text-align: right;">
                <el-pagination @size-change="handleSizeChange" 
                               @current-change="handleCurrentChange" 
                               :current-page="pagination.pageIndex"
                               :page-sizes="pageSizes" 
                               :page-size="pagination.pageSize" 
                               :layout="paginationLayout" 
                               :total="total">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import createForm from './createForm.vue'
    import {
        customAsync
    } from '@/api/async'
    export default {
        name: 'createTable',
        components: {
            createForm
        },
        props: {
            // Element UI Table attributes
            labelWidth: String,
            height: [String, Number],
            maxHeight: [String, Number],
            submitHandler:Function,
            stripe: Boolean,
            border: Boolean,
            btnData:{
                type:Object,
                default:()=>{
                    return {}
                }
            },
            fit: {
                type: Boolean,
                default: true
            },
            showHeader: {
                type: Boolean,
                default: true
            },
            showDelet: Boolean,
            showAdd: Boolean,
            addBtnText: String,
            deletBtnText: String,
            highlightCurrentRow: Boolean,
            currentRowKey: [String, Number],
            rowClassName: [String, Function],
            rowStyle: [String, Function],
            rowKey: [String, Function],
            emptyText: String,
            defaultExpandAll: Boolean,
            expandRowKeys: Array,
            defaultSort: Object,
            tooltipEffect: String,
            showSummary: Boolean,
            sumText: String,
            summaryMethod: Function,
            // custom attributes
            url: {
                type: String
            },
            type: String,
            method: {
                type: String
            },
            formOpt: {
                type: Array,
                // default: () => {
                //   return []
                // }
            },
            rules:Object,
            params: {
                type: Object,
                default: () => {
                    return {}
                }
            },
            autoLoad: {
                type: Boolean,
                default: true
            },
            data: {
                type: Array
            },
            dataHandler: {
                type: Function
            },
            columns: {
                type: Array,
                required: true
            },
            showPagination: {
                type: Boolean,
                default: true
            },
            pageSizes: {
                type: Array,
                default: () => {
                    return [10, 20]
                }
            },
            paginationLayout: {
                type: String,
                default: 'total, prev, pager, next, jumper, sizes'
            },
            pageIndexKey: {
                type: String,
                default: 'pageIndex'
            },
            pageSizeKey: {
                type: String,
                default: 'pageSize'
            }
        },
        data() {
            const _this = this
            return {
                Vue,
                pagination: {
                    pageIndex: 1,
                    pageSize: (() => {
                        const {
                            pageSizes
                        } = _this
                        if (pageSizes.length > 0) {
                            return pageSizes[0]
                        }
                        return 20
                    })()
                },
                total: 0,
                loading: false,
                tableData: [],
                cacheLocalData: []
            }
        },
        computed: {
            newSlotScope() {
                return Number(Vue.version.replace(/\./g, '')) >= 250
            }
        },
        methods: {
            handleSizeChange(size) {
                this.pagination.pageSize = size
                this.dataChangeHandler()
            },
            handleCurrentChange(pageIndex) {
                this.pagination.pageIndex = pageIndex
                this.dataChangeHandler(this.searchParam)
            },
            //查询操作，默认resetPageIndex=true，可用于执行重刷table操作
            searchHandle(resetPageIndex = true) {
                if (resetPageIndex) {
                    this.pagination.pageIndex = 1
                }
                if(arguments[0] && typeof(arguments[0]) == 'object') {
                   this.searchParam = arguments[0]             
                }
                this.dataChangeHandler(this.searchParam)
            },
            dataChangeHandler() {
                this.fetchHandler(arguments[0])
            },
            //获取table数据
            fetchHandler(formParams = {}) {
                this.loading = true
                let params = {}
                params = {
                    ...this.params
                }
                Object.assign(params, formParams)
                if (this.showPagination) {
                    params = Object.assign(params, {
                        pageIndex: this.pagination.pageIndex,
                        pageSize: this.pagination.pageSize
                    })
                }
                params.notShowLoading = true
                customAsync({
                    that: this,
                    method: this.method,
                    paramObj: params,
                    callback: res => {
                        this.loading = false
                        let result= res.body.data
                        if(this.showPagination) {
                          result = res.body.data
                            this.tableData = result
                          this.total = res.body.pager.recordCount
                        } else {
                          result = res.body
                          this.total = res.body.length
                        }
                        if (this.dataHandler) {
                            this.tableData = result.map(this.dataHandler)
                        } else {
                            this.tableData = result
                        }
                        this.$emit('getCurrentTableInfo', this.tableData, this.total)
                    },
                    errorCallback: () => {
                        this.loading = false
                    },
                    doCallbackWhenNetworkError: true
                })
            },
            //抛出el组件内部的$emit
            emitEventHandler(event) {
                this.$emit(event, ...Array.from(arguments).slice(1))
            },
            handleSelectionChange(val) {
                this.selectOfDelete = val
                this.emitEventHandler('selection-change', val)
            },
            //删除事件
            isDelete() {
                this.$emit('deleteEvent', this.selectOfDelete)
            }
        },
        mounted() {
            // event: expand changed to `expand-change` in Element v2.x
            this.$refs['table'].$on('expand', (row, expanded) => this.emitEventHandler('expand', row, expanded))
            if (this.autoLoad) {
                this.fetchHandler()
            }
        },
        watch: {}
    }
</script>
<style>
    .machine-table-deep {
        background-color: #e2e2e2 !important;
        color: #333;
        font-weight: normal;
    }

    .createTable-main .el-select {
        width: 100%;
    }
</style>
<style scoped>
    .operate-line {
        padding-left: 40px;
    }

    .table-wrpper {
        padding-bottom: 20px;
    }
</style>