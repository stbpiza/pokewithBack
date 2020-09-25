
//Post 더미 데이터
const postData = [
  { p_id : 1,
    nickname1 : 'rayra',
    u_like : '20',
    u_hate : '10',
    pokemon : '이상해씨',
    raidLevel : 1,
    startTime : '03:30',
    endTime : '04:15',
    minLevel : 20,
    nPass : 3,
    rPass : 0,
    p_end : 0},

  { p_id : 2,
    nickname1 : 'rora',
    u_like : '24',
    u_hate : '10',
    pokemon : '리자몽',
    raidLevel : 3,
    startTime : '03:30',
    endTime : '04:15',
    minLevel : 30,
    nPass : 0,
    rPass : 1,
    p_end : 0 },

  { p_id : 3,
    nickname1 : '야돈',
    u_like : '11',
    u_hate : '10',
    pokemon : '리자몽',
    raidLevel : 5,
    startTime : '03:30',
    endTime : '04:15',
    minLevel : 40,
    nPass : 5,
    rPass : 0,
    p_end : 1 },

  { p_id : 4,
    nickname1 : 'maylin',
    u_like : '21',
    u_hate : '15',
    pokemon : '히드런',
    raidLevel : 'mega',
    startTime : '03:30',
    endTime : '04:15',
    minLevel : 40,
    nPass : 3,
    rPass : 1,
    p_end : 1 },
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
    "u_like": "3",
    "u_hate": "0"
  },
  { "c_id": "3",
    "p_id": "1",
    "userId": "2649416911892763",
    "checkNum": "134",
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
    "u_like":"3",
    "u_hate":"0"
  },
]

//밖에서 ajax 코드를 빼고
//response 값을 받아서
/*
app
ajax data 
const result = response

view.allPostHtml(result, n)
*/


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


//allPostHtml() : 전체/필터 게시글 출력 함수
function allPostHtml(requiredData, n){

  let num = requiredData[n].p_id;
  console.log(num);
    
  let startDiv = document.createElement("div");
  startDiv.setAttribute("id", "result-box");
  startDiv.setAttribute("class", "card shadow mb-4");
  
  if(requiredData[n].p_end == "2"){
    startDiv.setAttribute("class", "ending card shadow mb-4");
  } else if (requiredData[n].p_end == "1") {
    startDiv.setAttribute("class", "raiding card shadow mb-4");
  }

  let nickDiv = document.createElement("div");
  nickDiv.setAttribute("class", "card-header py-3 d-flex flex-row align-items-center justify-content-between");

  let nickName = '';
  nickName += '<p class="m-0 text-gray"><b>'+requiredData[n].nickname1
              + '</b><i class="fa fa-thumbs-up updown" aria-hidden="true" style="font-size:10px"></i>' + requiredData[n].u_like
              + ' <i class="fa fa-thumbs-down" aria-hidden="true" style="font-size:10px"></i>' + requiredData[n].u_hate + '</p>'
  nickDiv.innerHTML = nickName;

  startDiv.appendChild(nickDiv);

  let cardDiv = document.createElement("div");
  cardDiv.setAttribute("class", "card-body cardBody"+num);
  startDiv.appendChild(cardDiv);

  if(requiredData[n].raidLevel == '1') {
      str = "<img src = 'img/1.PNG' style='width:50px'>"
  } else if(requiredData[n].raidLevel == '3') {
      str = "<img src = 'img/3.PNG' style='width:50px'>"
  } else if(requiredData[n].raidLevel == '5') {
      str = "<img src = 'img/5.PNG' style='width:50px'>"
  } else {
      str = "<img src = 'img/mega.PNG' style='width:50px'>"
  }
  
  cardDiv.innerHTML += '<input type="hidden" id="postId" name="postId" value="'+ requiredData[n].p_id +'">';
  cardDiv.innerHTML += '<p> Pokemon : ' + requiredData[n].pokemon+'</p>';
  cardDiv.innerHTML += '<p> Level of Raid : ' + str + '</p>';
  cardDiv.innerHTML += '<p> Start Time of Raid : ' + requiredData[n].startTime+'</p>';
  cardDiv.innerHTML += '<p> End Time of Raid : ' + requiredData[n].endTime+'</p>';
  cardDiv.innerHTML += '<p> Required Player Level : ' + requiredData[n].minLevel+'</p>';
  cardDiv.innerHTML += '<p> Premium Pass : <img src="img/3_premium.png" style="width:60px"> / <img src="img/2_premium.png" style="width:50px">' + requiredData[n].nPass+'</p>';
  cardDiv.innerHTML += '<p> Remote Pass : <img src="img/1_remote.png" style="width:60px"> ' + requiredData[n].rPass + '</p>';


  if(requiredData[n].p_end =="0"){
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
  }
  
  let postbox = document.querySelector("#post-box");
  postbox.appendChild(startDiv);

}


//allPost() : 모든 포스트/필터된 포스트를 출력하기 전 거치는 ajax
function allPostAjax(selectOption) {
  if(selectOption == 'three'){
    removeAllPost();
    
    var url = '/index/three';

    sendAjax(url, 'GET', null, function (res) {
      console.log(res.response);
      var result = JSON.parse(res.response);
      for (let i = 0; i < result.length; i++) {
        allPostHtml(result, i);
      }
    });
  }else if(selectOption == 'five'){
    removeAllPost();
    
    var url = '/index/five';

    sendAjax(url, 'GET', null, function (res) {
      console.log(res.response);
      var result = JSON.parse(res.response);
      for (let i = 0; i < result.length; i++) {
        allPostHtml(result, i);
      }
    });
  }else if(selectOption == 'mega'){
    removeAllPost();
    
    var url = '/index/mega';

    sendAjax(url, 'GET', null, function (res) {
      console.log(res.response);
      var result = JSON.parse(res.response);
      for (let i = 0; i < result.length; i++) {
        allPostHtml(result, i);
      }
    });
  }else{
    removeAllPost();
    var url = '/index';
  
    sendAjax(url, 'GET', null, function (res) {
      console.log(res.response);
      var result = JSON.parse(res.response);
      for (let i = 0; i < result.length; i++) {
        allPostHtml(result, i);
      }
    });
  }

  //if(selectOption == 'total'){
  //  for(let i = 0; i < postData.length; i++){
  //    allPostHtml(postData, i);
  //  }
  //}
};


//removeAllPost() : 필터링할 때 기존에 있던 포스트들 다 숨기는 기능
function removeAllPost(){
  let card = document.querySelectorAll(".card");
  for(let i = 0; i < card.length; i++){
    card[i].style.display = 'none';
  }
}


//윈도우가 로드될 때 allPost()를 실행시키기 위한 함수
if (window.addEventListener)
        window.addEventListener("load", allPostAjax, false);
else if (window.attachEvent)
      window.attachEvent("onload", allPostAjax);
else window.onload = allPostAjax;


//post 버튼에 onClick 함수 binding
let postBtn = document.getElementById('post-btn');
postBtn.addEventListener('click',createPost);


//createPost() : 새로운 post 탬플릿 생성 함수
function createPost(){

  let modalDiv = document.querySelector("#exampleModalCenter");

  let modalBox = document.createElement("div");
  modalBox.setAttribute("class", "modal-dialog modal-dialog-centered");
  modalBox.setAttribute("role", "document");
  modalDiv.appendChild(modalBox);

  let modalContent = document.createElement("div");
  modalContent.setAttribute("class", "modal-content");
  modalBox.appendChild(modalContent);

  let modalHeader = document.createElement("div");
  modalHeader.setAttribute("class", "modal-header");
  modalContent.appendChild( modalHeader);

  modalHeaderStr = '';
  modalHeaderStr += "<h5 class='modal-title id='exampleModalLongTitle'>New Post</h5>";
  modalHeaderStr += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
  modalHeader.innerHTML =  modalHeaderStr;

  let modalBody = document.createElement("div");
  modalBody.setAttribute("class", "modal-body");
  modalContent.appendChild(modalBody);

  let modalBodyStr = '';
  modalBodyStr += "<p>pokemon : <input type='text' id='pokemon' class='form-control' name='pokemon' placeholder='pokemon of number'></p>";
  modalBodyStr += `<p>Level of Raid : <select name="searchYear" id="raidLevel" class='form-control' name="raidLevel">
                      <option value="1">1</option>
                      <option value="3">3</option>
                      <option value="5">5</option>
                      <option value="mega">mega</option>
                      </select></p>`;
  modalBodyStr += "<p>Start Time of Raid : <input type='time' id='startTime' class='form-control' name='startTime'></p>";
  modalBodyStr += "<p>End Time of Raid : <input type='time' id='endTime' class='form-control' name='endTime'></p>";
  modalBodyStr += "<p>Minimum Level of Raid : <input type='number' id='minLevel' class='form-control' name='minLevel' value='minLevel'></p>";
  modalBodyStr += "<p>Premium Pass : <input type='number' id='nPass' class='form-control' name='nPass' value='nPass'></p>";
  modalBodyStr += "<p>Remote Pass : <input type='number' id='rPass' class='form-control' name='rPass' value='rPass'></p>";
  modalBody.innerHTML = modalBodyStr;

  let modalFooter = document.createElement("div");
  modalFooter.setAttribute("class", "modal-footer");
  modalContent.appendChild(modalFooter);

  let modalSubmit = document.createElement("button");
  modalSubmit.setAttribute("id", "submit-btn");
  modalSubmit.setAttribute("class", "border-0 btn-info comment-btn");
  modalSubmit.setAttribute("value", "submit");
  modalSubmit.addEventListener("click", addPost);

  let modalSubmitText = document.createTextNode('Submit');
  modalSubmit.appendChild(modalSubmitText);
  modalFooter.appendChild(modalSubmit);

  let modalClose = document.createElement("button");
  modalClose.setAttribute("class", "border-0 btn-secondary comment-btn");
  modalClose.setAttribute("data-dismiss", "modal");

  let modalCloseText = document.createTextNode('Close');
  modalClose.appendChild(modalCloseText);
  modalFooter.appendChild(modalClose);
}


//addPost() : 기존 게시글에 새 게시글의 데이터를 더하는 함수
/* 포스트를 생성하게 되면 바로 mypost 화면으로 리다이렉트 된다. */
function addPost(){
  alert("포스트가 성공적으로 등록되었습니다.");
  const addData = {
    pokemon : document.getElementById("pokemon").value,
    raidLevel : document.getElementById("raidLevel").value,
    startTime : document.getElementById("startTime").value,
    endTime : document.getElementById("endTime").value,
    minLevel : document.getElementById("minLevel").value,
    nPass : document.getElementById("nPass").value,
    rPass : document.getElementById("rPass").value,
  }

  const strObject = JSON.stringify(addData);

  var url = '/index';
  sendAjax(url, 'POST', strObject);

  // 밑 부분은 앞으로 서버랑 연결할 때 필요 없는 부분
  // postData.push(addData);

  // console.log(postData.push(addData));
  // console.log(postData);

  // allPostHtml(addData, postData.length-1);
}


//allComment() : 특정 포스트의 모든 댓글을 출력하는 함수
function allComment(resultData, num) {

  let currentDiv = document.querySelector(".cardBody"+num);

  let startDiv = document.createElement("div");
  startDiv.setAttribute("class", "card-body commentBody"+num);

  for(let i = 0; i<resultData.length; i++){
    if(num == resultData[i].p_id){
      let commentId = resultData[i].c_id;
      let commentW = document.createElement("div");
      commentW.setAttribute("class", "commentWrap comment"+commentId);

      let commentText = '';
      commentText += '<div> <p class="commentP">' + resultData[i].nickname1;
      commentText += '</p> <div style="float: right; width:60%;"><img src="img/1_remote.png" style="width:60px; margin-right:20px">' + resultData[i].checkNum.length + "</div></div>";
      commentW.innerHTML = commentText;

      startDiv.appendChild(commentW);
    }
  }

  let commentForm = document.createElement("div");
  commentForm.setAttribute('class', 'comment-wrap wrap'+ num);

  var str = "";
  str+="<span class='checked'><input type='checkbox' name='nickname"+num+"' value='1'> 1. nick1</span>";
  str+="<span class='checked'><input type='checkbox' name='nickname"+num+"' value='2'> 2. nick2</span>";
  str+="<span class='checked'><input type='checkbox' name='nickname"+num+"' value='3'> 3. nick3</span>";
  str+="<span class='checked'><input type='checkbox' name='nickname"+num+"' value='4'> 4. nick4</span>";
  str+="<span class='checked'><input type='checkbox' name='nickname"+num+"' value='5'> 5. nick5 </span></br>";
  
  commentForm.innerHTML = str;

  commentSubmit = document.createElement('input');
  commentSubmit.setAttribute('class', 'comment-submit');
  commentSubmit.setAttribute('type', 'submit');
  commentSubmit.setAttribute('value', 'submit');
  commentSubmit.setAttribute('onclick', 'commitComment('+num+')');

  commentForm.appendChild(commentSubmit);

  startDiv.appendChild(commentForm);
  
  currentDiv.appendChild(startDiv);

  var arrowUp = document.getElementById("comment"+num);
  arrowUp.setAttribute('onclick', 'hideComment('+num+')');
  
  arrowUp.innerHTML = 'comment <i class="fa fa-sort-up"></i>';

  var commentBox = document.querySelector(".commentBody"+num);
  commentBox.style.display = 'block';

}


//allCommentView() : 특정 포스트의 모든 댓글을 출력하기 전 거치는 ajax
function allCommentAjax(num) {
  var url = '/comment/'+num;

  const postId = num;
  
  sendAjax(url, 'GET', null, function(res){
    console.log(res);
    var result = JSON.parse(res.response);
    
    allComment(result, num);
  });
};


//hideComment() : 특정 포스트의 모든 댓글을 숨기고 보여주는 함수.
function hideComment(num) {
  var commentBox = document.querySelector(".commentBody"+num);
  var arrowDown = document.getElementById("comment"+num);

  if(commentBox.style.display == 'block'){
    commentBox.style.display = 'none';
    arrowDown.innerHTML = 'comment <i class="fa fa-sort-down"></i>';
  } else {
    commentBox.style.display = 'block';
    arrowDown.innerHTML = 'comment <i class="fa fa-sort-up"></i>';
  }
}


//commitComment() : 특정 포스트에 댓글 생성
/* 댓글을 생성하게 되면 바로 mypost 화면으로 리다이렉트 된다. */
function commitComment(num) {
  
  var check_count = document.getElementsByName("nickname"+num).length;

  console.log(check_count);
  
  var sum = 0; 
  for (var i=0; i<check_count; i++) {
      if (document.getElementsByName("nickname"+num)[i].checked == true) {
          var checkedStr = document.getElementsByName("nickname"+num)[i].value;
          sum += checkedStr.length;     
      }
  }

  const commitData = {
    userId : '1283902841946955',
    p_id : num,
    checkNum : sum
  }

  const strObject = JSON.stringify(commitData);

  var url = '/comment';
  sendAjax(url, 'POST', strObject);

  alert("댓글이 성공적으로 등록되었습니다.");
}


//makeFilteringButton() : 메인 페이지에 filtering할 select 버튼 생성
function makeFilteringButton(){
  let filterBox = document.querySelector(".filter");

  let select = document.createElement("select");
  select.setAttribute("id", "filterSelect");
  select.setAttribute("onchange", "filterOptionCheck()");
  filterBox.appendChild(select);

  let selectTotal = document.createElement("option");
  selectTotal.setAttribute("value", "total");
  let selectTotalText = document.createTextNode("total");
  selectTotal.appendChild(selectTotalText);

  let selectOne = document.createElement("option");
  selectOne.setAttribute("value", "three");
  let selectOneText = document.createTextNode("1~3");
  selectOne.appendChild(selectOneText);

  let selectTwo = document.createElement("option");
  selectTwo.setAttribute("value", "five");
  let selectTwoText = document.createTextNode("5");
  selectTwo.appendChild(selectTwoText);

  let selectThree = document.createElement("option");
  selectThree.setAttribute("value", "mega");
  let selectThreeText = document.createTextNode("mega");
  selectThree.appendChild(selectThreeText);

  document.getElementById("filterSelect").appendChild(selectTotal);
  document.getElementById("filterSelect").appendChild(selectOne);
  document.getElementById("filterSelect").appendChild(selectTwo);
  document.getElementById("filterSelect").appendChild(selectThree);
}


//filterOptionCheck() : 필터링할 value 대로 ajax를 걸어주는 함수
function filterOptionCheck(){
  var selectOption = document.getElementById("filterSelect").value;
  if(selectOption === 'total'){
    return allPostAjax(selectOption);
  }else if(selectOption === 'three'){
    return allPostAjax(selectOption);
  }else if(selectOption === 'five'){
    return allPostAjax(selectOption);
  }else{
    return allPostAjax(selectOption);
  }
}


//윈도우가 로드될 때 makeFilteringButton()를 실행시키기 위한 함수
if (window.addEventListener)
        window.addEventListener("load", makeFilteringButton, false);
else if (window.attachEvent)
      window.attachEvent("onload", makeFilteringButton);
else window.onload = makeFilteringButton;


