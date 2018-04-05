// load
document.write("<scr" + "ipt src=../js/validation/jquery.validate.min.js type='text/javascript' charset='utf-8'><\/scr" + "ipt>");

$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	$("form").validate({
		rules: {
			// member
			me_email: {
				required: true,
				email: true,
				maxlength: 50,
				remote: { // 信箱重複驗證 (sign-up) - AJAX
					url: "../member/sign-up-email-repeat.ajax", // 後台處理程序
					type: "post", // 數據發送方式
					dataType: "text", // 接受數據格式
					data: { // 要傳遞的數據
						me_email: function(){
							return $("#me_email").val();
						}
					}
				}
			},
			me_password: {
				required: true,
				pattern: /^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\S+$).+$/,
				minlength: 8,
				maxlength: 32
			},
			me_password_again: {
				required: true,
				equalTo : "#me_password"
			},
		},
		messages: {
			// member
			me_email: {
				required: "這裡必須填入資料",
				email: "信箱必須填入正確的格式",
				maxlength: "帳號必須小於50個字"
			},
			me_password: {
				required: "這裡必須填入資料",
				pattern: "密碼必須包含英文及數字，不可填入空白",
				minlength: "密碼必須大於8個字",
				maxlength: "密碼必須小於32個字"
			},
			me_password_again: {
				required: "這裡必須填入資料",
				equalTo: "密碼重複錯誤"
			},
		},
		highlight: function(element){
			$(element).addClass("is-invalid");
		},
		unhighlight: function(element){
			$(element).removeClass("is-invalid");
		},
		submitHandler: function(form){
			form.submit();
	    }
	});
});