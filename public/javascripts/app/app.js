'use strict';

var app = angular.module('itehApplication', ['ngRoute','ngMaterial','ngStorage', 'angular-jwt']);

app.config(
	    function Config($routeProvider, $httpProvider, jwtInterceptorProvider, jwtOptionsProvider) {
	    
    	jwtInterceptorProvider.tokenGetter = function() {
    	    return localStorage.getItem('token');
    	 }
	     
	     jwtOptionsProvider.config({
	    	 whiteListedDomains: ['requestb.in','localhost', 'jsonplaceholder.typicode.com'],
	    	 authPrefix: ''
	     });
	    	
	     $routeProvider
	     	.when('/', {
	            templateUrl : 'assets/templates/index.html',
	            controller : 'indexCtrl'
	        })
	     	.when('/home', {
	            templateUrl : 'assets/templates/home.html',
	            controller : 'homeCtrl'
	        })
	     	.when('/test',{
	     		templateUrl : 'assets/templates/test.html',
	     		controller: 'testCtrl'
	     	})
	     	.when('/register',{
	     		templateUrl: 'assets/templates/register.html',
	     		controller: 'registerCtrl'
	     	})
	     	.when('/login',{
	     		templateUrl : 'assets/templates/login.html',
	     		controller: 'loginCtrl'
	     	})
	     	.when('/prijave',{
	     		templateUrl : 'assets/templates/prijave.html',
	     		controller: 'prijaveCtrl'
	     	})
	        .otherwise('/');
	       
	     $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	     $httpProvider.interceptors.push('jwtInterceptor');
	     $httpProvider.interceptors.push('authHttpResponseInterceptor');
	     
	     
	  
});

app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
	  $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
	  $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
	  $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
});