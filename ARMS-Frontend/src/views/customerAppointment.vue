<template>
  <div class="appointments">
    <div class="title">
      Appointments
    </div>

    <div class="selections">
      <el-date-picker
        v-model="startDate"
        type="date"
        placeholder="Select Date">
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
          :label="item.plateNo"
          :value="item.plateNo">
        </el-option>
      </el-select>
    </div>


    <div class="button">
      <button  @click="createAppointment()">Create</button>
      <button  @click="checkAppointments()">Check</button>
    </div>

    <div class="table" v-if="tableData.length!==0">

      <el-table
        :data="tableData"
        style="width: 100%"
        max-height="250">

        <el-table-column
          fixed
          prop="workingSpaceID"
          label="日期"
          width="150">
        </el-table-column>

        <el-table-column
          prop="workingTechID"
          label="姓名"
          width="120">
        </el-table-column>

        <el-table-column
          prop="workingStartTime"
          label="省份"
          width="120">
        </el-table-column>

        <el-table-column
          prop="workingEndTime"
          label="市区"
          width="120">
        </el-table-column>
      </el-table>
    </div>


  </div>

</template>

<script>
import axios from "axios";
import Cookies from 'js-cookie'
import dayjs from 'dayjs'

export default {
  data() {
    return {
      tableData: [],
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
      startDate: '',
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


    axios.get(`http://localhost:8080/getCarsByCustomer/?username=${Cookies.get("userName")}`).then(res => {
      console.log(res);
      //this.s = res.data;
      this.cars = res.data;
    })
  },
  methods: {
    createAppointment(appointmentID, serviceName, plateNO, businessName,
                      startDate,startTime,endDate,endTime,spaceID,technicianID) {
      console.log(dayjs(this.startDate).format('YYYY-MM-DD'));
      console.log(this.space);
      // AXIOS.post(`appointment?appointmentID=${name}&serviceName=${duration}&duration=${duration}&duration=${duration}&duration=${duration}&duration=${duration}&price=${price}`)
      //   .then(response => {
      //     // JSON responses are automatically parsed.
      //     this.businesses.push(response.data)
      //     this.newBusiness = response.data
      //     this.errorBusiness =''
      //   })
      //   .catch(e => {
      //     var errorMsg = e.response.data.message
      //     console.log(errorMsg)
      //     this.errorService = e;
      //   });
    },
    async checkAppointments(){
      this.tableData = [];
      console.log("qqqqq");

      //bySpace
      let res = await axios.get('/findTimeSLotsBySpace/{space}');
      //byTech
      let res1 = await axios.get('/findTimeSLotsByTechinician/{techinician}');


      let result = res.concat(res1);
      result = result.filter(function(item){
        return dayjs(item.startDate).isSame(dayis(this.startDate));
      })
      result = result.sort(function(item1,item2) {
        if(dayjs(item1.startTime).isBefore(dayjs(item2.startTime))) {
          return -1;
        }
        else{return 1;}
      })
      result = result.map(function(item){
        return {
          workingSpaceID: item.spaceID,
          workingTechID: item.techinicianID,
          workingStartTime: item.startTime,
          workingEndTime: item.endTime
        }
      })
      this.tableData = result;
    }

  }

}
</script>

<style scoped>
.appointments {
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  height: 100%;
  width: 100%;
  font-size: 40px;
  color: rgb(167, 167, 167);
  font-weight: 600;
}
.title{
  display: flex;
  justify-content: center;
  width: 100%;
  text-align: center;
  margin-top: 10px;
}
.selections{
  margin-top: 30px;
}
.button{
  margin-top: 30px;
  width: 100%;
  text-align: center;
}
.table{
  margin-top:50px;
  width: 100%;
  text-align: center;
}
</style>
