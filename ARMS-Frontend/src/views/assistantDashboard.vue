<template>
  <div class="dashboard">
    <assistantSidebar/>
    <div class="content">

      <router-view/>
    </div>
  </div>
</template>

<script>
import assistantSidebar from '../components/assistantSidebar';
import assistantDashboard from "./assistantDashboard";
import customerSidebar from "../components/customerSidebar";
export default {
  name: 'assistantHome',
  components: {
    assistantSidebar
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
