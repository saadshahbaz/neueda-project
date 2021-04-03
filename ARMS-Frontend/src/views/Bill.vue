<template>
  <div>
    <div class="services">
      Bills
    </div>

    <div>
      <p>
        <span v-if="errorBill" style="color:#960f0f">{{errorBill}}</span>
      </p>
    </div>

    <div>
      <table class="table">
        <tr >
          <td><h5>ID</h5></td>
          <td><h5>amount</h5></td>
          <td><h5>status</h5></td>
          <td><h5>action</h5></td>
        </tr>
        <tr v-for="bill in bills" :key="bill.billNo">
          <td>{{ bill.billNo }}</td>
          <td>{{bill.amount  }}</td>
          <td>{{bill.paid }}</td>
          <td>
            <button @click="payBill(bill.billNo)">Pay</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Cookies from 'js-cookie'
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
  name: 'bills',
  data () {
    return {
      bills: [],
      errorBill: '',
      response: [],
    }
  },
  created() {
    this.getAllBills();
  },
  methods: {
    getAllBills: function (){
      // Initializing people from backend
      AXIOS.get(`/getBillsByCustomer?username=${Cookies.get("userName")}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.bills = response.data
          this.errorBill =''
        })
        .catch(e => {
          this.errorBill = e.response.data.message;
        });
    },
    payBill: function (billNo){
      // Initializing people from backend
      AXIOS.put(`/payBill?BillNo=${billNo}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.bills= []
          this.getAllBills()
          this.errorBill =''
        })
        .catch(e => {
          this.errorBill = e.response.data.message;
        });
    },


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
  top: 40px;
  right: 20px;
}
</style>
