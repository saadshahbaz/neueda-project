import { AXIOS } from "./axiosInstance";


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
            this.$router.push({name: "/home"});
          })
          .catch((e) => {
            var errorMsg = "Please enter a valid email and password"
            console.log(e);
            this.error = errorMsg;
          });
      }
    },
  }
};
