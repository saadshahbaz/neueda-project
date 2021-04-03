<template>
  <div>
  <div>
    <div class="services">
      Business
    </div>

    <div>
      <p>
        <span class="errorMsg" v-if="errorBusiness" style="color:#960f0f">{{errorBusiness}}</span>
      </p>
    </div>
    <table class="table">
      <tr >
        <td><h5>name</h5></td>
        <td><h5>address</h5></td>
        <td><h5>phone number</h5></td>
        <td><h5>email</h5></td>
      </tr>
      <tr v-for="business in businesses" :key="business.name">
        <td>{{ business.name }}</td>
        <td>{{ business.address }}</td>
        <td>{{ business.phoneNumber }}</td>
        <td>{{ business.email }}</td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newBusinessName" placeholder="Business Name">
        </td>
        <td>
          <input type="text" v-model="newBusinessAddress" placeholder="Business Address">
        </td>
        <td>
          <input type="text" v-model="newBusinessPhoneNum" placeholder="Business Phone Number">
        </td>
        <td>
          <input type="text" v-model="newBusinessEmail" placeholder="Business Email">
        </td>
      </tr>
      <tr>


      </tr>
    </table>
    <div class="box">
      <button v-bind:disabled="!newBusinessName" @click="createBusiness(newBusinessName, newBusinessAddress, newBusinessPhoneNum, newBusinessEmail)">Create</button>
      <button v-bind:disabled="!newBusinessName" @click="deleteBusiness(newBusinessName)">Delete</button>

    </div>

  </div>
    <section>
    <div class="services">
      Business Hours
    </div>
    </section>
  <div class="box">
    <section>
      <p>  </p>
      <p>select date range</p>
      <date-picker v-model="value1" type="date" range placeholder="Select date range"></date-picker>
    </section>
    <section>
      <p>select time range</p>
      <date-picker
        v-model="value2"
        type="time"
        range
        placeholder="Select time range"
      ></date-picker>
    </section>
    <section>
      <p>Business name</p>
      <input type="text" v-model="newBusinessName1" placeholder="Business Name">
      <button v-bind:disabled="!newBusinessName1" @click="createBusinessHour(startDate, endDate, startTime, endTime, newBusinessName1)">Create</button>
    </section>
    <section>
      <p>Delete Business Hour</p>
      <input type="text" v-model="businessHourId" placeholder="Business Hour ID">

      <button v-bind:disabled="!businessHourId" @click="deleteBusinessHour(businessHourId)">Delete</button>

    </section>

  </div>
    <div>
      <p>
        <span class="errorMsg" v-if="errorBusinessHour" style="color:#960f0f">{{errorBusinessHour}}</span>
      </p>
    </div>
    <table class="table">
      <tr >
        <td><h5>start date</h5></td>
        <td><h5>end date</h5></td>
        <td><h5>start time</h5></td>
        <td><h5>end time</h5></td>
        <td><h5>business name</h5></td>
      </tr>
      <tr v-for="businessHour in businessHours" :key="businessHour.business.name">
        <td>{{ businessHour.startDate }}</td>
        <td>{{ businessHour.endDate }}</td>
        <td>{{ businessHour.startTime }}</td>
        <td>{{ businessHour.endTime }}</td>
        <td>{{ businessHour.business.name }}</td>
      </tr>

    </table>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";
import DatePicker from 'vue2-datepicker';
import formatter from 'dayjs';
import 'vue2-datepicker/index.css';
import customerSidebar from "../components/customerSidebar";
var config = require("../../config");
// Axios config
var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});
export default {
  name: 'business',
  components: {
    DatePicker
  },
  data () {
    return {
      businesses: [],
      businessHours: [],
      newBusiness: '',
      newBusinessHour: '',
      errorBusiness: '',
      errorBusinessHour: '',
      response: [],
      newBusinessName: '',
      newBusinessAddress: '',
      newBusinessPhoneNum: '',
      newBusinessEmail: '',
      newBusinessName1: '',
      value1: [],
      value2: [],
      startDate: '',
      endDate: '',
      startTime: '',
      endTime: '',
      businessHourId: ''
    }
  },
  created() {
    this.getAllBusinesses();
    this.getAllBusinessHours();
  },
  methods: {
    getAllBusinesses: function (){
      // Initializing people from backend
      AXIOS.get(`/businesses`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businesses = response.data
          this.errorBusiness =''
        })
        .catch(e => {
          this.errorBusiness = e.response.data.message;
        });
    },
    getAllBusinessHours: function (){
      // Initializing people from backend
      AXIOS.get(`/businessHour`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businessHours = response.data
          this.errorBusinessHour =''
        })
        .catch(e => {
          this.errorBusinessHour = e.response.data.message;
        });
    },
    deleteBusiness: function (name){
      // Initializing people from backend
      AXIOS.delete(`/deleteBusiness/` +name)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businesses= []
          this.getAllBusinesses()
          this.newBusiness = ''
          this.errorBusiness =''
        })
        .catch(e => {
          this.errorBusiness = e.response.data.message;
        });
    },
    createBusiness: function (name, address, phoneNumber, email){
      // Initializing people from backend

      AXIOS.post(`/business/${name}/${address}/${phoneNumber}/${email}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businesses.push(response.data)
          this.newBusiness = response.data
          this.errorBusiness =''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorService = e;
        });
    },
    createBusinessHour: function (startDate, endDate, startTime, endTime, businessName){
      // Initializing people from backend
      this.endDate = formatter(this.value1.pop()).format('YYYY-MM-DD');
      this.startDate = formatter(this.value1.pop()).format('YYYY-MM-DD');
      this.endTime = formatter(this.value2.pop()).format('HH:MM:ss');
      this.startTime = formatter(this.value2.pop()).format('HH:MM:ss');

      AXIOS.post(`/businessHour/${this.startDate}/${this.endDate}/${this.startTime}/${this.endTime}/${businessName}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businessHours.push(response.data)
          this.newBusinessHour = response.data
          this.errorBusinessHour =''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorBusinessHour = e;
        });
    },
    deleteBusinessHour: function (id){
      // Initializing people from backend
      AXIOS.delete(`/deleteBusinessHour/` +id)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businessHours= []
          this.getAllBusinessHours()

          this.errorBusinessHour =''
        })
        .catch(e => {
          this.errorBusinessHour = e.response.data.message;
        });
    }
    //...
  },}
</script>

<style scoped>
.services {
  margin-top: 28px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  font-size: 40px;
  color: rgb(167, 167, 167);
  font-weight: 600;
}
.table {
  justify-content: center;
  align-items: center;
}
.selected {
  position: absolute;
  right: 20px;
}
</style>
