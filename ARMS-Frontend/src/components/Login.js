import { AXIOS } from "../components/axiosInstance";
import Cookies from 'js-cookie';

export default {
  name: "LoginForm",


    data() {
      return {
        username: "",
        password: "",
        error: "",
      };
    },
    methods: {
      login: function () {
        if (this.username == "") {
          this.error = "Please enter your name";
        }  else if (this.password == "") {
          this.error = "Please enter your password";
        }else {
          Cookies.set('username',this.username)
          AXIOS.put(`/loginCustomer/?username=${this.username}&password=${this.password}`)

            .then((response) => {
              this.error = "";
              this.username = "";
              this.password = "";
              window.location.href = "/customerHome"

            })
            .catch((e) => {
              this.error= "The password is invalid or the username doesn't exist"
              console.log(e);
              alert(e);
              alert("The password is invalid or the username doesn't exist");
            });
        }
      },
      loginAssistant: function () {
        if (this.username == "") {
          this.error = "Please enter your name";
        }  else if (this.password == "") {
          this.error = "Please enter your password";
        }else {
          Cookies.set('username',this.username)
          AXIOS.put(`/loginAssistant/?username=${this.username}&password=${this.password}`)

            .then((response) => {
              this.error = "";
              this.username = "";
              this.password = "";
              window.location.href = "/home"

            })
            .catch((e) => {
              this.error= "The password is invalid or the username doesn't exist"
              console.log(e);
              alert(e);
              alert("The password is invalid or the username doesn't exist");
            });
        }
      },
    }
  };


