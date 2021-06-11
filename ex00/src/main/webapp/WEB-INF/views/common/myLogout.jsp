<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Logout</title>
 <!-- Bootstrap Core CSS -->
 <link href="${contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 <!-- MetisMenu CSS -->
 <link href="${contextPath}/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
 <!-- Custom CSS -->
 <link href="${contextPath}/resources/dist/css/sb-admin-2.css" rel="stylesheet">
 <!-- Custom Fonts -->
 <link href="${contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
 <div class="container">
 <div class="row">
 <div class="col-md-4 col-md-offset-4">
 <div class="login-panel panel panel-default">
 <div class="panel-heading">
 <h2 class="panel-title">Really Logout ?</h2>
 </div>
 <div class="panel-body">
 <form role="form" action="${contextPath}/logout" method='post'>
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 <fieldset>
 <div>
 <button type='submit' class="btn btn-lg btn-success btn-block">Log Out</button>
 </div>
 </fieldset>
 </form>
 </div>
 </div>
 </div>
 </div>
 </div>
 <!-- jQuery -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <!-- Bootstrap Core JavaScript -->
 <script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
 <!-- Metis Menu Plugin JavaScript -->
 <script src="${contextPath}/resources/vendor/metisMenu/metisMenu.min.js"></script>
 <!-- Custom Theme JavaScript -->
 <script src="${contextPath}/resources/dist/js/sb-admin-2.js"></script>
</body>
</html>