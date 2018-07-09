export default (() => {
    let FTUFormOpt = [{
            label: '终端编号',
            type: 'text',
            placeholder: '请输入终端编号',
            prop: 'terminalCode',
        }, {
            label: '机械名称',
            type: 'text',
            placeholder: '请输入机械名称',
            prop: 'machineName',
        },
        {
            label: '终端状态',
            type: 'select',
            placeholder: '请选择终端状态',
            prop: 'terminalStatus',
            method: 'getDicList',
            params: {
                dcCodeMain: '08'
            },
            valueName: 'dcCode',
            labelName: 'dcname'
        },
        {
            col: 2,
            label: '',
            type: 'search',
            style: 'margin:0 auto;display:block'
        }, {
            col: 2,
            label: '',
            type: 'reset',

        },
        {
            col: 2,
            type: 'hidden'
        }
    ]
    return FTUFormOpt
})()