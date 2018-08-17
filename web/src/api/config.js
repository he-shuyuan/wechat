const mockConfig = require('../../config/mock.env')

//  baseURL 请求后台网关地址  wxUrl 微信http://url   trackUrl 埋点js地址
export default (() => {
  const env = process.env.CUR_ENV
  let config

  if (env === 'development') {
    // npm run dev模式
    config = {
      baseURL: window.location.origin
    }
  } else if (env === 'devmock') {
    // npm run dev:mock模式
    config = {
      baseURL: `http://${window.location.hostname}:${mockConfig.port}`
    }
  } else if (env === 'dev') {
    // npm run build:dev模式
    config = {
      baseURL: ''
    }
  } else if (env === 'test') {
    config = {
      baseURL: '',
    }
  } else if (env === 'preprod') {
    config = {
      baseURL: '',
    }
  } else if (env === 'prod') {
    config = {
      baseURL: ''
    }
  }

  config = {
    ...config,

    // 添加通用配置
    busTypeId:'2',//业产品线id
    url: '/route',
    appId: 'c23087dbd171434697f356859e9e9a93', //
    method: 'POST',//声明头部
    headers: { 'Content-Type': 'application/json; charset=utf-8' },
    // paramsSerializer: function(params) {
    //   return Qs.stringify(params)
    // },
    timeout: 20000,//超时时间
    withCredentials: false, // default
    responseType: 'json', // 报文格式
    maxContentLength: 2000,//报文大小
    validateStatus: function(status) {
      return status >= 200 && status < 300 // default
    },
    maxRedirects: 5, // default
  }
  return config
})()
