<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check }">
	<script type="text/javascript">
		alert('뉴스 삭제가 완료되었습니다.');
		location.href ='list.do';
	</script>
</c:if>
<c:if test="${!check }">
	<script type="text/javascript">
		alert('비밀번호가 불일치합니다.');
		history.go(-1);
	</script>
</c:if>