<template>
  <div id="appointments">
    <date-picker v-model="date" lang="en" type="date" format="YYYY-MM-dd"></date-picker>
  </div>
</template>

<script>
import DatePicker from 'vue2-datepicker'
export default {

  name: 'Appointments',
  components: {
    DatePicker
  },
  data(){
    return{
      date:''
    }
  }
}
</script>

<style scoped>
.appointments {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  font-size: 40px;
  color: rgb(167, 167, 167);
  font-weight: 600;
}
</style>
