<template>
  <div class="profile">
    Profile
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item >
        {{showUserName}}}
      </el-breadcrumb-item>
    </el-breadcrumb>>
  </div>
</template>

<script>

import Cookies from 'js-cookie'
export default {
  name: "Profile",
  computed:{
    showUserName(){
      return Cookies.get("userName")
    }
  }
}
</script>

<style scoped>

</style>
