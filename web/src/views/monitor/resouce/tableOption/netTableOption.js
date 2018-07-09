export default (() => {
    let netColumns
    netColumns = [{
            prop: 'machineImg',
            label: '',
            align: 'right',
            width: 100,
            slotName: 'machineImgOFnet'
        },
        {
            prop: '',
            label: '机械名称 / 编码',
            align: 'left',
            minWidth: 100,
            slotName: 'machineNameAndIdOfNet'
            // render: row => {
            //     return row.machineName + '</br>' + row.machineId
            // }
        },
        {
            prop: 'terminalCode',
            label: '物联网终端',
            minWidth: 100,

        },
        {
            prop: 'internetTime',
            label: '入网时间',
            minWidth: 100
        },
        {
            prop: 'projectName',
            label: '所属项目',
            minWidth: 100
        },
    ]
    return netColumns
})()
