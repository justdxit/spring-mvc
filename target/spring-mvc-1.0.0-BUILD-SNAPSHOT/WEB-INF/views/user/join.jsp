<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Moodeon<</title>
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

<!--회원가입 ID 중복 확인-->
<script>
    function checkUserIdExist() {
        //form:input path="user_id"
        var user_id = $("#user_id").val()

        if(user_id.length == 0) {
            alert("아이디를 입력해주세요")
            return
        }

        $.ajax({
            url: "${root }user/checkUserIdExist/" + user_id,
            type: "get",
            dataType: "text",
            success: function (result) {
                //trim() 양쪽 공백 제거
                if (result.trim() == "true") {
                    alert("사용할 수 있는 아이디입니다")
                    $("#userIdExist").val("true")
                } else {
                    alert("사용할 수 없는 아이디입니다")
                    $("#userIdExist").val("false")
                }
            }
        })

        //사용자 아이디 입력칸에 키보드를 누르면 무조건 false
        function resetUserIdExist() {
            $("#userIdExist").val("false")
        }
    }
</script>
<body>
<!-- 상단 메뉴 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 회원가입 본문 -->
<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card shadow">
                <div class="card-body">
                    <form:form action="${root }user/join_done" method="post" modelAttribute="joinUserBean">
                        <form:hidden path="userIdExist"/>
                        <div class="form-group">
                            <!-- beans/UserBean 참고해서 path 설정 -->
                            <form:label path="user_name">이름</form:label>
                            <form:input path="user_name" class="form-control"/>
                            <form:errors path="user_name" style="color:red"/>
                        </div>
                        <div class="form-group">
                            <form:label path="user_id">아이디</form:label>
                            <div class="input-group">
                                <form:input path="user_id" class="form-control" onkeypress="resetUserIdExist"/>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary" onclick="checkUserIdExist()">중복확인</button>
                                </div>
                            </div>
                            <form:errors path="user_id" style="color:red"/>
                        </div>
                        <div class="form-group">
                            <form:label path="user_pw">비밀번호</form:label>
                            <form:password path="user_pw" class="form-control"/>
                            <form:errors path="user_pw" style="color:red"/>
                        </div>
                        <div class="form-group">
                            <form:label path="user_pw2">비밀번호 확인</form:label>
                            <form:password path="user_pw2" class="form-control"/>
                            <form:errors path="user_pw2" style="color:red"/>
                        </div>
                        <div class="form-group">
                            <div class="text-end">
                                <form:button class="btn btn-primary">회원가입</form:button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>