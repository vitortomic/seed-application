'use strict';

var app = angular.module('itehApplication', ['ngRoute','ngMaterial']);

app.config(
	    function Config($routeProvider, $httpProvider) {
	    
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
	        .otherwise('/');
	       
	     $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	  
});