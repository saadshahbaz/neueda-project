<template>
  <div id="sign-up-form">
    <form action="" class="sign-up-form"><div class="row my-3 px-3 pr-3">



    </div>
      <div class="row my-3 px-3"><input
        type="text" name="username"
        id="username"
        placeholder="Username"
        v-model="username" /></div>

      <div class="row my-3 px-3"><input
        type="password"
        name="password"
        id="password"
        placeholder="Password"
        v-model="password"
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
      <div class="button px-3 py-1 mt-1 mb-3" type="submit" @click="signup">Sign Up</div>
    </form>


  </div>
</template>
<script>
import { AXIOS } from "./axiosInstance";
import Router from "../router";
import Cookies from 'js-cookie'

export default {
  name: "SignUpForm",

  /**
   * declaration of the page's data
   */
  data() {
    return {
      username: "",
      password: "",
      email: "",
      phonenumber: "",
      user: "",
      error: "",
    };
  },
  methods: {
    /**
     * Sign up as a Customer, with username, password, email, and phone number
     * throw exception if the password is less than 9 characters or the email is incorrect.
     */
    signup: function () {
      Cookies.set('userName',this.username)
      AXIOS.post(`/customer/?username=${this.username}&password=${this.password}&email=${this.email}&phonenumber=${this.phonenumber}`)
        .then((response) => {
          this.email = "";
          this.phonenumber = "";
          this.error = "";
          this.username = "";
          this.password = "";
          window.location.href = "/customerHome"

        })
        .catch((e) => {
          alert(e.response.data.message);
        });
    },
  }
};

</script>
<style scoped>
#login-form,
.sign-up-form {
  padding: 5px;
  width: 100%;
  margin: auto;
  text-align: center;
}

input {
  background: transparent;
  border: none;
  border-bottom: 1px solid black;
  width: 100%;
  color: black;
  font-family: "Montserrat";
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
  font-family: "Georgia";
  font-size: 20px;
  color: white;
  background-color: #4f5f5e;
  border: none;
  transition: 0.5s;
  width: fit-content;
}

.button:hover {
  background-color: #268078;
}

</style>
