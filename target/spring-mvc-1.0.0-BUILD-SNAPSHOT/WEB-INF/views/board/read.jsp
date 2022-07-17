<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>

<!-- 상단 메뉴 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 글 내용 -->
<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card shadow">
                <div class="card-body">
                    <div class="form-group">
                        <label for="board_writer_name">작성자</label>
                        <input type="text" id="board_writer_name" name="board_writer_name" class="form-control"
                               value="${readContentBean.content_writer_name}" disabled="disabled"/>
                    </div>
                    <div class="form-group">
                        <label for="board_date">작성날짜</label>
                        <input type="text" id="board_date" name="board_date" class="form-control"
                               value="${readContentBean.content_date}" disabled="disabled"/>
                    </div>
                    <div class="form-group">
                        <label for="board_subject">제목</label>
                        <input type="text" id="board_subject" name="board_subject" class="form-control"
                               value="${readContentBean.content_subject}" disabled="disabled"/>
                    </div>
                    <div class="form-group">
                        <label for="board_content">내용</label>
                        <textarea id="board_content" name="board_content" class="form-control" rows="10"
                                  style="resize:none" disabled="disabled">${readContentBean.content_text}</textarea>
                    </div>
                    <!-- 첨부파일이 존재할 때만 노출 -->
                    <c:if test="${readContentBean.content_file != null}">
                        <div class="form-group">
                            <label for="board_file">첨부 이미지</label>
                            <img src="${root }upload/${readContentBean.content_file}" width="100%" alt=""/>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <div class="text-end">
                            <a href="${root }board/main?board_info_idx=${board_info_idx}&page=${page}"
                               class="btn btn-primary">목록보기</a>
                            <c:if test="${loginUserBean.user_idx==readContentBean.content_writer_idx}">
                                <a href="${root }board/modify?board_info_idx=${board_info_idx}&content_idx=${content_idx}&page=${page}"
                                   class="btn btn-info">수정하기</a>
                                <a href="${root }board/delete?board_info_idx=${board_info_idx}&content_idx=${content_idx}"
                                   class="btn btn-danger">삭제하기</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>

<!--bottom-->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>
</body>
</html>






