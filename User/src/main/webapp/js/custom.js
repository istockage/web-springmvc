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