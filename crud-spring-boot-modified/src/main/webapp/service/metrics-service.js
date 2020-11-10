angular.module('mobiApplication').service('metricsService',['$http',function($http){
	
	return {
		getMetrics: function(page) {
			console.log("page="+page);
			return $http.get("api/metrics/allByPage?page="+page);
			//return $http.get("api/metrics/all");
		},
		findMetricsBySearch: function(placa, valmin, valmax, page) {
			return $http.get("api/metrics/search?placa=" + placa + "&valmin=" + valmin+ "&valmax=" + valmax+ "&page=" + page);
		}
	}
	
}]);