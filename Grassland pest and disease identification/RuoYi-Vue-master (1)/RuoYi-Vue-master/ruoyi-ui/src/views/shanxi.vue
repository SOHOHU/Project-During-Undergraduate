<template>
  <div class="animated fadeIn" style="background-color: white">
    <Row>
      <div id="main"></div>
    </Row>
  </div>
</template>

<script>
import echarts from 'echarts'
import 'echarts/map/js/province/shanxi1.js'

export default {
  name: "map",
  data () {
    return {
      dataList:[
        { name: "西安市", value: 89250 },
        { name: "宝鸡市", value: 83631 },
        { name: "咸阳市", value: 66910 },
        { name: "渭南市", value: 98322 },
        { name: "延安市", value: 98322 },
        { name: "汉中市", value: 59734 },
        { name: "榆林市", value: 180765 },
        { name: "安康市", value: 51157 },
        { name: "商洛市", value: 44463 },
        { name: "铜川市", value: 71211 },
      ]
    }
  },
  methods: {
    buildMap(){
      let myChart = echarts.init(document.getElementById('main'));
      let option = {
        tooltip: {
          formatter:function(params,ticket, callback){
            return params.seriesName+'<br />'+params.name+'：'+params.value
          }//数据格式化
        },
        visualMap: {
          min: 30000,
          max: 200000,
          left: 'left',
          top: 'bottom',
          text: ['高', '低'],
          inRange: {
            color: ['#e0f3db', '#a8ddb5', '#41ab5d', '#006837'] // 从浅绿到深绿
          },
          show: true
        },
        geo: {
          map: '陕西',
          roam: false,//不开启缩放和平移
          zoom:1.23,//视角缩放比例
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
          }
        },
        series : [
          {
            name: '2022年人均GDP(人民币)',
            type: 'map',
            geoIndex: 0,
            data:this.dataList
          }
        ]

      };
      myChart.setOption(option);
    }
  },
  mounted() {
    this.buildMap()
  }
}
</script>

<style scoped>
*{margin:0;padding:0}
html,body{
  width:100%;
  height:100%;
}
#main{
  width:600px;
  height:450px;
  margin: 150px auto;
  border:none;
}
</style>
