var infoPassApp = angular.module('infoPassApp', ['ui.bootstrap', 'toastr', 'ngAnimate', 'ngTouch', 'ui.select', 'ngSanitize', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.selection', 'routeEditor']);
infoPassApp.controller('infoPassAppCtrl', ['$scope', '$http', 'RouteManager', 'toastr', '$uibModal', '$log', '$document', 'toastrConfig', function ($scope, $http, RouteManager, toastr, $uibModal, $log, $document, toastrConfig) {
    var vm = this;
    vm.findPass = {};
    vm.stationFrom = {};
    vm.stationTimeTableForm = {};
    vm.Route = {};
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
    vm.searchForPassengers = function () {
        if (angular.equals(vm.Route, {})) {
            toastr.warning("Route is not selected!")
        }
        vm.findPass.data = [];
        loadData('infoPass', vm.findPass,
            formatDate(vm.df),
            vm.Route.id);
        //TODO open accordion
    };
    vm.findPass = {
        columnDefs: [
            {name: 'id', width: '10%'},
            {name: 'name', width: '20%'},
            {name: 'surname', width: '20%'},
            {name: 'birthday', width: '10%'},
            {name: 'stationFrom', width: '20%'},
            {name: 'stationTo', width: '20%'}
        ],
        data: []
    };
    vm.findPass.rowHeight = 30;
    vm.findPass.onRegisterApi = function (gridApi) {
        vm.gridApi = gridApi;
    };

    function loadData(dataPath, table, dateFrom, stFrom) {
        $http.get('http://localhost:8080/' + dataPath,
            {params: {routeId: stFrom, dayFrom: dateFrom}})
            .then(function (response) {
                table.data = response.data;
                if (response.data.length === 0) {
                    toastr.warning('Nothing to show', "");
                } else {
                    toastr.success(response.data.length + ' rows loaded', "", {"positionClass": "toast-bottom-right"})
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
    vm.routeList = [];
    RouteManager.loadAll().then(function (data) {
        vm.routeList = data;
    });
    //-----------Notification--------------------------
    vm.showSuccessMsg = function (msg) {
        toastr.success(msg);
    };
    /*Date picker zone--->*/
    vm.today = function () {
        vm.df = new Date();
    };
    vm.today();
    vm.clear = function () {
        vm.df = null;
    };
    //****
    vm.dateOptions = {
        //dateDisabled: disabled,
        formatYear: 'yyyy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(1999, 1, 1),
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

