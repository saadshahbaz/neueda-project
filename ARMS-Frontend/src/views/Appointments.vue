<template>
  <div>
  <div class="allAppointments">
    Appointments
  </div>
  <div>
    <table class="table">
      <tr >
        <td><h5>service</h5></td>
        <td><h5>plate</h5></td>
        <td><h5>start date</h5></td>
        <td><h5>start time</h5></td>
        <td><h5>end date</h5></td>
        <td><h5>end time</h5></td>
        <td><h5>technician</h5></td>
        <td><h5>space</h5></td>
      </tr>
      <tr v-for="appointment in appointments" :key="appointment.plateNo">
        <td>{{ appointment.serviceName }}</td>
        <td>{{ appointment.plateNo }}</td>
        <td>{{ appointment.startDate }}</td>
        <td>{{ appointment.startTime }}</td>
        <td>{{ appointment.endDate }}</td>
        <td>{{ appointment.endTime }}</td>
        <td>{{ appointment.technicianID }}</td>
        <td>{{ appointment.spaceID }}</td>
        <td>
          <ul>
            <li v-for="event in appointment.events">
              {{event.name}}
            </li>
          </ul>
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
export default {
  name: 'appointments',
  data(){
    return{
      appointments: [],
      errorAppointment: '',
      response: [],
    }
  },
  created() {
    this.getAppointments();
  },
  methods:{
    getAppointments: function (){
      // Initializing people from backend
      AXIOS.get(`/getAppointments`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.appointments = response.data
          this.errorAppointment =''
        })
        .catch(e => {
          this.errorAppointment = e.response.data.message;
        });
    }
  }
}
</script>

<style scoped>
.allAppointments {
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
  margin-top: 28px;
  justify-content: center;
  align-items: center;
}
</style>
