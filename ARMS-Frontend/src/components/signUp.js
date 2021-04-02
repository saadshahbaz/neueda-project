import { AXIOS } from "./axiosInstance";
import Router from "../router";

export default {
  name: "SignUpForm",

  /**
   * declaration of the page's data
   */
  data() {
    return {
      username: "",
      password: "",
      email: "",
      phonenumber: "",
      user: "",
      error: "",
    };
  },
  methods: {
    signup: function () {

      if (this.username == "") {
        this.error = "Please enter your name";
      } else if (this.email == "") {
        this.error = "Please enter your email";
      } else if (this.password == "") {
        this.error = "Please enter your password";
      } else if (this.phonenumber == "") {
        this.error = "Please enter your phonenumber";
      } else {
        AXIOS.post(`/customer/?username=${this.username}&password=${this.password}&email=${this.email}&phonenumber=${this.phonenumber}`)

          .then((response) => {
            this.email = "";
            this.phonenumber = "";
            this.error = "";
            this.username = "";
            this.password = "";
            window.location.href = "/customerHome"

          })
          .catch((e) => {
            this.error= "Please enter a valid email and password"
            console.log(e);
            alert(e);
            alert("invalid password");
          });
      }
    },
  }
};
