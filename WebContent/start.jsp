<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<style>
body{
	background-image: url(images/welcome.jpg); 
	background-size:32%;
}  
#div{ 
 	border:0px solid #9ed5ff; 
 	width:380px; 
 	height:300px; 
	/*background-color:#58D8F8;*/
 	float:left; 
 	margin-left:725px; 
 	margin-top:300px; 
 	margin-bottom:1px 
 } 
 #div1{ 
 	border:1px solid #9ed5ff; 
 	background-color:#89F4F5; 
 	width:330px; 
 	height:150px; 
 	margin-top:40px; 
 } 
</style>
<title>进入登入页面and验证码</title>
<!-- 引入jquery-->
<script type="text/javascript" src="jquery/jquery-3.4.1.js"></script><!-- 引用jquery.js -->
</head>
<body onload="err();" >
	<form action="loginServlet" method="post">
	<div align="center" id="div">
	<div id="div1"><br/>
			<table style="border-collapse:collapse">
				<tbody>
					<tr>
						<td>用户名：</td>
						<td><input type="text" name="username"/></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" name="password"/></td>
					</tr>
					<tr>
					</tr>
					<tr>
						 <td colspan="2" align="center">
						 <input type="submit" value="登录" /> 
						 <input type="reset" value="重置" /> 
						</td> 
					</tr>
				</tbody>
			</table>
		<div align="center" style="display: none;" id="code">
			<input type="hidden" name="hiddenCode" id="hiddenCodeId" value="${hiddenCode}">
		           验证码:<input type="text" name="randomCode" />
				<img id="vimg" title="点击更换" onclick="changeCode();" src="AuthImageServlet"><br />
			<input type="hidden" name="err" id="errId" value="${err}" /> 
			<span id="span"></span>
		</div>
	</div>
	</div>
	</form>
</body>
<script type="text/javascript">
	function changeCode() {
		var imgNode = document.getElementById("vimg");
		imgNode.src = "AuthImageServlet?t=" + Math.random();// Math.random(); 随机数，只是为了每次都请求sevlet，如果每次请求的都是同一个值，那么只会请求一次servlet
	}
	function err() {
		var hiddenCodeId = document.getElementById("hiddenCodeId").value; /* 得到隐藏验证码输入框的值 */
		var err = document.getElementById("errId").value;// 得到错误的值
		if ("" == hiddenCodeId) {// 如果值等于空，就说明是第一次登录
			document.getElementById("code").style.display = "none";// 将验证码输入框隐藏起来
		} else if ("1" == hiddenCodeId) {// 如果等于1 说明不是第一次
			document.getElementById("code").style.display = "";// 将验证码输入框 
		}
		if ("0" == err) {// 如果err等于0或2，表示用户名或密码错误 
			alert("请输入验证码");			/*  弹出框效果  */
			$("#span").html("请输入验证码!").css("color", "green");/* 直接在页面打出来 */
		} else if ("1" == err) {// 如果err等于1，表示验证码输入错误
			alert("请输入用户名或密码");
			$("#span").html("请输入用户名或密码").css("color", "green");
		} else if ("2" == err) {// 如果err等于1，表示验证码输入错误
			alert("用户名或密码错误");
			$("#span").html("用户名或密码错误").css("color", "green");
		} else if ("3" == err){// 如果err等于1，表示验证码输入错误
			alert("验证码输入错误");
			$("#span").html("验证码输入错误").css("color", "green");
		}
	}
</script>
</html>