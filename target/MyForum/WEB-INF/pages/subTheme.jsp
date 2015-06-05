<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
  <c:if test="${!empty subthemes}">
    <table class="table table-bordered table-hover">
      <tr>
        <th class="text-center col-md-6" style="vertical-align: middle">Name</th>
        <th class="text-center col-md-1" style="vertical-align: middle">Author</th>
        <th class="text-center col-md-1" style="vertical-align: middle">Messages in</th>
        <th class="text-center col-md-2" style="vertical-align: middle">Last message from</th>
        <sec:authorize access="hasRole('admin') or hasRole('mod')">
          <th>&nbsp;</th>
        </sec:authorize>
      </tr>
      <c:forEach items="${subthemes}" var="subtheme">
        <tr>
          <td><a href="/message/${subtheme.id}"><h1>${subtheme.name}</h1></a></td>
          <td style="vertical-align: middle">${subtheme.author}</td>
          <td style="vertical-align: middle">${subtheme.messagesCount}</td>
          <td style="vertical-align: middle">${subtheme.lastMessage}</td>
          <sec:authorize access="hasRole('admin') or hasRole('mod')">
            <td style="vertical-align: middle"><a href="javascript:SubThemeUtil.deleteSubTheme(${subtheme.id})"}>
              <button type="button" class="btn btn-danger">Delete</button></a>&nbsp;&nbsp;
              <a href="updateSubTheme/${subtheme.id}"><button type="button" class="btn btn-info">Update</button></a></td>
          </sec:authorize>
        </tr>
      </c:forEach>
    </table>
  </c:if>
  <sec:authorize access="isAuthenticated()">
    <a href="addSubTheme"><button class="btn btn-primary btn-block"><h2>Add Topic</h2></button></a>
  </sec:authorize>
  <div class="back-link-container">
    <a href="/"><button class="btn btn-info">Back</button></a>
  </div>
</t:template>