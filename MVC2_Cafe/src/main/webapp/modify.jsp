<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>

<style>
.product-id {
	display:none;
}

</style>


<body>
	
<form id="modifyForm" action="modifyProc" method="post">
  <div class="mb-3 product-id">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="product_id" value="${dto.getProduct_id()}">
  </div>
    <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="product_name" placeholder="아이디 입력" value="${dto.getProduct_name()}">
  </div>
      <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="product_price" placeholder="아이디 입력" value="${dto.getProduct_price()}">
  </div>

  <button type="submit" class="btn btn-primary" id="completeBtn">수정완료</button>
</form>
	
<script>
// 수정완료 버튼을 누르면 알림창 띄워주고 확인을 누르면 form 제출 취소를 누르면 form 제출 x
$("#completeBtn").on("click",function(){
	// true or false로 값이 나온다
	let answer = confirm("정말 수정하시겠습니까?");
	
	if(answer) { // 확인 = true 일때 폼을 제출하겠다  false이면 제출x
		$("#modifyForm").submit();
	}
	
	
})
</script>
	
	
	
</body>
</html>