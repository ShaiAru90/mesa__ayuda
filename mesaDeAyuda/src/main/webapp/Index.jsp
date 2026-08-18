<%@ page contentType="text/html;charset=UTF-8" %>
<%-- Redirige automáticamente al login --%>
<% response.sendRedirect(request.getContextPath() + "/login"); %>
