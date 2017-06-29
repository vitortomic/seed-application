app.controller('registerCtrl', function($scope, $mdDialog, usersService, $location){
	$scope.register = function(ev){
		if($scope.user.password != $scope.repeatPassword){
			showErrorAlert(ev);
			return;
		}
		result = usersService.registerUser($scope.user);
		result.then(function(response){
			alert("Uspesna registracija");
			$scope.goToLogin();
		}, function(error){
			alert("Neuspeh: " + error.data);
		});
	}
	
	$scope.goToLogin = function(){
		$location.path("/login");
	}
	
	var showErrorAlert = function(ev){
		$mdDialog.show(
			      $mdDialog.alert()
			        .parent(angular.element(document.querySelector('#popupContainer')))
			        .clickOutsideToClose(true)
			        .title('Sifre se ne poklapaju!')
			        .textContent('Molimo unesite sifru ponovo')
			        .ariaLabel('Alert Dialog Demo')
			        .ok('Okej')
			        .targetEvent(ev)
		);
	}
	
});