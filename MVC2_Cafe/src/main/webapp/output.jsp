<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>
	<table class="table tbl-cafe">
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">가격</th>
				<th scope="col">수정</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="{empty list}">
					<tr>
						<td colsapn=5>보여줄 메세지가 없습니다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="dto">
						<tr>
							<td>${dto.getProduct_id()}</td>
							<td>${dto.getProduct_name()}</td>
							<td>${dto.getProduct_price()}</td>
							<td><button type="button" class="btn btn-primary btn-sm deleteBtn"
									 value="${dto.getProduct_id()}">삭제</button></td>
							<td><button type="button" class="btn btn-secondary btn-sm modifyBtn"
									 value="${dto.getProduct_id()}">수정</button></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<script>
	
	
	
	// 삭제 버튼을 눌렀을 때
	$(".tbl-cafe").on("click", ".deleteBtn", function() {
		
		// 삭제 버튼을 눌렸을 때 그 행의 시퀀스값 
		let seq = $(this).val();
		
		// form 태그 동적 생성
		let deleteForm = $("<form id='deleteForm'></form>");
		
		// 위에서 생성한 form태그에 속성 넣어주기 action / method
		deleteForm.attr("action", "/delete");
		deleteForm.attr("method", "post");
		
		// 데이터를 전송하기 위해 form에 input 태그를 추가
		deleteForm.append($("<input/>", {type: "text", name: "seq", value : seq}));
	
		// body에 위에서 만든 form 넣어주기(이때 form 태그는 안보임)
		$(deleteForm).appendTo("body").css("display","none");
		
		// 스크립트 영역에서 만든 deleteForm을 즉시 submit 하기
		deleteForm.submit();
		
	})
	
	// 수정 버튼을 눌렀을 때 
		$(".tbl-cafe").on("click", ".modifyBtn", function() {
		
		// 삭제 버튼을 눌렸을 때 그 행의 시퀀스값 
		let seq = $(this).val();
		
		// seq번호를 modify url로 get방식 전송
		location.href = "/modify?seq=" + seq; // get방식이기 때문에 delete 처럼 form으로 연결할 필요 x 
		
	})
	
	
	
	
	
	
	</script>

</body>
</html>