var routeEditor = angular.module('routeEditor', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav']);
angular.module('addressFormatter', []).filter('address', function () {
    return function (input) {
        return input.street + ', ' + input.city + ', ' + input.state + ', ' + input.zip;
    };
});

routeEditor.controller('routeEditCtrl', ['$scope', '$rootScope', '$http', '$q', '$interval', function ($scope, $rootScope, $http, $q, $interval, stationService) {
        $scope.gridOptions = {};
        $http({method: 'GET', url: 'http://localhost:8080/stations'}).then(function success(response) {
            $scope.gridOptions.columnDefs[1].editDropdownOptionsArray = response.data;
        });
        $scope.gridOptions.columnDefs = [
            {name: 'routeId', enableCellEdit: false, width: '10%'},
            {
                name: 'stationId', displayName: 'Station', editableCellTemplate: 'ui-grid/dropdownEditor', width: '20%',
                editDropdownValueLabel: 'name', editDropdownIdLabel: 'id', cellFilter: 'griddropdown:this'
            },
            {name: 'arrivalTime', enableCellEdit: false, width: '10%'},
            {name: 'stopMin', enableCellEdit: false, width: '10%'},
            {name: 'day', width: '10%', enableCellEdit: true},
            {
                name: 'Delete',
                width: '10%',
                cellTemplate: '<button class="status-button btn btn-xs btn-danger" ng-click="grid.appScope.deleteRow(row)">Delete</button>'
            }
        ];
        $scope.deleteRow = function (row) {
            console.log("Perform delete");
        };
        $scope.saveRow = function (rowEntity) {
            // create a fake promise - normally you'd use the promise returned by $http or $resource
            var deferred = $q.defer();
            $scope.gridApi.rowEdit.setSavePromise(rowEntity, deferred.promise);
            $http({
                method: 'PUT',
                url: 'http://localhost:8080/saveRoute',
                data: rowEntity
            }).then(function success(response) {
                    deferred.resolve(response.data.question);
                }, function error(response) {
                    deferred.reject(response.status);
                }
            );
            return deferred.promise;
        };
        $scope.gridOptions.onRegisterApi = function (gridApi) {
            //set gridApi on scope
            $scope.gridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
        };
        // Refresh the grid, calling the appropriate rest method.
        $scope.refreshGrid = function () {
            stationService.get(function (data) {
                $scope.stations = data;
            })
        };
        $http.get('http://localhost:8080/routes')
            .then(function (response) {
                var data = response.data;
                for (i = 0; i < data.length; i++) {
                    data[i].registered = new Date(data[i].registered);
                }
                $scope.gridOptions.data = data;
            });
    }]
)
    .filter('griddropdown', function () {
        return function (input, context) {
            var map = context.col.colDef.editDropdownOptionsArray;
            var idField = context.col.colDef.editDropdownIdLabel;
            var valueField = context.col.colDef.editDropdownValueLabel;
            var initial = context.row.entity[context.col.field];
            if (typeof map !== "undefined") {
                for (var i = 0; i < map.length; i++) {
                    if (map[i][idField] == input) {
                        return map[i][valueField];
                    }
                }
            } else if (initial) {
                return initial;
            }
            return input;
        };
    });
