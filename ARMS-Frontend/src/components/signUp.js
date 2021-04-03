import { AXIOS } from "./axiosInstance";
import Router from "../router";
import Cookies from 'js-cookie'

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
        Cookies.set('userName',this.username)
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
            alert(e.response.data.message);
          });
    },
  }
};
