'use strict'
const express = require('express')
const mockConfig = require('../config/mock.env')
const app = express()
const fs = require('fs')
const path = require('path')
const JSON5 = require('json5')
require('json5/lib/register')
const apiiList = require('../src/api/apiList.json5')

app.all('/*', function(req, res, next) {
    // CORS支持
    res.header('Access-Control-Allow-Origin', '*')
    res.header('Access-Control-Allow-Methods', 'POST, OPTIONS')
    res.header('Access-Control-Allow-Headers', 'Content-Type')
    next()
})

app.post('/*', function(req, res) {
    // 得到文件名
    let fileName = ''
    for (let key in apiiList) {
        if (apiiList.hasOwnProperty(key)) {
            let apiPath = apiiList[key][1]
            if (req.path.match(apiPath)) {
                fileName = key
                break
            }
        }
    }
    // 读文件
    let result
    try {
        const filePath = path.resolve(__dirname, './' + fileName + '.json')
        const stringFile = fs.readFileSync(filePath, 'utf-8')
        result = {
            head: { errorCode: '0' },
            body: JSON.parse(stringFile),
        }
    } catch (error) {
        console.log('error ', error)
        result = {
            head: { errorCode: '1', errorMsg: '未找到文件' + error.path },
        }
    }
    // 模拟300~600ms网络延迟
    const latency = Math.random() * 300 + 300
    global.setTimeout(function() {
        res.send(result)
    }, latency)
})

const server = app.listen(mockConfig.port, function() {
    console.log('mock service serves here http://' + mockConfig.host + ':' + mockConfig.port)
})
