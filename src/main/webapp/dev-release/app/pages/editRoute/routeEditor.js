var routeEditor = angular.module('routeEditor', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav']);
angular.module('addressFormatter', []).filter('address', function () {
    return function (input) {
        return input.street + ', ' + input.city + ', ' + input.state + ', ' + input.zip;
    };
});
routeEditor.controller('routeEditCtrl', ['$scope', '$rootScope', '$http', '$q', '$interval', function ($scope, $rootScope, $http, $q, $interval, stationService) {
        var vm = this;
        vm.gridOptions = {};
        vm.stationGrid = {};

        function loadData(dataPath, dropDownPath, dropdowncoln, table) {
            $http.get('http://localhost:8080/' + dataPath)
                .then(function (response) {
                    table.data = response.data;
                });
            $http({method: 'GET', url: 'http://localhost:8080/' + dropDownPath})
                .then(function success(response) {
                    table.columnDefs[dropdowncoln].editDropdownOptionsArray = response.data;
                });
        }

        loadData('routes', 'stations', '1', vm.gridOptions);
        loadData('stations', 'lines', '1', vm.stationGrid);
        $http.get('http://localhost:8080/stations')
            .then(function (response) {
                vm.stationGrid.data = response.data;
            });
        vm.gridOptions.columnDefs = [
            {name: 'routeId', enableCellEdit: false, width: '10%'},
            {
                name: 'stationId', displayName: 'Station', editableCellTemplate: 'ui-grid/dropdownEditor', width: '30%',
                editDropdownValueLabel: 'name', editDropdownIdLabel: 'id', cellFilter: 'griddropdown:this'
            },
            {name: 'arrivalTime', enableCellEdit: true, width: '10%'},
            {name: 'stopMin', enableCellEdit: true, width: '10%'},
            {name: 'day', width: '10%', enableCellEdit: true},
            {
                name: 'Delete',
                width: '10%',
                cellTemplate: '<button class="status-button btn btn-xs btn-danger" ng-click="grid.appScope.deleteRow(row)">Delete</button>'
            }
        ];
        vm.stationGrid.columnDefs = [
            {name: 'stationId', enableCellEdit: false, width: '10%'},
            {name: 'name', enableCellEdit: true, width: '10%'},
            {
                name: 'line',
                enableCellEdit: true,
                editableCellTemplate: 'ui-grid/dropdownEditor',
                width: '30%',
                editDropdownValueLabel: 'name',
                editDropdownIdLabel: 'id',
                cellFilter: 'griddropdown:this'
            }
        ];
        vm.deleteRow = function (row) {
            console.log("Perform delete");
        };
        vm.saveRow = function (rowEntity) {
            var deferred = $q.defer();
            vm.gridApi.rowEdit.setSavePromise(rowEntity, deferred.promise);
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
        vm.gridOptions.onRegisterApi = function (gridApi) {
            //set gridApi on scope
            vm.gridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRow);
        };
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
