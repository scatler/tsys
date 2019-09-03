var routeEditor = angular.module('routeEditor', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.selection']);
angular.module('addressFormatter', []).filter('address', function () {
    return function (input) {
        return input.street + ', ' + input.city + ', ' + input.state + ', ' + input.zip;
    };
});
routeEditor.controller('routeEditCtrl', ['$scope', '$rootScope', '$http', '$q', '$timeout', 'stationsManager', 'Station', '$interval', function ($scope, $rootScope, $http, $q, $timeout, stationsManager, Station, $interval, uiGridConstants) {
        var vm = this;
        vm.gridOptions = {};
        vm.stationGrid = {};
        vm.stationList = {};
        stationsManager.loadAll().then(function (data) {
            vm.stationList = data;
            vm.stationGrid.data = vm.stationList;
            vm.gridOptions.columnDefs[1].editDropdownOptionsArray = vm.stationList;
            //vm.gridOptions.columnDefs[1].editDropdownOptionsArray = angular.copy(vm.stationGrid.data);
        });

        function loadData(dataPath, dropDownPath, dropdowncoln, table) {
            $http.get('http://localhost:8080/' + dataPath)
                .then(function (response) {
                    table.data = response.data;
                });
        }

        loadData('routes', 'stations', '1', vm.gridOptions);
        /*        vm.gridOptions = {
                    enableRowSelection: false,
                    enableSelectAll: false,
                    showGridFooter: true
                };*/
        vm.gridOptions.columnDefs = [
            {name: 'id', enableCellEdit: true, width: '10%'},
            {name: 'routeId', enableCellEdit: true, width: '10%'},
            {
                name: 'stationId',
                displayName: 'Station',
                editableCellTemplate: 'ui-grid/dropdownEditor',
                width: '30%',
                editDropdownValueLabel: 'name',
                editDropdownIdLabel: 'id',
                cellFilter: 'griddropdown:this',
            },
            {name: 'arrivalTime', enableCellEdit: true, width: '10%'},
            {name: 'stopMin', enableCellEdit: true, width: '10%'},
            {name: 'day', width: '10%', enableCellEdit: true},
            {
                name: 'Delete',
                width: '10%',
                cellTemplate: '<button type="button" class="btn btn-danger btn-icon"><i class="ion-nuclear"></i></button>'
            }
        ];
        vm.gridOptions.enableFiltering = true;
        vm.stationGrid.columnDefs = [
            {name: 'id', enableCellEdit: false, width: '10%'},
            {name: 'name', enableCellEdit: true, width: '30%'},
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
        /*Copy rows function*/
        vm.copyRows = function () {
            var selectedObject = vm.gridApi.selection.getSelectedRows(),
                newData = [],
                selectedIndexData = [],
                newDataArray = [];
                angular.forEach(selectedObject, function (data) {
                newData = angular.copy(data);
                selectedIndexData.push(vm.gridOptions.data.indexOf(data));
                newDataArray.push(newData);
            });
            for (var i = 0; i < newDataArray.length; i++) {
                newDataArray[i].id = null;
                vm.gridOptions.data.splice((selectedIndexData[i] + i + 1), 0, newDataArray[i]); //Insert after selected row
            }
            $interval(function () {
                vm.gridApi.rowEdit.setRowsDirty(newDataArray);
            }, 0, 1);
        };
        vm.addData = function () {
            vm.stationList.push(new Station({"id": null, "name": "sta1", "lineId": 1}));
            vm.gridApi.core.notifyDataChange('all');
            vm.stationGridApi.core.notifyDataChange('all');
            console.log('mes');
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
                    rowEntity.id = 787878;
                }, function error(response) {
                    deferred.reject(response.status);
                }
            );
            return deferred.promise;
        }

        vm.saveRowStation = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveStation', vm.stationGridApi);
            $timeout(function () {
                vm.gridApi.core.notifyDataChange('all');
                vm.gridApi.core.queueGridRefresh();
                vm.gridApi.core.refresh();
                vm.gridApi.core.refreshRows();
                vm.gridApi.core.clearAllFilters(true, true, true);
                stationsManager.loadAll().then(function (data) {
                    vm.gridOptions.columnDefs[1].editDropdownOptionsArray = data;
                    //vb.gridOptions.columnDefs[1].cellFilter =
                });
                console.log('notified');
                $rootScope.$apply();
            }, 500);
        };
        vm.scrollToFocus = function (rowIndex, colIndex) {
            vm.gridApi.cellNav.scrollToFocus(vm.gridOptions.data[rowIndex], vm.gridOptions.columnDefs[colIndex]);
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
            griddropdown.$stateful = true;
            return input;
        };
    });
