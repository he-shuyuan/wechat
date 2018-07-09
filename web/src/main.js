import Vue from 'vue'

import 'normalize.css/normalize.css'// A modern alternative to CSS resets
import './styles/element-#EC7700/index.css'
import '@/styles/index.scss' // global css

import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/zh-CN'

import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
//import '@/permission' // permission control

import mcComponents from '@/components/mainTpl/mcComponents' // machine 组件 {table, form, select, cascader}

Vue.use(mcComponents)
Vue.use(ElementUI, { locale })
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
