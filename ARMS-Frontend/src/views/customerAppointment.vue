<template>
  <div class="appointments">
    Appointments

    <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择日期">
    </el-date-picker>

    <el-select v-model="space" placeholder="Space">
      <el-option
        v-for="item in spaceOptions"
        :key="item.id"
        :label="item.id"
        :value="item.id">
      </el-option>
    </el-select>

    <el-select v-model="technician" placeholder="Techinician">
      <el-option
        v-for="item in techOptions"
        :key="item.id"
        :label="item.id"
        :value="item.id">
      </el-option>
    </el-select>

    <el-select v-model="service" placeholder="Service">
      <el-option
        v-for="item in services"
        :key="item.id"
        :label="item.name"
        :value="item.name">
      </el-option>
    </el-select>

    <el-select v-model="car" placeholder="Car">
      <el-option
        v-for="item in cars"
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


      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      value1: '',
      value2: '',

      //curUserName = Cookies.get("userName"),

      space: '',
      spaceValue: '',
      spaceOptions: [],

      technician: '',
      techValue:'',
      techOptions: [],

      service:'',
      serviceValue:'',
      services: [],

      car:'',
      carValue:'',
      cars: []

    }
  },
  mounted: function () {
    axios.get('http://localhost:8080/allSpace').then(res => {
      console.log(res);
      //this.s = res.data;
      this.spaceOptions = res.data;
    })

    axios.get('http://localhost:8080/allSpace').then(res => {
      console.log(res);
      //this.s = res.data;
      this.techOptions = res.data;
    })

    axios.get('http://localhost:8080/services').then(res => {
      console.log(res);
      //this.s = res.data;
      this.services = res.data;
    })


    axios.get('http://localhost:8080/getCarsByCustomer/?username=$Cookies.get("userName")').then(res => {
      console.log(res);
      //this.s = res.data;
      this.cars = res.data;
    })
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
