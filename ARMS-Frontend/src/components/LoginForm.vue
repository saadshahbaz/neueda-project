<template>
  <div id="login-form">
    <form action=""><div class="row my-3 px-3">
      <input type="text" name="username" id="username" placeholder="Username" v-model="username" /><br /></div>
      <div class="row my-3 px-3">
        <input
          type="password"
          name="password"
          id="password"
          placeholder="Password"
          v-model="password"
        /></div>
      <div class="button px-3 py-1 mt-1 mb-3" v-on:click="login">Login</div>
      <div class="button px-3 py-1 mt-1 mb-3" v-on:click="loginAssistant">Login As Assistant</div>
    </form>

  </div>
</template>
<script >
import { AXIOS } from "../components/axiosInstance";
import Cookies from 'js-cookie';

export default {
  name: "LoginForm",


  data() {
    return {
      username: "",
      password: "",
      error: "",
    };
  },
  methods: {
    login: function () {
      Cookies.set('username',this.username)
      AXIOS.put(`/loginCustomer/?username=${this.username}&password=${this.password}`)
        .then((response) => {
          this.error = "";
          this.username = "";
          this.password = "";
          window.location.href = "/customerHome"
        })
        .catch((e) => {
          alert(e.response.data.message);
        });
    },
    loginAssistant: function () {
      Cookies.set('username',this.username)
      AXIOS.put(`/loginAssistant/?username=${this.username}&password=${this.password}`)
        .then((response) => {
          this.error = "";
          this.username = "";
          this.password = "";
          window.location.href = "/home"
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
