<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import {getCounttype} from "@/api/pests/pests"

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      type: [],
      value2: []
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      getCounttype().then(response => {
        console.log(response);
        let datas = [];
        let array = response.data;
        for (let i = 0; i < array.length; i++) {
          let item = {
            value: array[i].value,
            name: array[i].order
          };
          datas.push(item);
        }
        this.chart.setOption({
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          series: [
            {
              name: 'WEEKLY WRITE ARTICLES',
              type: 'pie',
              roseType: 'radius',
              radius: [15, 95],
              center: ['50%', '50%'],
              data: datas,
              animationEasing: 'cubicInOut',
              animationDuration: 2600
            }
          ]
        })
      })

    }
  }
}
</script>
