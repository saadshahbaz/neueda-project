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
<script src="../components/signUp.js">
</script>
<style scoped src="../components/signInForm.css">
</style>
