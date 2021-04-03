<template>

  <div class="block riqi" v-show=see>

    <span class="check-add-del-span">日期</span>
    <el-date-picker
      v-model="gradeDate"
      @focus="riqi"
      type="date"
      placeholder="选择日期"
      format="yyyy年MM月dd日"
      value-format="yyyy-MM-dd"
      @change="toWeek"
    >
    </el-date-picker>

    <el-table
      :data="timeData"
      style="width: 100%">
      <el-table-column width="80" label="周" fixed="left" prop="label" align="center" :formatter="week"> </el-table-column>
      <el-table-column width="150" label="日期" prop="timez" align="center"></el-table-column>
    </el-table>



  </div>

</template>

<script>
export default {
  data(){
    return{
      gradeDate:'',//所选择的日期
      timeData:[]//存放表格数据
    }
  },
  toWeek: function(){
    var Setime = this.gradeDate; //给所选择的日期一个变量名
    var ji = new Date(Setime).getDay() //将日期转换成周
    var date = new Date(Setime); //指定在对象中存储的日期
    var timeList = []
    //  console.log(date)
    for(var i=1-ji;i<8-ji;i++){ //根据选定的星期进行循环一周
      var q = new Date(); //获取当前日期
      // console.log(i);
      let tt = q.setTime(date.getTime() + 3600 * 1000 * 24*i);//将当前日期设置成指定日期的前i天的毫秒日期
      let td = new Date(tt); //将获取到的毫秒日期指定到对象中存储
      let time = td.getFullYear() + "-" + (td.getMonth()+1) + "-" + td.getDate(); //将毫秒日期转为年月日格式
      // console.log(time);
      timeList.push(time) //将根据选择的日期循环出来一周的日期放进数组里面
    }
    this.timeData = [];
    for(var i=0;i<7;i++){
      this.timeData.push({
        timez:timeList[i],
        label:i+1
      })
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
