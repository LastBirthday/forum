<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>

    <form:form method="post" commandName="updatetheme">
        <table>
            <div class="form-group">
                <tr>
                    <td class="col-md-1"><form:label for="enterName" path="name">Name</form:label></td>
                    <td class="col-md-4"><form:input class="form-control" id="enterName" placeholder="Enter name" path="name"/></td>
                    <td class="text-left col-md-7"><form:errors cssClass="error" path="name"></form:errors></td>
                </tr>
            </div>
            <tr>
                <td class="col-md-1"></td>
                <td class="col-md-4"><input class="btn btn-success btn-block" type="submit" value="Update theme"/></td>
            </tr>
        </table>
    </form:form>
    <div class="back-link-container">
        <a href="/"><button class="btn btn-info">Back</button></a>
    </div>
</t:template>
