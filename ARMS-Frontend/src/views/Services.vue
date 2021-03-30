<template>
  <div>
  <div class="services">
    Services
  </div>
  <div id="eventregistration">
    <h2>People</h2>
    <table>
      <tr>
        <td>John</td>
        <td>Event to attend</td>
      </tr>
      <tr>
        <td>
          <input type="text" placeholder="Person Name">
        </td>
        <td>
          <button>Create</button>
        </td>
      </tr>
    </table>
    <p>
      <span style="color:red">Error: Message text comes here</span>
    </p>
  </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.services {
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
