<template>
  <div class="dashboard">
    <customerSidebar/>
    <div class="content">

      <router-view/>
    </div>
  </div>
</template>

<script>
import customerSidebar from '../components/customerSidebar'
export default {
  name: 'home',

  components: {
    customerSidebar
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style>
.dashboard {
  display: grid;
  grid-template-columns: 1fr 5fr;
  background-color: teal;
  height: 100vh;
  width: 100vw;
}

.content {
  background-color: white;
  border-radius: 10px;
  margin: 6px 6px 6px 0px;
}
</style>
