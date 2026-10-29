<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.DataFrame" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null)
    {
  %>
      <p style="color: red;"><%= errorMessage %></p>
  <%
    }
  %>
  <table>
    <%
      DataFrame patients = (DataFrame) request.getAttribute("patients");
      String[] columnNames = patients.getColumnNames();
      if (patients != null)
      {
    %>
    <tr>
        <%
        for(String columnName : columnNames)
        {
          %>
          <td><%=columnName%></td>
          <%
        }
        %>
    </tr>
          <%
        for (int i = 0; i < patients.getRowCount(); i++) 
        {
          %>
    <tr>
    <%
          for(String columnName : columnNames)
          {
            %>
            <td><%=patients.getValue(columnName, i)%></td>
            <%
          }
          %>
    </tr>
          <%
        }
      }
    %>
  </table>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
