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
          Cookies.set('username',this.username)
          AXIOS.put(`/loginCustomer/?username=${this.username}&password=${this.password}`)
            .then((response) => {
              this.error = "";
              this.username = "";
              this.password = "";
              window.location.href = "/customerHome"
            })
            .catch((e) => {
              alert(e.response.data.message);
            });
      },
      loginAssistant: function () {
          Cookies.set('username',this.username)
          AXIOS.put(`/loginAssistant/?username=${this.username}&password=${this.password}`)
            .then((response) => {
              this.error = "";
              this.username = "";
              this.password = "";
              window.location.href = "/home"
            })
            .catch((e) => {
              alert(e.response.data.message);
            });
      },
    }
  };


