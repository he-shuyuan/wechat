export default (() => {
    let formOpt = [{
            col: 6,
            label: '组织类型',
            type: 'select',
            prop: 'insStyle',
            placeholder: '请选择组织类型',
            method:'getDicList',
            params:{dcCodeMain:'18'},
            valueName:'dcCode',
            labelName:'dcname'
        },
        {
            col: 6,
            label: '公司名称',
            type: 'text',
            placeholder: '请输入公司名称',
            prop: 'insName',
        },
        
        {
            col: 6,
            label: '统一社会信用代码',
            labelWidth:'130px',
            type: 'text',
            placeholder: '请输入统一社会信用代码',
            prop: 'organizationCode',
        },
        {
            col: 0.5,
            type: 'hidden',
        },
        {   
            col: 2,
            type: 'search',
        },
        
        {
            col: 3,
            type: 'reset',
        },
        {
            col: 0.5,
            type: 'hidden',
        },
    ]
    return formOpt
})()
