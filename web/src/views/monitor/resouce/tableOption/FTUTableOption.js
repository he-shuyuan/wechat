export default (() => {
    let FTUColumns
    FTUColumns = [{
            prop: 'terminalType',
            label: '终端型号',
            width: 100,
        },
        {
            prop: 'terminalCode',
            label: '物联网终端编号',
            minWidth: 100,
        },
        {
            prop: '',
            label: '终端状态',
            width: 100,
            slotName:'terminalStatus'

        },
        // {
        //     prop: 'internetTime',
        //     label: '电池电量',
        //     minWidth: 100
        // },
        {
            prop: 'machineName',
            label: '机械名称',
            minWidth: 100,
            slotName: 'machineNameOfFTU'
        },
        {
            prop: 'internetTime',
            label: '数据最后上报时间',
            minWidth: 200
        },
        {
            prop: 'internetTime',
            label: '安装时间',
            minWidth: 200
        },
        {
            prop: '',
            label: '操作',
            width: 100,
            slotName:'operate'
        },
    ]
    return FTUColumns
})()
