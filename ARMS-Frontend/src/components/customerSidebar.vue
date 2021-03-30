<template>
  <div class="sidebar">
    <div class="title">
      Assistant
    </div>
    <div class="menu-items">
      <router-link to="/home" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Business
        </div>
      </router-link>
      <router-link to="/home/services" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Services
        </div>
      </router-link>
      <router-link to="/home/space" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Space
        </div>
      </router-link>
      <router-link to="/home/technicians" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Technicians
        </div>
      </router-link>
      <router-link to="/home/appointments" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Appointments
        </div>

      </router-link>
      <router-link to="/home/reminders" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Reminders
        </div>
      </router-link>

      <router-link to="/" active-class="active" tag="button" exact class="side-btn">
        <div class="link-container">
          Log Out
        </div>
      </router-link>

    </div>
  </div>
</template>

<script>
export default {
  name: "customerSidebar"
}
</script>

<style scoped>

</style>
