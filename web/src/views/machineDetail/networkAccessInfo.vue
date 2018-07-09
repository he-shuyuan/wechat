<template>
    <el-form label-width="120px"
             label-position="left"
             class="uni-form-header">

        <el-card shadow="always"
                 class="netInfo">
            <el-form-item label="入网信息">
                <el-select v-model="selectedMachine"
                           filterable
                           :filter-method="filterMachine"
                           @change="change"
                           placeholder="输入或选择机械">
                    <el-option v-for="item in machineOptions"
                               :key="item.value"
                               :label="item.label"
                               :value="item">
                        <span>{{ item.label }}</span>
                        <span>{{ item.value }}</span>
                    </el-option>
                </el-select>
            </el-form-item>
        </el-card>

        <el-card shadow="always"
                 class="netStatus">
            <div slot="header">
                <el-form-item label="入网状态">
                    <span v-if="MachineInfo.terminalCode">
                        <i class="el-icon-success"
                           style="color:#67C23A;"></i>
                        <span>已入网</span>
                    </span>
                    <span v-else>
                        <i class="el-icon-warning"
                           style="color:#FF9831;"></i>
                        <span>未入网</span>
                    </span>
                </el-form-item>
            </div>
            <el-form-item label="">
                <div v-if="MachineInfo.terminalCode"
                     class="gray">已入网</div>
                <div v-else
                     class="gray">机械未入网，无法获取运行信息，如果您需要入网，请购买入网硬件设备。</div>
            </el-form-item>
        </el-card>

        <el-card shadow="always"
                 class="bind-terminal">
            <div slot="header"
                 class="header-slot">
                <span>绑定终端</span>
            </div>
            <el-form-item label="物联网编码">
                <el-input placeholder="请输入硬件终端编号"
                          v-model="terminalId"
                          :readonly="MachineInfo.terminalCode">
                </el-input>
                <el-button type="primary"
                           size="medium"
                           v-if="!MachineInfo.terminalCode"
                           @click.native="bindTerminal">绑定终端</el-button>
            </el-form-item>
        </el-card>

        <el-card shadow="always">
            <div slot="header"
                 class="header-slot">
                <span>入网记录</span>
            </div>
            
            <div class="reset" v-if="terminalLog.length>0">
                <el-form inline
                         label-width="100px" 
                         v-for="(prop,index) in terminalLog"
                         :key="index">
                    <el-form-item label="机械编号">
                        {{prop.machineId}}
                    </el-form-item>
                    <br>
                    <el-form-item label="创建人">
                        {{prop.createUserName}}
                    </el-form-item>
                    <el-form-item label="创建时间">
                        {{toDatetime(prop.createTime,'yyyy-MM-dd')}}
                    </el-form-item>
                    <el-form-item label="修改人">
                        {{prop.updateUserName}}
                    </el-form-item>
                    <el-form-item label="最近修改时间">
                        {{toDatetime(prop.updateTime,'yyyy-MM-dd')}}
                    </el-form-item>
                </el-form>
            </div>
            <el-form-item label="" v-else>
                <div class="gray">暂无入网记录</div>
            </el-form-item>
        </el-card>

    </el-form>
</template>


<script>
    import { standardAsync } from '@/api/async'
    import utils from 'utils'
    export default {
        components: {},
        data() {
            return {
                selectedMachine: {},
                terminalId: '',

                allOptions: [],
                machineList: [],
                machineOptions: [],
                urlMachineId: '',
                MachineInfo: {},
                terminalLog: []
            }
        },
        created() {
            this.urlMachineId = this.$route.query.machineId
            this.fetchMachineOptions()
        },
        mounted() {

        },
        methods: {
            fetchMachineOptions() {
                standardAsync(this, 'queryNotAllNetMachine', {}, res => {
                    this.machineList = res.body
                    if (this.machineList.length < 1) {
                        if(this.urlMachineId) {
                            this.change({value:this.urlMachineId})
                        }
                        return
                    }
                    // 将url参数相关的机械移至数组头部
                    let urlMachineId = this.urlMachineId
                    if (this.urlMachineId) {
                        let foundIndex = this.machineList
                            .findIndex(item => item.machineId === this.urlMachineId)
                        if (foundIndex >= 0) {
                            let moveItem = this.machineList.splice(foundIndex, 1)[0]
                            this.machineList.unshift(moveItem)
                        } else {
                            this.change({value:this.urlMachineId})
                        }
                    }
                    // 得到options
                    this.machineOptions = this.allOptions = this.machineList.map(item => ({
                        value: item.machineId,
                        label: item.machineName
                    }))
                    // 默认选中
                    if (this.machineOptions[0].value === this.urlMachineId) {
                        this.selectedMachine = this.machineOptions[0]
                        this.change(this.selectedMachine)
                    }
                })
            },
            filterMachine(text) {
                if (text) {
                    const reg = new RegExp(text, 'i')
                    this.machineOptions =
                        this.machineList
                            .map(item => ({ value: item.machineId, label: item.machineName }))
                            .filter(item => reg.test(item.label) || reg.test(item.value))
                } else {
                    this.machineOptions = this.allOptions
                }
            },
            change(item) {
                standardAsync(this, 'queryMachineInfo', { machineId: item.value }, res => {
                    this.MachineInfo = res.body
                    if(this.MachineInfo.terminalCode) {
                        this.terminalId = this.MachineInfo.terminalCode
                        this.queryTerminalLog(item.value)
                    } else {
                        this.terminalLog = []
                        this.terminalId = ''
                    }
                })
            },
            bindTerminal() {
                if (!this.selectedMachine.value) {
                    this.$message.error('请选择要绑定的机械');
                    return
                }
                if (!this.terminalId) {
                    this.$message.error('请输入硬件终端号');
                    return
                }

                let param = {
                    machineId: this.selectedMachine.value,
                    terminalCode: this.terminalId
                }
                standardAsync(this, 'registerTerminal', param, res => {
                    let foundIndex = this.machineList
                        .findIndex(item => item.machineId === this.selectedMachine.value)
                    this.machineList.splice(foundIndex, 1)
                    this.machineOptions = this.allOptions = this.machineList.map(item => ({
                        value: item.machineId,
                        label: item.machineName
                    }))
                    this.selectedMachine = {}
                    this.$message.success('绑定成功');
                })
            },
            queryTerminalLog(machineId) {
                standardAsync(this, 'queryTerminalLog', { machineId: machineId }, res => {
                    this.terminalLog = res.body
                })
            },
            toDatetime:utils.toDatetime
        },
        computed: {

        },
    }
</script>


<style lang="scss"
       scoped>
    @import '~@/styles/vueImport/index.scss';

    $input-width:400px;

    .el-card {
        margin: 20px;
        .header-slot {
            font-weight: 700;
            color: #606266;
            font-size: 14px;
        }
    }

    .el-form-item {
        margin: 0;
    }

    .gray {
        color: #929292;
    }

    .el-select-dropdown__item {
        span:nth-child(1) {
            float: left;
        }
        span:nth-child(2) {
            float: right;
            color: #8492a6;
            font-size: 13px;
        }
    }

    .netInfo {
        .el-select {
            width: $input-width;
        }
    }

    .bind-terminal {
        .el-input {
            width: $input-width;
        }
        .el-button {
            margin-left: 20px;
        }
    }

</style>
