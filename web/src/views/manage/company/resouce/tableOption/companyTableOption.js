import utils from '@/utils/'
export default (()=>{
    let columns = [{
        prop: 'insName',
        label: '公司名称',
        minWidth: 150,
        slotName: 'insName'
    },
    {
        prop: 'insStyle',
        label: '组织类型',
        minWidth: 100,
           render: row => {
             return row.insStyle == '1801'?'有限责任公司' : '个体户'
           }
    },
    {
        prop: 'legal',
        label: '法人代表',
        minWidth: 100,
        
    },
    {
        prop: 'openDate',
        label: '登记日期',
        minWidth: 100,
        render: row => {
            return utils.toDatetime(row.openDate,'yyyy-MM-dd')
        }
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