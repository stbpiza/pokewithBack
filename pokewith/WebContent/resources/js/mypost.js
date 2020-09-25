
//더미 데이터
const postData = [
    { p_id : 1,
      nickname1 : 'nickname1',
      u_like : '20',
      u_hate : '10',
      pokemon : '이상해씨',
      raidLevel : 1,
      startTime : '03:30',
      endTime : '04:15',
      minLevel : 20,
      nPass : 3,
      rPass : 0,
      p_end : 0}
]

const commentData = [
    { "c_id": "1",
      "p_id": "1",
      "userId": "1668589466621909",
      "checkNum": "12",
      "c_end": "0",
      "nickname1": "2eeebug",
      "friendCode1": "617654262236",
      "nickname2": null,
      "friendCode2": null,
      "nickname3": null,
      "friendCode3": null,
      "nickname4": null,
      "friendCode4": null,
      "nickname5": null,
      "friendCode5": null,
      "u_like": "88",
      "u_hate": "0"
    },
    { "c_id": "2",
      "p_id": "1",
      "userId": "1649416911892763",
      "checkNum": "1",
      "c_end": "0",
      "nickname1": "fdfd",
      "friendCode1": "123412341234",
      "nickname2": null,
      "friendCode2": null,
      "nickname3": null,
      "friendCode3": null,
      "nickname4": null,
      "friendCode4": null,
      "nickname5": null,
      "friendCode5": null,
      "u_like": "1",
      "u_hate": "0"
    },
    { "c_id": "3",
      "p_id": "1",
      "userId": "2649416911892763",
      "checkNum": "1",
      "c_end":"0",
      "nickname1": "fdfddd",
      "friendCode1": "234545671234",
      "nickname2": "dgdfde",
      "friendCode2": "234545671234",
      "nickname3": "dsgsdffddd",
      "friendCode3": "134545671234",
      "nickname4": "dsgsdfd",
      "friendCode4": "134645171234",
      "nickname5": "dsgs3",
      "friendCode5": "112464517123",
      "u_like":"2",
      "u_hate":"0"
    },
    { "c_id": "4",
      "p_id": "1",
      "userId": "2649416911892763",
      "checkNum": "13",
      "c_end":"0",
      "nickname1": "adfdsfd",
      "friendCode1": "234545671234",
      "nickname2": "dgdfde",
      "friendCode2": "234545671234",
      "nickname3": "dsgsdffddd",
      "friendCode3": "134545671234",
      "nickname4": "dsgsdfd",
      "friendCode4": "134645171234",
      "nickname5": "dsgs3",
      "friendCode5": "112464517123",
      "u_like":"3",
      "u_hate":"0"
    },
    { "c_id": "5",
      "p_id": "1",
      "userId": "2649416911892763",
      "checkNum": "12",
      "c_end":"0",
      "nickname1": "adfad",
      "friendCode1": "234545671234",
      "nickname2": "dgdfde",
      "friendCode2": "234545671234",
      "nickname3": "dsgsdffddd",
      "friendCode3": "134545671234",
      "nickname4": "dsgsdfd",
      "friendCode4": "134645171234",
      "nickname5": "dsgs3",
      "friendCode5": "112464517123",
      "u_like":"3",
      "u_hate":"0"
    }
]


//sendAjax() : ajax 연결 (POST/GET)
function sendAjax(url, method, data, callback){
  var httpReq = new XMLHttpRequest();

  httpReq.open(method, url, true);
  console.log('good');

  httpReq.setRequestHeader('Access-Control-Allow-Headers', '*');
  httpReq.setRequestHeader('Content-type', 'application/json');
  httpReq.setRequestHeader('Access-Control-Allow-Origin', '*');
  console.log('ok');

  httpReq.onreadystatechange = function () {
      console.log('들어옴1');
      if (httpReq.readyState === 4 && httpReq.status === 200) {
        console.log('들어옴2');
        console.log(httpReq.responseText);
        callback(httpReq);
      }
  };

  if(data != null){
    console.log("POST방식");
    httpReq.send(data);
  } else {
    console.log("GET방식");
    httpReq.send();
  }

}

//showMyPost() : myPost 게시글을 보는 함수
function showMyPost(resultData){
    
  let num = resultData.p_id;
  console.log(num);
  
  let startDiv = document.createElement("div");
  startDiv.setAttribute("class", "card shadow");
  startDiv.setAttribute("id", "result-box");

  let nickDiv = document.createElement("div");
  nickDiv.setAttribute("class", "card-header py-3 d-flex flex-row align-items-center justify-content-between");

  let nickName = '';
  nickName += '<p class="m-0 text-gray"><b>'+resultData.nickname1
              + '</b><i class="fa fa-thumbs-up updown" aria-hidden="true" style="font-size:10px"></i>' + resultData.u_like
              + ' <i class="fa fa-thumbs-down" aria-hidden="true" style="font-size:10px"></i>' + resultData.u_hate
              + '</p> <button class="endCheck" onclick="endPost('+num+'); endChat('+resultData.chat+')">end</button>'
  nickDiv.innerHTML = nickName;

  startDiv.appendChild(nickDiv);

  if(resultData.p_end == null || resultData.p_end == "2") {
    let currentDiv = document.querySelector("#my-box");
    let endPage = document.createElement("div");
    endPage.setAttribute("class", "card-body endBody");

    endStr = '<p class="endStr">Nothing!</p>';

    endPage.innerHTML = endStr;

    currentDiv.appendChild(endPage);
  } else {

    let cardDiv = document.createElement("div");
    cardDiv.setAttribute("class", "myCard card-body cardBody"+num);
    startDiv.appendChild(cardDiv);

    if(resultData.raidLevel == '1') {
        str = "<img src = 'img/1.PNG' style='width:50px'>"
    } else if(resultData.raidLevel == '3') {
        str = "<img src = 'img/3.PNG' style='width:50px'>"
    } else if(resultData.raidLevel == '5') {
        str = "<img src = 'img/5.PNG' style='width:50px'>"
    } else {
        str = "<img src = 'img/mega.PNG' style='width:50px'>"
    }
    
    cardDiv.innerHTML += '<input type="hidden" id="postId" name="postId" value="'+ resultData.p_id +'">';
    cardDiv.innerHTML += '<p> Pokemon : ' + resultData.pokemon+'</p>';
    cardDiv.innerHTML += '<p> Level of Raid : ' + str + '</p>';
    cardDiv.innerHTML += '<p> Start Time of Raid : ' + resultData.startTime+'</p>';
    cardDiv.innerHTML += '<p> End Time of Raid : ' + resultData.endTime+'</p>';
    cardDiv.innerHTML += '<p> Required Player Level : ' + resultData.minLevel+'</p>';
    cardDiv.innerHTML += '<p> Premium Pass : <img src="img/3_premium.png" class="remote1"> / <img src="img/2_premium.png" class="remote2">' + resultData.nPass+'</p>';
    cardDiv.innerHTML += '<p> Remote Pass : <img src="img/1_remote.png" class="remote1"> ' + resultData.rPass + '</p>';

    let commentDiv3 = document.createElement('div');
    commentDiv3.setAttribute('class', 'commentBox');
    commentDiv3.setAttribute('id', 'commentBox');

    let commentA = document.createElement('button');
    commentA.setAttribute('id', 'comment'+num)
    commentA.setAttribute('class', 'hide-link');
    commentA.setAttribute('onclick', 'allCommentAjax('+num+')');
  
  
    let commentText = document.createTextNode('comment');
    commentA.appendChild(commentText);

    let arrowDown = document.createElement("i");
    arrowDown.setAttribute("class", "fa fa-sort-down");

    commentA.appendChild(arrowDown);

    commentDiv3.appendChild(commentA);
  
    let divide = document.createElement('div');
    divide.setAttribute('class', 'dropdown-divider');
  
    commentDiv3.appendChild(divide);
    cardDiv.appendChild(commentDiv3);

    let currentDiv = document.querySelector("#my-box");
  
    let chattingDiv = document.createElement("div");
    chattingDiv.setAttribute("class", "chattingDiv");
    currentDiv.appendChild(chattingDiv);

      //enter
      let chattingLink = document.createElement("a");
      chattingLink.href = '/rooms/' + resultData.chat;
      chattingLink.className = 'chattingLink';
      chattingLinkText = document.createTextNode("Start Chatting!");
      chattingLink.appendChild(chattingLinkText);
      chattingDiv.appendChild(chattingLink);  
      currentDiv.appendChild(startDiv);

      if(resultData.chat == null){
        //null일 때
        chattingLink.style.display = 'none';
      }else{
        //null이 아닐 때
        chattingLink.style.display = 'block';
      }

      //create
      let chattingLink2 = document.createElement("a");
      chattingLink2.href = createChatting(resultData.chat);
      chattingLink2.className = 'chattingLink';
      chattingLinkText2 = document.createTextNode("create Chatting!");
      chattingLink2.appendChild(chattingLinkText2);
      chattingDiv.appendChild(chattingLink2);  
      currentDiv.appendChild(startDiv);

      if(resultData.chat == null){
        //null일 때
        chattingLink2.style.display = 'none';
      }else{
        //null이 아닐 때
        chattingLink2.style.display = 'block';
      }
  }
}
      

//allPost() : myPost 게시글을 출력하기 전 거치는 ajax
/* 세션이 도입되면 하드 코딩했던 부분 지움 */
function myPostAjax() {
  let url = '/mypost';
  
  sendAjax(url, 'GET', null, function(res){
    console.log(res.response);
    var result = JSON.parse(res.response);
    
    showMyPost(result);
  });

  // for(let i = 0; i < postData.length; i++){
  //   allPostHtml(postData, i);
  // }
};

//mypost 페이지 들어왔을 시 바로 showMyPost() 함수가 호출되도록 함
if (window.addEventListener)
        window.addEventListener("load", myPostAjax, false);
else if (window.attachEvent)
      window.attachEvent("onload", myPostAjax);
else window.onload = myPostAjax;

//allComment(num) : myPost 게시글의 모든 댓글을 보는 함수
function allComment(resultData) {

  let num = resultData[0].p_id;
  let currentDiv = document.querySelector(".cardBody"+num);
  
  let checkNum = document.createElement('div');
  checkNum.setAttribute('id', 'checkNum');
  
  checkNum.innerHTML = 'Selectable number of accounts';
  
  currentDiv.appendChild(checkNum);
  
  let startDiv = document.createElement("div");
  startDiv.setAttribute("class", "card-body commentBody"+num);
  
  if(resultData == '[]'){
    let nullDiv = 'No comments yet.';
    startDiv.innerHTML = nullDiv;
  }

  arr = [];
  for(let i = 0; i<resultData.length; i++){
    if(num == resultData[i].p_id && resultData[i].c_end == '0'){
      let commentId = resultData[i].c_id;
      let commentW = document.createElement("div");
      commentW.setAttribute("class", "commentWrap comment"+commentId);

      let commentText = '';
      commentText += '<div> <p class="commentP"> <input type="checkbox" id="checkUser'+resultData[i].c_id+'" class="checkUser" onclick="checkUser(this, '+resultData[i].p_id+')" name="'+resultData[i].c_id+'" value="'+ resultData[i].checkNum +'">' + resultData[i].nickname1 + "</div>";
      commentText += '</p><div class="commentDiv"><div class="d-flex align-items-center justify-content-between"><div><img src="img/1_remote.png" class="remote1"> <span id="commentLength'+commentId+'" class="commentLength">' + resultData[i].checkNum.length + "</span></div><i class='fas fa-times delete' onclick='deleteComment("+resultData[i].c_id+","+resultData[i].p_id+")'><i></div></div></div>";
      commentW.innerHTML = commentText;

      startDiv.appendChild(commentW);
      
    } else {
      if(resultData[i].c_end == '1'){
        checkNum.innerHTML = "<h5 class='nick1'>"+resultData[i].nickname1+"'s</5>";
        showNick(resultData);
      }
    }
  }
  
  let comSub = document.createElement("div");
  comSub.setAttribute("class", "comSub");

  startDiv.appendChild(comSub);

  currentDiv.appendChild(startDiv);

  let arrowUp = document.getElementById("comment"+num);
  arrowUp.setAttribute('onclick', 'hideComment('+num+')');
  
  arrowUp.innerHTML = 'comment <i class="fa fa-sort-up"></i>';

  let commentBox = document.querySelector(".commentBody"+num);
  commentBox.style.display = 'block';  
}

//allPost() : myPost 게시글의 댓글을 출력하기 전 거치는 ajax
function allCommentAjax(num) {
  
  let url = '/comment/'+num;
  
  const postId = num;
  
  sendAjax(url, 'GET', null, function(res){
    console.log(res.response);
    var result = JSON.parse(res.response);   
    allComment(result);
  });
};


//전역 변수 comment_id_names, c_id를 배열로 담는 변수다.
let comment_id_names = [];

//checkUser() : 같이할 유저를 체크 확인하는 함수
/* 음수로 넘어가면 더이상 체크할 수 없게끔 한다. 
선택할 때마다 실시간으로 잔여 계정 수가 바뀐다. 
MVC 패턴으로 찢을 때 다시 최솟값 체크하는 부분을 수정해야 한다.*/
function checkUser(here, num) {
  let checkedUser = document.getElementsByClassName("checkUser");
  console.log('check user : '+checkedUser);
  
  let sum = 0;
  let count = checkedUser.length;
  let temp=[];
  for(let i=0; i < count; i++ ){
    if( checkedUser[i].checked == true ){
      sum += parseInt(checkedUser[i].value.length);
      temp.push(checkedUser[i].name);
      checkSubmit(num);
    }
  }
  comment_id_names = temp;
  checkedUser.value = sum;

  console.log(sum);

  let checkNum = document.querySelector("#checkNum");

  //MVC 패턴으로 찢을 때 다시 건드릴 부분
  let leftNum1 = 20 - ( postData[0].nPass +  postData[0].rPass + sum);
  let leftNum2 =  10 - ( postData[0].rPass + sum);
  let leftNum3 = 5 - sum;
  let leftNum = Math.min(leftNum1, leftNum2, leftNum3);

  console.log("최솟값 " + leftNum);

  if(leftNum >= 0) {
    checkNum.innerHTML = 'Selectable number of accounts : ' + leftNum;
  } else {
    alert("Can't select anymore");
    for(let i=0; i < count; i++ ){
      checkedUser[i].checked = false;
    }
    checkNum.innerHTML = 'Please select again';
  }
}

//checkSubmit() : 유저를 체크하면 submit 버튼이 생기는 함수
function checkSubmit(num){

  let comSub = document.querySelector(".comSub");
  comSub.innerHTML = '<input type="button" value="submit" class="check-submit" onclick="sendCheck('+num+')">';
}

//showCheck() : 서버 쪽에 데이터를 보내주고 새로고침하는 함수
function sendCheck(p_id) {

  console.log(p_id)
  console.log('arr length = '+comment_id_names);
  console.log(typeof(comment_id_names));
  console.log(comment_id_names);

  if(confirm("Did you check the user's nickname well?")){
      
    const sendData = {
      p_id : p_id,
      c_id : comment_id_names,
      chat : Math.random().toString(36).substr(2,11)
    }

    const strObject = JSON.stringify(sendData);
    
    console.log(strObject);

    var url = '/mypost';
    sendAjax(url, 'PUT', strObject);
    
    window.location.reload();
  } else {
    alert('You canceled it.');
  }
}

//showChatting() : 랜덤 생성한 문자열을 서버 쪽으로 보냄
function createChatting(chat){

  const chatData = {
    chat : chat
  }

  const strObject = JSON.stringify(chatData);
  
  console.log(strObject);

  var url = '/room/new';
  sendAjax(url, 'POST', strObject);
}

//showNick() : 새로고침 하고 난 뒤 comment를 누르면 고른 사람의 친구코드와 닉네임이 보이게 하는 함수
function showNick(result){

  console.log(result+", "+ arr);

  //모든 사람이 단 댓글을 순회
  for(let i = 0; i < result.length; i++){
    //checknum 값에 해당되는 nickname과 친구코드만 보여준다.
    if(arr = result[i].c_id && result[i].c_end == '1'){
      for(let j = 0; j< result[i].checkNum.length; j++){
        let onePersonCheckNum= result[i].checkNum[j];
        console.log(onePersonCheckNum);
        switch(onePersonCheckNum){
          case '1' :
            // 만일 onePersonCheckNum이 1이라면 그에 해당하는 nickname과 friendcode를 함수에 넣는다. 
            showNickMaker(result[i].nickname1, result[i].friendCode1);
            break;
          case '2' : 
            showNickMaker(result[i].nickname2, result[i].friendCode2);
            break;
          case '3' : 
            showNickMaker(result[i].nickname3, result[i].friendCode3);
            break;
          case '4' : 
            showNickMaker(result[i].nickname4, result[i].friendCode4); 
            break;
          case '5' : 
            showNickMaker(result[i].nickname5, result[i].friendCode5);
            break;
        }
      }
    }
  }  
}

//showNickMaker() : 그 사람의 nickname과 friendCode를 담아 view로 보여주는 함수
function showNickMaker(nickname, friendCode){
  console.log('showNickMaker : ' + nickname, friendCode );

  let checkNum = document.querySelector("#checkNum");

  let resultDiv = document.createElement("div");
  resultDiv.setAttribute("class", "checkResult");

  let onePersonInfo = '';
  onePersonInfo += 'nickname : ' + nickname + '</br> friendCode :' + friendCode+'</br>';
  
  //commentBox 안의 내용물을 변경
  resultDiv.innerHTML = onePersonInfo;

  checkNum.appendChild(resultDiv);
}


//hideComment() : 모든 댓글을 숨기고 보여주는 함수.
function hideComment(num) {
  let checkNum = document.querySelector("#checkNum");
  let commentBox = document.querySelector(".commentBody"+num);
  let arrowDown = document.getElementById("comment"+num);

  if(commentBox.style.display == 'block'){
      checkNum.style.display = 'none';
      commentBox.style.display = 'none';
      arrowDown.innerHTML = 'comment <i class="fa fa-sort-down"></i>';
  } else {
      checkNum.style.display = 'block';
      commentBox.style.display = 'block';
      arrowDown.innerHTML = 'comment <i class="fa fa-sort-up"></i>';
  }
}

//endPost() : 레이드 종료 버튼.
/* session에 있는 nickname1과 mypost에 떠있는 포스트 작성자의 nickname1이
같을 경우에만 end 버튼 활성화 */
function endPost(num) {

  let currentDiv = document.querySelector("#my-box");

  if(confirm('Are you sure you want to end the raid?')){

    const addData = { p_id : num}
    const strObject = JSON.stringify(addData);
    console.log(strObject);

    var url = '/mypost/end';
    sendAjax(url, 'POST', strObject);


    let endPage = document.createElement("div");
    endPage.setAttribute("class", "card-body endBody");

    endStr = '<p class="endStr">Nothing!</p>';

    endPage.innerHTML = endStr;

    currentDiv.appendChild(endPage);

    let resultDiv = document.querySelector("#result-box");
    currentDiv.removeChild(resultDiv);

  } else {
    alert('You canceled it');
  }
}

//endChat() : 채팅방 삭제하는 기능
function endChat(chat) {

  const chatData = { chat : chat }
  const strObject = JSON.stringify(chatData);
  console.log(strObject);

  var url = '/room/delete';
  sendAjax(url, 'POST', strObject);
}

//deleteComment() : 댓글 지우는 기능
/* session에 있는 nickname1과 mypost에 떠있는 포스트 작성자의 nickname1이
같을 경우에만 X 버튼 활성화 */
function deleteComment(c_id, p_id){
  var commentBox = document.querySelector(".commentBody"+p_id);
  var comment = document.querySelector(".comment"+c_id);
  if(confirm("Do you really want to erase it?")){

    const addData = { c_id : c_id }
    const strObject = JSON.stringify(addData);
    console.log(strObject);

    var url = '/mypost/mycomment';
    sendAjax(url, 'DELETE', strObject);

    commentBox.remove(comment);
  }

}