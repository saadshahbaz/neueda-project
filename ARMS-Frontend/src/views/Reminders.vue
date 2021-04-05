<template>
  <div class="reminders">
    <div class="text">
      Send Reminders
    </div>

    <div>
      <table class="table">
        <tr>
          <td><h5>username</h5></td>
          <td><h5>password</h5></td>
          <td><h5>email</h5></td>
          <td><h5>phoneNumber</h5></td>
        </tr>
        <tr v-for="customer in customers" :key="customer.username">
          <td>{{ customer.username }}</td>
          <td>{{ customer.password }}</td>
          <td>{{ customer.email }}</td>
          <td>{{ customer.phoneNumber }}</td>
        </tr>
      </table>
    </div>

    <form class="contact-form" @submit.prevent="sendEmail">
      <div class="contact-form">
      <label>Name</label>
      <input type="text" name="name"/>
      </div>
      <div class="contact-form">
      <label >Email</label>
      <input type="email" name="email"/>
      </div>
      <div class="contact-form">
      <label>Subject</label>
      <input type="subject" name="subject"/>
      </div>
      <div class="contact-form">
      <label>Message</label>
      <textarea name="message"></textarea>
      </div>
      <div class="contact-form">
      <input type="submit" value="Send">
      </div>
    </form>
  </div>
</template>

<script>
import emailjs from 'emailjs-com';
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
  name: 'reminders',
  data() {
    return {
      customers: [],
    }
  },
  created() {
    this.getAllCustomers();
  },
  methods: {
    sendEmail: (e) => {
      emailjs.sendForm('service_h4u5l6f', 'template_yvy4y2l', e.target, 'user_l81nXQipEe0in44WFrlhp')
        .then((result) => {
          console.log('SUCCESS!', result.status, result.text);
        }, (error) => {
          console.log('FAILED...', error);
        });
    },
    getAllCustomers: function () {
      // Initializing people from backend
      AXIOS.get(`/allCustomers`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.customers = response.data
        })
    },
  },

}
</script>

<style scoped>
.text{
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
.contact-form{
  display: block;
  margin:auto;
  text-align: center;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  width: 50%;
}
.container {
  display: block;
  margin:auto;
  text-align: center;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  width: 50%;
}
</style>
