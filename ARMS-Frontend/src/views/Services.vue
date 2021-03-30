<template>
  <div>
  <div class="services">
    Services
  </div>
    <p>
      <span style="color:#06411c">Error: Message text comes here</span>
    </p>
    <table class="table">
      <tr v-for="service in services" >
        <td>{{ service.name }}</td>
        <td>{{ service.duration }}</td>
        <td>{{ service.price }}</td>
        <td>
          <ul>
            <li v-for="event in service.events">
              {{event.name}}
            </li>
          </ul>
        </td>
      </tr>
      <tr >
        <td>name</td>
        <td>duration</td>
        <td>price</td>
        <td>action</td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newService" placeholder="Service Name">
        </td>
        <td>
          <input type="text" placeholder="Duration">
        </td>
        <td>
          <input type="text" placeholder="Price">
        </td>
        <td>
          <button v-bind:disabled="!newService" @click="createService(newService.name, newService.duration, newService.price)">Create</button>
          <button>Update</button>
          <button>Delete</button>
        </td>
      </tr>
    </table>

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
      services: [],
      newService: '',
      errorService: '',
      response: []
    }
  },
  created() {
    this.getAllServices();
  },
  methods: {
    getAllServices: function (){
      // Initializing people from backend
      AXIOS.get(`/services`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService =''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    deleteService: function (name){
      // Initializing people from backend
      AXIOS.delete(`/deleteService?name=${name}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService =''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    updateService: function (name, duration, price){
      // Initializing people from backend
      AXIOS.delete(`/updateService?name=${name}&duration=${duration}&price=${price}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService =''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    createService: function (name, duration, price){
      // Initializing people from backend
      AXIOS.delete(`/service?name=${name}&duration=${duration}&price=${price}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.newService = response.data
          this.errorService =''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
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
</style>
