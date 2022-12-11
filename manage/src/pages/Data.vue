<template>
  <div style="height: 5000px">

    <div class="block" style="text-align: center">
      <span class="demonstration">数据查询</span>
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

    <el-row class="tac"  style="background:  rgba(131,116,116,0.06);margin-top: 20px">
      <el-col :span="4" :offset="1" >
        <div class="box1">
          <div><h3>总发帖数</h3></div>
          <div style="margin-top: 20px"><h1 v-if="sum1.count!=''" style="font-size: 20px;color: #6f42c1">{{sum1.count}}</h1><h1 v-if="sum1.count==''||sum1.count==null">0</h1></div>
        </div>
<!--        <div class="box1">-->
<!--          <div><h3>发布贴子</h3></div>-->
<!--          <div style="margin-top: 20px"><h1 v-if="postnumber!=''" style="font-size: 20px;color: #6f42c1">{{postnumber}}</h1><h1 v-if="postnumber==''||postnumber==null">0</h1></div>-->
<!--        </div>-->
      </el-col>

      <el-col :span="4" :offset="2" >
        <div class="box1">
          <div><h3>帖子总评论量</h3></div>
          <div style="margin-top: 20px"><h1  v-if="sum1.sumcomment_counts!=''" style="font-size: 20px;color: #6f42c1">{{sum1.sumcomment_counts}}</h1><h1 v-if="sum1.sumcomment_counts==null||sum1.sumcomment_counts==''">0</h1></div>
        </div>
<!--        <div class="box1">-->
<!--          <div><h3>发布评论</h3></div>-->
<!--          <div style="margin-top: 20px"><h1 v-if="Commentsnumber!=''" style="font-size: 20px;color: #6f42c1">{{Commentsnumber}}</h1><h1 v-if="Commentsnumber==''||Commentsnumber==null">0</h1></div>-->
<!--        </div>-->
      </el-col>

      <el-col :span="4" :offset="2">
        <div class="box1">
          <div><h3>帖子总点赞数</h3></div>
          <div style="margin-top: 20px"><h1 v-if="sum1.sumup_counts!=''" style="font-size: 20px;color: #6f42c1">{{sum1.sumup_counts}}</h1><h1 v-if="sum1.sumup_counts==null||sum1.sumup_counts==''">0</h1></div>
        </div>
<!--        <div class="box1">-->
<!--          <div><h3>发表提问</h3></div>-->
<!--          <div style="margin-top: 20px"><h1  v-if="Interlocutionnumber!=''" style="font-size: 20px;color: #6f42c1">{{Interlocutionnumber}}</h1><h1 v-if="Interlocutionnumber==''||Interlocutionnumber==null">0</h1></div>-->
<!--        </div>-->
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
      <div id="ciyun" style="width: 300px; height: 300px"></div>
      <div id="ping" style="width: 400px;height:400px;"></div>
      <div id="main" style="width: 400px;height:400px;"></div>
    </div>

    <div class="block" style="text-align: center">
      <span class="demonstration">数据查询</span>
      <el-date-picker
        v-model="value1"
        type="monthrange"
        range-separator="至"
        start-placeholder="开始月份"
        end-placeholder="结束月份"
        format="M月"
        value-format="M"
      >
      </el-date-picker>
      <el-button type="primary" @click="selectpostBYmonth" >查询</el-button>
    </div>
    <div id="myChart" style="height:400px;margin-top: 20px"></div>
  </div>
</template>


<script>
import * as echarts from 'echarts';
import theme from '../assets/theme.json'
export default {
  name: 'Data',
  data() {
    return {
      value1:'',
      username: "",
      user: '',
      tip:2022,
      postnumber:'',
      Interlocutionnumber:'',
      Commentsnumber:'',
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
      startmonth:'',
      endmonth:'',
      postnumber2:[],
      monthnumber2:[],
      interlocutionnumber2:[],
      commentsnumber2:[]
    }
  },
  mounted() {

    var data = {
      value: [{
        "name": "java",
        "value": 1446
      },
        {
          "name": "C++",
          "value": 928
        },
        {
          "name": "vue",
          "value": 906
        },
        {
          "name": "springboot",
          "value": 825
        },
        {
          "name": "python",
          "value": 6
        },
      ],
      image:"data:image/png;base64,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"
    }
    echarts.registerTheme('theme',theme)
    var myChart2 = echarts.init(document.getElementById('ciyun'),theme);
    var maskImage = new Image();
    maskImage.src = data.image
    maskImage.onload = function(){
      myChart2.setOption({
        backgroundColor:'#fff',
        tooltip: {
          show: false
        },
        title: {
          text: '标签词云图',
          left: 'center',
          top:"top"
        },
        series: [{
          type: 'wordCloud',
          gridSize: 1,
          sizeRange: [12, 55],
          rotationRange: [-45, 0, 45, 90],
          maskImage: maskImage,
          textStyle: {
            normal: {
              color: function() {
                return 'rgb(' +
                  Math.round(Math.random() * 255) +
                  ', ' + Math.round(Math.random() * 255) +
                  ', ' + Math.round(Math.random() * 255) + ')'
              }
            }
          },
          left: 'center',
          top: 'center',
          // width: '96%',
          // height: '100%',
          right: null,
          bottom: null,
          // width: 300,
          // height: 200,
          // top: 20,
          data: data.value
        }]
      })
    }

    var myChart1 = echarts.init(document.getElementById('main'));
    myChart1.setOption({
      title: {
        text: '柱状图',
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

    echarts.registerTheme('theme',theme)
    var myChart = echarts.init(document.getElementById('myChart'),theme);
    myChart.setOption({
      legend: {
        orient: 'vertical',
        x:'right',
        y:'top'
      },
      title: {
        text: '数据折线图',
        left: 'center',
        top:"top"
      },
      xAxis: {
        name:"月份",
        data: [],
      },
      yAxis: {
        name:"数量",
        splitNumber : 0,
      },
      series: [
        {
          data: [],
          type: 'line',
          name:'发帖数',
          areaStyle: {
            opacity: 0,
            color: 'rgba(236,234,230,0)'
          }
        },
        {
          data: [],
          type: 'line',
          name:'提问数',
          areaStyle: {
            opacity: 0,
            color: 'rgba(227,165,39,0)'
          }
        },
        {
          data: [],
          type: 'line',
          name: '评论数',
          areaStyle: {
            opacity: 0,
            color: 'rgb(241,186,10)'
          }
        },
      ]
    });

    echarts.registerTheme('theme', theme)
    var ping = echarts.init(document.getElementById('ping'),theme);
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
    selectpostBYmonth(){
      this.startmonth= this.value1[0]
      this.endmonth = this.value1[1]
      console.log(this.startmonth)
      console.log(this.endmonth)
      if (this.value1.length == '0') {
        this.$alert('请输入要查询的月份！', '提示', {
          confirmButtonText: '确定',
          type: 'waring',
        });
      } else {
        this.$http.post('http://localhost:8888/Post/selectsumpostmonth1/' + JSON.parse(localStorage.getItem("users")).id,
          {
            "startmonth": this.startmonth,
            "endmonth": this.endmonth,
          },
          {emulateJSON: true},
        ).then(function (res) {
          if (res.data.code === 200) {
            this.postnumber2 = res.data.data.arry;
            this.monthnumber2 = res.data.data.arrx;
            this.interlocutionnumber2 = res.data.data.arrz;
            this.commentsnumber2 = res.data.data.arrm;
            console.log(this.postnumber2);
            console.log(this.monthnumber2);
            console.log(this.interlocutionnumber2);
            console.log(this.commentsnumber2);
            echarts.registerTheme('theme', theme)
            var myChart = echarts.init(document.getElementById('myChart'),theme);
            myChart.setOption({
              legend: {
                orient: 'vertical',
                x:'right',
                y:'top'
              },
              title: {
                text: '数据折线图',
                left: 'center',
                top: "top"
              },
              xAxis: {
                name: "月份",
                data: this.monthnumber2,
              },
              yAxis: {
                splitNumber : 0,
                name: "数量",
              },
              series: [
                {
                  data: this.postnumber2,
                  type: 'line',
                  name: '发帖数',
                  areaStyle: {
                    opacity: 0,
                    color: 'rgb(232,22,163)'
                  }
                },
                {
                  data: this.interlocutionnumber2,
                  type: 'line',
                  name: '提问数',
                  areaStyle: {
                    opacity: 0,
                    color: 'rgb(13,248,142)'
                  }
                },
                {
                  data: this.commentsnumber2,
                  type: 'line',
                  name: '评论数',
                  areaStyle: {
                    opacity: 0,
                    color: 'rgb(241,186,10)'
                  }
                },
              ]
            });
          } else {
          }
        })
      }
    },
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
    selectSumCommentsBYTime() {
      this.$http.post('http://localhost:8888/Comments/selectSumCommentsBYTime',
        {"user_id": JSON.parse(localStorage.getItem("users")).id,
          "startTime":this.pickdata.startTime,
          "endTime": this.pickdata.endTime,
        },
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code ===200) {
          this.Commentsnumber = res.data.data;
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
        this.$alert('请输入要查询的日期！', '提示', {
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
        this.selectSumCommentsBYTime();
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
