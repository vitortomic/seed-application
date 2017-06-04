var app = angular.module('itehApplication');

app.factory('usersService', ['$http', function($http){
	return {
		//example
		getAllUsersPromise : function (){
			return $http.get("https://jsonplaceholder.typicode.com/users/").then(function(response){
				return response.data;
			});
		}
	}
}]);


