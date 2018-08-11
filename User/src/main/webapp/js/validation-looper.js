// load
document.write("<scr" + "ipt src=" + getRootPath() + "/js/validation/jquery.validate.min.js type='text/javascript' charset='utf-8'><\/scr" + "ipt>");

// 取得根目錄
function getRootPath(){
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf("/") + 1);
	return prePath + postPath;
}

$(document).ready(function(){
	
	// regex
	$.validator.addMethod("pattern", function(value, element, regex){
		return regex.test(value);
	});
	
	$("#change-password-form").validate({
		rules: {
			// settings/account
			me_password: {
				required: true
			},
			me_password_new: {
				required: true,
				pattern: /^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\S+$).+$/,
				minlength: 8,
				maxlength: 32
			},
			me_password_new_again: {
				required: true,
				equalTo : "#me_password_new"
			},
		},
		messages: {
			// settings/account
			me_password: {
				required: "這裡必須填入資料"
			},
			me_password_new: {
				required: "這裡必須填入資料",
				pattern: "密碼必須包含英文及數字，不可填入空白",
				minlength: "密碼必須大於8個字",
				maxlength: "密碼必須小於32個字"
			},
			me_password_new_again: {
				required: "這裡必須填入資料",
				equalTo: "密碼重複錯誤"
			},
		},
		errorElement: "span",
		errorPlacement: function(error, element) {
			$(element).next().append(error);
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