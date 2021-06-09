<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <c:set> 태그 이용해 pageContext 내장 객체의 컨텍스트 이름을 변수 contextPath에 미리 설정 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<%@include file="../myinclude/myheader.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header">Board - List</h3>
                </div><!-- /.col-lg-12 -->
            </div><!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>게시글 목록</h4>
                        </div><!-- /.panel-heading -->
                        <div class="panel-body">
                            <table style="width=100%;text-align;center;" 
                            		class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th style="text-align;center;">번호</th>
                                        <th style="text-align;center;">제목</th>
                                        <th style="text-align;center;">작성자</th>
                                        <th style="text-align;center;">작성일</th>
                                        <th style="text-align;center;">수정일</th>
                                        <th style="text-align;center;">조회수</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${boardList }" var="board"> <%-- boardList: 컨트롤러에서 보낸 목록객체 이름 --%>
                                   		<c:if test="${board.bdelFlag ==1 }">
                                   			<tr style="background-color:Mocaasin; text-align:center">
                                   				<td><c:out value="${board.bno }"/></td>
                                   				<td colspan="5"><em>작성자에 의하여 삭제된 게시글입니다.</em></td> <%-- --%>
                                   			</tr>
                                   		</c:if>
                                   		<c:if test="${board.bdelFlag==0 }">
                                   			<tr>
                                   				<td><c:out value="${board.bno }"/></td>
                                   				<td style="text-align:left;"><c:out value="${board.btitle }"/></td>
                                   				<td><c:out value="${board.bwriter }"/></td>
                                   				<td><fmt:formatDate pattern="yyyy/MM/dd" value="${board.bregDate }"/><br>
                                   					<em>sysdate:</em> ${board.bregDate } <!--sysdate -->
                                   				</td>
                                   				<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.bmodDate }"/><br>
                                   					<em>systimestamp(0):</em> ${board.bmodDate } <!-- systimestamp(0) -->
                                   				</td>
                                   				<td>${board.bviewsCnt }</td>
                                   			</tr>
                                   		</c:if>
                                	</c:forEach>
                                </tbody>
                            </table><!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
        </div>
        <!-- /#page-wrapper -->

<%@include file="../myinclude/myfooter.jsp" %>