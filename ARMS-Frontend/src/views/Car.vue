<template>
  <div>
    <div class="cars">
      Cars
    </div>

    <div>
    <p>
      <span v-if="errorCar" style="color:#960f0f">{{errorCar}}</span>
    </p>
    </div>

    <div>
    <table class="table">
      <tr >
        <td>customer</td>
        <td>manufacturer</td>
        <td>model</td>
        <td>year</td>
        <td>plateNo</td>
      </tr>
      <tr v-for="car in cars" :key="car.plateNo">
        <td>{{ car.manufacturer }}</td>
        <td>{{ car.model }}</td>
        <td>{{ car.year }}</td>
        <td>{{ car.plateNo }}</td>
        <td>
          <ul>
            <li v-for="event in car.events">
              {{event.name}}
            </li>
          </ul>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newCarManufacturer" placeholder="Manufacturer">
        </td>
        <td>
          <input type="text" v-model="newCarModel" placeholder="Model">
        </td>
        <td>
          <input type="text" v-model="newCarYear" placeholder="Year">
        </td>
        <td>
          <input type="text" v-model="newCarPlateNo" placeholder="Plate Number">
        </td>
        <td>
          <button v-bind:disabled="!newCarPlateNo" @click="createCar(newCarManufacturer, newCarModel,newCarYear,newCarPlateNo)">Create</button>
          <button v-bind:disabled="!newCarPlateNo" @click="updateCar(newCarManufacturer, newCarModel,newCarYear,newCarPlateNo)">Update</button>
          <button v-bind:disabled="!newCarPlateNo" @click="deleteCar(newCarPlateNo)">Delete</button>
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
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

import Cookies from 'js-cookie'
export default {
  name: 'cars',
  data () {
    return {
      cars: [],
      newCar: '',
      errorCar: '',
      response: [],
      newCarManufacturer:'',
      newCarModel:'',
      newCarYear:'',
      newCarPlateNo:''
    }
  },
  created() {
    this.getCarsByCustomer();
  },
  methods: {
    getCarsByCustomer: function (){
      // Initializing people from backend
      AXIOS.get(`/getCarsByCustomer?username=${Cookies.get("userName")}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.cars = response.data
          this.errorCar =''
        })
        .catch(e => {
          this.errorCar = e.response.data.message;
        });
    },
    deleteCar: function (plateNo){
      // Initializing people from backend
      AXIOS.delete(`/deleteCar?plateNo=${plateNo}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.cars = response.data
          this.errorCar =''
        })
        .catch(e => {
          this.errorCar = e.response.data.message;
        });
    },
    updateCar: function (manufacturer, model, year, plateNo){
      // Initializing people from backend
      AXIOS.delete(`/updateCar?customer=${Cookies.get("userName")}&manufacturer=${manufacturer}&model=${model}&year=${year}&plateNo=${plateNo}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.cars = response.data
          this.errorCar =''
        })
        .catch(e => {
          this.errorCar = e.response.data.message;
        });
    },
    createCar: function ( manufacturer, model, year, plateNo){
      // Initializing people from backend
      AXIOS.post(`/car?customer=${Cookies.get("userName")}&manufacturer=${manufacturer}&model=${model}&year=${year}&plateNo=${plateNo}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.cars.push(response.data)
          this.errorCar =''
        })
        .catch(e => {
          this.errorCar = e.response.data.message;
        });
    },
    //...
  },}

</script>

<style scoped>
.cars {
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
