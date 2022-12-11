<template>
<div>
  <div v-for="(item,index) in Allpost.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="index" style="margin-left: 10px">
          <div class="message" id="a2">
            <div><strong id="a3" style="font-size: 18px;">{{item.post_title}}</strong></div>
            <div style="display: flex;margin-top: 10px">
               <div><img :src=item.img class="img"></div>
               <div style="margin-left: 20px;position: relative;">
                 <div class="textOVerThree "><a style="cursor: pointer">{{item.articleContentMd}}</a></div>
                 <div style="bottom: 0;position: absolute">
                   <img  @click="handleClickUp(item.id)" v-if="isUp" src="../assets/img/dianzan.jpg" width="20px" height="20px" class="a1">
                   <img @click="handleClickUp(item.id)"  v-else src="../assets/img/2.png" width="20px" height="20px" class="a1">
                   <a>{{item.view_counts}}赞</a>
                   <span>

                     <img :src=item.avatar width="20px" height="20px" style="border-radius: 50%;margin-left: 50px;">
                      <a style="color: #6f42c1;cursor: pointer">{{item.username}}</a>
                   </span>

                 </div>
               </div>


            </div>
          </div>
  </div>

  <el-pagination align='center'
                 @size-change="handleSizeChange"
                 @current-change="handleCurrentChange"
                 :current-page="currentPage"
                 :page-sizes="[1,3,5,10,20]"
                 :page-size="pageSize"
                 layout="total, sizes, prev, pager, next, jumper"
                 :total="Allpost.length">
  </el-pagination>


  <el-row  class="tac" style="background-color: rgba(225,221,232,0.24)">
    <el-col :span="4" :offset="1" >
      <div style="width: 100%">
          <div><a>专家列表</a></div>
      </div>
    </el-col>
  </el-row>

</div>
</template>

<script>

export default {
  name: "myhome",
  data(){
    return{
      isUp: false,
      support:'',
      Allpost:'',
      post_id:'',
      currentPage:1,
      totalCount:0,
      pageSize:10,
    }
  },
  methods:{
    handleClickUp (iid) {
      this.post_id=iid;
      if(this.isUp===false)
      {
        this.$http.post('http://localhost:8888//postDetail/Good', {
          "up_counts":1,
          "id":this.post_id
        })
      }

      else
      {
        this.$http.post('http://localhost:8888//postDetail/Good', {
          "up_counts":-1,
          "id":this.post_id
        })
        this.support=this.support-1
      }

      this.isUp = !this.isUp
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    },
    findAllPost(){
      this.$http.post('http://localhost:8888/Post/selectPostAllMessage',
        {emulateJSON: true},
            ).then(function (res) {
              if (res.data.code == 200) {
                this.Allpost= res.data.data;
              } else {
              }
            })
          }
  },
  created() {
    this.findAllPost()
  }
}
</script>

<style scoped>
.message{
  width: 100%;
  height: 130px;
  border: 1px solid rgba(65, 105, 225, 0.2);
  margin-top: 10px;
  padding: 10px 0 10px 0;
}

.img{
  width: 150px;
  height: 100px;
}

.textOVerThree {
  display: -webkit-box;
  overflow: hidden;
  white-space: normal !important;
  text-overflow: ellipsis;
  word-wrap:break-word;
  -webkit-line-clamp:3;
  -webkit-box-orient: vertical;
  width: 75vw;
}

.a1:hover{
  color: #a0e020;
  cursor:pointer;
}

#a2:hover{
  background-color: rgba(51, 66, 86, 0.02);
}

#a3:hover{
  text-decoration:underline;
  cursor: pointer;
}

</style>
