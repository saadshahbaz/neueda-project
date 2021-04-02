<template>
  <div>
    <div class="Business">
      Business
    </div>

    <p>
      <span v-if="errorService" style="color:#960f0f">{{errorService}}</span>
    </p>
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
        <td>{{ business.phoneNum }}</td>
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
        <td>
          <button v-bind:disabled="!newBusinessName" @click="createBusiness(newBusinessName, newBusinessAddress, newBusinessPhoneNum, newBusinessEmail)">Create</button>
          <button v-bind:disabled="!newBusinessName" @click="deleteBusiness(newBusinessName)">Delete</button>
        </td>
      </tr>
    </table>

  </div>
  <div class="box">
    <section>
      <p>date range</p>
      <date-picker v-model="value1" type="date" range placeholder="Select date range"></date-picker>
    </section>
    <section>
      <p>time range</p>
      <date-picker
        v-model="value2"
        type="time"
        range
        placeholder="Select time range"
      ></date-picker>
    </section>
    <td>
      <button v-bind:disabled="!newBusinessName" @click="createBusinessHour(value1[0], value1[1], value2[0], value2[1], newBusinessName)">Create</button>
    </td>
  </div>
</template>

<script>
import axios from "axios";
import Router from "../router";
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
  name: 'services',
  data () {
    return {
      businesses: [],
      businessHours: [],
      newBusiness: '',
      newBusinessHour: '',
      errorBusiness: '',
      response: [],
      newBusinessName: '',
      newBusinessAddress: '',
      newBusinessPhoneNum: '',
      newBusinessEmail: '',
      value1: [],
      value2: [],
    }
  },
  created() {
    this.getAllBusinesses();
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
      AXIOS.post(`/businessHour/${startDate}/${endDate}/${startTime}/${endTime}/${businessName}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.businessHours.push(response.data)
          this.newBusinessHour = response.data
          this.errorBusiness =''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorService = e;
        });
    },
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
