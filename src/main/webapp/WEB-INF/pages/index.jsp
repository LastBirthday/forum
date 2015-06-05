<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
  <c:if test="${!empty themes}">
    <table class="table table-bordered table-hover">
      <tr>
        <th class="text-center col-md-6" style="vertical-align: middle">Theme name</th>
        <th class="text-center col-md-1" style="vertical-align: middle">Author</th>
        <th class="text-center col-md-1" style="vertical-align: middle">Topics in</th>
        <th class="text-center col-md-2" style="vertical-align: middle">Created</th>
        <sec:authorize access="hasRole('admin')">
          <th>&nbsp;</th>
        </sec:authorize>
      </tr>
      <c:forEach items="${themes}" var="theme">
        <tr>
          <td><a href="subTheme/${theme.id}"><h1>${theme.name}</h1></a></td>
          <td style="vertical-align: middle">${theme.author}</td>
          <td style="vertical-align: middle">${theme.themeCount}</td>
          <td style="vertical-align: middle">${theme.created}</td>
          <sec:authorize access="hasRole('admin')">
            <td style="vertical-align: middle"><a href="javascript:ThemeUtil.deleteTheme(${theme.id})"}>
              <button type="button" class="btn btn-danger">Delete</button></a>&nbsp;&nbsp;
                <a href="updateTheme/${theme.id}"><button type="button" class="btn btn-info">Update</button></a></td>
          </sec:authorize>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  <sec:authorize access="hasRole('admin')">
    <a href="addTheme"><button class="btn btn-primary btn-block"><h2>Add Theme</h2></button></a>
  </sec:authorize>
</t:template>