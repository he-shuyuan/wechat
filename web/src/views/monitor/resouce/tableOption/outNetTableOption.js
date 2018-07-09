export default (() => {
    let netColumns
    netColumns = [{
        prop: 'machineImg',
        label: '',
        align: 'right',
        width: 100,
        slotName: 'machineImgOfOutNet'
    },{
        prop: '',
        label: '机械名称 / 编码',
        align: 'left',
        minWidth: 100,
        slotName: 'machineNameAndIdOfOutNet'
    }, {
        prop: 'machineType',
        label: '状态',
        minWidth: 100,
        slotName: 'machineType'
    }, {
        prop: 'updateTime',
        label: '状态修改时间',
        minWidth: 100,

    }]
    return netColumns
})()
