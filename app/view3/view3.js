'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view3', {
    templateUrl: 'view3/view3.html',
    controller: 'View3Ctrl'
  });
}])

.controller('View3Ctrl', ['$scope', '$http', function($scope, $http) {
  $http({
    method: 'GET',
    url: 'http://localhost:8081/email/unique'
  }).then(function successCallback(response) {
    console.log(response.data)
    $scope.duplicate = response.data;
  });
}]);