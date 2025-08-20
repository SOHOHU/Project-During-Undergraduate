<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import {getCountnumber} from "@/api/pests/pests"
const animationDuration = 3000

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
      chart: null
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
      getCountnumber().then(response => {
        console.log(response);
        let array = response.data;
        let v1 = [];
        let v2 = [];
        let v3 = [];
        let v4 = [];
        for (let i = 0; i < array.length; i++) {
          v1.push(array[i].value1);
          v2.push(array[i].value2);
          v3.push(array[i].value3);
          v4.push(array[i].value4);
        }
        this.chart.setOption({
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          radar: {
            radius: '66%',
            center: ['50%', '42%'],
            splitNumber: 8,
            splitArea: {
              areaStyle: {
                color: 'rgba(127,95,132,.3)',
                opacity: 1,
                shadowBlur: 45,
                shadowColor: 'rgba(0,0,0,.5)',
                shadowOffsetX: 0,
                shadowOffsetY: 15
              }
            },
            indicator: [
              {name: '患病个数', max: 100},
              {name: '病害目类', max: 100},
              {name: '病害科类', max: 100},
              {name: '病害种类', max: 100},
            ]
          },
          legend: {
            left: 'center',
            bottom: '10',
            data: ['虫害', '毒草', '病害', '鼠害']
          },
          series: [{
            type: 'radar',
            symbolSize: 0,
            areaStyle: {
              normal: {
                shadowBlur: 13,
                shadowColor: 'rgba(0,0,0,.2)',
                shadowOffsetX: 0,
                shadowOffsetY: 10,
                opacity: 0.3
              }
            },
            data: [
              {
                value: v1,
                name: '虫害'
              },
              {
                value: v2,
                name: '毒草'
              },
              {
                value: v3,
                name: '病害'
              },
              {
                value: v4,
                name: '鼠害'
              }
            ],
            animationDuration: animationDuration
          }]
        })
      })

    }
  }
}
</script>
