import Vue from 'vue'
import Router from 'vue-router'
import Login from '../pages/Login'
import Home from '../components/Home'
import InfoPage from '../pages/InfoPage'
import ConsumerPage from '../pages/ConsumerPage'
import Comments from '../pages/Comments'
import Post from '../pages/Post'
import User from '../pages/User'
import Register from '../pages/Register'
import UserCenter from "../pages/UserCenter";
import WriteMsg from "../pages/WriteMsg";
import PostManager from "../pages/PostManager";
import CommentManager from "../pages/CommentManager";
import Data from "../pages/Data";
import MyQuestions from "../pages/MyQuestions";
import PostDetail from "../pages/postDetail"
import MyMessage from "../pages/MyMessage";
import Report from "../pages/Report";
import CommentList from "../pages/CommentList";
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/PostDetail',
      name: 'PostDetail',
      component: PostDetail,
      children:[{
        path: '/CommentList',
        name: 'CommentList',
        component: CommentList
      },

      ]
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
    {
      path: '/Home',
      name: Home,
      meta: {
        requireAuth: true
      },
      component: Home,
      children: [
        {
          path: '/Report',
          name: 'Report',
          component: Report
        },
        {
          path: '/InfoPage',
          name: 'InfoPage',
          component: InfoPage
        },
        {
          path: '/ConsumerPage',
          name: 'ConsumerPage',
          component: ConsumerPage
        },
        {
          path: '/post',
          name: 'Post',
          component: Post
        },
        {
          path: '/Comments',
          name: 'Comments',
          component: Comments
        },
        {
          path: '/User',
          name: 'User',
          component: User
        }

      ]
    },
    {
      path: '/UserCenter',
      name: 'UserCenter',
      component: UserCenter,
      redirect: '/WriteMsg',
      children: [
        {
          path: '/MyMessage',
          name: 'MyMessage',
          component: MyMessage
        },
        {
          path: '/WriteMsg',
          name: 'WriteMsg',
          component: WriteMsg
        },
        {
          path: '/PostManager',
          name: 'PostManager',
          component: PostManager
        },
        {
          path: '/CommentManager',
          name: 'CommentManager',
          component: CommentManager
        },
        {
          path: '/CommentManager',
          name: 'CommentManager',
          component: CommentManager
        },
        {
          path: '/Data',
          name: 'Data',
          component: Data
        },
        {
          path: '/MyQuestions',
          name: 'MyQuestions',
          component: MyQuestions
        }
      ]
    }

  ]
})
