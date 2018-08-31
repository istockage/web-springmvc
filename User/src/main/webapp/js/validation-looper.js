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
	
	// settings/account/info
	$("#account-info-form").validate({
		rules: {
			me_lastname: {
				maxlength: 20
			},
			me_firstname: {
				maxlength: 20
			},
			me_email: {
				required: true,
				email: true,
				maxlength: 50
			}
		},
		messages: {
			me_lastname: {
				maxlength: "必須小於 20 個字"
			},
			me_firstname: {
				maxlength: "必須小於 20 個字"
			},
			me_email: {
				required: "必須填入資料",
				email: "必須填入正確的信箱格式",
				maxlength: "必須小於 50 個字"
			}
		},
		errorElement: "span",
		errorPlacement: function(error, element) {
			$(element).siblings(".invalid-feedback").append(error);
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
	
	// settings/account/change-password
	$("#account-change-password-form").validate({
		rules: {
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
			}
		},
		messages: {
			me_password: {
				required: "必須填入資料"
			},
			me_password_new: {
				required: "必須填入資料",
				pattern: "必須包含英文及數字，不可填入空白",
				minlength: "必須大於 8 個字",
				maxlength: "必須小於 32 個字"
			},
			me_password_new_again: {
				required: "必須填入資料",
				equalTo: "重複錯誤"
			}
		},
		errorElement: "span",
		errorPlacement: function(error, element) {
			$(element).siblings(".invalid-feedback").append(error);
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
	
	// settings/securities-account/add, settings/securities-account/edit
	$("#securities-account-add-form, #securities-account-edit-form").validate({
		rules: {
			sa_SecuritiesBrokerHeadEntity: {
				min: 1
			},
			sa_SecuritiesBrokerBranchEntity: {
				min: 1
			},
			sa_no: {
				required: true,
				pattern: /^[0-9]{7}$/
			},
			sa_discount: {
				digits: true,
				min: 0,
				max: 100
			}
		},
		messages: {
			sa_SecuritiesBrokerHeadEntity: {
				min: "必須選擇"
			},
			sa_SecuritiesBrokerBranchEntity: {
				min: "必須選擇"
			},
			sa_no: {
				required: "必須填入資料",
				pattern: "必須填入 7 位數字"
			},
			sa_discount: {
				digits: "必須填入數字",
				min: "必須填入介於 0 到 100 的數字",
				max: "必須填入介於 0 到 100 的數字"
			}
		},
		errorElement: "span",
		errorPlacement: function(error, element) {
			$(element).siblings(".invalid-feedback").append(error);
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