var routeEditor = angular.module('routeEditor', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav']);
angular.module('addressFormatter', []).filter('address', function () {
    return function (input) {
        return input.street + ', ' + input.city + ', ' + input.state + ', ' + input.zip;
    };
});
routeEditor.controller('routeEditCtrl', ['$scope', '$rootScope','$http', 'Station', '$q', '$interval', 'stationsManager', function ($scope, $rootScope, $http, $q, $interval, stationService) {
        var vm = this;
        vm.gridOptions = {};
        vm.stationGrid = {};
        vm.stationsList = [];

        function loadStations() {
            $http({method: 'GET', url: 'http://localhost:8080/stations'})
                .then(function success(response) {
                    vm.stationsList = response.data;
                    vm.gridOptions.columnDefs[1].editDropdownOptionsArray = vm.stationsList;
                    vm.stationGrid.data = vm.stationsList;
                });
        }

        loadStations();

        function loadData(dataPath, dropDownPath, dropdowncoln, table) {
            $http.get('http://localhost:8080/' + dataPath)
                .then(function (response) {
                    table.data = response.data;
                });
/*            $http({method: 'GET', url: 'http://localhost:8080/' + dropDownPath})
                .then(function success(response) {
                    table.columnDefs[dropdowncoln].editDropdownOptionsArray = response.data;
                    /!*vm.stationsList = response.data;*!/
                });*/
        }

        loadData('routes', 'stations', '1', vm.gridOptions);
        loadData('stations', 'lines', '2', vm.stationGrid);
/*        $http.get('http://localhost:8080/stations')
            .then(function (response) {
                vm.stationGrid.data = response.data;
            });*/
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
                cellTemplate: '<button class="status-button btn btn-xs btn-danger" ng-click="vm.deleteRow(row)">Delete</button>'
            }
        ];
        vm.stationGrid.columnDefs = [
            {name: 'id', enableCellEdit: false, width: '10%'},
            {name: 'name', enableCellEdit: true, width: '10%'},
            {
                name: 'lineId',
                enableCellEdit: true,
                editableCellTemplate: 'ui-grid/dropdownEditor',
                width: '30%',
                editDropdownValueLabel: 'name',
                editDropdownIdLabel: 'id',
                cellFilter: 'griddropdown:this'
            },
            {name: 'timezone', enableCellEdit: true, width: '10%'},
        ];
        vm.deleteRow = function (row) {
            console.log("Perform delete");
        };

        function saveRowToDb(rowEntity, path, tableApi) {
            var deferred = $q.defer();
            tableApi.rowEdit.setSavePromise(rowEntity, deferred.promise);
            $http({
                method: 'PUT',
                url: 'http://localhost:8080/' + path,
                data: rowEntity
            }).then(function success(response) {
                    deferred.resolve(response.data);
                }, function error(response) {
                    deferred.reject(response.status);
                }
            );
            return deferred.promise;
        }

        vm.saveRowStation = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveStation', vm.stationGridApi)
        };
        vm.saveRowRoute = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveRoute', vm.gridApi)
        };
        vm.gridOptions.onRegisterApi = function (gridApi) {
            vm.gridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowRoute);
        };
        vm.stationGrid.onRegisterApi = function (gridApi) {
            vm.stationGridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowStation)
        }
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
