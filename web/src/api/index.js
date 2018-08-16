import axios from 'axios'
import config from './config'
import util from './util'
import sessionStorageUtil from '@/utils/sessionStorageUtil'

const appId = config.appId

// api工厂
const apiFactory = uri => payload => axios.post('/gateway' + uri, JSON.stringify(payload), config)

// 读取api列表
const apiList = require('./apiList.json5')
// 组装api
let assembledApiList = {}
for (let key in apiList) {
  if (apiList.hasOwnProperty(key)) {
    let item = apiList[key]
    assembledApiList[key] = apiFactory(item + `?appId=${appId}&requestId=${util.getUUID()}&token=${sessionStorageUtil.getToken()}`)
  }
}

export default {
  getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    var r = window.location.search.substr(1).match(reg)
    if (r != null) return unescape(r[2])
    return null
  },

  ...assembledApiList,

}
