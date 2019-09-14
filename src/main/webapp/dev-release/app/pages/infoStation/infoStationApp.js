var infoStationApp = angular.module('infoStationApp', ['ui.bootstrap', 'toastr', 'ngAnimate','ngTouch', 'ui.select', 'ngSanitize', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.selection', 'routeEditor']);


infoStationApp.controller('infoStationCtrl', ['$scope', '$http', 'stationsManager','toastr','$uibModal', '$log','$document', 'toastrConfig',function ($scope, $http, stationsManager,toastr, $uibModal, $log, $document,  toastrConfig) {
    var vm = this;
    vm.findTrains = {};
    vm.stationFrom = {};
    vm.stationTimeTableForm = {};

    vm.panels = {
        infoTable: false,
        infoForm: false,

    };

    vm.toastOptions = {
        "autoDismiss": false,
        "positionClass": "toast-bottom-right",
        "timeOut": "5000",
        "extendedTimeOut": "2000",
        "allowHtml": false,
        "closeButton": false,
        "tapToDismiss": true,
        "progressBar": false,
        "newestOnTop": true,
        "maxOpened": 0,
        "preventDuplicates": false,
        "preventOpenDuplicates": false
    };

    angular.extend(toastrConfig, vm.toastOptions);

    vm.searchForTrains = function (dateFrom, stFrom) {
        if (angular.equals(vm.stationFrom,{})) {
            toastr.warning("Station is not selected!")
        }

        vm.findTrains.data = [];
        loadData('infoStation', vm.findTrains,
            formatDate(vm.df),
            vm.stationFrom.id);
        //TODO open accordion
    };
    vm.findTrains = {
        columnDefs: [
                {name: 'trainId', width: '25%'},
                {name: 'routeId', width: '25%'},
                {name: 'routeName', width: '25%'},
                {name: 'arrivalTime', width: '25%'},
        ],
        data: []
    };

    vm.findTrains.rowHeight = 30;
    vm.findTrains.onRegisterApi = function (gridApi) {
        vm.gridApi = gridApi;
    };





/*    vm.oneAtATime = true;
    vm.groups = [
        {
            title: 'Dynamic Group Header - 1',
            content: 'Dynamic Group Body - 1'
        },
        {
            title: 'Dynamic Group Header - 2',
            content: 'Dynamic Group Body - 2'
        }
    ];*/

/*    vm.addItem = function () {
        var newItemNo = $scope.items.length + 1;
        $scope.items.push('Item ' + newItemNo);
    };*/
/*    vm.status = {
        isCustomHeaderOpen: false,
        isFirstOpen: true,
        isFirstDisabled: false
    };
    */




    function loadData(dataPath, table, dateFrom, stFrom) {
        $http.get('http://localhost:8080/' + dataPath,
            {params: {stationFrom: stFrom, dayFrom: dateFrom }})
            .then(function (response) {
                table.data = response.data;
                if (response.data.length === 0) {

                    toastr.warning('Nothing to show',"");
                }
                else {
                    toastr.success(response.data.length + ' rows loaded',"", {"positionClass": "toast-bottom-right"})
                }
            });
    }


    /*My function*/


    function formatDate(date) {
        var day = date.getDate();
        var monthIndex = date.getMonth() + 1;
        var year = date.getFullYear();
        return monthIndex + '/' + day + '/' + year;
    }

    /*Panels*/

    /*Dropdown menu*/
    vm.stationList = [];
    stationsManager.loadAll().then(function (data) {
        vm.stationList = data;
    });

    //-----------Notification--------------------------
    vm.showSuccessMsg = function (msg) {
        toastr.success(msg);
    };


    /*Date picker zone--->*/
    vm.today = function () {
        vm.dt = new Date();
        vm.df = new Date();
    };
    vm.today();
    vm.clear = function () {
        vm.dt = null;
    };

    //****
    vm.dateOptions = {
        //dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };
    // Disable weekend selection

    //****
    vm.open1 = function () {
        vm.popup1.opened = true;
    };
    vm.open2 = function () {
        vm.popup2.opened = true;
    };
    vm.open3 = function () {
        vm.popup3.opened = true;
    };
    vm.setDate = function (year, month, day) {
        vm.dt = new Date(year, month, day);
    };
    vm.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    vm.format = vm.formats[1];
    vm.altInputFormats = ['M!/d!/yyyy'];
    vm.popup1 = {
        opened: false
    };
    vm.popup2 = {
        opened: false
    };
    vm.popup3 = {
        opened: false
    }
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 1);
    vm.events = [
        {
            date: tomorrow,
            status: 'full'
        },
        {
            date: afterTomorrow,
            status: 'partially'
        }
    ];

    function getDayClass(data) {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);
            for (var i = 0; i < vm.events.length; i++) {
                var currentDay = new Date(vm.events[i].date).setHours(0, 0, 0, 0);
                if (dayToCheck === currentDay) {
                    return vm.events[i].status;
                }
            }
        }
        return '';
    }

    /*<----Date picker zone*/
    /*Ticket*/
    vm.birthday = new Date();
    vm.userForm = {
        name: '',
        surname: '',
        birthday: '',
        station1Id: vm.stationTo,
        station2Id: vm.stationFrom,
        trd: ''
    };
/*    vm.testForm = {
        //id: 1,
        name: 'JS',
        surname: 'Spring',
        birthday: '2019/09/06',
        station1Id: 1011,
        station2Id: 1013,
        trd: 70
    };*/
/*    vm.ticketId = {};*/

/*    vm.enterPesonalInfo = function(trd) {
        vm.panels.ticketOpen = true;
        vm.userForm.trd = trd;
    };

    vm.buyTicket = function () {

        vm.userForm.station1Id = vm.stationFrom.id;
        vm.userForm.station2Id = vm.stationTo.id;
        vm.userForm.birthday = formatDate(vm.userForm.birthday);

        $http.post('http://localhost:8080/saveTicket', vm.userForm).success(function (response) {
            if (response) {
               /!* vm.ticketId = response.data;*!/
                vm.ticketId = response;
                vm.panels.infoOpen = true;
                vm.panels.trainOpen = false;
                vm.panels.ticketOpen = false;
                vm.showSuccessMsg('You bought a ticket');
                //vm.openModal();
            } else {

            }
        })
            .catch(function (data, status, headers, config) {
                toastr.warning('Something wrong', data.data.error, {})
            })
    }*/

/*
    function openModal (msg) {
        vm.items = msg;
        vm.open();
    }
*/


    //----------------------Modal
 /*   vm.items = 'Hello!';

    vm.animationsEnabled = true;

    vm.open = function (size, parentSelector) {
        var parentElem = parentSelector ?
            angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
        var modalInstance = $uibModal.open({
            animation: vm.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'myModalContent.html',
            controller: 'AccordionDemoCtrl',
            controllerAs: '$ctrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                items: function () {
                    return vm.items;
                }
            }
        });


        modalInstance.result.then(function (selectedItem) {
            vm.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };



    vm.openComponentModal = function () {
        var modalInstance = $uibModal.open({
            animation: vm.animationsEnabled,
            component: 'modalComponent',
            resolve: {
                items: function () {
                    return vm.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            vm.selected = selectedItem;
        }, function () {
            $log.info('modal-component dismissed at: ' + new Date());
        });
    };

    vm.openMultipleModals = function () {
        $uibModal.open({
            animation: vm.animationsEnabled,
            ariaLabelledBy: 'modal-title-bottom',
            ariaDescribedBy: 'modal-body-bottom',
            templateUrl: 'stackedModal.html',
            size: 'sm',
            controller: function($scope) {
                $scope.name = 'bottom';
            }
        });

        $uibModal.open({
            animation: vm.animationsEnabled,
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'stackedModal.html',
            size: 'sm',
            controller: function($scope) {
                $scope.name = 'top';
            }
        });
    };

    vm.toggleAnimation = function () {
        vm.animationsEnabled = !vm.animationsEnabled;
    };*/

}]);

