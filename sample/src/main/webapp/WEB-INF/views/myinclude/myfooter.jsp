<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <c:set> 태그 이용해 pageContext 내장 객체의 컨텍스트 이름을 변수 contextPath에 미리 설정 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

</div>
    <!-- /#wrapper -->


    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <!-- 기본 설정되어 있는데, 문서에서는 아래처럼 주석처리 함 -->
<%--    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>
--%>
</body>

</html>
