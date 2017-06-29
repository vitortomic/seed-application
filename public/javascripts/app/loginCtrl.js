app.controller('loginCtrl', function($scope, $location, usersService){
	
	$scope.goToRegister = function(){
		$location.path("/register");
	}
	
	$scope.login = function(){
		var result = usersService.login($scope.email, $scope.password);
		result.then(function(response){
			localStorage.setItem('token', response.data.token);
			$location.path("/index")
		}, function(error){
			alert("Neuspesan login");
		})
	}
	
});