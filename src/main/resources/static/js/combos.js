var app = angular.module("app", [])
		.controller("HttpGetController", function ($scope, $http, $filter) {
								  
		$scope.myFunction1 = function(fecha){
			var formattedDate = $filter('date')(fecha, 'yyyy-MM-dd');
        $http({
        	method: 'GET',
        	url: '/anidados/lista/',
        	params: {text: formattedDate},
        	headers : {'Content-type': 'application/json'}
        	}).then(function (response) {
            $scope.content = response.data;
      
        });
	}					
});