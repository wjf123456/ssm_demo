<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>

<html>
  <head>
    <title></title>
    <link rel="static/css/main.css" type="text/css" />
  </head>
  <body>
    <div>
      <t:insertAttribute name="header" />
    </div>
    <div>
      <t:insertAttribute name="body" />
    </div>
    <div>
      <t:insertAttribute name="footer" />
    </div>
  </body>
</html>
