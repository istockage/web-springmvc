// settings/securities-account
$("#sa_SecuritiesBrokerHeadEntity").change(function(){
	var securities_broker_branch_select = $("#sa_SecuritiesBrokerBranchEntity");
	securities_broker_branch_select.empty();
	securities_broker_branch_select.append("<option value='0'>請選擇</option>");
	$.getJSON("../../settings/securities-account/securities-broker-branch-list.ajax", {"sb_sh_id": $(this).val()}, function(data){
		$.each(data, function(index, securitiesBrokerBranchEntity){
			var securities_broker_branch_option = $("<option value=" + securitiesBrokerBranchEntity.sb_id + "></option>").append(securitiesBrokerBranchEntity.sb_name);
			securities_broker_branch_select.append(securities_broker_branch_option);
		});
	});
});

// stock/inventory/add
$("input[type=radio][name=buy-sell]").change(function(){
	if (this.value == "buy"){
		$("#buy-radio").addClass("active").css("cursor", "default");
		$("#sell-radio").removeClass("active").removeAttr("style");
		$("#spot-share-radio").children("input").prop("checked", true);
		$("#margin-purchase-radio").removeClass("not-show");
		$("#short-sale-radio").addClass("not-show");
		$("#drawing-lots-radio").removeClass("not-show");
		$("#buy-sell-card").removeClass("alert-success").addClass("alert-danger");
		$("#buy-form").removeClass("not-show");
		$("#sell-form").addClass("not-show");
		$("#sell-form input").val("");
		$("#sell-form input").removeClass("is-invalid");
	} else if (this.value == "sell") {
		$("#buy-radio").removeClass("active").removeAttr("style");
		$("#sell-radio").addClass("active").css("cursor", "default");
		$("#spot-share-radio").children("input").prop("checked", true);
		$("#margin-purchase-radio").addClass("not-show");
		$("#short-sale-radio").removeClass("not-show");
		$("#drawing-lots-radio").addClass("not-show");
		$("#buy-sell-card").removeClass("alert-danger").addClass("alert-success");
		$("#buy-form").addClass("not-show");
		$("#buy-form input").val("");
		$("#buy-form input").removeClass("is-invalid");
		$("#sell-form").removeClass("not-show");
	}
});

// stock/inventory/add (#st_buy_time, #st_sell_time)
var readyFlatpickr = {
	init: function init(){
		this.bindUIActions();
	},
	bindUIActions: function bindUIActions(){
		this.handleFlatpickr();
	},
	dateTime: function dateTime(){
		return flatpickr('#st_buy_time, #st_sell_time', {
			disableMobile: true,
			enableTime: true,
			dateFormat: 'Y-m-d H:i'
		});
	},
	handleFlatpickr: function handleFlatpickr(){
		this.dateTime();
	}
}
readyFlatpickr.init();