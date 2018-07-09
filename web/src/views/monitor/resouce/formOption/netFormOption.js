export default (() => {
    let netFormOpt = [{
            label: '机械类型',
            type: 'cascader',
            placeholder: '试试搜索：挖掘机',
            prop: 'machineType',
            dcGroup:'jxgroup'
        }, {
            label: '所属项目',
            type: 'select',
            prop: 'projectId',
            placeholder: '请选择所属项目',
            initData:[]
        }, {
            label: '入网开始时间',
            type: 'startDate',
        },
        {
            label: '',
            type: 'search',
        },
        {
            label: '机械名称',
            type: 'text',
            placeholder: '请输入机械名称',
            prop: 'machineName',
        },
        {
            label: '机械编号',
            type: 'text',
            placeholder: '请输入机械编号',
            prop: 'machineId',
        },
        {
            label: '入网结束时间',
            type: 'endDate',
        },
        {
            label: '',
            type: 'reset',
        },
    ]
    return netFormOpt
})()