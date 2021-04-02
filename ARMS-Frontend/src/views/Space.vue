<template>
  <div>
    <div class="services">
      Space
    </div>

    <p>
      <span v-if="errorSpace" style="color:#960f0f">{{errorSpace}}</span>
    </p>
    <table class="table">
      <tr >
        <td><h5>space id</h5></td>
      </tr>
      <tr v-for="space in spaces" :key="space.id">
        <td>{{ space.id }}</td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newSpaceId" placeholder="Space ID">
        </td>

        <td>
          <button v-bind:disabled="!newSpaceId" @click="createSpace(newSpaceId)">Create</button>
          <button v-bind:disabled="!newSpaceId" @click="deleteSpace(newSpaceId)">Delete</button>
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
  name: 'spaces',
  data () {
    return {
      spaces: [],
      newSpace: '',
      errorSpace: '',
      response: [],
      newSpaceId: '',

    }
  },
  created() {
    this.getAllSpaces();
  },
  methods: {
    getAllSpaces: function (){
      // Initializing people from backend
      AXIOS.get(`/allSpace`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.spaces = response.data
          this.errorSpace =''
        })
        .catch(e => {
          this.errorSpace = e.response.data.message;
        });
    },

    deleteSpace: function (id){
      // Initializing people from backend
      AXIOS.delete(`/deleteSpacee?id=${id}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.spaces= []
          this.getAllSpaces()
          this.newSpace = ''
          this.errorSpace =''
        })
        .catch(e => {
          this.errorSpace = e.response.data.message;
        });
    },

    createSpace: function (id){
      // Initializing people from backend
      AXIOS.post(`/space?id=${id}`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.spaces.push(response.data)
          this.newSpace = response.data
          this.errorSpace =''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSpace = e;
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
