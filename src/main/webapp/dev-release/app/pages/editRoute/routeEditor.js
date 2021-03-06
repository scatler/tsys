var routeEditor = angular.module('routeEditor', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.autoResize', 'ui.grid.selection']);
routeEditor.controller('routeEditCtrl',
    function ($scope,
              $rootScope,
              $http,
              $q,
              $timeout,
              stationsManager,
              Station,
              $interval,
              RouteManager,
              Route,
              toastr,
              TrainManager,
              Train,
              TrdManager) {
        "ngInject";
        var vm = this;
        vm.gridOptions = {};
        vm.stationGrid = {};
        vm.routeGrid = {};
        vm.trainGrid = {};
        vm.trdGrid = {};
        vm.stationList = {};
        vm.routeList = {};
        vm.trainList = {};
        vm.allApi = [];
        vm.sendMessage = function () {
            $http.get('http://localhost:8080/sendMessage')
                .then(function (response) {
                    toastr.sendMessage("Message sent");
                });
        };
        RouteManager.loadAll().then(function (data) {
            vm.routeList = data;
            vm.routeGrid.data = data;
            vm.gridOptions.columnDefs[1].editDropdownOptionsArray = data;
        });
        TrainManager.loadAll().then(function (data) {
            vm.trainList = data;
            vm.trainGrid.data = data;
        });
        stationsManager.loadAll().then(function (data) {
            vm.stationList = data;
            vm.stationGrid.data = vm.stationList;
            vm.gridOptions.columnDefs[2].editDropdownOptionsArray = vm.stationList;
        });
        TrdManager.loadAll().then(function (data) {
            vm.trdList = data;
            vm.trdGrid.data = data;
        });

        function loadData(dataPath, table) {
            $http.get('http://localhost:8080/' + dataPath)
                .then(function (response) {
                    table.data = response.data;
                });
        }

        loadData('routesStations', vm.gridOptions);
        //loadData('routes', vm.routeGrid);
        vm.gridOptions.columnDefs = [
            {name: 'id', enableCellEdit: true, width: '10%'},
            {
                name: 'routeId',
                enableCellEdit: true,
                editableCellTemplate: 'ui-grid/dropdownEditor',
                width: '30%',
                editDropdownValueLabel: 'name',
                editDropdownIdLabel: 'id',
                cellFilter: 'griddropdown:this'
            },
            {
                name: 'stationId',
                displayName: 'Station',
                editableCellTemplate: 'ui-grid/dropdownEditor',
                width: '30%',
                editDropdownValueLabel: 'name',
                editDropdownIdLabel: 'id',
                cellFilter: 'griddropdown:this'
            },
            {name: 'arrivalTime', enableCellEdit: true, width: '10%'},
            {name: 'stopMin', enableCellEdit: true, width: '10%'},
            {name: 'day', width: '10%', enableCellEdit: true},
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
            {name: 'timezone', enableCellEdit: true, width: '30%'},
        ];
        vm.routeGrid.columnDefs = [
            {name: 'id', enableCellEdit: false, width: '20%'},
            {name: 'name', enableCellEdit: true, width: '80%'},
        ];
        //vm.routeGrid.enableRowHashing = false;
        vm.trainGrid.columnDefs = [
            {name: 'id', enableCellEdit: false, width: '10%'},
            {name: 'name', enableCellEdit: true, width: '30%'},
            {name: 'seats', enableCellEdit: true, width: '30%'},
        ];
        vm.trdGrid.columnDefs = [
            {name: 'trainId', enableCellEdit: false, width: '10%'},
            {name: 'routeId', enableCellEdit: true, width: '30%'},
            {name: 'day', enableCellEdit: true, width: '30%'}
        ];
        vm.deleteRow = function (row) {
            console.log("Perform delete");
        };
        /*Copy rows function*/
        vm.copyRows = function (tableApi) {
            var selectedObject = tableApi.selection.getSelectedRows(),
                newData = [],
                selectedIndexData = [],
                newDataArray = [];
            angular.forEach(selectedObject, function (data) {
                newData = angular.copy(data);
                selectedIndexData.push(tableApi.grid.options.data.indexOf(data));
                newDataArray.push(newData);
            });
            for (var i = 0; i < newDataArray.length; i++) {
                newDataArray[i].id = null;
                tableApi.grid.options.data.splice((selectedIndexData[i] + i + 1), 0, newDataArray[i]); //Insert after selected row
            }
            $interval(function () {
                tableApi.rowEdit.setRowsDirty(newDataArray);
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
                    rowEntity.id = response.data;
                    vm.refreshAllTables();
                }, function error(response) {
                    deferred.reject(response.status);
                }
            );
            return deferred.promise;
        }

        vm.scrollToFocus = function (rowIndex, colIndex) {
            vm.gridApi.cellNav.scrollToFocus(vm.gridOptions.data[rowIndex], vm.gridOptions.columnDefs[colIndex]);
        };
        /*Saving functions*/
        vm.saveRowStation = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveStation', vm.stationGridApi);
        };
        vm.saveRowRoute = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveRoute', vm.routeGridApi);
            $timeout(function () {
                $scope.$digest();
            }, 200);
        };
        vm.saveRowRouteStation = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveRouteStation', vm.gridApi)
        };
        vm.saveRowTrain = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveTrain', vm.trainGridApi);
        };
        vm.saveRowTrd = function (rowEntity) {
            saveRowToDb(rowEntity, 'saveTrd', vm.trdGridApi);
        };
        /*------------Saving function*/
        /*Register api------------*/
        vm.gridOptions.onRegisterApi = function (gridApi) {
            vm.gridApi = gridApi; //route->station
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowRouteStation);
            vm.allApi.push(gridApi);
        };
        vm.stationGrid.onRegisterApi = function (gridApi) {
            vm.stationGridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowStation);
            vm.allApi.push(gridApi);
        };
        vm.routeGrid.onRegisterApi = function (gridApi) {
            vm.routeGridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowRoute);
            vm.allApi.push(gridApi);
        };
        vm.trainGrid.onRegisterApi = function (gridApi) {
            vm.trainGridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowTrain);
            vm.allApi.push(gridApi);
        };
        vm.trdGrid.onRegisterApi = function (gridApi) {
            vm.trdGridApi = gridApi;
            gridApi.rowEdit.on.saveRow($scope, vm.saveRowTrd);
            vm.allApi.push(gridApi);
        }
        /*-----------Register api*/
    }
)
    .filter('griddropdown', function () {
        function mapFilter(input, context) {
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
        mapFilter.$stateful = true;
        return mapFilter;
    });
