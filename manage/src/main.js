import Vue from 'vue'
import App from './App'
import router from './router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/main.css'
import 'babel-polyfill'
import VCharts from 'v-charts'
import VueResource from 'vue-resource'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import store  from './store'
import * as echarts from 'echarts';

Vue.use(echarts);
Vue.use(mavonEditor)
Vue.use(ElementUI)
Vue.use(VCharts)
Vue.use(VueResource)
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.getters.getA !== '') {
        next()
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
