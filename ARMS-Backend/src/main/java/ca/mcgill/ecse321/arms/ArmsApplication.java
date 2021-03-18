package ca.mcgill.ecse321.arms;

import ca.mcgill.ecse321.arms.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@SpringBootApplication
public class ArmsApplication {
  private static User currentuser;
  private static Date date;
  private static Time time;
  public static void main(String[] args) {
    SpringApplication.run(ArmsApplication.class, args);
  }

  @RequestMapping("/")
  public String greeting(){
    return "Hello world!";
  }

  public static Date getCurrentDate() {
    return date;
  }

  public static void setCurrentDate(Date curDate) {
    date = curDate;
  }

  public static Time getCurrentTime() {
    return time;
  }

  public static void setCurrentTime(Time curTime) {
    time = curTime;
  }

  public static void setSystemDateAndTime() {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateAndTime = formatter.format(calendar.getTime());
    Date curDate = Date.valueOf(dateAndTime.substring(0, 10));
    Time curTime = Time.valueOf(dateAndTime.substring(11));
    ArmsApplication.setCurrentDate(curDate);
    ArmsApplication.setCurrentTime(curTime);
  }

  public static User getCurrentuser() {
    return currentuser;
  }

  public static void setCurrentuser(User currentuser) {
    ArmsApplication.currentuser = currentuser;
  }
}