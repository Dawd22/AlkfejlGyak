<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<jsp:include page="/JediController" />

<table>
    <tr>
        <th>
            Name
        </th>
        <th>
            Gender
        </th>
        <th>
            Rank
        </th>
        <th>
            Councilmember
        </th>
    </tr>

    <c:forEach var="item" items="${requestScope.jedik}">
    <tr>
        <td>
                ${item.name}
        </td>
        <td>
                ${item.gender}
        </td>
        <td>
                ${item.rank}
        </td>
        <td>
                ${item.councilmember}
        </td>
        <td>
            <a href="../DeleteJediController?Id=${item.id}"> <button> Törlés </button> </a>
            <a href="../UpdateJediController?Id=${item.id}"> <button> Frissítés </button> </a>
        </td>
    </tr>
    </c:forEach>
</table>

<a href="add-jedi.jsp"><button>Hozzáadás</button></a>

</body>
</html>