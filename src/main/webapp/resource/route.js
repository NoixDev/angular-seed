angular.module('ngRoute', ['ngRoute'])

 .controller('RouteController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 })

.config(function($routeProvider, $locationProvider) {
  $routeProvider
   .when('/Login', {
    templateUrl: 'login.html',
    controller: 'LoginController',
    resolve: {
      // I will cause a 1 second delay
      delay: function($q, $timeout) {
        var delay = $q.defer();
        $timeout(delay.resolve, 1000);
        return delay.promise;
      }
    }	
  })
  .when('/home', {
    templateUrl: 'home.html',
    controller: 'HomeController'
  });

  // configure html5 to get links working on jsfiddle
  $locationProvider.html5Mode(true);
});