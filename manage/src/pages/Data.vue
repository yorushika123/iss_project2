<template>
  <div style="height: 5000px">

    <div class="block" style="text-align: center">
      <span class="demonstration">日期查询</span>
      <el-date-picker
        v-model="valuedate"
        type="daterange"
        align="right"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions"
        format="yyyy 年 MM 月 dd 日"
        value-format="yyyy-MM-dd"
      >
      </el-date-picker>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </div>

    <el-row class="tac"  style="background:  rgba(131,116,116,0.04);margin-top: 20px">
      <el-col :span="4" :offset="1" >
        <div class="box1">
          <div><h3>总发帖数</h3></div>
          <div style="margin-top: 20px"><h1 v-if="sum1.count!=''" style="font-size: 20px;color: #6f42c1">{{sum1.count}}</h1><h1 v-if="sum1.count==''||sum1.count==null">0</h1></div>
        </div>
        <div class="box1">
          <div><h3>发布贴子</h3></div>
          <div style="margin-top: 20px"><h1 v-if="postnumber!=''" style="font-size: 20px;color: #6f42c1">{{postnumber}}</h1><h1 v-if="postnumber==''||postnumber==null">0</h1></div>
        </div>
      </el-col>

      <el-col :span="4" :offset="2" >
        <div class="box1">
          <div><h3>帖子总评论量</h3></div>
          <div style="margin-top: 20px"><h1  v-if="sum1.sumcomment_counts!=''" style="font-size: 20px;color: #6f42c1">{{sum1.sumcomment_counts}}</h1><h1 v-if="sum1.sumcomment_counts==null||sum1.sumcomment_counts==''">0</h1></div>
        </div>
        <div class="box1">
          <div><h3>发布评论</h3></div>
          <div style="margin-top: 20px"><h1 style="font-size: 20px;color: #6f42c1">0</h1></div>
        </div>
      </el-col>

      <el-col :span="4" :offset="2">
        <div class="box1">
          <div><h3>帖子总点赞数</h3></div>
          <div style="margin-top: 20px"><h1 v-if="sum1.sumup_counts!=''" style="font-size: 20px;color: #6f42c1">{{sum1.sumup_counts}}</h1><h1 v-if="sum1.sumup_counts==null||sum1.sumup_counts==''">0</h1></div>
        </div>
        <div class="box1">
          <div><h3>发表提问</h3></div>
          <div style="margin-top: 20px"><h1  v-if="Interlocutionnumber!=''" style="font-size: 20px;color: #6f42c1">{{Interlocutionnumber}}</h1><h1 v-if="Interlocutionnumber==''||Interlocutionnumber==null">0</h1></div>
        </div>
      </el-col>

      <el-col :span="4" :offset="2">
        <div class="box1">
          <div><h3>帖子总点击数</h3></div>
          <div style="margin-top: 20px"><h1 v-if="sum1.sumview_counts!=''" style="font-size: 20px;color: #6f42c1">{{sum1.sumview_counts}}</h1><h1 v-if="sum1.sumview_counts==null||sum1.sumview_counts==''">0</h1></div>
        </div>
      </el-col>
    </el-row>
    <!--  <div><h2>数据图表</h2></div>-->
    <div style="display: flex;margin-top: 70px">
      <div id="ping" style="width: 400px;height:400px;"></div>
      <div id="myChart" style="width: 400px;height:400px;"></div>
      <div id="main" style="width: 400px;height:400px;"></div>
    </div>

  </div>
</template>


<script>
import * as echarts from 'echarts';
export default {
  name: 'Data',
  data() {
    return {
      username: "",
      user: '',
      tip:2022,
      postnumber:'',
      Interlocutionnumber:'',
      sum1:[],
      sum2:[],
      // sumpost:'',
      // sumgood:'',
      // sumview:'',
      // sumcomment:'',
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      valuedate: '',
      pickdata: {
        startTime: '',
        endTime: '',
      },
    }
  },
  mounted() {
    var myChart1 = echarts.init(document.getElementById('main'));
    myChart1.setOption({
      title: {
        text: '帖子发布标签柱状图',
        left: 'center',
        top:"top"
      },
      tooltip: {},
      xAxis: {
        name: '标签',
        data: ['Java', 'C++', 'C#', 'Vue', 'python','Js']
      },
      yAxis: {
        name: '数量',
      },
      series: [
        {
          color: '#5984c5',
          type: 'bar',
          data: [131, 132, 362, 101, 210, 220]
        }
      ]
    });

    var myChart = echarts.init(document.getElementById('myChart'));
    myChart.setOption({
      title: {
        text: '帖子发布折线图',
        left: 'center',
        top:"top"
      },
      xAxis: {
        name:"月份",
        data: [1,2,3,4,5,6,7,8,9,10,11,12,]
      },
      yAxis: {
        name:"发帖数量",
      },
      series: [
        {
          data: [10, 40, 28, 23, 40,12,44,22,56,12,34,12],
          type: 'line',
          name:'平均',
          areaStyle: {
            opacity: 0.9,
            color: 'rgba(236,234,230,0)'
          }
        },
      ]
    });

    var ping = echarts.init(document.getElementById('ping'));
    ping.setOption({
      title: {
        text: '贴子标签分类图',
        left: 'center',
        top:"top"
      },
      series: [
        {
          type: 'pie',
          data: [
            {
              value: 335,
              name: 'c++'
            },
            {
              value: 123,
              name: 'java'
            },
            {
              value: 131,
              name: 'springboot'
            },
            {
              value: 232,
              name: 'js'
            },
            {
              value: 391,
              name: 'C#'
            },
          ],
          radius: '40%'
        }
      ],

    });
  },
  methods: {
    selectSumInterlocutionBYTime() {
      this.$http.post('http://localhost:8888/Interlocution/selectSumInterlocutionBYTime',
        {"creater_id": JSON.parse(localStorage.getItem("users")).id,
          "startTime":this.pickdata.startTime,
          "endTime": this.pickdata.endTime,
        },
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code ===200) {
          this.Interlocutionnumber = res.data.data;
        } else {
        }
      })
    },
    selectSum() {
      this.$http.post('http://localhost:8888/Post/selectSum/'+JSON.parse(localStorage.getItem("users")).id,
        {"author_id": JSON.parse(localStorage.getItem("users")).id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code === 200) {
          this.sum1= res.data.data;
        } else {
        }
      })
    },
    onSubmit() {
      console.log(this.valuedate)
      if (this.valuedate.length == '0') {
        this.$alert('请输入要查询的月份！', '提示', {
          confirmButtonText: '确定',
          type: 'waring',
        });
      } else {
        this.pickdata.startTime = this.valuedate[0]
        this.pickdata.endTime = this.valuedate[1]
        console.log(this.pickdata.startTime)
        console.log(this.pickdata.endTime)
        this.$http.post('http://localhost:8888/Post/selectSumBYtime',
          {"author_id": JSON.parse(localStorage.getItem("users")).id,
                 "startTime":this.pickdata.startTime,
                  "endTime": this.pickdata.endTime,
          },
          {emulateJSON: true},
        ).then(function (res) {
          if (res.data.code === 200) {
            this.sum2= res.data.data;
          } else {
          }
        })
        this.selectSumInterlocutionBYTime();
      }
    }
  },
  created() {
    this.selectSum()
  }
}
</script>

<style scoped>
.box1{
  width: 100%;
  height: 100px;
  /*border:  1px solid #bbb7b7;*/
  text-align: center;
}
</style>
