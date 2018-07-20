import utils from '@/utils/'
export default (()=>{
    let columns = [{
        prop: 'insName',
        label: '公司名称',
        minWidth: 150
    },
     {
        prop: 'organizationCode',
        label: '组织信用代码',
        minWidth: 100
    },
    {
        prop: 'insTypeName',
        label: '组织类型',
        minWidth: 100
    },
    {
        prop: 'busTypeName',
        label: '所属业务',
        minWidth: 100,
        
    },
    {
        prop: '',
        label: '操作',
        minWidth: 200,
        slotName: 'handle'
    },
]
return columns
})()