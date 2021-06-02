<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%@ include file="../myinclude/myheader.jsp" %> 

<<<<<<< HEAD
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12"><h3 class="page-header">Board - Modify</h3></div><!-- /.col-lg-12 -->
    </div><!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>게시글 수정-삭제</h4></div><!-- /.panel-heading -->
                <div class="panel-body">
                
<form id="frmModify" role="form" method="post" >
    <div class="form-group">
        <label>글번호</label>
        <input class="form-control" value='<c:out value="${board.bno}"/>'
               name = "bno" readonly="readonly"/>
    </div>
    
    <div class="form-group">
        <label>글제목</label>
        <input class="form-control" value='<c:out value="${board.btitle}"/>' name="btitle" />
    </div>

    <div class="form-group">
        <label>글내용</label>
        <!-- <textarea>와 </textarea>는 한 줄에 작성되어야 데이터베이스 저장 시에 필요 없는 공백이 포함되지 않음 -->
        <textarea class="form-control" rows="3" 
                  name="bcontent"><c:out value="${board.bcontent}"/></textarea>
    </div>
    
    <div class="form-group">
        <label>작성자</label>
        <input class="form-control" name="bwriter" value='<c:out value="${board.bwriter}"/>'
               readonly="readonly"/>
    </div>
    
    <div class="form-group">
        <label>최종수정일</label> [등록일시: <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
                                                         value="${board.bregDate}"/>]
        <input class="form-control" name="bmodDate"
               value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.bmodDate}"/>'
               disabled="disabled" />
    </div>

    <button type="button" id="btnModify" data-oper="modify" class="btn btn-default">수정</button>
    <button type="button" id="btnRemove" data-oper="remove" class="btn btn-danger">삭제</button>
    <button type="button" id="btnList" data-oper="list" class="btn btn-info">취소</button>
    
    <input type='hidden' name='pageNum' value='${myBoardPagingDTO.pageNum}'> 
    <input type='hidden' name='rowAmountPerPage' value='${myBoardPagingDTO.rowAmountPerPage}'>

</form>

=======
<div class="row">
    <div class="col-lg-12">
    	<h3 class="page-header">Board - Modify</h3>
    </div><!-- /.col-lg-12 -->
</div><!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
        
        	<div class="panel-heading"><h4>게시글 수정-삭제</h4>
        	</div><!-- /.panel-heading -->
           	<div class="panel-body">
                
				<form id="frmModify" role="form" method="post" >
				    <div class="form-group">
				        <label>글번호</label>
				        <input class="form-control" value='<c:out value="${board.bno}"/>'
				               name = "bno" readonly="readonly"/>
				    </div>
				    
				    <div class="form-group">
				        <label>글제목</label>
				        <input class="form-control" value='<c:out value="${board.btitle}"/>' name="btitle" />
				    </div>
				
				    <div class="form-group">
				        <label>글내용</label>
				        <!-- <textarea>와 </textarea>는 한 줄에 작성되어야 데이터베이스 저장 시에 필요 없는 공백이 포함되지 않음 -->
				        <textarea class="form-control" rows="3" 
				                  name="bcontent"><c:out value="${board.bcontent}"/></textarea>
				    </div>
				    
				    <div class="form-group">
				        <label>작성자</label>
				        <input class="form-control" name="bwriter" value='<c:out value="${board.bwriter}"/>'
				               readonly="readonly"/>
				    </div>
				    
				    <div class="form-group">
				        <label>최종수정일</label> [등록일시: <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
				                                                         value="${board.bregDate}"/>]
				        <input class="form-control" name="bmodDate"
				               value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.bmodDate}"/>'
				               disabled="disabled" />
				    </div>
				
				    <button type="button" id="btnModify" data-oper="modify" class="btn btn-default">수정</button>
				    <button type="button" id="btnRemove" data-oper="remove" class="btn btn-danger">삭제</button>
				    <button type="button" id="btnList" data-oper="list" class="btn btn-info">취소</button>
				
					<%-- 추가 --%>
					<input type='hidden' name='pageNum' value='${myBoardPagingDTO.pageNum}'>
					<input type='hidden' name='rowAmountPerPage' value='${myBoardPagingDTO.rowAmountPerPage}'>
				 	<input type='hidden' name='scope' value='${myBoardPagingDTO.scope}'>
				 	<input type='hidden' name='keyword' value='${myBoardPagingDTO.keyword}'> 
				</form>
>>>>>>> 26dd8fef8f6f29ad2c5becec90b544226e48b820

                </div><!-- /.panel-body -->
            </div><!-- /.panel -->
        </div><!-- /.col-lg-12 -->
    </div><!-- /.row -->
</div><!-- /#page-wrapper -->


<script>

//form의 수정/삭제/목록보기 버튼에 따른 동작 제어
var frmModify = $("#frmModify");
 
$('button').on("click", function(e){ 

    //e.preventDefault();  //버튼 유형이 submit가 아니므로 설정할 필요 없음
    var operation = $(this).data("oper");  //각 버튼의 data-xxx 속성에 설정된 값을 저장
	
    alert("operation: "+ operation);

<<<<<<< HEAD
    if(operation == "modify"){  //게시물 수정 요청
        frmModify.attr("action", "${contextPath}/myboard/modify");
    
    } else if(operation == "remove"){  //게시물 삭제 요청
        frmModify.attr("action", "${contextPath}/myboard/delete");
    
    } else if(operation == "list"){  //게시물 목록 화면 요청
    	
    	var pageNumInput = $("input[name='pageNum']").clone();
		var rowAmountInput = $("input[name='rowAmountPerPage']").clone();

        frmModify.empty();
    	
        frmModify.append(pageNumInput);
		frmModify.append(rowAmountInput);
        frmModify.attr("action","${contextPath}/myboard/list").attr("method","get");
 
=======
    if(operation == "modify"){ //게시물 수정 요청
    	 frmModify.attr("action", "${contextPath}/myboard/modify").attr("method","post");
    } else if(operation == "remove"){ //게시물 삭제 요청
    	 frmModify.attr("action", "${contextPath}/myboard/delete").attr("method","post");//bdelFlag만업데이트
    	 //frmModify.attr("action", "${contextPath}/myboard/remove").attr("method","post"); //삭제됨
    } else if(operation == "list"){  //게시물 목록 화면 요청
    	
    	var pageNumInput = $("input[name='pageNum']").clone();
    	var rowAmountInput = $("input[name='rowAmountPerPage']").clone();
    	var scopeInput = $("input[name='scope']").clone();
    	var keywordInput = $("input[name='keyword']").clone(); 

    	frmModify.empty(); //form의 모든 input을 삭제

        frmModify.attr("action","${contextPath}/myboard/list").attr("method","get");
        
        frmModify.append(pageNumInput);
        frmModify.append(rowAmountInput);
        frmModify.append(scopeInput);
        frmModify.append(keywordInput); 
>>>>>>> 26dd8fef8f6f29ad2c5becec90b544226e48b820
    }
    
    frmModify.submit() ; //요청 전송

 });

</script>



<%@ include file="../myinclude/myfooter.jsp" %> 
