<template>
  <div class="animated fadeIn" style="background-color: white">
    <Row>
      <div id="main"></div>
    </Row>
  </div>
</template>

<script>
import echarts from 'echarts';
import 'echarts/map/js/china.js';
import 'echarts/map/js/province/shanxi1.js'

export default {
  name: "map",
  data() {
    return {
      currentMapName: 'china', // 默认显示中国地图
      anotherMapName: '陕西', // 另一个地图名称
      dataList:[
        { name: "西安市", value: 10108 },
        { name: "宝鸡市", value: 18143 },
        { name: "咸阳市", value: 10196 },
        { name: "渭南市", value: 13043 },
        { name: "延安市", value: 37030 },
        { name: "汉中市", value: 27101 },
        { name: "榆林市", value: 43070 },
        { name: "安康市", value: 23529 },
        { name: "商洛市", value: 19586 },
        { name: "铜川市", value: 3882 },

        {name: '南海诸岛', value: 0},
        {name: '天津', value: 11916.85},
        {name: '上海', value: 6340.5},
        {name: '重庆', value: 82403},
        {name: '河北', value: 187700},
        {name: '河南', value: 167000},
        {name: '云南', value: 394000},
        {name: '辽宁', value: 145900},
        {name: '黑龙江', value: 454000},
        {name: '湖南', value: 211800},
        {name: '安徽', value: 139600},
        {name: '山东', value: 157100},
        {name: '新疆', value: 1664900},
        {name: '江苏', value: 102600},
        {name: '浙江', value: 101800},
        {name: '江西', value: 166900},
        {name: '湖北', value: 185900},
        {name: '广西', value: 236700},
        {name: '甘肃', value: 454000},
        {name: '山西', value: 156000},
        {name: '内蒙古', value: 1183000},
        {name: '陕西', value: 205600},
        {name: '吉林', value: 187400},
        {name: '福建', value: 121400},
        {name: '贵州', value: 176100},
        {name: '广东', value: 179800},
        {name: '青海', value: 722300},
        {name: '西藏', value: 1228400},
        {name: '四川', value: 485000},
        {name: '宁夏', value: 66400},
        {name: '海南', value: 34000},
        {name: '台湾', value: 36193},
        {name: '香港', value: 1106},
        {name: '澳门', value: 32.8}

  ]
    };
  },
  mounted() {
    // this.registerMaps();
    this.buildMap();
  },
  methods: {
    // registerMaps() {},

    buildMap() {
      let myChart = echarts.init(document.getElementById("main"));
      let option = {
        tooltip: {
          trigger: 'item',
          formatter:function(params,ticket, callback) {
            return params.seriesName + '<br />' + params.name + '：' + params.value
          }
        },
        visualMap: {
          min: 0,
          max: 1000000,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],
          inRange: {
            color: ['#e0f3db', '#c7e9c0', '#a1d99b', '#74c476', '#41ab5d', '#238b45', '#006d2c', '#00441b', '#006837']
          },
          show: true
        },
        geo: {
          map: this.currentMapName, // 使用当前地图名称
          roam: true,
          label: {
            emphasis: {
              show: true,
              color: '#fff'
            }
          },
          // itemStyle: {
          //   normal: {
          //     areaColor: '#323c48',
          //     borderColor: '#111'
          //   },
          //   emphasis: {
          //     areaColor: '#2a333d'
          //   }
          // }
        },
        series: [
          {
            name: '草原占地面积（平方公里）',
            type: 'map',
            map: this.currentMapName, // 使用当前地图名称
            label: {
              normal: {
                show: true,
                fontSize:'10',//注意：地图省份名字字体如果过大会导致字体重叠
                color: 'rgba(0,0,0,0.7)'
              }
            },
            itemStyle: {
              normal:{
                borderColor: 'rgba(0, 0, 0, 0.2)'
              },
              emphasis:{
                areaColor: '#F3B329',//鼠标选择区域颜色
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            // geoIndex: 0,
            data: this.dataList
          }
        ]
      };
      myChart.setOption(option);
      myChart.on('click', this.handleMapClick);
    },
    handleMapClick(param) {
      // 处理点击事件
      switch (param.name) {
        case '陕西':
          this.currentMapName = this.anotherMapName; // 切换到另一个地图
          this.buildMap(); // 重新渲染地图
          break;
        default:
          this.currentMapName = 'china'; // 切换回中国地图
          this.buildMap(); // 重新渲染地图
          break;
      }
    }
  }
};
</script>

<template>
  <div class="animated fadeIn" style="background-color: white">
    <Row>
      <div id="main"></div>
    </Row>
  </div>
</template>

<script>
import echarts from 'echarts';
import 'echarts/map/js/china.js';
import 'echarts/map/js/province/shanxi1.js'

export default {
  name: "map",
  data() {
    return {
      currentMapName: 'china', // 默认显示中国地图
      anotherMapName: '陕西', // 另一个地图名称
      dataList:[
        { name: "西安市", value: 10108 },
        { name: "宝鸡市", value: 18143 },
        { name: "咸阳市", value: 10196 },
        { name: "渭南市", value: 13043 },
        { name: "延安市", value: 37030 },
        { name: "汉中市", value: 27101 },
        { name: "榆林市", value: 43070 },
        { name: "安康市", value: 23529 },
        { name: "商洛市", value: 19586 },
        { name: "铜川市", value: 3882 },

        {name: '南海诸岛', value: 0},
        {name: '天津', value: 11916.85},
        {name: '上海', value: 6340.5},
        {name: '重庆', value: 82403},
        {name: '河北', value: 187700},
        {name: '河南', value: 167000},
        {name: '云南', value: 394000},
        {name: '辽宁', value: 145900},
        {name: '黑龙江', value: 454000},
        {name: '湖南', value: 211800},
        {name: '安徽', value: 139600},
        {name: '山东', value: 157100},
        {name: '新疆', value: 1664900},
        {name: '江苏', value: 102600},
        {name: '浙江', value: 101800},
        {name: '江西', value: 166900},
        {name: '湖北', value: 185900},
        {name: '广西', value: 236700},
        {name: '甘肃', value: 454000},
        {name: '山西', value: 156000},
        {name: '内蒙古', value: 1183000},
        {name: '陕西', value: 205600},
        {name: '吉林', value: 187400},
        {name: '福建', value: 121400},
        {name: '贵州', value: 176100},
        {name: '广东', value: 179800},
        {name: '青海', value: 722300},
        {name: '西藏', value: 1228400},
        {name: '四川', value: 485000},
        {name: '宁夏', value: 66400},
        {name: '海南', value: 34000},
        {name: '台湾', value: 36193},
        {name: '香港', value: 1106},
        {name: '澳门', value: 32.8}

      ]
    };
  },
  mounted() {
    // this.registerMaps();
    this.buildMap();
  },
  methods: {
    // registerMaps() {},

    buildMap() {
      let myChart = echarts.init(document.getElementById("main"));
      let option = {
        tooltip: {
          trigger: 'item',
          formatter:function(params,ticket, callback) {
            return params.seriesName + '<br />' + params.name + '：' + params.value
          }
        },
        visualMap: {
          min: 0,
          max: 1000000,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],
          inRange: {
            color: ['#e0f3db', '#c7e9c0', '#a1d99b', '#74c476', '#41ab5d', '#238b45', '#006d2c', '#00441b', '#006837']
          },
          show: true
        },
        geo: {
          map: this.currentMapName, // 使用当前地图名称
          roam: true,
          label: {
            emphasis: {
              show: true,
              color: '#fff'
            }
          },
          // itemStyle: {
          //   normal: {
          //     areaColor: '#323c48',
          //     borderColor: '#111'
          //   },
          //   emphasis: {
          //     areaColor: '#2a333d'
          //   }
          // }
        },
        series: [
          {
            name: '草原占地面积（平方公里）',
            type: 'map',
            map: this.currentMapName, // 使用当前地图名称
            label: {
              normal: {
                show: true,
                fontSize:'10',//注意：地图省份名字字体如果过大会导致字体重叠
                color: 'rgba(0,0,0,0.7)'
              }
            },
            itemStyle: {
              normal:{
                borderColor: 'rgba(0, 0, 0, 0.2)'
              },
              emphasis:{
                areaColor: '#F3B329',//鼠标选择区域颜色
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            // geoIndex: 0,
            data: this.dataList
          }
        ]
      };
      myChart.setOption(option);
      myChart.on('click', this.handleMapClick);
    },
    handleMapClick(param) {
      // 处理点击事件
      switch (param.name) {
        case '陕西':
          this.currentMapName = this.anotherMapName; // 切换到另一个地图
          this.buildMap(); // 重新渲染地图
          break;
        default:
          this.currentMapName = 'china'; // 切换回中国地图
          this.buildMap(); // 重新渲染地图
          break;
      }
    }
  }
};
</script>

<style scoped>
*{margin:0;padding:0}
html,body{
  width:100%;
  height:100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
#main{
  width:800px;
  height:600px;
  margin-left: 250px;
  margin-top: 50px;
  border:none;
}
</style>


