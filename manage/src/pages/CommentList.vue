<template>
  <div style="margin: 20px 0">
    <div style="margin: 10px 0;font-size: 24px;padding: 10px 0;border-bottom: 1px solid #cccccc">评论列表</div>
    <div style="margin: 20px 0 ;text-align: left;">
      <div style="padding: 10px 0;" v-for="item in comments" :key="item.id">
        <div style="display: flex">
          <div style="width: 50px;margin-right: 20px">
            <el-avatar :size="50"
                       :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"></el-avatar>
          </div>
          <div style="flex: 1">
            <div style="">{{ item.username }}<span style="margin-left:10px;font-size: 8px ">{{ item.time }}</span></div>
            <div style="margin-top: 10px">{{ item.content }}</div>
          </div>
        </div>
        <div style="text-align: right;padding: 5px">
          <el-button type="text" @click="item.replyFlag=!item.replyFlag">回复</el-button>
          <el-button type="text" @click="reply">举报</el-button>
          <div>
            <el-form :model="replyComment" v-if="item.replyFlag">
              <el-form-item prop="content">
                <el-input :rows="2" v-model="replyComment" type="textarea" placeholder="说说你的高见"></el-input>
              </el-form-item>
              <el-form-item style="text-align: right">
                <el-button type="primary" @click="replyComments(item.id)">回复</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
        <div v-if="item.reply" style="margin-left: 100px;background-color:aliceblue;padding: 10px;border-radius: 10px"><div v-for="sub in item.reply" :key="sub.id">
               <div style="padding: 5px 0; ">{{ sub.username }}<span>回复</span><span style="color: cornflowerblue">@{{item.username}} </span>
                 <span><span style="color: #666;margin-left: 10px">{{sub.content}}</span></span></div>
             </div></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CommentList',
  data() {
    return {

      replyComment: '',
      comments: [
        {
          id: '1',
          user_id: '1',
          time: '2022-12-4 12:13:56',
          username: '张三',
          content: '123',
          avatar: require('../assets/img/ban.png'),
          replyFlag: false,
          reply: [
            {
              username:'李四',
              contents:'测试回复',
            }
          ],
        },
        {
          id: '2',
          user_id: '2',
          username: '张三',
          content: '456',
          replyFlag: false,
        }, {
          id: '2',
          user_id: '3',
          username: '张三',
          content: '789',
          replyFlag: false,
        },
      ],
    }
  },
  methods: {
    replyComments(comments_id) {
      this.$http.post('http://localhost:8888//postDetail/replyComments', {
        comments_id: comments_id,
        contents: this.replyComment,
      }).then(res => {
        if (res.data.code == 200) {
          console.log('yes')
        } else {
          console.log('no')
        }
      })
    }
  }
}
</script>
<style>
.image {
  position: relative;
  width: 48px;
  height: 48px;
  flex-shrink: 0;
}
</style>
