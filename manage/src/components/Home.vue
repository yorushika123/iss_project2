<template>
<div>
  <Header></Header>
  <Aside></Aside>
  页面
  <div class="content-box" :class="{'content-collapse':collapse}">
  <router-view></router-view>
  </div>
</div>
</template>

<script>
import bus from '../assets/js/bus'
import Header from './Header'
import Aside from './Aside'
export default {
  name: 'Home',
  components: {Aside, Header},
  data () {
    return {
      collapse: false
    }
  },
  created () {
    bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style scoped>

</style>
