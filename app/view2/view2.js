'use strict';

angular.module('myApp.view2', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'view2/view2.html',
            controller: 'View2Ctrl'
        });
    }])

    .controller('View2Ctrl', ['$scope', '$http', function ($scope, $http) {
        $http({
            method: 'GET',
            url: 'http://localhost:8081/person/unique'
        }).then(function successCallback(response) {
            console.log(response.data)
            $scope.emails = response.data;
        });
    }]);