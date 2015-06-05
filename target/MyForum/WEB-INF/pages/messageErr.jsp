<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
  <c:if test="${!empty messages}">
    <c:forEach items="${messages}" var="message">
      <table class="table table-hover">
        <td class="col-md-10">
          <blockquote>
            <footer class="text-left">From ${message.author}</footer>
            <p class="text-left">${message.message}</p>
          </blockquote>
        </td>
        <sec:authorize access="hasRole('admin') or hasRole('mod')">
          <td class="col-md-2" style="vertical-align: middle"><a href="javascript:MessageUtil.deleteMessage(${message.id})"}>
            <button class="btn btn-danger">Delete</button></a></td>
        </sec:authorize>
      </table>
    </c:forEach>
  </c:if>
  <form:form method="post" commandName="message">
    <table>
      <div class="form-group">
        <tr>
          <td class="col-md-2"><form:label for="enterMess" path="message">New message:</form:label></td>
          <td class="col-md-6"><form:textarea class="form-control" rows="3" id="enterMess" placeholder="Enter message" path="message"/></td>
          <td class="text-left col-md-3 error">Message can not be empty</td>
          <td class="col-md-3"></td>
        </tr>
      </div>
      <tr>
        <td class="col-md-2"></td>
        <td class="col-md-4"><input class="btn btn-success btn-block" type="submit" value="Add message"/></td>
      </tr>
    </table>
  </form:form>
  <div class="back-link-container">

    <a href="/redirectBack"><button class="btn btn-info">Back</button></a>

  </div>
</t:template>

