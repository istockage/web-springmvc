$(document).ready(function() {
	// stock/inventory
	if ($("#stock-inventory-table #st_sell_time").text().length == 0) {
		$("#stock-inventory-table>tbody>tr>td:nth-child(n+11):nth-child(-n+20)").css("background-color", "#f2f3f4");
	}
});

// settings/securities-account
$("#sh_no").change(function() {
	var securities_broker_branch_select = $("#sb_no");
	securities_broker_branch_select.empty();
	securities_broker_branch_select.append("<option value='0'>請選擇</option>");
	$.getJSON("../../settings/securities-account/securities-broker-branch-list.ajax", {"sh_no": $(this).val()}, function(data) {
		$.each(data, function(index, securitiesBrokerBranchEntity) {
			var securities_broker_branch_option = $("<option value=" + securitiesBrokerBranchEntity.sb_no + "></option>").append(securitiesBrokerBranchEntity.sb_name);
			securities_broker_branch_select.append(securities_broker_branch_option);
		});
	});
});

// stock/inventory/add
$("input[type=radio][name=buy-sell]").change(function() {
	if (this.value == "buy") {
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
	init: function init() {
		this.bindUIActions();
	},
	bindUIActions: function bindUIActions() {
		this.handleFlatpickr();
	},
	dateTime: function dateTime() {
		return flatpickr('#stock-inventory-add-form #st_buy_time, #stock-inventory-add-form #st_sell_time', {
			disableMobile: true,
			enableTime: true,
			dateFormat: 'Y-m-d H:i'
		});
	},
	handleFlatpickr: function handleFlatpickr() {
		this.dateTime();
	}
}
readyFlatpickr.init();

var select2Demo = {
	init: function init() {
		this.bindUIActions();
	},
	bindUIActions: function bindUIActions() {
		$.fn.select2.defaults.set('width', '100%');
		this.remoteData();
	},
	getStates: function getStates() {
		return $('#select2-source-states').html();
	},
	remoteData: function remoteData() {
		var formatRepo = function formatRepo(repo) {
			if (repo.loading) {
				return repo.text;
			}
			var markup = '<div class="media">' + '<div class="user-avatar mr-2"><img src="' + repo.owner.avatar_url + '" /></div>' + '<div class="media-body">' + '<h6 class="my-0">' + repo.full_name + '</h6>';
			if (repo.description) {
				markup += '<div class="small text-muted">' + repo.description + '</div>';
			}
			markup += '<ul class="list-inline small text-muted">' + '<li class="list-inline-item"><i class="fa fa-flash"></i> ' + repo.forks_count + ' Forks</li>' + '<li class="list-inline-item"><i class="fa fa-star"></i> ' + repo.stargazers_count + ' Stars</li>' + '<li class="list-inline-item"><i class="fa fa-eye"></i> ' + repo.watchers_count + ' Watchers</li>' + '</ul>' + '</div></div>';
			return markup;
		}
		var formatRepoSelection = function formatRepoSelection(repo) {
			return '<div class="user-avatar user-avatar-xs mr-2"><img src="' + repo.owner.avatar_url + '" /></div>' + repo.full_name || repo.text;
		}
		$('#select2-data-remote').select2({
			ajax: {
				url: 'https://api.github.com/search/repositories',
				dataType: 'json',
				delay: 250,
				data: function data(params) {
					return {
						q: params.term, // search term
						page: params.page
					}
				},
				processResults: function processResults(data, params) {
					params.page = params.page || 1;
					return {
						results: data.items,
						pagination: {
							more: params.page * 30 < data.total_count
						}
					}
				},
				cache: true
			},
			escapeMarkup: function escapeMarkup(markup) {
				return markup;
			},
			minimumInputLength: 1,
			templateResult: formatRepo,
			templateSelection: formatRepoSelection
		});
	}
}
select2Demo.init();