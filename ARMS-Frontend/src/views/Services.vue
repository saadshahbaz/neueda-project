<template>
  <div>
    <div class="services">
      Services
    </div>
    <div>
      <select class="selected" v-model="selected" @change="sortServices(selected)">
        <option value="0">default</option>
        <option value="1">by price (lowest to highest)</option>
        <option value="2">by price (highest to lowest)</option>
      </select>
    </div>

    <div>
      <p>
        <span class="errorMsg" v-if="errorService" style="color:#960f0f">{{ errorService }}</span>
      </p>
    </div>

    <div>
      <table class="table">
        <tr>
          <td><h5>name</h5></td>
          <td><h5>duration</h5></td>
          <td><h5>price</h5></td>
          <td><h5>action</h5></td>
        </tr>
        <tr v-for="service in services" :key="service.name">
          <td>{{ service.name }}</td>
          <td>{{ service.duration }}</td>
          <td>{{ service.price }}</td>
          <td>
            <ul>
              <li v-for="event in service.events">
                {{ event.name }}
              </li>
            </ul>
          </td>
        </tr>
        <tr>
          <td>
            <input type="text" v-model="newServiceName" placeholder="Service Name">
          </td>
          <td>
            <input type="text" v-model="newServiceDuration" placeholder="Duration">
          </td>
          <td>
            <input type="text" v-model="newServicePrice" placeholder="Price">
          </td>
          <td>
            <button v-bind:disabled="!newServiceName"
                    @click="createService(newServiceName, newServiceDuration, newServicePrice)">Create
            </button>
            <button v-bind:disabled="!newServiceName"
                    @click="updateService(newServiceName, newServiceDuration, newServicePrice)">Update
            </button>
            <button v-bind:disabled="!newServiceName" @click="deleteService(newServiceName)">Delete</button>
          </td>
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
  headers: {"Access-Control-Allow-Origin": frontendUrl}
});
export default {
  name: 'services',
  data() {
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
    /*
    get all the services and display error message if exception occurs
    */
    getAllServices: function () {
      // Initializing people from backend
      AXIOS.get(`/services`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService = ''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    /*
    sort the services from highest price to lowest price and
    display error message if exception occurs
    */
    sortServicesHtoL: function () {
      // Initializing people from backend
      AXIOS.get(`servicesHtoL`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService = ''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    /* sort the services from lowest price to highest price and
    display error message if exception occurs
    */
    sortServicesLtoH: function () {
      // Initializing people from backend
      AXIOS.get(`servicesLtoH`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = response.data
          this.errorService = ''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    /*
    delete a service and display error message if exception occurs
     */
    deleteService: function (name) {
      // Initializing people from backend
      AXIOS.delete(`/deleteService?name=${name}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = []
          this.getAllServices()
          this.newService = ''
          this.errorService = ''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    /*
    update a service and display error message if exception occurs
    */
    updateService: function (name, duration, price) {
      // Initializing people from backend
      AXIOS.put(`/updateService?name=${name}&duration=${duration}&price=${price}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services = []
          this.getAllServices()
          this.newService = ''
          this.errorService = ''
        })
        .catch(e => {
          this.errorService = e.response.data.message;
        });
    },
    /*
    create a service and display error message if exception occurs
    */
    createService: function (name, duration, price) {
      // Initializing people from backend
      AXIOS.post(`/service?name=${name}&duration=${duration}&price=${price}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.services.push(response.data)
          this.newService = response.data
          this.errorService = ''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorService = errorMsg;
        });
    },
    /*
    sort services in default order and display error message if exception occurs
    */
    sortServices: function (value) {
      if (value == 1) {
        this.sortServicesLtoH()
      } else if (value == 2) {
        this.sortServicesHtoL()
      } else if (value == 0) {
        this.getAllServices()
      }
    }
  },
}
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
  top: 40px;
  right: 20px;
}

.errorMsg {
  top: 50px;
}
</style>
