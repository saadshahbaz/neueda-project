<template>
  <div>
    <div class="profile">
      Profile
    </div>
    <form action="" class="profile"> <div class="row my-3 px-3 pr-3">
    <div class="row my-3 px-3"><input
      type="password"
      name="oldpassword"
      id="OldPassword"
      placeholder="OldPassword"
      v-model="oldpassword"
    /></div>
    <div class="row my-3 px-3"><input
      type="password"
      name="newpassword"
      id="newpassword"
      placeholder="NewPassword"
      v-model="newpassword"
    /></div>
    <div class="row my-3 px-3"><input
      type="email" name="email"
      id="email"
      placeholder="Email"
      v-model="email" /></div>

    <div class="row my-3 px-3"><input
      type="text"
      name="phonenumber"
      id="phonenumber"
      placeholder="Phone Number"
      v-model="phonenumber"
    /></div>
    <div class="button px-3 py-1 mt-1 mb-3" type="submit" @click="change">Change Information</div>
    </div> </form>
  </div>
</template>

<script>
import {AXIOS} from "../components/axiosInstance";

export default {
  name: "Profile",
  data() {
    return {
      oldpassword: "",
      newpassword: "",
      email: "",
      phonenumber: "",
      realpassword:"",
      username:"",
      error: "",
    };
  },
  methods: {
    change: function () {
      if (this.oldpassword== "") {
        alert("Please enter the old password");
      }  else {
        AXIOS.get(`/getCurrentCustomer`)
          .then((response) =>{
          this.realpassword=response.data.password;
          this.username=response.data.username;
          if (this.email==""){
            this.email=response.data.email;
          }
          if (this.phonenumber==""){
            this.phonenumber=response.data.phonenumber;
          }
          if (this.realpassword != this.oldpassword){
            alert("The password is incorrect");
          }
          else{
            if (this.newpassword==""){
              this.newpassword=response.data.password;
            }
            AXIOS.put(`/updateCustomer/?username=${this.username}&password=${this.newpassword}&email=${this.email}&phonenumber=${this.phonenumber}`)

              .then((response) => {
                this.oldpassword= "";
                this.newpassword= "";
                this.email= "";
                this.phonenumber= "";
                this.realpassword="";
                this.username="";
                alert("Update Successfully");

              })
              .catch((e) => {
                this.error= "Please enter a valid email and password";
                console.log(e);
                alert(e);
                alert("Please recheck the information");
              });
          }
        })

      }
    },

  }
}
</script>

<style scoped>
.profile {
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
input {
  margin-top: 40px;
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
