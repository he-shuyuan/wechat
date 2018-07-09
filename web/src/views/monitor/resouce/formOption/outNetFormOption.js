export default (() => {
    let outNetFormOpt = [{
            col: 6,
            label: '机械类型',
            type: 'cascader',
            placeholder: '试试搜索：挖掘机',
            prop: 'machineType',
            dcGroup: 'jxgroup'
        },
        {
            label: '机械编号',
            col: 6,
            type: 'text',
            placeholder: '请输入机械编号',
            prop: 'machineId'
        },
        {
            label: '机械名称',
            col: 6,
            type: 'text',
            placeholder: '请输入机械名称',
            prop: 'machineName',
		},
        {
            col: 2,
            label: '',
            type: 'search',
            style: 'margin:0 auto;display:block'
        },
        {
            col: 2,
            label: '',
            type: 'reset'
		},
		{
			col: 2,
			type:'hidden'
		}
    ]
    return outNetFormOpt
})()