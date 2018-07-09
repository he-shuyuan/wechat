'use strict'
// 获取内网IP
function getLocalIP() {
  var os = require('os'),
    iptable = {},
    ifaces = os.networkInterfaces(),
    localIP = ''
  for (var dev in ifaces) {
    if (localIP) {
      break
    } else {
      ifaces[dev].forEach(function(details, alias) {
        if (details.family == 'IPv4') {
          if (/^10\./.test(details.address) || /^192\.168/.test(details.address)) {
            localIP = details.address
            return
          }
        }
      })
    }
    return localIP
  }
}

module.exports = {
  host: getLocalIP(),
  port: 9111,
}
