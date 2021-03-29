<template>
  <div id = "loginApp">
    <!-- 網站主區塊 -->
    <section id="loginBody" class="login-frame">
      <div class="frame-bg"></div>
      <div class="frame-opacity"></div>

      <div class="frame-container">
        <!-- Flex 容器-->
        <main class="UI-frame">
          <!-- 動畫區塊的框架 -->
          <div class="Animation-frame">

            <!-- Switch UI panel -->
            <div class="SwitchUI-frame">
              <div class="SwitchUI-bg"></div>
              <div class="SwitchUI-op"></div>
              <div class="SwitchUI-content">
                <!-- 左邊 -->
                <div class="left-content" id="leftPage">
                  <div class="txt-div">
                    <h2>Have an account? </h2>
                    已經有帳號了？趕緊登入。
                  </div>
                  <div class="btn-switch" id="leftBtn">
                    登入
                  </div>

                </div>
                <!-- 右邊 -->
                <div class="right-content" id="rightPage">

                  <div class="txt-div">
                    <h2>Don't have an account? </h2>
                    加入會員，開始這趟旅途。
                  </div>

                  <div class="btn-switch" id="rightBtn">
                    註冊
                  </div>

                </div>

              </div>
            </div>
            <!-- Switch UI panel -->

            <!-- Loging UI panel -->

            <div class="LogInUI-frame" id = "uiFrame1">
              <div class="LogInUI-bg"></div>
              <div class="LogInUI-bg-opacity"></div>

              <!-- 內容 -->
              <div class="LogInUI-contenter" id="loginPanel">

                <!-- 登入面板 -->
                <div class="slide-frame" id="slideUIFrameLogin" >
                  <div class="LogInUI-contenter-title">
                    <div> 登入 </div>
                  </div>

                  <div class="LogInUI-contenter-section">
                    <div> Account: </div>
                    <input type="text" name="account">
                    <br>
                    <div>Passowrd: </div>
                    <input type="password" name="password">
                    <button>Log in</button>
                    <button @click="ChangeToReg()" class="phoneButton">Sign up</button>
                    <br>

                  </div>
                </div>
                <!-- 登入面板 -->

                <!-- 註冊面板 -->
                <div class="slide-frame" id="slideUIFrameReg">
                  <div class="LogInUI-contenter-title">
                    <div> 註冊 </div>
                  </div>

                  <div class="LogInUI-contenter-section">
                    <div> Account: </div>
                    <input type="text" name="account">
                    <br>
                    <div>Passowrd: </div>
                    <input type="password" name="password">
                    <br>
                    <div>重複 Passowrd: </div>
                    <input type="password" name="password">
                    <button>Sign up</button>
                    <button @click="ChangeToLogin()" class="phoneButton">Log in</button>
                  </div>
                </div>
                <!-- 註冊面板 -->

              </div>
              <!-- 內容 -->
            </div>
            <!-- Loging UI panel -->
          </div>
          <!-- 動畫區塊的框架 -->
        </main>
      </div>
    </section>

  </div>
</template>

<script>
export default {
  name: 'loginApp',
  mounted(){

    this.switchLoginUIPanel();
  },
  methods : {
    switchLoginUIPanel(){
      let rightBtn = document.getElementById('rightBtn');
      let leftBtn = document.getElementById('leftBtn');
      let uiFrame1 = document.getElementById('uiFrame1');
      let leftPage = document.getElementById('leftPage');
      let rightPage = document.getElementById('rightPage');
      let RegPanel = document.getElementById('slideUIFrameReg');
      let loginPanel = document.getElementById('slideUIFrameLogin');
      rightBtn.addEventListener('click', function(){
        uiFrame1.style.marginLeft = '580px';
        leftPage.style.opacity = 1;
        rightPage.style.opacity = 0;
        loginPanel.style.marginLeft = '-400px';
      });
      leftBtn.addEventListener('click', function(){
        uiFrame1.style.marginLeft = '20px';
        leftPage.style.opacity = 0;
        rightPage.style.opacity = 1;
        loginPanel.style.marginLeft = 0;

      });
    },
    ChangeToReg(){
      let slideUIFrameLogin = document.getElementById('slideUIFrameLogin');

      slideUIFrameLogin.style.display = 'none';

    },
    ChangeToLogin(){
      let slideUIFrameLogin = document.getElementById('slideUIFrameLogin');

      slideUIFrameLogin.style.display = 'block';
    }
  }
};
</script>

<style>
body{
  margin: 0;
}
/* unvisited link */
a:link {
  text-decoration: none;
  color: #333;
}
/* visited link */
a:visited {
  text-decoration: none;
  color: #333;
}
/* mouse over link */
a:hover {
  text-decoration: none;
  color: #333;
}
/* selected link */
a:active {
  text-decoration: none;
  color: #333;
}
.fbLogin:link{
  text-decoration: none;
  color:#99b3ff;

}
/* visited link */
.fbLogin:visited {
  text-decoration: none;

  color:#99b3ff;
}
/* mouse over link */
.fbLogin:hover {
  text-decoration: none;

  color:#ccd9ff;
}
/* selected link */
.fbLogin:active {
  text-decoration: none;

  color:#99b3ff;
}
/*
Click on the Menu Icon to transform it to "X":
*/
.menu-button{
  position: fixed;
  z-index: 100;
  top: 5px;
  left: 5px;
}
.menu-container {
  display: inline-block;
  margin-left: 10px;
  cursor: pointer;
}
.bar1, .bar2, .bar3 {
  width: 35px;
  height: 5px;
  background-color: #fff;
  margin: 6px 0;
  transition: 0.4s;
}
.change .bar1 {
  -webkit-transform: rotate(-45deg) translate(-9px, 6px);
  transform: rotate(-45deg) translate(-9px, 6px);
}
.change .bar2 {opacity: 0;}
.change .bar3 {
  -webkit-transform: rotate(45deg) translate(-8px, -8px);
  transform: rotate(45deg) translate(-8px, -8px);
}
/*
  導航CSS
*/
nav{
  position: fixed;
  z-index: 99;
  background-color: lightblue;
  top: 0;
  left: 0;
  height: 100vh;
  width: 0;
  box-shadow: 0 10px 20px 0 rgba(0, 0, 0, 0.4), 0 10px 30px 0 rgba(0, 0, 0, 0.5);
  overflow: hidden;
  transition: 0.5s;
}
.nav-bg{
  position: relative;
  z-index: 100;
  height: 100vh;
  width: 300px;
  background-color: lightblue;
  top: 0;
}
.nav-opacity{
  position: relative;
  z-index: 101;
  height: 100vh;
  width: 300px;
  background-color: #333;
  opacity: 0.4;
  top: -100vh;
}
.nav-content{
  position: relative;
  z-index: 102;
  height: 100vh;
  width: 300px;
  top: -200vh;
}
/*
  Section 背景
*/
.login-frame{
  z-index: 0;
  width: 100%;
  height: 100vh;
  background-color: black;
}
.frame-bg{
  position: relative;
  z-index: 1;
  width: 100%;
  height: 100vh;
  top: 0;
  background-image: url('../assets/loginPage.jpeg');
  background-size: cover;

}
.frame-opacity{
  position: relative;
  z-index: 2;
  width: 100%;
  height: 100vh;
  top: -100vh;
  background-color: #333;
  opacity: 0.4;
}
.frame-container{
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  z-index: 3;
  width: 100%;
  height: 100vh;
  top: -200vh;
}
/*
  UI frame
*/
.UI-frame{
  z-index: 4;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 1200px;
  height: 700px;
  overflow: hidden;
}
.Animation-frame{
  flex: none;
  z-index: 5;
  width: 1000px;
  height: 500px;
}
/*
  Switch UI panel
*/
.SwitchUI-frame{
  flex: none;
  position: relative;
  z-index: 6;
  top: 50px;
  width: 1000px;
  height: 400px;
  overflow: hidden;
  box-shadow: 0 10px 20px 0 rgba(0, 0, 0, 0.4), 0 10px 30px 0 rgba(0, 0, 0, 0.5);

}
.SwitchUI-bg{
  position: relative;
  z-index: 7;
  width: 1000px;
  height: 400px;
  background-color: lightblue;
}
.SwitchUI-op{
  position: relative;
  z-index: 8;
  top: -400px;
  width: 1000px;
  height: 400px;
  background-color: #333;
  opacity: 0.5;
}
.SwitchUI-content{
  position: relative;
  display: flex;
  justify-content: space-between;
  z-index: 9;
  top: -800px;
  width: 1000px;
  height: 400px;

}
.left-content{
  flex: none;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 400px;
  height: 400px;
  opacity: 0;
  color: #fff;
  transition: opacity 0.6s;
}
.right-content{
  flex: none;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 400px;
  height: 400px;
  opacity: 1;
  color: #fff;
  transition: opacity 0.6s;

}
.txt-div{
  flex: none;
  width: 400px;
  height: 200px;
  text-align: center;

}
.btn-switch{
  flex: none;
  width: 120px;
  height: 40px;
  border:2px solid #fff;
  text-align: center;
  font-size: 28px;
  cursor: pointer;
}
.btn-switch:hover{
  background-color: #fff;
  color: #333;
}
/*
  Login UI panel
*/
.LogInUI-frame{
  flex: none;
  position: relative;
  z-index: 10;
  top: -400px;
  width: 400px;
  height: 500px;
  color: white;
  box-shadow: 0 10px 20px 0 rgba(0, 0, 0, 0.4), 0 10px 30px 0 rgba(0, 0, 0, 0.5);
  overflow: hidden;
  border-radius: 0px;
  margin-left: 20px;
  transition: margin-left  0.8s;
}
.LogInUI-bg{
  position: relative;
  z-index:11;
  width: 400px;
  height: 500px;
  /*background-image: url('../img/bg02.jpg');*/
  /*
  background-image: linear-gradient(-90deg, rgb(0, 191, 255) , rgb(0, 115, 153));
  */
  background-size: cover;
}
.LogInUI-bg-opacity{
  position: relative;
  z-index: 12;
  width: 400px;
  height: 500px;
  top: -500px;
  background-image: linear-gradient(90deg, #666666, #333333);
  opacity: 0.6;

}
.LogInUI-contenter{
  position: relative;
  z-index: 13;
  display: flex;
  width: 400px;
  height: 500px;
  top: -1000px;

}
/* 滑動框 */
.slide-frame{
  flex: none;

  transition: 0.85s;
}
/*
  面板內表單
*/
.LogInUI-contenter-title{
  width: 400px;
  height: 60px;
  /*
  background-image: linear-gradient(-90deg, #666666, #333333);
  */
  color: white;
  font-size: 26px;
}
.LogInUI-contenter-title > div{
  margin-top: 10px;
  margin-left: 10px;
}
.LogInUI-contenter-section{
  width: 400px;
  height:400px;

}
.LogInUI-contenter-section > div{
  margin-left: 50px;
  margin-top: 35px;
  font-size: 18px;
}
.LogInUI-contenter-section > input{

  width: 300px;
  height:34px;
  margin-top: 5px;
  margin-left: 50px;
  background-color: #fff3;
  border-radius: 5px;
  border-style: solid;
  border-color: #fff;
  color: white;
  font-size: 18px;
}
.LogInUI-contenter-section > button{

  width: 120px;
  height: 40px;
  margin-left: 50px;
  margin-top: 20px;
  background-color: #fff0;
  border:2px solid #fff;
  text-align: center;
  font-size: 20px;
  color: #fff;
  cursor: pointer;
}
.LogInUI-contenter-section > button:hover{

  width: 120px;
  height: 40px;
  margin-left: 50px;
  margin-top: 20px;
  background-color: #fff;
  border:2px solid #fff;
  text-align: center;
  font-size: 20px;
  color: #333;
  cursor: pointer;
}
.phoneButton{
  display: none;
}
@media(max-width: 500px){
  .UI-frame{
    z-index: 4;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 400px;
    height: 700px;
    overflow: hidden;
  }
  .Animation-frame{
    flex: none;
    z-index: 5;
    width: 400px;
    height: 500px;
  }
  .SwitchUI-frame{
    flex: none;
    position: relative;
    z-index: 6;
    top: 50px;
    width: 400px;
    height: 400px;
    overflow: hidden;
    box-shadow: 0 10px 20px 0 rgba(0, 0, 0, 0.4), 0 10px 30px 0 rgba(0, 0, 0, 0.5);

  }
  .SwitchUI-bg{
    position: relative;
    z-index: 7;
    width: 400px;
    height: 400px;
    background-color: lightblue;
  }
  .SwitchUI-op{
    position: relative;
    z-index: 8;
    top: -400px;
    width: 400px;
    height: 400px;
    background-color: #333;
    opacity: 0.5;
  }
  .SwitchUI-content{
    position: relative;
    display: flex;
    justify-content: space-between;
    z-index: 9;
    top: -800px;
    width: 400px;
    height: 400px;

  }
  .left-content{
    flex: none;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 400px;
    height: 400px;
    opacity: 0;
    color: #fff;
    transition: opacity 0.6s;
  }
  .right-content{
    flex: none;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 400px;
    height: 400px;
    opacity: 1;
    color: #fff;
    transition: opacity 0.6s;

  }
  .phoneButton{
    display:inline-block;
  }
}
</style>
