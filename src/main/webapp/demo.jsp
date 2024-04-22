<%--
  Created by IntelliJ IDEA.
  User: Tài
  Date: 20/04/2024
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${cars}" var="car">

                <h2 ><a href="car-single.jsp">${car.carName}</a></h2>
                <div >
                    <span>${car.carType}</span>
                    <p> ${car.rentalPrice} <span>/day</span></p>
                </div>


</c:forEach>
</body>
</html>
