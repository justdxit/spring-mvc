<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>
<script>
    alert("글 작성 완료")
    location.href="${root}board/main?board_info_idx=${writeContentBean.content_board_idx}"
<%--    ${board_info_idx} 값이 넘어오지 않는 문제 있음--%>
</script>
