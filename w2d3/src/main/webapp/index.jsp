<!DOCTYPE html>
<html>
<head>
    <title>Include Example</title>

</head>
<body>
<h2>Welcome to the Include Example</h2>
<p>This is the index page.</p>
<%@ include file="header.jsp" %>
<a href="include">Include example</a>

<p>This is some content below the header.</p>
<jsp:include page="footer.jsp"/>
</body>
</html>
