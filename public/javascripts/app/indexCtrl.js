app.controller('indexCtrl', function ($rootScope,$scope, $location, $http, usersService, $location) {
	
	$scope.users = [];
	
	usersService.listUsers().then(function(response){
		$scope.users = response.data;
	});
	
	$scope.logout = function(){
		usersService.logout();
		$location.path("/login");
	}
	
});





