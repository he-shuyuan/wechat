export default (() => {
    let formOpt = [
         {
            col: 8,
            label: '业务类型',
            type: 'select',
            prop: 'busTypeId',
            placeholder: '请选择业务类型',
            method:'queryBusTypeList',
            params:{},
            valueName:'busTypeId',
            labelName:'busTypeName'
        },
        {
            col: 2,
            type: 'hidden',
        },{
            col: 8,
            label: '组织类型',
            type: 'select',
            prop: 'typeId',
            placeholder: '请选择组织类型',
            method:'queryInsTypeList',
            params:{},
            valueName:'id',
            labelName:'name'
        },
          {
            col: 2,
            type: 'hidden',
        },
        {
            col: 3,
            type: 'reset',
        },
        {
            col: 8,
            label: '公司名称',
            type: 'text',
            placeholder: '请输入公司名称',
            prop: 'insName',
        },
         {
            col: 2,
            type: 'hidden',
        },
        {
            col: 8,
            label: '统一社会信用代码',
            labelWidth:'130px',
            type: 'text',
            placeholder: '请输入统一社会信用代码',
            prop: 'organizationCode',
        },
          {
            col: 2,
            type: 'hidden',
        },
        {   
            col: 2,
            type: 'search',
        },
    ]
    return formOpt
})()
