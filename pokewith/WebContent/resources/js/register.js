//INITIALIZE VARIABLES
const registerBtn = document.querySelector(".register-btn");
const clearBtn = document.querySelector(".clear-btn");
const errMsg = document.querySelector(".err-msg");

const inputName = document.querySelector(".nickname1");
const inputCode = document.querySelector(".friendCode1");

//DUMMY DATA FROM GET REQUEST
const userInfoData = {
  userId: "1668589466621909",
  nickname1: "",
  friendCode1: "",
};

//DUMMY DATA FOR POST REQUEST
let userInfoInput = {
  userId: "1668589466621909",
  nickname1: "",
  friendCode1: "",
};

//BINDING CLEAR BUTTTON EVENT
function clearInput(event) {
  event.preventDefault();
  inputName.value = "";
  inputCode.value = "";
  errMsg.innerText = "";
}

//BINDING HANDLING REGISTER EVENT
function handleRegister() {
  const sendName = inputName.value;
  // CHECK INPUT NICKNAME VALUE FROM REGULAR EXPRESSION
  const nameRgx = RegExp(/^[가-힣A-Za-z0-9_\-]{5,20}$/);
  const name = nameRgx.test(sendName);
  if (name == false) {
    errMsg.innerText = "Check your Nickname.";
  } else {
    const sendCode = inputCode.value;
    // CHECK INPUT FRIENDCODE VALUE FROM REGULAR EXPRESSION
    const codeRgx = RegExp(/^[0-9_\-]{12}$/);
    const code = codeRgx.test(sendCode);
    if (code == false) {
      errMsg.innerText = "Check your Friend code.";
    } else {
      userInfoInput.nickname1 = sendName;
      userInfoInput.friendCode1 = sendCode;
      console.log("ajax ready :)");
      registerAPI();
    }
  }
}
// ADDITIONAL USER INFORMATION FOR POST REQUEST

function registerAPI() {
  const httpReq = new XMLHttpRequest();
  const url = "/signup";

  httpReq.open("POST", url, true);
  console.log("good");

  httpReq.setRequestHeader("Access-Control-Allow-Headers", "*");
  httpReq.setRequestHeader("Content-type", "application/json");
  httpReq.setRequestHeader("Access-Control-Allow-Origin", "*");
  console.log("ok");

  httpReq.onreadystatechange = function () {
    console.log("test1");
    console.log(httpReq.status);
    if (httpReq.readyState === 4 && httpReq.status === "success") {
      console.log("test2");
      alert(httpReq.responseText);
    }
  };

  console.log("DATA : " + JSON.stringify(userInfoInput));
  httpReq.send(JSON.stringify(userInfoInput));
}

//BINDING SINGLE EVENT LISTENER FOR EACH BUTTONS
registerBtn.addEventListener("click", handleRegister);
clearBtn.addEventListener("click", clearInput);
