import axios from 'axios'
import config from './config'

const devName = ''
const appId = config.appId
const cmsAppId = '000111'

import localStorageUtil from '@/utils/localStorageUtil'

var guidGenerator = function() {
  return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
}
export const getUUID = function() {
  return (
    guidGenerator() +
    guidGenerator() +
    guidGenerator() +
    guidGenerator() +
    guidGenerator() +
    guidGenerator() +
    guidGenerator() +
    guidGenerator()
  )
}
// url参数模板
const urlParam = {
  normal: '?requestId=' + getUUID() + '&appId=' + appId,
  hsy: '?requestId=' + getUUID() + '&appId=' + appId + '&develop=-hsy',
  lzw: '?requestId=' + getUUID() + '&appId=' + appId + '&develop=-lzw',
  cms: '?requestId=' + getUUID() + '&appId=' + cmsAppId,
  wyz: '?requestId=' + getUUID() + '&appId=' + appId + '&develop=-wyz',
  lxy: '?requestId=' + getUUID() + '&appId=' + appId + '&develop=-lxy',
}
if (process.env.NODE_ENV !== 'development') {
  for (let key in urlParam) {
    urlParam[key] = '?requestId=' + getUUID() + '&appId=' + appId
    if (key == 'cms') {
      urlParam[key] = '?requestId=' + getUUID() + '&appId=' + cmsAppId
    }
  }
}

// api工厂
const apiFactory = uri => payload => axios.post('/mgateway' + uri, JSON.stringify(payload), config)
const cmsApiFactory = uri => payload => axios.post('/gateway' + uri, JSON.stringify(payload), config)

// 函数调用映射
const factoryMapper = {
  apiFactory,
  cmsApiFactory,
}
// 读取api列表
const apiList = require('./apiList.json5')
// 组装api
let assembledApiList = {}
for (let key in apiList) {
  if (apiList.hasOwnProperty(key)) {
    let item = apiList[key]
    let funcName = item[0]
    assembledApiList[key] = factoryMapper[funcName](item[1] + urlParam[item[2]])
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
