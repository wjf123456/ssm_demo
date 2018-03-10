<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Sign In</h1>
<form action="signIn" method="post">

    <input name="username"/> <br>
    <input name="password"/> <br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Sign In"/>

</form>
