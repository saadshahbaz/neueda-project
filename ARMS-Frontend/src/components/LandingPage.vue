<template>
  <div id="landing-page" class="p-0 m-0">
    <h1 class="mb-3">Welcome!</h1>
    <h2>Please <strong>sign up</strong> or <strong>log in</strong>  to see more.</h2>
    <div class="sign-in-form">
      <div class="form-tabs row">
        <div class="form-tab col-sm" v-bind:class="{ active: form === 'login' }"
             v-on:click="form = 'login'"><h2
        >
          Login
        </h2></div>
        <div class="form-tab col-sm" v-bind:class="{ active: form === 'signup' }"
             v-on:click="form = 'signup'"><h2
        >
          Sign Up
        </h2></div>

      </div>
      <div v-if="form === 'signup'">
        <SignUpForm/>
      </div>
      <div v-else-if="form === 'login'">
        <LoginForm />
      </div>
    </div>
  </div>
</template>
<script>
import SignUpForm from "../components/SignUpForm";
import LoginForm from "../components/LoginForm";
export default {
  name: "LandingPage",
  components: {
    SignUpForm,
    LoginForm,
  },

  data() {
    return {
      form: "signup",
      username: "",
      error: ""
    };
  },

};
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lora:wght@500;700&family=Montserrat:ital,wght@0,400;0,700;1,400;1,700&display=swap');
#app {
  background: #E9E7DB;
}



body {
  background: #E9E7DB;
  color: black;
}


/* width */

::-webkit-scrollbar {
  width: 10px;
}


/* Track */

::-webkit-scrollbar-track {
  background: #a09f98;
}


/* Handle */

::-webkit-scrollbar-thumb {
  background: #006F45;
  border-radius: 10px;
}


/* Handle on hover */

::-webkit-scrollbar-thumb:hover {
  background: #005233;
}

#landing-page {
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100vw;
  height: 100vh;
  overflow-x: hidden;
  overflow-y: hidden;
  position: relative;
  margin: 0;
  background: #f1f6f6;
}

.sign-in-form {
  display: flex;
  flex-direction: column;
  margin: 30px auto;
  text-align: left;
  width: 25%;
  box-sizing: border-box;
  border: 1px solid #000000;
  background: #f1f6f6;
  z-index: 1;
}

.form-tabs {
  box-sizing: border-box;
  border: 1px solid #268078;
  margin: 0;
}

.form-tab {
  text-align: center;
  width: 50%;
  padding: 10px;
}

.form-tabs h2 {
  cursor: pointer;
  margin: 0;
  font-family: 'Lora';
}

.active {
  color: white;
  background-color: #268078;
}

h1 {
  color: #268078;
  font-size: 96px;
}

h2 {
  font-size: 24px;
}

</style>
