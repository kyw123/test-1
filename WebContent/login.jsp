<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
label.error{
	color: red;
} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎进入用户管理系统！</title>
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script type="text/javascript" src="jquery/jquery-3.4.1.js"></script><!-- 引用jquery.js -->
<!--首先jquery.然后是jquery.validate.js，然后自己的validate.js文件，这样才能起作用，
而自己在head.php载入jquery，jquery.validate.js，以及自己的validate.js文件后，
又在自己的页面中重新载入了一次jquery，自然出现了$（#form）.validate is not a function的错误了。-->
<script type="text/javascript" src="validate/lib/jquery.js"></script><!-- 引入验证js包 -->
<script type="text/javascript" src="validate/dist/jquery.validate.js"></script><!-- 引入验证js包 -->
<script type="text/javascript" src="jquery/jquery.serializejson.js"></script><!-- jquery.serializejson.js -->
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="validate/dist/localization/messages_zh.js"></script>
<link rel="shortcut icon" href="images/bitbug_favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="images/bitbug_favicon.ico" type="image/x-icon" />
<script type="text/javascript">
//注意minlength 和 maxlength要搭配使用。有minlength就有maxlength。
$(document).ready(function(){
	$("#form1").validate({
		rules:{
			addName:{
				required: true,
				minlength: 2,
				maxlength: 10
			},
			addAge:{
				required: true,
				minlength: 2,
				maxlength: 3
			},
			addShengao:{
				required :true,
				minlength:2,
				maxlength:3
			},
			addTel:{
				required: true,
				minlength:11,			
				maxlength: 11
			},
			addSfz:{
				required: true,
				minlength:18,
				maxlength: 18
			},
			addSex:{
				required:true
			},
			addAihao:{
				required:true,
				rangelength:[1,6],
				minlength:1,
				maxlength:6
			},
			//addAddr: "required",
			addAddr: {
	          required: true,
	          minlength:1,
	          maxlength:2
	        },
			addBirth:{
				required:true
			},
			//addMx:"required",
			addMx:{
				required:true,
				minlength:1,
				maxlength:6
			},
		    addPassword: {
		        required: true,
		        minlength:6,
		        maxlength:10
		      },
	        addConfirm_password: {
	           required: true,
	           minlength:6,
	           maxlength:10,
	           equalTo: "#addPassword"
	        },
	        addEmail: {
	           required: true,
	           email: true //验证电子邮箱格式。
	        }
		},
		messages:{
			addName:{
				required: "请输入用户名",
		        minlength: "用户名必需由两个字母或十个字母组成",
		        maxlength:"最长只为10个"
		    },
			addAge:{
				required: "请输入年龄",
				minlength:"年龄用两个数字组成",
				maxlength: "年龄的最大长度只能为3个数字"
			},
	        addShengao:{
				required :"请输入身高",
				minlength:"身高由2个数字或三个数字组成",
				maxlength:"最长为3个"
			},
			addTel:{
				required :"请输入11位电话号码的数字",
				minlength:"电话由11位数字组成",
				maxlength:"最长为11位数"
			},
			addSfz:{
				required:"请输入18位数字的身份证号码",
				minlength:"身份证为18位数字组成",
				maxlength:"最长为18位"
			},
			addSex:{
				required:"请输入你的性别"
			},
			addAihao:{
				required:"请输入你的爱好",
				rangelength:"选中的区间在1,到6个",
				minlength:"最少选中1个",
				maxlength:"最多为6个"
			},
			//addAddr: "下拉菜单是必选",
			addAddr: {
	          required: "地址是必选的",
	          minlength: "最少要选中1项",
	          maxlength:"最多只能选中2项"
	        },
	        addBirth:{
				required:"请输入你的出生年月"
			},
			//addMx: "下拉菜单是必选",
			addMx:{
				required:"请选择你喜欢的明星",
				minlength:"最少选中1个明星",
				maxlength:"最多选择6个明星"
			},
			addPassword: {
			   required:"请输入密码",
			   minlength:"密码长度不能小于6 个字母",
			   maxlength:"密码不能超过10个字母"
	     	 },
	     	addConfirm_password: {
		       required: "请输入密码",
		       minlength: "密码长度不能小于6 个字母",
		       maxlength:"密码不能超过10个字母",
		       equalTo: "两次密码输入不一致"    // 验证两个输入框的内容是否相同。
		     },
		    addEmail: "请输入一个正确的邮箱",
		}
	});
	$("#form2").validate({
		rules:{
			updateName:{
				required: true,
				minlength: 2,
				maxlength: 10
			},
			updateAge:{
				required: true,
				minlength: 2,
				maxlength: 3
			},
			updateShengao:{
				required :true,
				minlength:2,
				maxlength:3
			},
			updateTel:{
				required: true,
				minlength:11,			
				maxlength: 11
			},
			updateSfz:{
				required:true,
				minlength:18,
				maxlength: 18
			},
			updateSex:{
				required:true
			},
			updateAh:{
				required:true,
				rangelength:[2,6],
				minlength:2,
				maxlength:6
			},
			updateAddr:{
				required:true,
				minlength:1,
				maxlength:2
			},
			updateBirth:"required",
			updateMx:{
				required:true,
				minlength:1,
				maxlength:6
			},
			updatePassword: {
		        required: true,
		        minlength:6,
		        maxlength:10
		    },
		    updateConfirm_password: {
	           required: true,
	           minlength:6,
	           maxlength:10,
	           equalTo:"#updatePassword"
	        },
	        updateEmail: {
	           required: true,
	           email: true //验证电子邮箱格式。
	        }
		},
		messages:{
			updateName:{
		        required: "请输入用户名",
		        minlength: "用户名必需由两个字母或十个字母组成",
		        maxlength:"最长只为10个"
		    },
		    updateAge:{
				required: "请输入年龄",
				minlength:"年龄用两个数字组成",
				maxlength: "年龄的最大长度只能为3个数字"
			},
			updateShengao:{
				required :"请输入身高",
				minlength:"身高由2个数字或三个数字组成",
				maxlength:"最长为3个"
			},
			updateTel:{
				required :"请输入11位电话号码的数字",
				minlength:"电话由11位数字组成",
				maxlength:"最长为11位数"
			},
			updateSfz:{
				required:"请输入18位数字的身份证号码",
				minlength:"身份证为18位数字组成",
				maxlength:"最长为18位"
			},
			updateSex:{
				required:"请输入你的性别"
			},
			updateAh:{
				required:"请输入你的爱好",
				rangelength:"选中的区间在2,到6个",
				minlength:"最少选中2个",
				maxlength:"最多为6个"
			},
			updateAddr:{
				required:"请修改你的地址",
				minlength:"最少选中1个",
				maxlength:"最多为2个"
			},
			updateBirth: "请输入你的出生年月",
			updateMx:{
				required:"请选择你喜欢的明星",
				minlength:"最少选中1个",
				maxlength:"最多为6个"
			},
			updatePassword:{
			   required:"请输入密码",
			   minlength:"密码长度不能小于6 个字母",
			   maxlength:"密码不能超过10个字母"
	     	},
	     	updateConfirm_password: {
		       required: "请输入密码",
		       minlength: "密码长度不能小于6 个字母",
		       maxlength:"密码不能超过10个字母",
		       equalTo: "两次密码输入不一致"    // 验证两个输入框的内容是否相同。
		    },
		    updateEmail: "请输入一个正确的邮箱",
		},
	});
	// 显示/隐藏新增div框 
	$("#add").click(function(){
		$("#addDiv").toggle();
	});
	//清空新增div输入框的值
	function reset(){
		 $("#addId").val("");
		 $("#name").val("");
		 $("#age").val("");
		 $("#shengao").val("");
		 $("#tel").val("");
		 $("#addr").val("");
		 $("#sfz").val("");
		 $("#mx").val("");
		 $(".ah").val("");
		 $("#addBirth").val("");
		 $("#addPassword").val("");
		 $("#addConfirm_password").val("");
		 $("#addEmail").val("");
	}
	//全选
	$("#checkall").click(function(){
		$.each($("input[name='addAihao']"), function(){
			this.checked=true;
		});
	});
	
	//全不选
	$("#uncheck").click(function(){
		$.each($("input[name='addAihao']"), function(){
			this.checked=false;
		});
	});
	
	//反选，原来是true,改成false. 原来是false,改成true
	$("#invert").click(function(){
		$.each($("input[name='addAihao']"), function(){
			if(this.checked==false){
				this.checked= true;
			}
			else{
				this.checked=false;
			}
		});
	});
	// 全选 
	$("#checkallUpdate").click(function(){
		$.each($("input[name='updateAh']"),function(){
			this.checked=true;
		});
	})
	
	//全不选 
	$("#uncheckUpdate").click(function(){
		$.each($("input[name='updateAh']"), function(){
			this.checked=false;
		});
	});
	
	//反选 
	$("#invertUpdate").click(function(){
		$.each($("input[name='updateAh']"),function(){
			if(this.checked==false){
				this.checked=true;
			}else{
				this.checked=false;
			}
		})
	});
	//添加用户。
	$("#adduser").click(function(){
		var verify = $("#form1").valid();//获取验证结果,如果为false表示验证失败.如果返回true,表示验证成功
		if(!verify){
			return false;
		}
		var pageSize = $("#selectId").val(); 
		var serialize1= $("#form1").serializeJSON();
		var sex = $('input[name="addSex"]:checked').val(); 	
		serialize1.addSex =sex;
		
		var ah="";
		// 迭代获取复选框选中的值
		$.each($("input[name='addAihao']"), function(){
			if(this.checked){
				ah = ah + $(this).val() +",";
			}
		});
		ah = ah.substring(0,ah.length-1); // substring截取去掉后面的逗号
		serialize1.addAihao=ah;// 向json对象里面设置属性
		
		// select通过下拉列表选择多个明星。
		var mx="";
		$( "select[name='addMx'] option:selected" ).each(function() {
			mx+=$(this).val()+",";
		});
		mx = mx.substring(0,mx.length-1);
		serialize1.addMx=mx;
		//select通过下拉列表获取多个地址。
		var addr="";
		$("select[name='addAddr'] option:selected").each(function(){
			addr+=$(this).val()+",";
		});
		addr = addr.substring(0,addr.length-1);
		alert(addr);
		serialize1.addAddr=addr;
		var ss= $("#form1").serialize();
		alert(ss);
		$.ajax({
			 url:"userAdd", // 请求的路径 
			 type:"post",//请求方式
			 data:serialize1,/* {id:id,name:name,age:age,sex:sex,shengao:shengao,tel:tel,addr:addr,sfz:sfz,star:star,aihao:aihao},//json格式数据就行 */
			 // data :发送到服务器的数据。
			 async:false,//变成同步了
			 dataType:"json", // 服务器返回的数据。
			 success:function(aa){ // 由服务器返回的数据
				 var table = $("#userlist tr");
				 if(table.length != pageSize-1){
					 query(1);
					 reset();
					 $("#addDiv").hide();
	 				 and(); // 删除和修改。
				 }else{
					 query(1);
					 var tr = "<tr><td>"+aa.id+"</td><td>"+aa.name+"</td><td>"+aa.age+"</td><td>"+aa.sex+"</td><td>"+aa.shengao+"</td><td>"+aa.tel+"</td><td>"+aa.addr+"</td><td>"+aa.sfz+"</td><td>"+aa.star+"</td><td>"+aa.aihao+"</td><td>"+aa.birthday+"</td><td>"+aa.password+"</td><td>"+aa.confirm_password+"</td><td>"+aa.email+"</td><td><a href='#' class='update' abc='"+aa.id+"'>修改</a>&nbsp;&nbsp;&nbsp;<a href='#' class='delete' abc='"+aa.id+"'>删除</a></td></tr>"
	 				 $("#userlist").append(tr);//清空值，并且隐藏Div
	 				 $("#addDiv").hide();//将添加框进行隐藏 
					 reset();//将值进行清空。
	 				and();
				 }
			 },
			 error:function(xhr,status,error){
				alert(error);
			 }
		 });
	});
	
	//修改列表 
	 $("#upadteuser").click(function(){
		 	var yz = $("#form2").valid();// 获取输入验证的结果。 如果为false表示验证失败。true表示成功。
		 	if(!yz){
		 		return false;
		 	}
		 	var ser = $("#form2").serializeJSON();    // 第三种方式。但是必须引入js包。jquery.serializeJSON.js
			//var serialize = $("#form2").serialize();  // 第三种方式。但是必须引入js包。jquery.serialize.js
			//修改单选按钮 
		 	var sex = $('input[name="updateSex"]:checked').val();
			//修改下拉列表的明星值
			var mx="";
			$( "select[name='updateMx'] option:selected" ).each(function() {
				mx += $(this).val()+",";
			});
			mx = mx.substring(0,mx.length-1);
			ser.updateMx = mx;
			//修改下拉列表的地址值 
			var addr="";
			$("select[name='updateAddr'] option:selected").each(function(){
				addr+=$(this).val()+",";
			});
			addr = addr.substring(0,addr.length-1);
			ser.updateAddr = addr;
			
			//修改复选框按钮 
			var xgah = "";
			$.each($("input[name='updateAh']"), function(){
				if(this.checked){
					xgah += $(this).val() +",";
				}
			});
			xgah = xgah.substring(0,xgah.length-1);	// 去掉后面的逗号。
			//进行赋值。
			ser.updateAh = xgah;
			ser.updateSex = sex;
			$.ajax({
				 url:"userUpdate",
				 type:"post",//请求方式
				 data:ser,/*{id:id,name:name,age:age,sex:sex,shengao:shengao,tel:tel,addr:addr,sfz:sfz,star:star,aihao:aihao},*///json格式数据就行
				 //data :发送到服务器的数据。
				 async:false,//变成同步了
				 dataType:"json",
				 success:function(data){
					 alert(data);
					//alert("修改成功");
					$("#userlist tr").find("td:eq(0)").each(function(){
						//  根据id找到我们修改的那一行
						if ($(this).text() == data.id){
							$(this).parent().find("td:eq(0)").text(data.id);
							$(this).parent().find("td:eq(1)").text(data.name);
							$(this).parent().find("td:eq(2)").text(data.age);
							$(this).parent().find("td:eq(3)").text(data.sex);
							$(this).parent().find("td:eq(4)").text(data.shengao);
							$(this).parent().find("td:eq(5)").text(data.tel);
							$(this).parent().find("td:eq(6)").text(data.addr);
							$(this).parent().find("td:eq(7)").text(data.sfz);
							$(this).parent().find("td:eq(8)").text(data.star);
							$(this).parent().find("td:eq(9)").text(data.aihao);
							$(this).parent().find("td:eq(10)").text(data.dirthday);
							$(this).parent().find("td:eq(11)").text(data.password);
							$(this).parent().find("td:eq(12)").text(data.confirm_password);
							$(this).parent().find("td:eq(13)").text(data.email);
						}
					});
					$("#updateDiv").hide();
				 },
				 error:function(xhr,status,error){
					alert(error);
				 }
			});
	 });
	
	//删除和修改的方法 
	function and(){
	    //删除id的值 
		 $(".delete").click(function(){
			if(confirm("你确定要删除")){
				 //alert($(this).parent().parent().find("td:eq(0)").text());//第一种获取id的值。
				 var id = $(this).attr("abc");//获取ID,通过ID到数据库里面查询 //通过属性来获取id的值 。
				 var a = $(this).parent().parent();
				 $.ajax({
					 //url:"userDelete",data:{"id":id},第二种获取id的值。 
					 url:"userDelete?id=" + id, // 第一种获取id的值。 
					 type:"get",//请求方式
					 async:false,//变成同步了
					 success:function(){
						 query(1); //alert(a.remove());
					 },
					 error:function(xhr,status,error){
						alert(error);
					 }
				 });
			 }else{
				 alert("删除失败！");
			 }
		 });
	    
	    
		//发送邮件
		$(".email").click(function(){
			if(confirm("确定发送邮箱！")){
				var id = $(this).attr("abc");//取里面的属性,id
				var email = $(this).attr("abcd");//取里面的属性,id
				alert(id+" "+email);
				var tr = $(this).parent().parent();
				$.ajax({
			    	url:"email",
			    	type:"post",
			    	data:{"id":id,"email":email},
			    	success:function(){
			    		alert("发送邮箱成功!");
			    	},
			    	error:function(a,b,c){
			    		alert("发送邮箱失败！");
			    	}
			    });	
			}else{
				alert("邮箱发送失败!");
			}
		});
	    //修改回显
		 $(".update").click(function(){
			 if(confirm("你确定要修改吗?")){
				 var id = $(this).attr("abc");//获取ID,通过ID到数据库里面查询
				 $.ajax({
					 url:"userUpdateEcho?id=" + id,
					 type:"post",//请求方式
					 async:false,//变成同步了
					 dataType:"json",
					 success:function(user) {
							$("#updateId").val(user.id);
							$("#updateName").val(user.name);
							$("#updateAge").val(user.age);
							// 单选按钮回显
							$.each($("input[type=radio][name=updateSex][value="+user.sex+"]"), function(){
								this.checked=true;
							});
							$("#updateShengao").val(user.shengao);
							$("#updateTel").val(user.tel);
							// 地址下拉列表回显
							$("#updateAddr").val("");
							$.each(user.addr,function(i,obj){
								 $("#updateAddr option[value='"+obj+"']").prop("selected",true);
							});
							$("#updateSfz").val(user.sfz);
							// 明星下拉列表回显
							$("#updateMx").val("");//先进行清空
							$.each(user.star,function(i,obj){
								 $("#updateMx option[value='"+obj+"']").prop("selected",true);
							});
							$("#updateBirth").val(user.birthday);
							// 爱好复选框回显
							var likes = user.aihao.split(",");//$(".updateAh").val(user.aihao); //[object Object]
							$.each($("input[name='updateAh']"), function(){//为什么加这句话.先把所有的选项清空.不选择
								this.checked=false;
							});
							$.each($("input[name='updateAh']"), function(){////根据我们返回的值,来设置选中 循环我们所有的选项
								for(var i = 0;i<likes.length;i++){//需要与我们返回的值,逐个比较.
									if($(this).val() == likes[i]){
										this.checked=true;
										break;
									}
								}
							});
							$("#updatePassword").val(user.pawd);
							$("#updateConfirm_password").val(user.confirm_password);
							$("#updateEmail").val(user.email);
						},
					 error:function(xhr,status,error){
						alert(error);
					 }
				 });
				 $("#updateDiv").show();
			 }else{
				 alert("修改失败!");
			 }
		});
	 }
	$("#first").click(function(){
		query(1);
	});
	$("#last").click(function(){
		var pageCount = $("#pageCount").text();
		query(pageCount);
	});
	$("#up").click(function(){
		var pageNow = $("#pageNow").text();
		if(pageNow!=1){
			query(parseInt(pageNow)-1);
		}
	});
	$("#down").click(function(){
		var pageNow = $("#pageNow").text();
		var pageCount = $("#pageCount").text();
		if(pageNow != pageCount){
			query(parseInt(pageNow)+1);	
		}
	});
	
	$("#selectId").click(function(){
		 $("#pageNow").text("1");
		 query(1);
	});

	//查询列表
	function query(pageNow){
		var pageSize = $("#selectId").val();
		 $.ajax({
			 url:"userQuery",
			 type:"post",//请求方式
			 async:false,//变成同步了
			 dataType:"json",
			 data:{"pageSize":pageSize,"pageNow":pageNow},//pagesize每页显示多少条记录 ,pageno当前第几页
			 success:function(page){ 
				 var userlist = page.list;
				 var pagecount = page.pageCount;
				 var pageSize= page.pageSize;
				 var pageNow = page.pageNow;
				 $("#pageNow").text(pageNow);
				 $("#pageSize").text(pageSize);
				 $("#pageCount").text(pagecount);
				 $("#userlist").empty();
				 $("#userlist").append("<tr><th>id</th><th>姓名</th><th>年龄</th><th>性别</th><th>身高</th><th>电话</th><th>地址</th><th>身份证</th><th>喜欢的明星</th><th>爱好</th><th>出生日期</th><th>密码</th><th>验证密码</th><th>Email</th><th>操作</th></tr>");
				 for(var i = 0;i < userlist.length;i++){
					var test ="";
					if(userlist[i].mark==1){
	    				test = "<a href='#' abc='"+userlist[i].id+"'abcd='"+userlist[i].email+"'class='email'>发送邮件</a>";
	    			}
					var tr = "<tr><td>"+userlist[i].id+"</td><td>"+userlist[i].name+"</td><td>"+userlist[i].age+"</td><td>"+userlist[i].sex+
					"</td><td>"+userlist[i].shengao+"</td><td>"+userlist[i].tel
					+"</td><td>"+userlist[i].addr+"</td><td>"
					+userlist[i].sfz+"</td><td>"+userlist[i].star+"</td><td>"+userlist[i].aihao+"</td><td>"+userlist[i].birthday+"</td><td>"+userlist[i].pawd+
					"</td><td>"+userlist[i].confirm_password+"</td><td>"+userlist[i].email+"</td><<td><a href='#' class='update' abc='"
					+userlist[i].id+"'>修改</a>&nbsp;&nbsp;&nbsp;<a href='#'class='delete'abc='"+userlist[i].id+"'>删除</a>&nbsp;&nbsp;"+test+"</td></tr>"
					$("#userlist").append(tr);
				}
				and();
			 },
			 error:function(xhr,status,error){
				alert(error);
			 }
		});
	}
	and(); // and()这个方法用来修改和删除用的。
	query(1);//页面一进来就会调用这个方法。用于查询数据的方法。
});
</script>
</head>
<body>
	<!-- 表格 -->
	<input type="button" value="显示/隐藏新增" id="add">
	<table border = "1" style="border-collapse:collapse" id="userlist"></table>
		第&nbsp;<span id="pageNow" style="color:red"></span>&nbsp;页
		总共 &nbsp;<span id="pageCount" style="color: red"></span>&nbsp;页
		每页显示：<select id="selectId" name = "selectId">
				  <option value="3" class="option">3</option>
				  <option value="5" class="option">5</option>
				  <option value="10" class="option">10</option>
			    </select>条数据&nbsp;
	 	<br/>
			<input type="button" id="first" value="首页">
			<input type="button" id="up" value="上页">
			<input type="button" id="down" value="下页">
			<input type="button" id="last"  value="尾页">
		<br/>
		<br/>
	<!-- 添加的页面-->
	<form action=" " id ="form1">
		<div id="addDiv" style="display: none">
		<fieldset style="width:650px; height:480px">
		<legend style="color:red;">用户添加信息</legend>
			<input type="hidden" id="addId"><br>
			<span class="s">*</span><b>输入姓名:</b>&nbsp;&nbsp;<input type="text" placeholder="请输入用户名"   id="name" name = "addName" title="请输入名称"><br>
			<span class="s">*</span><b>输入年龄:</b>&nbsp;&nbsp;<input type="text" placeholder="请输入年龄"  id="age" name = "addAge"><br>
			<span class="s">*</span><b>输入性别:</b>&nbsp;&nbsp;
						<input type="radio" name="addSex" class = "sex" value="1" id="nan"/>男
						<input type="radio" name="addSex" class = "sex" value="2" id="nv"/>女<br>
			&nbsp;<b>输入身高:</b>&nbsp;&nbsp;<input type="text" placeholder="请输入身高" id="shengao" name ="addShengao"><br>
			&nbsp;<b>输入电话:</b>&nbsp;&nbsp;<input type="text" placeholder="请输入电话" id="tel" name = "addTel"><br>
			&nbsp;<b>输入目前所在城市（地址）:</b>
					<!-- multiple=multiple 表示创建一个多选下拉列表-->
					<select id="addr" name="addAddr" title="Please select something!" multiple>
							<option value ="kz">--请选择地址--</option>
							<option value="sz">深圳</option>
							<option value="gd">广东</option>
							<option value="fs">佛山</option>
							<option value="gz">广州</option>
							<option value="qy">清远</option>
							<option value="bj">北京</option>
							<option value="sh">上海</option>
							<option value="zj">浙江</option>
							<option value="cs">长沙</option>
							<option value="yz">永州</option>
							<option value="xm">厦门</option>
					</select><br>
			<span class="s">*</span><b>输入身份证:</b>&nbsp;&nbsp;<input type="text" id ="sfz"class="s1" placeholder="请输入身份证"  name = "addSfz"><br>
			<b>请选择喜欢的明星：</b><select name="addMx" id="star" multiple>
					<option id="qxz" value="qxz">--YOU喜欢的明星--</option>
					<option id="lyf" value="lyf">李易峰</option>
					<option id="zly" value="zly">赵丽颖</option>
					<option id="wyf" value="wyf">吴亦凡</option>
					<option id="mmq" value="mmq">孟美岐</option>
					<option id="wxy" value="wxy">吴宣仪</option>
					<option id="xzq" value="xzq">薛之谦</option>
					<option id="zdd" value="zdd">张大大</option>
			</select><br/>
			<b>个人爱好：</b><input type="checkbox" name="addAihao" class="ah" value="cg"/>唱歌
					<input type="checkbox" name="addAihao" class="ah" value="xq"/>下棋
					<input type="checkbox" name="addAihao" class="ah" value="tw"/>跳舞
					<input type="checkbox" name="addAihao" class="ah" value="ks"/>看书
					<input type="checkbox" name="addAihao" class="ah" value="lq"/>篮球
					<input type="checkbox" name="addAihao" class="ah" value="dy"/>电影
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<br/>
					<input type="button" value="全选" id="checkall"/>&nbsp;&nbsp;
					<input type="button" value="反选" id="invert"/>&nbsp;&nbsp;
					<input type="button" value="取消" id="uncheck"/><br/>
			<b>出生日期:</b><input class="Wdate" type="text"  placeholder="请输入出生日期" id="addBirth" name="addBirth" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"><br/>
			<b>密码:</b><input type="password" id="addPassword" name="addPassword" ><br/>
			<b>验证密码:</b><input type="password" id="addConfirm_password" name="addConfirm_password"><br/>
			<b>Email</b><input type="email" id="addEmail" name="addEmail"><br/>
			<input type="submit" id="adduser" value="新增"/>
	  </fieldset>
	  </div>
	  <!-- checkbox 的 minlength 表示必须选中的最小个数，maxlength 表示最大的选中个数，rangelength:[2,3] 表示选中个数区间。 -->
	</form>
	<!-- 修改页面 -->
	<form action="" id="form2">
		<div id="updateDiv" style="display: none">
		<fieldset style="width:650px; height:480px">
			<legend style="color:red;">用户修改信息</legend>
			<input type="hidden" id="updateId" name = "updateId"><br>
			&nbsp;<b>修改姓名:</b>&nbsp;&nbsp;<input type="text" id="updateName" name="updateName"><br>
			&nbsp;<b>修改年龄:</b>&nbsp;&nbsp;<input type="text" id="updateAge" name="updateAge"><br>
			&nbsp;<b>修改性别:</b>&nbsp;&nbsp;<input type="radio" name="updateSex" class="updateSex" value="1"/>男
										    <input type="radio" name="updateSex" class="updateSex" value="2"/>女<br/>
			&nbsp;<b>输入身高:</b>&nbsp;&nbsp;<input type="text" id="updateShengao" name="updateShengao"><br>
			<span class="s">*</span><b>修改电话:</b>&nbsp;&nbsp;<input type="text" id="updateTel" name="updateTel"><br>
			<span class="s">*</span><b>修改目前所在城市（地址）:</b>
								<select id="updateAddr" name ="updateAddr" multiple>
										<option value ="kz">--请选择地址--</option>
										<option value="sz">深圳</option>
										<option value="gd">广东</option>
										<option value="fs">佛山</option>
										<option value="qy">清远</option>
										<option value="gz">广州</option>
										<option value="bj">北京</option>
										<option value="sh">上海</option>
										<option value="zj">浙江</option>
										<option value="cs">长沙</option>
										<option value="yz">永州</option>
										<option value="xm">厦门</option>
								</select><br>
			&nbsp;<b>修改身份证：</b>&nbsp;&nbsp;<input type="text" id ="updateSfz" name="updateSfz"><br>
			<b>请选择喜欢的明星：</b><select name="updateMx" id="updateMx" multiple>
									<option id="qxz" value="qxz">--YOU喜欢的明星--</option>
									<option id="lyf" value="lyf">李易峰</option>
									<option id="zly" value="zly">赵丽颖</option>
									<option id="wyf" value="wyf">吴亦凡</option>
									<option id="mmq" value="mmq">孟美岐</option>
									<option id="wxy" value="wxy">吴宣仪</option>
									<option id="xzq" value="xzq">薛之谦</option>
									<option id="zdd" value="zdd">张大大</option>
								 </select><br/>
					<b>个人爱好：</b><input type="checkbox" name="updateAh" class="updateAh" value="cg"/>唱歌
					<input type="checkbox" name="updateAh" class="updateAh" value="xq"/>下棋
					<input type="checkbox" name="updateAh" class="updateAh" value="tw"/>跳舞
					<input type="checkbox" name="updateAh" class="updateAh" value="ks"/>看书
					<input type="checkbox" name="updateAh" class="updateAh" value="lq"/>篮球
					<input type="checkbox" name="updateAh" class="updateAh" value="dy"/>电影
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<br/>
					<input type="button" value="全选" id="checkallUpdate"/>&nbsp;&nbsp;
					<input type="button" value="反选" id="invertUpdate"/>&nbsp;&nbsp;
					<input type="button" value="取消" id="uncheckUpdate"/><br/>
					<b>出生日期:</b><input class="Wdate" type="text" id="updateBirth" name="updateBirth" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"><br/>
					<b>密码:</b><input type="password" id="updatePassword" name="updatePassword" ><br/>
					<b>验证密码:</b><input type="password" id="updateConfirm_password" name="updateConfirm_password"><br/>
					<b>Email</b><input type="email" id="updateEmail" name="updateEmail"><br/>
				    <input type="submit" id="upadteuser" value="修改"/>
			</fieldset>
		</div>
	</form>
</body>
</html>