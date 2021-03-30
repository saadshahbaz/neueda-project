<template>
  <div class="appointments">
    Appointments
    <el-select v-model="space" placeholder="Space">
      <el-option
        v-for="item in options"
        :key="item.id"
        :label="item.id"
        :value="item.id">
      </el-option>
    </el-select>
  </div>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      space:0,
      options: [],
      value: ''
    }
  },
  mounted: function () {
    axios.get('http://localhost:8080/allSpace').then(res => {
      console.log(res);
      //this.s = res.data;
      //t 数组触发响应
    });

  }
}
</script>

<style scoped>
.appointments {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  font-size: 40px;
  color: rgb(167, 167, 167);
  font-weight: 600;
}
</style>
