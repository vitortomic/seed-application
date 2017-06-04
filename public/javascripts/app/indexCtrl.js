app.controller('indexCtrl', function ($rootScope,$scope, $location, $http) {
	
	$scope.studenti;
	
	$http.get('/studenti').then(function(response){
		$scope.studenti = response.data;
		console.log($scope.studenti);
	}, function(error){
		console.log(error);
	});
});

http.get('/stagod').then(function(response){
	response.data
});





