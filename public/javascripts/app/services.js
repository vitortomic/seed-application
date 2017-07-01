var app = angular.module('itehApplication');

app.filter('dateFilter', function(){
	return function(dateString){
		return new Date(dateString);
	}
});

app.filter('reverse', function() {
	  return function(items) {
	    return items.slice().reverse();
	  };
});

app.filter('phoneNumber', function () {
    return function (tel) {
        if (!tel) { return ''; }

        var value = tel.toString().trim().replace(/^\+/, '');

        if (value.match(/[^0-9]/)) {
            return tel;
        }

        var country, city, number;

        switch (value.length) {
            case 10: // +1PPP####### -> C (PPP) ###-####
                country = 1;
                city = value.slice(0, 3);
                number = value.slice(3);
                break;

            case 11: // +CPPP####### -> CCC (PP) ###-####
                country = value[0];
                city = value.slice(1, 4);
                number = value.slice(4);
                break;

            case 12: // +CCCPP####### -> CCC (PP) ###-####
                country = value.slice(0, 3);
                city = value.slice(3, 5);
                number = value.slice(5);
                break;

            default:
                return tel;
        }

        if (country == 1) {
            country = "";
        }

        number = number.slice(0, 3) + '-' + number.slice(3);

        return (country + " (" + city + ") " + number).trim();
    };
});

app.factory('usersService', ['$http', function($http){
	return {
		//example
		getAllUsersPromise : function (){
			return $http.get("https://jsonplaceholder.typicode.com/users/").then(function(response){
				return response.data;
			});
		},
		registerUser: function(user){
			return $http.post("/register", user);
		},
		login: function(email, password){
			return $http.post("/login",{
				"email": email,
				"password": password
			});
		},
		listUsers: function(){
			return $http.get("/users");
		},
		logout: function(){
			return $http.post("/logout")
		}
	}
}]);

app.factory('prijaveService', ['$http', function($http){
	return {
		
		listPrijave: function(){
			return $http.get("/prijave").then(function(response){
				return response.data;
			});
		},
		updatePrijavu: function(prijava){
			return $http.put("/update-prijavu", prijava).then(function(response){
				return response.data;
			})
		},
		deletePrijavu: function(prijava){
			return $http({
				url: '/obrisi-prijavu/' + prijava.id ,
				method: 'DELETE',
			}).then(function(response){
				
			})
		}
		
	}
}]);


app.factory('authHttpResponseInterceptor',['$q','$location',function($q,$location) {
    var getRedirectLink = function(){
    	return "/#!/login";
    }
	
	return {
        response: function(response){
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if (rejection.status === 401) {
                window.location = getRedirectLink();
            }
            return $q.reject(rejection);
        }
    };
}]);


app.factory('arrayUtils', [function(){
	var service = {
		removeObjectFromArrayByPropertyName: 
			function(object, propertyName, array){
				var indexForRemoval = array.map(function(element){
					return element["propertyName"];
				}).indexOf(object["propertyName"]);
				array.splice(indexForRemoval,1);
			}
		,
		removeObjectFromArray: 
			function(object, array){
				return array.filter(function(element){
					return !angular.equals(element,object);
				});
			}
			
	}
	return service;
}]);


app.factory('tokenService',['$localStorage',function($localStorage) {
	var service = {
			getToken: getToken
	};
	
	return service;
	
    function getToken() {
    	$localStorage.userToken = localStorage.getItem("userToken");
    	return $localStorage.userToken;
    }
}]);


var data = {nesto:"nesto", nestodrugo:"nestodrugo"}


app.factory('dateUtil', [function(){
	return {
		formatDateForApi: function(date){
			var dateString = "";
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			if(day.toString().length < 2) day = "0" + day;
			if(month.toString().length < 2) month = "0" + month;
			return year + "-" + month + "-" + day;
		}
	}
}]);

