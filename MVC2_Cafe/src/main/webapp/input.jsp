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
<body>
	
 <form action="/insert" method="post">
 <div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">메뉴 이름</span>
  <input type="text" class="form-control" placeholder="메뉴를 적으세요" aria-label="Username" aria-describedby="basic-addon1" name="product_name">
 </div>
  <div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">메뉴 가격</span>
  <input type="text" class="form-control" placeholder="메뉴 가격을 적으세요" aria-label="Username" aria-describedby="basic-addon1" name="product_price">
 </div>
 <button type="button" class="btn btn-primary btn-sm" id="backBtn">뒤로가기 버튼</button>
 <button type="submit" class="btn btn-secondary btn-sm">등록 버튼</button>
 </form>
	
  <script>
  document.getElementById('backBtn').onclick = function() {
	    location.href="/index.jsp";
	}
  
  </script>





	
	
</body>
</html>