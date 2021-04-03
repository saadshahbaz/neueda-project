<template>
  <div>
    <div class="services">
      Available services
    </div>
    <div>
      <select class="selected" v-model="selected" @change="sortServices(selected)">
        <option value="0">default</option>
        <option value="1">by price (lowest to highest)</option>
        <option value="2">by price (highest to lowest)</option>
      </select>
    </div>

    <div>
      <table class="table">
        <tr >
          <td><h5>name</h5></td>
          <td><h5>duration</h5></td>
          <td><h5>price</h5></td>
        </tr>
        <tr v-for="service in services" :key="service.name">
          <td>{{ service.name }}</td>
          <td>{{ service.duration }}</td>
          <td>{{ service.price }}</td>
        </tr>
      </table>
    </div>
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
      response: [],
      newServiceName: '',
      newServiceDuration: '',
      newServicePrice: '',
      selected: '0',
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
    sortServicesHtoL: function (){
      // Initializing people from backend
      AXIOS.get(`servicesHtoL`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService =''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    sortServicesLtoH: function (){
      // Initializing people from backend
      AXIOS.get(`servicesLtoH`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService =''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    sortServices: function (value) {
      if (value==1){
        this.sortServicesLtoH()
      }else if (value==2){
        this.sortServicesHtoL()
      }else if (value==0){
        this.getAllServices()
      }
    }
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
  font-size: 30px;
  color: rgb(167, 167, 167);
  font-weight: 600;
}
.table {
  margin-top: 28px;
  justify-content: center;
  align-items: center;
}
.selected {
  margin-top: 10px;
  position: absolute;
  top: 40px;
  right: 20px;
}
</style>
