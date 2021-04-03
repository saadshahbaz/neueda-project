<template>
  <div>
    <div class="Profile" action="created">
      Profile
    </div>
    <form action="" class="profile"> <div class="row my-3 px-20 pr-3">
    <div class="row my-3 px-20">
      UserName: {{this.username}}
    </div>
    <div class="row my-3 px-20">
      Email: {{this.email}}
    </div>
    <div class="row my-3 px-20">
      Phone Number: {{this.phonenumber}}
    </div>

    </div> </form>
  </div>
</template>

<script>
import {AXIOS} from "../components/axiosInstance";

/**
 * show the information of a customer
 */
export default {

  name: "InformationUpdate",
  data() {
    return {
      username: "",
      email: "",
      phonenumber : "",
    };
  },
  created() {
      AXIOS.get(`/getCurrentCustomer`)
        .then((response) => {
          this.username = response.data.username;
          this.email = response.data.email;
          this.phonenumber = response.data.phonenumber;
        })
  }

}
</script>

<style scoped>
.Profile{
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


input:focus {
  outline: none;
}

.row {
  border: none;
  width: 100%;
  height: 100px;
  font-size: 30px;
  align-items: center;
  display: flex;
  margin-left:100px;
  marign-bottom:50px;
  margin-top:200px;
}

</style>
