<template>
  <div>
    <div class="services">
      Technician
    </div>

    <p>
      <span v-if="errorTechnician" style="color:#960f0f">{{errorTechnician}}</span>
    </p >
    <table class="table">
      <tr >
        <td><h5>id</h5></td>
        <td><h5>name</h5></td>
        <td><h5>email</h5></td>
      </tr>
      <tr v-for="technician in Technicians" :key="technician.id">
        <td>{{ technician.id}}</td>
        <td>{{ technician.name }}</td>
        <td>{{ technician.email }}</td>

      </tr>

      <tr>


      </tr>
    </table>
    <div class="box">
      <p>New Technician Information</p >


      <input type="text" v-model="newTechnicianId" placeholder="Technician ID">

      <input type="text" v-model="newTechnicianName" placeholder="Technician Name">

      <input type="text" v-model="newTechnicianEmail" placeholder="Technician Email">

    </div>

    <div class="box">
      <p>  </p >
      <button v-bind:disabled="!newTechnicianId" @click="createTechnician(newTechnicianId,newTechnicianName,newTechnicianEmail)">Create</button>
      <button v-bind:disabled="!newTechnicianId" @click="updateTechnician(newTechnicianId, newTechnicianName, newTechnicianEmail)">Update</button>
      <button v-bind:disabled="!newTechnicianId" @click="deleteTechnician(newTechnicianId)">Delete</button>
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
  name: 'technicians',
  data () {
    return {
      Technicians: [],
      newTechnician: '',
      errorTechnician: '',
      response: [],
      newTechnicianId: '',
      newTechnicianName: '',
      newTechnicianEmail: '',
    }
  },
  created() {
    this.getAllTechnicians();
  },
  methods: {
    getAllTechnicians: function (){
      // Initializing people from backend
      AXIOS.get(`/technicians`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.Technicians = response.data
          this.errorTechnician =''
        })
        .catch(e => {
          this.errorTechnician = e.response.data.message;
        });
    },
    deleteTechnician: function (id){
      // Initializing people from backend
      AXIOS.delete(`/deleteTechnician?id=${id}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.Technicians= []
          this.getAllTechnicians()
          this.newTechnician = ''
          this.errorTechnician =''
        })
        .catch(e => {
          this.errorTechnician = e.response.data.message;
        });
    },
    updateTechnician: function (id, name, email){
      // Initializing people from backend
      AXIOS.put(`/updateTechnician?id=${id}&name=${name}&email=${email}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.Technicians= []
          this.getAllTechnicians()
          this.newTechnician = ''
          this.errorTechnician =''
        })
        .catch(e => {
          this.errorTechnician = e.response.data.message;
        });
    },
    createTechnician: function (id,name,email){
      // Initializing people from backend
      AXIOS.post(`/technician?id=${id}&name=${name}&email=${email}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.Technicians.push(response.data)
          this.newTechnician = response.data
          this.errorTechnician =''
        })
        .catch(e => {
          this.errorTechnician = e.response.data.message;
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
