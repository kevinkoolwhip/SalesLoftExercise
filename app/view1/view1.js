'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope', '$http', function($scope, $http) {
  $http({
    method: 'GET',
    url: 'http://localhost:8081/person'
  }).then(function successCallback(response) {
    console.log(response.data)
    $scope.persons = response.data;
  });
}]);