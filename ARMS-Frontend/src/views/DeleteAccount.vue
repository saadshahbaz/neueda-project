<template>
  <div>
  <div class="row my-3 px-3"><input
    name="Delete"
    id="OldPassword"
    placeholder="Please input delete to confirm"
    v-model="deleteInput"/></div>
  <div class="button px-3 py-1 mt-1 mb-3" type="submit" @click="deleteAccount">Delete Account</div>
  </div>
</template>

<script>
import {AXIOS} from "../components/axiosInstance";
export default {
  name: "deleteAccount",
  data(){
    return{
      deleteInput : "",
    };
  },
  methods: {
  deleteAccount: function () {
    if (this.deleteInput!= "delete") {
      alert("Please enter delete to confrim");
    }  else {
      AXIOS.delete(`/deleteCustomer`)
        .then((response) => {
        this.deleteInput="";
        alert("Delete Successfully");
        window.location.href = "/"
      })
        .catch((e) => {
          alert("You have unpaid bills");
        });

    }

    }
  },
}
</script>

<style scoped>
input {
  margin-top: 300px;
  margin-left:100px;
  background: transparent;
  border: none;
  border-bottom: 1px solid black;
  width: 80%;
  color: black;
  font-family: "Montserrat";
  align-items: center;
  display: flex;
  font-size: 30px;
  justify-content: center;
}

input:focus {
  outline: none;
}

.row {
  border: none;
  width: 100%;
  margin: auto;
}

.button {
  margin: auto;
  font-family: "Lora";
  font-size: 30px;
  color: white;
  background-color: black;
  border: none;
  transition: 0.5s;
  width: fit-content;
}

.button:hover {
  background-color: #006F45;
}
</style>
