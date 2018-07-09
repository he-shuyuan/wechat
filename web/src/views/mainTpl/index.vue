<template>
    <mc-table stripe :formOpt="formOpt" :columns="columns" :showPagination="false" type="selection" method="queryProduct" :params="params">
        <template slot-scope="scope" slot="yi">
            <div>
                <el-tag>
                    {{scope.row.productId}}
                </el-tag>
            </div>
        </template>
        <template slot-scope="scope" slot="handle">
            <a href="#" @click="test(scope.row)">删除 </a>
        </template>
    </mc-table>
</template>

<script>
    export default {
        data() {
            return {
                formOpt: [{
                        label: '产品名称',
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'q',
                    }, {
                        label: '入网开始时间',
                        type: 'startDate',
                    }, {
                        label: '下拉框',
                        type: 'select',
                        prop: 'isInput3',
                        placeholder: '请选择',
                        initData: [{
                                value: '测试',
                                label: '测试'
                            },
                            {
                                value: 'lalala',
                                label: '测试lall'
                            }
                        ]
                    },
                    {
                        label: '',
                        searchBtnText:'查询',
                        type: 'search',
                        
                    },
                    {
                        label: '输入框',
                        type: 'text',
                        placeholder: '请输入',
                        prop: 'isInput1',
                    },{
                        label: '入网结束时间',
                        type: 'endDate',
                    }, 
                    {
                        col:6,
                        label:'',
                        type:'hidden',
                    },
                    {
                        label: '',
                        type: 'reset',
                    },
                    // {
                    //   label:'',
                    //   type: 'hidden',
                    //   name:'yinchang',
                    //   default:'sdsd'
                    // }
                ],
                columns: [{
                        prop: 'productId',
                        label: '产品ID',
                        minWidth: 200,
                        slotName: 'yi'
                    },
                    {
                        prop: 'productName',
                        label: '产品名称',
                        minWidth: 200
                    },
                    {
                        prop: 'brandName',
                        label: '银行名称',
                        minWidth: 200,
                        render: row => {
                          return row.brandName + '</br>' + row.productName
                        }
                    },
                    {
                        prop: '',
                        label: '操作',
                        width: 100,
                        slotName: 'handle'
                    },
                ],
                params: {
                    brandId: '1'
                }
            }
        }
    }

</script>

<style>


</style>
