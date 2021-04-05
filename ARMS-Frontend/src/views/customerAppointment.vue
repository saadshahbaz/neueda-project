<template>
  <div class="appointments">
    <div class="title">
      Appointments
    </div>

    <div>
      <p>
        <span class="errorMsg" v-if="errorAppointment" style="color:#960f0f">{{ errorAppointment }}</span>
      </p>
    </div>

    <div>
      <table class="table">
        <tr class="table-title">
          <td><h5>ID</h5></td>
          <td><h5>service</h5></td>
          <td><h5>plate No</h5></td>
          <td><h5>start date</h5></td>
          <td><h5>start time</h5></td>
          <td><h5>space</h5></td>
          <td><h5>technician</h5></td>
          <td><h5>action</h5></td>
        </tr>
        <tr class="table-content" v-for="apt in appointments" :key="apt.appointmentID">
          <td>{{ apt.appointmentID }}</td>
          <td>{{ apt.serviceName }}</td>
          <td>{{ apt.plateNo }}</td>
          <td>{{ apt.startDate }}</td>
          <td>{{ apt.startTime }}</td>
          <td>{{ apt.spaceID }}</td>
          <td>{{ apt.technicianID }}</td>
          <td>
            <button @click="deleteAppointment(apt.appointmentID)">DELETE</button>
          </td>
        </tr>
      </table>
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

      <el-time-picker
        v-model="newAppointmentStartTime"
        :picker-options="{
      selectableRange: '09:00:00 - 23:30:00'
    }"
        placeholder="任意时间点">
      </el-time-picker>
      <el-time-picker
        arrow-control
        v-model="newAppointmentEndTime"
        :picker-options="{
      selectableRange: '09:30:00 - 23:30:00'
    }"
        placeholder="任意时间点">
      </el-time-picker>

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
      <button  @click="updateAppointment()">Update</button>
      <button  @click="deleteAppointment()">Delete</button>
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
          label="Space"
          width="150">
        </el-table-column>

        <el-table-column
          prop="workingTechID"
          label="Technician"
          width="120">
        </el-table-column>

        <el-table-column
          prop="workingStartTime"
          label="StartTime"
          width="120">
        </el-table-column>

        <el-table-column
          prop="workingEndTime"
          label="endTime"
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

var config = require("../../config");
// Axios config
var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: {"Access-Control-Allow-Origin": frontendUrl}
});
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
      cars: [],

      appointments:[],
      newAppointment:'',
      errorAppointment:'',
      response:[],
      newAppointmentID:1,
      newAppointmentServiceName:'',
      newAppointmentPlateNo:'',
      newAppointmentBusinessName:'',
      newAppointmentStartDate:'',
      newAppointmentStartTime:'',
      newAppointmentEndDate:'',
      newAppointmentEndTime:'',
      businessName:'ARMS'
    }
  },
  created() {
    this.getAllAppointments();
  },
  mounted: function () {
    AXIOS.get(`/allSpace`).then(res => {
      console.log(res);
      //this.s = res.data;
      this.spaceOptions = res.data;
    })

    AXIOS.get(`/technicians`).then(res => {
      console.log(res);
      //this.s = res.data;
      this.techOptions = res.data;
    })

    AXIOS.get(`/services`).then(res => {
      console.log(res);
      //this.s = res.data;
      this.services = res.data;
    })


    AXIOS.get(`/getCarsByCustomer/?username=${Cookies.get("userName")}`).then(res => {
      console.log(res);
      //this.s = res.data;
      this.cars = res.data;
    })
  },
  methods: {
    // incrementAppointmentID(appointmentID){
    //
    // },
    deleteAppointment(appointmentID){
      // Initializing people from backend
      if(confirm("Do you really want to delete?")) {
        AXIOS.delete(`/deleteAppointment?appointmentID=${appointmentID}`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.appointments = []
            this.getAppointments()
            this.newAppointment = ''
            this.errorAppointment = ''
          })
          .catch(e => {
            this.errorAppointment = e.response.data.message;
          });
      }
    },
    updateAppointment(){
      // Initializing people from backend
      AXIOS.put(`updateAppointment?appointmentID=${this.newAppointmentID}&serviceName=${this.service}&plateNo=${this.car}&businessName=${this.businessName}&startDate=${dayjs(this.startDate).format('YYYY-MM-DD')}&startTime=${dayjs(this.newAppointmentStartTime).format('HH:MM:ss')}&endDate=${dayjs(this.startDate).format('YYYY-MM-DD')}&endTime=${dayjs(this.newAppointmentEndTime).format('HH:MM:ss')}&technicianID=${this.technician}&spaceID=${this.space}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.appointments = []
          this.getAppointments()
          this.newAppointment = ''
          this.errorAppointment = ''
        })
        .catch(e => {
          this.errorAppointment = e.response.data.message;
        });
    },
    createAppointment() {
      this.newAppointmentID = Math.floor(Math.random()*1000000)+1;
      console.log("ID is : "+this.newAppointmentID);
      AXIOS.post(`appointment?appointmentID=${this.newAppointmentID}&serviceName=${this.service}&plateNo=${this.car}&businessName=${this.businessName}&startDate=${dayjs(this.startDate).format('YYYY-MM-DD')}&startTime=${dayjs(this.newAppointmentStartTime).format('HH:MM:ss')}&endDate=${dayjs(this.startDate).format('YYYY-MM-DD')}&endTime=${dayjs(this.newAppointmentEndTime).format('HH:MM:ss')}&technicianID=${this.technician}&spaceID=${this.space}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.appointments.push(response.data)
          this.newAppointment = response.data
          this.errorAppointment =''
        })
        .catch(e => {
          this.errorAppointment = e.response.data.message;
        });
      if (this.errorAppointment == ''){
        this.newAppointmentID++
        console.log(this.newAppointmentID)
      }
    },
    getAllAppointments() {
      AXIOS.get(`/getAppointments`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.appointments = response.data
          this.errorAppointment =''
        })
        .catch(e => {
          this.errorAppointment = e.response.data.message;
        });
    },
    // checkAppointments(){
    //   this.tableData = [];
    //   console.log("here i come in");
    //   AXIOS.get(`/findTimeSlotsBySpaceID/${this.space}`)
    //     .then(response => {
    //       this.tableData = response.filter(function (item) {
    //         return dayjs(item.startDate).isSame(dayis(this.startDate));
    //       }).sort(function (item1, item2) {
    //         if (dayjs(item1.startTime).isBefore(dayjs(item2.startTime))) {
    //           return -1;
    //         } else {
    //           return 1;
    //         }
    //       }).map(function (item) {
    //         return {
    //           workingSpaceID: item.spaceID,
    //           workingTechID: item.techinicianID,
    //           workingStartTime: item.startTime,
    //           workingEndTime: item.endTime
    //         }
    //       })
    //     })
    // }
      // ,
      async checkAppointments(){
        this.tableData = [];
        console.log("qqqqq");

        //bySpace

        let res = await AXIOS.get(`/findTimeSlotsBySpaceID/${this.space}`);
        console.log(res);
        //byTech
        let res1 = await AXIOS.get(`/findTimeSlotsByTechnicianID/${this.technician}`);
        console.log(res1);


        let result = res.data.concat(res1.data);
        console.log(result);
        result = result.filter((item)=>{
          return dayjs(item.startDate).isSame(dayjs(this.startDate));
        })
        result = result.sort(function(item1,item2) {
          if(dayjs(item1.startTime).isBefore(dayjs(item2.startTime))) {
            return -1;
          }
          else{return 1;}
        })
        result = result.map(function(item){
          return {
            workingSpaceID: item.space.id,
            workingTechID: item.technician.id,
            workingStartTime: item.startTime,
            workingEndTime: item.endTime
          }
        })
        console.log(result);
        this.tableData = result;

      }

  }

}
</script>

<style scoped>

.title{
  display: flex;
  justify-content: center;
  width: 100%;
  text-align: center;
  margin-top: 10px;
  font-size: 25px;
}
.selections{
  margin-top: 30px;
}
.button{
  font-size: 15px;
  margin-top: 30px;
  width: 100%;
  text-align: center;
}
.table{

  justify-content: center;
  align-items: center;
}
.errorMsg {
  top: 50px;
  font-size: 20px;
  text-align: center;
  justify-content: center;
  left: 200px;
}
.table-title{
  font-size: 25px;
}
.table-content{
  justify-content: center;
  align-items: center;
  font-size: 15px;
}
</style>
