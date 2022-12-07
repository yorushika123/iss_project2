<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu"
             :default-active="onRoutes"
             :collapse="collapse"
             background-color="#334256"
             text-color="#ffffff"
             active-text-color="#20a0ff"
             router
    >
      <template v-for="item in items">
        <template>
          <el-menu-item :index="item.index" :key="item.index" style="margin-bottom: 20px">
            <i :class="item.icon"></i>
            {{item.title}}
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from '../assets/js/bus'
export default {
  name: 'Aside',
  data () {
    return {
      collapse: false,
      items: [
        {
          icon: 'el-icon-document',
          index: 'InfoPage',
          title: '系统首页'
        },
        {
          icon: 'el-icon-document',
          index: 'ConsumerPage',
          title: '问题管理'
        },
        {
          icon: 'el-icon-document',
          index: 'Post',
          title: '帖子管理'
        },
        {
          icon: 'el-icon-document',
          index: 'Comments',
          title: '评论管理'
        },
        {
          icon: 'el-icon-document',
          index: 'User',
          title: '用户管理'
        },
        {
          icon: 'el-icon-document',
          index: 'Report',
          title: '举报信息'
        }
      ]
    }
  },
  computed: {
    onRoutes () {
      return this.$route.path.replace('/', '')
    }
  },
  created () {
    /*通过bus进行组件通信，来折叠侧边栏*/
    bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style scoped>
.sidebar{
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  background-color: #334256;
  overflow-y: scroll;
}
.sidebar-el-menu:not(.el-menu--collapse){
  width: 150px;
}
.sidebar::-webkit-scrollbar{
  width: 0;
}
.sidebar>ul{
  height: 100%;
}
</style>
