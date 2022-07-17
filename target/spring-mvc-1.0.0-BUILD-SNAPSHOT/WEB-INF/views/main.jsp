<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Moodeon</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 게시판 미리보기 부분 -->
<div class="container" style="margin-top:100px">
    <div class="row">
        <c:forEach var="sub_list" items="${list}" varStatus="status">
            <div class="col-lg-6" style="margin-top:20px">
                <div class="card shadow">
                    <div class="card-body">
                        <h4 class="card-title">${board_list[status.index].board_info_name}</h4>
                        <table class="table table-hover" id="board_list">
                            <thead>
                            <tr>
<%--                                <th class="text-center w-25">글번호</th>--%>
                                <th>제목</th>
                                <th class="text-center w-25 d-none d-xl-table-cell">작성날짜</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="obj" items="${sub_list}">
                                <tr>
<%--                                    <td class="text-center">${obj.content_idx}</td>--%>
                                    <th>
                                        <a href='${root }board/read?board_info_idx=${board_list[status.index].board_info_idx}&content_idx=${obj.content_idx}&page=1'>${obj.content_subject}</a>
                                    </th>
                                    <td class="text-center d-none d-xl-table-cell">${obj.content_date}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <a href="${root }board/main?board_info_idx=${board_list[status.index].board_info_idx}"
                           class="btn btn-primary">더보기</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>