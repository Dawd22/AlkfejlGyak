
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<jsp:useBean id="jedi" class="org.example.model.Jedi" scope="request"/>
<div class="container">
    <form action="${pageContext.request.contextPath}/JediController" method="post">
        <input type="hidden" name="id" value="${jedi.id}" />
        <label for="name">Name</label>
<input required name="name" type="text" id="name" value="${jedi.name}">
<label for="gender">Gender</label>
<input required name="gender" type="text" id="gender" value="${jedi.gender}">
<label for="rank">Rank</label>
<input required name="rank" type="text" id="rank" value="${jedi.rank}">
<label for="councilmember">Councilmember</label>
<input required name="councilmember" type="text" id="councilmember" value="${jedi.councilmember}">
<button id="submit" type="submit">Hozzáadás</button>
</form>
</div>
</body>
</html>