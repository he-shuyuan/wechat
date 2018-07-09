<template>
  <div class="create-form">
    <el-form :model="params"
      :rules="rules"
      ref="form"
      class="search-form"
      :label-width="labelWidth?labelWidth:'100px'">
      <slot name="before"
        :row="params"></slot>
      <el-row :gutter="20">
        <el-col v-for="(data,index) in formOpt"
          :key="index"
          :span="data.type=='hidden'&& !data.col?0:data.col || 6">
          <el-form-item :prop="(data.type == 'startDate'|| data.type == 'endDate' || 
                      data.type == 'area'|| data.type == 'cascader') && !data.prop ?
                      (data.type == 'startDate'?'internetTimeStart'
                      :data.type == 'endDate'?'internetTimeEnd':
                      data.type == 'cascader'?'cascader':
                      'areaCodeList')
                      :data.prop"
            :label="data.label"
            :label-width="data.label?data.labelWidth:'0'">
            <el-input v-if="data.type=='text' || data.type=='hidden'"
              v-model.trim="data.type=='hidden'?data.default: params[data.modelValue]"
              :type="data.type"
              :style="data.style"
              :prefix-icon="data.prefixIcon"
              :placeholder="data.placeholder"
              :suffix-icon="data.suffixIcon"
              :size="data.size"
              :name="data.name"
              :clearable="data.clearable || true"
              :readonly="data.readonly"
              :maxlength="data.maxlength"
              :minlength="data.minlength"
              :disabled="data.disabled"
              :max="data.max"
              :min="data.min"></el-input>
            <el-select v-if="data.type=='select'"
              v-model="params[data.modelValue]"
              :style="data.style"
              :method="data.method"
              :params="data.params"
              :loading="data.loading"
              :loading-text="data.loadingText"
              :valueName="data.valueName"
              :labelName="data.labelName"
              :clearable="data.clearable || true"
              :placeholder="data.placeholder"
              :size="data.size"
              :filterable="data.filterable || true"
              :disabled="data.disabled"
              :name="data.name">
              <el-option v-for="item in data.initData"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            <el-date-picker v-if="data.type == 'startDate'"
              style="max-width:100%"
              :style="data.style"
              v-model="params[data.modelValue]"
              type="date"
              :editable="data.editable || false"
              :size="data.size"
              :clearable="data.clearable || true"
              :format="data.format"
              :value-format="data.valueFormat?data.valueFormat:'yyyy-MM-dd'"
              :placeholder="data.placeholder||'选择开始日期'">
            </el-date-picker>
            <el-date-picker v-if="data.type == 'endDate'"
              style="max-width:100%"
              :style="data.style"
              v-model="params[data.modelValue]"
              type="date"
              :clearable="data.clearable || true"
              :editable="data.editable || false"
              :size="data.size"
              :format="data.format"
              :value-format="data.valueFormat?data.valueFormat:'yyyy-MM-dd'"
              :placeholder="data.placeholder||'选择结束日期'">
            </el-date-picker>
            <el-date-picker v-if="data.type == 'date'"
              v-model="params[data.modelValue]"
              style="max-width:100%"
              :style="data.style"
              :clearable="data.clearable || true"
              type="daterange"
              :editable="data.editable || false"
              :size="data.size"
              :prefix-icon="data.prefixIcon"
              :clear-icon="data.clearIcon"
              :default-value="data.defaultValue"
              :start-placeholder="data.startPlaceholder ||'开始日期' "
              :end-placeholder="data.endPlaceholder || '结束日期'"
              range-separator="——"
              :align="data.align"
              :format="data.format"
              :value-format="data.valueFormat?data.valueFormat:'yyyy-MM-dd'"
              :name="data.name">
            </el-date-picker>
            <el-cascader v-if="data.type == 'area'"
              :options="data.initData?data.initData:areaOptions"
              @active-item-change="handleItemChange"
              v-model="params[data.modelValue]"
              style="max-width:100%;width:100%;"
              :style="data.style"
              :method="data.method"
              :size="data.size"
              :clearable="data.clearable || true"
              :expand-trigger="data.expandTrigger || 'click'"
              :show-all-levels="data.showAllLevels"
              :filterable="data.filterable || true"
              :placeholder="data.placeholder||'请选择项目区域'"
              :change-on-select="data.changeOnSelect"
              :disabled="data.disabled"
              :separator="data.separator || '  '"></el-cascader>
            <el-cascader v-if="data.type == 'cascader'"
              :options="data.initData?data.initData:dcGroupOptions"
              @active-item-change="handleItemChange"
              v-model="params[data.modelValue]"
              style="max-width:100%;width:100%;"
              :style="data.style"
              :dcGroup="data.dcGroup"
              :size="data.size"
              :clearable="data.clearable || true"
              :expand-trigger="data.expandTrigger || 'hover'"
              :show-all-levels="data.showAllLevels || false"
              :filterable="data.filterable || true"
              :placeholder="data.placeholder||'请选择'"
              :change-on-select="data.changeOnSelect"
              :disabled="data.disabled"
              :separator="data.separator"></el-cascader>
            <el-button v-if="data.type == 'search'"
              :type="data.type"
              :size="data.size"
              :style="data.style"
              @click="search">{{data.searchBtnText || '搜索'}}</el-button>
            <el-button v-if="data.type == 'reset'"
              :type="data.type"
              :size="data.size"
              :style="data.style"
              @click="reset">{{data.resetBtnText || '重置'}}</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <slot name="after"
        :row="params"></slot>
    </el-form>
  </div>
</template>

<script>
import { standardAsync, customAsync } from "@/api/async";
import utils from "utils";
let areaOptions = [];
let dcGroupOptions = [];
const dictMapper = (list, labelName, valueName) =>
  list.map(item => ({
    label: item[labelName],
    value: item[valueName]
  }));
export default {
  props: {
    formOpt: {
      type: Array,
      default() {
        return [];
      }
    },
    submitHandler: {
      type: Function
    },
    labelWidth: String,
    slotProp: Array,
    rules: Object
  },
  data() {
    let params = {};
    //初始化Form
    this.formOpt.forEach((v, i) => {
      if (v.type == "area") {
        v.modelValue = v.prop ? v.prop : "areaCodeList";
        params[v.modelValue] = [];
        if (!v.initData) {
          if (v.method) {
            this.setAreaOptions(v.method);
          } else {
            this.setAreaOptions();
          }
        }
      } else if (v.type == "startDate") {
        v.modelValue = v.prop ? v.prop : "internetTimeStart";
        params[v.modelValue] = "";
      } else if (v.type == "endDate") {
        v.modelValue = v.prop ? v.prop : "internetTimeEnd";
        params[v.modelValue] = "";
      } else if (v.type == "cascader") {
        v.modelValue = v.prop ? v.prop : "cascader";
        params[v.modelValue] = [];
        if (!v.initData) {
          if (v.dcGroup) {
            this.setDcGroupOptions(v);
          } else if (v.method) {
          }
        }
      } else if (v.type == "date") {
        v.modelValue = v.prop ? v.prop : "internetTime";
        params[v.modelValue] = "";
      } else {
        v.modelValue = v.prop ? v.prop : null;
        params[v.modelValue] = "";
      }
      if (v.type == "select" && !v.initData && v.method) {
        this.selectInit(v);
      }
    });
    if (this.slotProp && this.slotProp.length > 0) {
      for (let item of this.slotProp) {
        for (let key in item) {
          if (item[key] == Array) {
            params[key] = [];
          } else if (item[key] == Object) {
            params[key] = {};
          } else {
            params[key] = "";
          }
        }
      }
    }
    return {
      params,
      areaOptions,
      dcGroupOptions,
      areaInit: [],
      formEvent: null
    };
  },
  created() {},
  mounted() {
    this.formEvent = this.$refs.form;
  },
  methods: {
    search() {
      //表单数据处理
      delete this.params.undefined;
      let params = {
        ...this.params
      };
      for (let item of this.formOpt) {
        if (item.type == "data") {
          if (item.prop) {
            params[item.prop + "Start"] = this.params[item.prop][0];
            params[item.prop + "End"] = this.params[item.prop][1];
          } else {
            params["internetTimeStart"] = this.params["internetTime"]
              ? this.params["internetTime"][0]
              : "";
            params["internetTimeEnd"] = this.params["internetTime"]
              ? this.params["internetTime"][1]
              : "";
          }
        }
        if (item.type == "area") {
          if (
            this.params[item.modelValue] &&
            this.params[item.modelValue].length > 0
          ) {
            params.areaProvince = this.params[item.modelValue][0];
            params.areaCity = this.params[item.modelValue][1];
          }
        }
        if (item.type == "cascader") {
          params[item.modelValue] = this.params[item.modelValue][
            this.params[item.modelValue].length - 1
          ];
        }
        if (item.type == "startDate") {
          this.curStartDate = this.params[item.modelValue];
        }
        if (item.type == "endDate") {
          this.curEndDate = this.params[item.modelValue];
        }
        if (
          this.curStartDate &&
          this.curEndDate &&
          new Date(this.curStartDate).getTime() -
            new Date(this.curEndDate).getTime() >
            0
        ) {
          this.$alert(
            '<div style="font-size:16px;">开始时间不能大于结束时间</div></br>',
            "提示",
            {
              confirmButtonText: "确定",
              dangerouslyUseHTMLString: true
            }
          );
          return;
        }
      }
      this.newParams = params;
      if (this.submitHandler) {
        this.submitHandler(this.newParams);
      }
      this.$emit("searchHandle", this.newParams);
      return this.newParams;
    },
    reset() {
      //重置表单并刷新table
      this.$refs["form"].resetFields();
      for (let key in this.newParams) {
        this.newParams[key] = "";
      }
      this.$parent.searchHandle();
    },
    handleItemChange(array) {
      // this.$emit('active-item-change',array)
    },
    setAreaOptions(method) {
      //初始化地区选择栏数据
      let _method = method ? method : "getAreaList";
      customAsync({
        that: this,
        method: _method,
        paramObj: {
          notShowLoading: true
        },
        callback: res => {
          //sessionStorage储存区域列表
          areaOptions = utils.areaDataHandler(res.body);
        },
        errorCallback: () => {}
      });
    },
    DicDataHandle(item, res) {
      let _label = "";
      let _value = "";
      if (item.valueName) {
        _value = item.valueName;
      } else {
        _value = "value";
      }
      if (item.labelName) {
        _label = item.labelName;
      } else {
        _label = "label";
      }
      let initData = res.map(_item => {
        return {
          value: _item[_value],
          label: _item[_label]
        };
      });
      item.initData = initData;
      //重载刷新数据视图
      item.type = "text";
      this.$nextTick(() => {
        item.type = "select";
      });
    },
    selectInit(item) {
      let initData;
      let dicItemName;
      if (item.params.dcCodeMain) {
        dicItemName = "dcCodeMain_" + item.params.dcCodeMain;
      }
      item.loading = true;
      let params = {};
      if (item.params) {
        params = {
          ...item.params
        };
      }
      params.notShowLoading = true;
      customAsync({
        that: this,
        method: item.method,
        paramObj: params,
        callback: res => {
          item.loading = false;
          this.DicDataHandle(item, res.body);
        },
        errorCallback: () => {}
      });
    },
    dcGroupDatahandle(res) {
      dcGroupOptions = res.map(item => ({
        value: item.dcCodeMain,
        label: item.dcNameMain,
        children: dictMapper(item.dcList, "dcName", "dcCode")
      }));
      this.dcGroupOptions = dcGroupOptions;
    },
    setDcGroupOptions(item) {
      customAsync({
        that: this,
        method: "getDicGroupList",
        paramObj: {
          notShowLoading: true,
          dcGroup: item.dcGroup
        },
        callback: res => {
          console.log("sres", res);
          this.dcGroupDatahandle(res.body);
        },
        errorCallback: () => {}
      });
    }
  }
};
</script>

<style scoped>
.search-form {
  background: #fff;
  padding: 20px 10px;
}
</style>
<style>
.create-form .el-button {
  line-height: normal;
  height: 40px;
  padding: 10px 20px;
}

.create-form .el-form-item__content {
  /* line-height: normal; */
}
</style>