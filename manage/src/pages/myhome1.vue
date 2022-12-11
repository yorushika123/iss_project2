<template>
<div>
  <el-row  class="tac" style="background-color: rgba(225,221,232,0.24)">
    <el-col :span="6" :offset="1" >
      <div style="width: 100%">
        <div><a>专家列表</a></div>

        <el-table
          :data="Posts"
          style="width: 100%">
          <el-table-column
            label="序号"
            type="index"
            :index="Nindex"
            width="180">
          </el-table-column>
          <el-table-column
            prop="post_title"
            label="标题"
            width="180">
          </el-table-column>
        </el-table>

      </div>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "myhome1"
}
</script>

<style scoped>

</style>
