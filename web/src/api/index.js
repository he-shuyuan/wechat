import axios from 'axios'
import config from './config'
import util from './util'
const appId = '000101'
// api工厂
const apiFactory = uri => payload => axios.post('/gateway' + uri, JSON.stringify(payload), config)
// 读取api列表
const apiList = require('./apiList.json5')

export default {
  /**
   * 抽象api
   * @param  {[type]} method [description]
   * @param  {[type]} param  [description]
   * @return {[type]}        [description]
   */
  abstractApi:function(method,param){
    if(apiList.hasOwnProperty(method)){
      return apiFactory(apiList[method] + `?appId=${appId}&requestId=${util.getUUID()}&token=${util.sessionStorage.getToken()}`)(param);
    }
  }

}
