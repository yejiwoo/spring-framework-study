/**
 * myreply.js: 댓글/답글 데이터 처리용 Ajax Closure Module */
alert("댓글처리 클로저 모듈 실행됨======================"); 

/**
 * myreply.js: 댓글/답글 데이터 처리용 Ajax Closure Module
 */
var myCommentClsr = ( function() {

 //브라우저의 상세 페이지에서 Javascript에 의해 호출 시 전달받는 매개변수 설명
 //bnoAndPage: 서버로 전달할 값,
 //callback: 서버 처리 성공 시 브라우저에 의해 처리되는 함수
 //error: 서버 처리 실패 시 브라우저에 의해 처리되는 함수
 //댓글 목록(페이징) - ajax() 함수 사용
 function getCmtList(pagingParams, callback, error) {
	 var bno = pagingParams.bno;
	 var page = pagingParams.page || 1;
	
	 console.log("getCmtList()가 전달받은 bno: " + bno) ;
	 console.log("getCmtList()가 전달받은 page: " + page) ;
	 console.log("getCmtList()함수의 ajax 처리 시작....................");
	 //댓글 목록 조회 컨트롤러의 매핑 URL: GET /replies/pages/{bno}/{page}
	 //$.ajax() 함수는, 자바스크립트 객체를 매개값으로 받아 처리
	 $.ajax({ type: 'get', //전송유형: get 방식
		 url: "/mypro00/replies/pages/" + bno + "/" + page, //컨트롤러 메소드 호출 URL
		 dataType: 'json', //서버로부터 받는 값들이 JSON 형식임(서버의 produces 속성에 대응)
		 //'json', 'xml' , 'text', 'html' ,'script' 값을 설정할 수 있음
		 //서버처리 성공 시 실행 함수, XHR (XML Http Request) 
		 //result 매개변수를 통해 서버로부터 전달받은 데이터가 callback 함수에 의해 처리됨
		 success : function(replyPagingCreator, status, xhr){
			 if(callback){ //callback 매개변수에 함수가 있으면 ,참
			 	callback(replyPagingCreator);
			 }
		 },
		 //서버처리 실패 시, 매개변수로 전달된 함수 실행, XHR (XML Http Request)
		 error : function(xhr, status, er){
			 if(error){ //error 매개변수에 함수가 있으면 ,참
			 	error(er);
			 }
		 }
	 }
	 ); //.ajax END
 }//getCmtList END

//위 함수를 아래처럼 짤 수도 있음
//댓글 목록(페이징) - getJSON() 함수 사용
// function getCmtList(bnoAndPage, callback, error) {
// var bno = bnoAndPage.bno;
// var page = bnoAndPage.page || 1;
//
// //$.getJSON() 함수는, 컨트롤러 메서드에 get 방식으로 요청하고, 결과를 JSON 객체를 받음
// //[형식] $.getJSON(url, [data,서버로 전달할 데이터], callback-function)
// //서버에서 오류발생 시, 처리할 함수가 지정되지 못하므로, fail() 함수로 별도로 지정해야 함
// $.getJSON( "/mypro00/replies/pages/" + bno + "/" + page ,
// function(replyPagingCreator) {
// if (callback) {
// callback(replyPagingCreator);
// }
// } //callback 처리함수 END
// ).fail(function(xhr, status, err) { //jQuery Ajax Chaining 메서드: fail 메서드 사용(ajax의 error를 대체)
// if (error) {
// error();
// }
// }
// );
// }//getCmtList END

//댓글 등록
 function registerCmt(comment, callback, error){
 var bno = comment.bno;
 console.log("registerCmt() 에 전달된 bno: " + bno);
 console.log("registerCmt() 함수의 comment 등록 ajax 처리 시작....................");
 //댓글 등록 컨트롤러의 매핑 URL: GET /replies/{bno}/new
 //data: 브라우저가 서버에게 보내는 데이터
 //contentType: 서버에게 보내는 데이터의 MIME타입 설정, 컨트롤러의 consumes 속성에 대응
 $.ajax( { type: "post",
 url: "/mypro00/replies/" + bno + "/new",
 data: JSON.stringify(comment),
 contentType : "application/json; charset=utf-8",
 success : function(result, status, xhr){
 if(callback){
 callback(result);
 }
 },
 error : function(xhr, status, er){ 
 if(error){
 error(er);
 }
 }
 }
 );//.ajax END
 }//registerCmt 함수 END
 //답글 등록:
 function registerReply(reply, callback, error){
 var bno = reply.bno;
 var prno = reply.prno;
 console.log("registerReply() 전달받은 bno: " + bno);
 console.log("registerReply() 전달받은 prno: " + prno);
 console.log("registerReply()함수의 댓글에 대한 답글 등록 ajax 처리 시작..................");
 //답글 등록 컨트롤러의 매핑 URL: GET /replies/{bno}/{prno}/new
 //data: 브라우저가 서버에게 보내는 데이터
 //contentType: 서버에게 보내는 데이터의 MIME타입 설정, 컨트롤러의 consumes 속성에 대응
 $.ajax( { type: "post",
 url: "/mypro00j/replies/" + bno + "/" + prno + "/new",
 data: JSON.stringify(reply),
 contentType : "application/json; charset=utf-8",
 dataType : 'text',
 success : function(result, status, xhr){
 if(callback){
 callback(result);
 }
 },
 error : function(xhr, status, er){
 if(error){
 error(er);
 }
 }
 }
 );//.ajax END
 }//registerReply 함수 END 

//jQuery Ajax의 get() 메소드 사용: url 주소로 GET 방식으로 요청, 객체로 받음
 //get( url [, data] [, success(data, textStatus, jqXHR)] [, dataType] )
	 //댓글 조회
	 function getCmtReply(bnoAndRno, callback, error) {
		 var bno = bnoAndRno.bno;
		 var rno = bnoAndRno.rno;
		 console.log("getCmtReply() 전달받은 bno: " + bno);
		 console.log("getCmtReply() 전달받은 rno: " + rno);
		 console.log("getCmtReply() 함수의 특정 댓글에 조회 ajax 처리 시작......");
	
		 //댓글 조회 컨트롤러의 매핑 URL: GET /replies/{bno}/{rno}  .json 추가
		 $.get("/mypro00/replies/" + bno + "/" + rno + ".json", //json 형식으로 전송됨
			function(result) {
				 if (callback) {
				 	callback(result); 
				 }
			}
			).fail(function(xhr, status, err) {
				 if (error) {
				 	error();
				 }
			 }
		 );//fail END
	 }//get 함수 END

 //댓글 수정: 수정된 특정 댓글을 서버로 전송
	 function modifyCmtReply(comment, callback, error) {
	 var bno = comment.bno;
	 var rno = comment.rno;
	
	 console.log("modifyCmtReply() 전달받은 bno: " + bno);
	 console.log("modifyCmtReply() 전달받은 rno: " + rno);
	 console.log("modifyCmtReply() 함수의 특정 댓글 수정 ajax 처리 시작......");
	 //댓글 수정 컨트롤러의 매핑 URL: PUT 또는 PATCH /replies/{bno}/{rno}
	 $.ajax( { type: "put",
	 url : "/mypro00/replies/" + bno + "/" + rno,
	 data: JSON.stringify(comment),
	 contentType: "application/json; charset=utf-8",
	 dataType : "text", // 서버로부터 전달받는 데이터 유형을 설정(xml, text, html, json 등)
	 success: function(modifyResult, status, xhr) {
	 if (callback) {
	 callback(modifyResult);
	 }
	 },
	 error: function(xhr, status, er) {
	 if (error) {
	 error(er);
	 }
	 }
	 }
	 );//ajax END
	 }//update END

	//댓글 삭제(실제 삭제)
	 function removeCmtReply(comment, callback, error) {
		 var bno = comment.bno;
		 var rno = comment.rno;
		 var rwriter = comment.rwriter
		
		 console.log("removeCmtReply() 전달받은 bno: " + bno);
		 console.log("removeCmtReply() 전달받은 rno: " + rno);
		 console.log("removeCmtReply() 전달받은 rwrier: " + rwriter);
		 console.log("removeCmtReply() 함수의 댓글 삭제 ajax 처리 시작.......");
		 //댓글 삭제 컨트롤러의 매핑 URL: DELETE /replies/{bno}/{rno}
		 $.ajax( { type: "delete",
			 url : "/mypro00/replies/" + bno + "/" + rno,
			 data : JSON.stringify({bno: bno, rno: rno, rwriter: rwriter}),
			 contentType : "application/json; charset=utf-8",
			 success : function(removeResult, status, xhr) {
				 if (callback) {
				 	callback(removeResult);
				 }
			 },
			error: function(xhr, status, er) {
				 if (error) {
				 	error(er);
				 }
			}
		}
		); //ajax END
	}//removeCmtReply 함수 END


//날짜시간 표시형식 설정 (서버와 상관없음)
 //JSON 날짜시간을 그대로 표시하면 1594169682000 이렇게 표시됩니다.
 //일반적인 날짜 시간 표시 형식으로 표시,
 function showDatetime(datetimeValue) {
 var dateObj = new Date(datetimeValue);//전달된 댓글의 수정 날짜시간 값 저장
 var str ="";
 var yyyy = dateObj.getFullYear(); //YYYY
 var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
 var dd = dateObj.getDate();
 var hh = dateObj.getHours();
 var mi = dateObj.getMinutes();
 var ss = dateObj.getSeconds();
 str= [yyyy, '/',
 (mm > 9 ? '' : '0') + mm, '/',
 (dd > 9 ? '' : '0') + dd, ' ',
 (hh > 9 ? '' : '0') + hh, ':',
 (mi > 9 ? '' : '0') + mi, ':',
 (ss > 9 ? '' : '0') + ss].join(''); //배열값으로 하나의 문자열로 합침
 return str ;
 }

	return {
		getCmtList : getCmtList, //댓글 목록
		registerCmt : registerCmt, //게시물의 댓글 등록
		registerReply : registerReply, //댓글의 답글 등록
		getCmtReply : getCmtReply, //댓글-답글 조회
		modifyCmtReply : modifyCmtReply, //댓글-답글 수정
		removeCmtReply : removeCmtReply, //댓글-답글 삭제(실제 삭제)
		showDatetime : showDatetime //날짜시간 표시형식 지정

	};

})(); //즉시 실행 함수