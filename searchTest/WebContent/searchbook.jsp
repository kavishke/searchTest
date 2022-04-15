
<%@page import="java.util.*;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Search Book</title>

</head>

<body>

<table align="center">

<%

List booklist=new ArrayList();

booklist=(ArrayList)request.getAttribute("booklist");

if(booklist!=null && booklist.size()>0 ){

%>

<h2 align="center">Book List</h2>

<tr>

<th>ISBN Code</th>

<th>Book Name</th>

<th>Category</th>

</tr>

<%

for(int i=0;i<booklist.size();i++){

List book=(List)booklist.get(i);

%>

<tr>

<td><%=book.get(1) %></td>

<td><%=book.get(2) %></td>

<td><%=book.get(3) %></td>

</tr>

<%

}

}else{

%>

Not  Available  Any Book Details

<%}%>

</table>

</body>
</html>