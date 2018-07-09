import createTable from './createTable.vue'
import createForm from './createForm.vue'
import createSelectOfDc from './createSelectOfDc.vue'
import createCascader from './createCascader.vue'

export default (Vue)=>{
    Vue.component("mc-table",createTable);
    Vue.component("mc-form",createForm);
    Vue.component("mc-select",createSelectOfDc);
    Vue.component("mc-cascader",createCascader);
}