<template>
  <div class="row my-3 px-3"><input
    name="Delete"
    id="OldPassword"
    placeholder="Please input delete to confirm"
    v-model="deleteAccount"/></div>
  <div class="button px-3 py-1 mt-1 mb-3" type="submit" @click="deleteAccount">Delete Account</div>
</template>

<script>
import {AXIOS} from "../components/axiosInstance";
export default {
  name: "DeleteAccount",
  data(){
    return{
      deleteAccount : "",
    };
  },
  methods: {
  deleteAccount: function () {
    if (this.deleteAccount!= "delete") {
      alert("Please enter delete to confrim");
    }  else {
      AXIOS.delete(`/deleteCustomer`)
        .then((response) => {
        this.deleteAccount="";
          alert("Delete Successfully");
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

</style>
