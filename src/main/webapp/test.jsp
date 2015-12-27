<%
//response.getWriter().print("callback({\"name\":\"test\"});");
//response.getWriter().print("var datatest = {\"name\":\"test\"};");
String funtion_JS = request.getParameter("jsoncallback");
response.getWriter().print(funtion_JS+"({\"name\":\"test\"});");
response.getWriter().flush();
%>