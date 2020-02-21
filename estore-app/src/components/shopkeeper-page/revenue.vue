<template>
  <mu-container v-resize="resize">
    <mu-paper :z-depth="1" style="margin-top: 24px;">
      <mu-row>
        <mu-col>
          <mu-flex justify-content="center" style="margin-top: 24px;font-size: 24px; font-weight: 600;">
            {{ this.currentDate.format('YYYY-MM-DD') }} 前一周的营业额
          </mu-flex>
        </mu-col>
      </mu-row>
      <mu-row>
        <mu-col>
          <v-chart style="width: 100%;height: 500px;" ref="chart"
            :options="options" :auto-resize="true"/>
        </mu-col>
      </mu-row>
    </mu-paper>

    <mu-row>
      <mu-col>
        <mu-flex justify-content="end">
          <mu-slide-top-transition>
            <mu-paper style="margin-top: -426px;" :z-depth="1" v-show="datePickerShow">
              <mu-date-picker :date.sync="pickedDate"></mu-date-picker>
            </mu-paper>
          </mu-slide-top-transition>
        </mu-flex>
      </mu-col>
      <mu-col span="1">
        <mu-flex justify-content="end">
          <mu-button style="margin-top: -28px; margin-right: 16px;" @click="pickDate"
            fab color="error">
            <mu-icon value="today"></mu-icon>
          </mu-button>
        </mu-flex>
      </mu-col>
    </mu-row>


  </mu-container>
</template>

<script>
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'

export default {
  data() {
    return {
      pickedDate: new Date(),
      currentDate: new Date(),
      datePickerShow: false,
      options: {
        color: ['#2196f3'],
        tooltip : {
          trigger: 'axis',
          axisPointer : {
            type : 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '8%',
          bottom: '12%',
          containLabel: true
        },
        xAxis: [
          {
            type : 'category',
            name: '日期',
            data : [],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            name: '营业额',
            type : 'value'
          }
        ],
        series : [
          {
            name: '金额（￥）',
            type: 'bar',
            barWidth: '60%',
            data:[]
          }
        ]
      }
    }
  },
  methods: {
    resize() {
      this.$refs.chart.resize()
    },
    pickDate() {
      if (this.datePickerShow) {
        if (this.currentDate.format('YYYY-MM-DD') != this.pickedDate.format('YYYY-MM-DD')) {
          this.currentDate = this.pickedDate
          this.fetchData(this.currentDate)
        }
      }
      this.datePickerShow = !this.datePickerShow
    },
    fetchData(currentDate) {
      let xAxisData = this.options.xAxis[0].data
      let seriesData = this.options.series[0].data
      xAxisData.splice(0, xAxisData.length)
      seriesData.splice(0, seriesData.length)

      for (let i = 7; i > 0; i--) {
        let beforeDate = new Date().setDate(currentDate.getDate()-i)
        this.options.xAxis[0].data.push(
          new Date(beforeDate).format('YYYY-MM-DD')
        )
      }

      this.axios.get('/api/orders/week/' + currentDate.format('YYYY-MM-DD'))
      .then(response => {
        for (let i = 0; i < 7; i++) {
          let date = xAxisData[i]
          let revenue = 0
          response.data.forEach(item => {
            if (item.ct == date) revenue = item.revenue
          })
          seriesData.push(revenue.toFixed(2))
        }
      })
    }
  },
  mounted() {
    this.fetchData(new Date())
  }
}
</script>

<style>
.hidden {
  visibility: hidden;
}
</style>
