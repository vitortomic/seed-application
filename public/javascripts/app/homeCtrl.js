app.controller('homeCtrl', function ($rootScope,$scope, $location, usersService, $http) {
	$scope.ispisi = function(){
		if(!$scope.user || !$scope.user.firstName || !$scope.user.lastName || !$scope.user.dateOfBirth){
			alert("sva polja su obavenza!");
			return;
		}
		ispisiPodatke($scope.user);
	}

	var ispisiPodatke = function(user){
		var ispis = "Zdravo " + user.firstName + ", prezivas se " + user.lastName;

		if(isPunoletan(user)){
			ispis += " i stariji si od 18 godina";
		}else{
			ispis +=" i mladji si od 18 godina";
		}
		alert(ispis);
	}

	var isPunoletan = function(user){
		var starost = new Date().getFullYear() - user.dateOfBirth.getFullYear();
		return starost >= 18 ? true : false;
	}

	/*
	//jedan nacin
	var popuniUserePrekoServisa = function(){
		usersService.getAllUsersPromise().then(function(users){
			$scope.users = users;
		})
	}

	popuniUserePrekoServisa();
	*/

	//laksi
	$http.get("https://jsonplaceholder.typicode.com/users/").then(function(response){
				$scope.users =  response.data;
	});

	$scope.pop = function(){
		$scope.users.splice(0,1);
	}
	
	$scope.oniKojimaJeIdVeciOd5 = function(){
		$scope.users = $scope.users.filter(function(user){
			return user.id>5;
		});
	}
});