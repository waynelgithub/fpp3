 var app = angular.module("UserManagement", []);
     
      //Controller Part
      app.controller("UserManagementController", function($scope, $http) {
     
        //Initialize page with default data which is blank in this example
        $scope.house = [];
        $scope.form = {
          houseId : -1,
          description : "",
		  price  : "",
		  houseType  : "",
		  houseStatus   : "",
		  housePattern   : "",
		  houseCity   : "",
		  houseArea   : "",
		  address   : "",
		  houseSize   : "",
		  houseOwner   : "",
		  createdDT   : "",
		  updatedDT   : "",
		  gmapURL   : ""
        };
     
        //Now load the data from server
        _refreshPageData();
     
        //HTTP POST/PUT methods for add/edit employee
        $scope.submitHouse = function() {
     
          var method = "";
          var url = "";
          if ($scope.form.houseId == -1) {
            //Id is absent so add employee - POST operation
            method = "POST";
            url = 'house2';
          } else {
            //If Id is present, it's edit operation - PUT operation
            method = "PUT";
            url = 'house2/' + $scope.form.houseId;
          }
     
          $http({
            method : method,
            url : url,
            data :  angular.toJson($scope.form)  ,
             headers : {
              'Content-Type' : 'application/json'
            }          
          }).then( _success, _error );
        };
     
        //HTTP DELETE- delete employee by Id
        $scope.removeHouse = function(house) {
          $http({
            method : 'DELETE',
            url : 'house2/' + house.houseId
          }).then(_success, _error);
        };
 
        //In case of edit employee, populate form with employee data
        $scope.editHouse = function(house) {
          $scope.form.description = house.description;
          $scope.form.price = house.price;
          $scope.form.houseType = house.houseType;
          $scope.form.houseStatus = house.houseStatus;
          $scope.form.housePattern = house.housePattern;
          $scope.form.houseCity = house.houseCity;
          $scope.form.houseArea = house.houseArea;
          $scope.form.address = house.address;
          $scope.form.houseSize = house.houseSize;
          $scope.form.houseOwner = house.houseOwner;
          $scope.form.createdDT = house.createdDT;
          $scope.form.updatedDT = house.updatedDT;
          $scope.form.gmapURL = house.gmapURL;
          $scope.form.houseId = house.houseId;
         
        };
     
        /* Private Methods */
        //HTTP GET- get all employees collection
        function _refreshPageData() {
          $http({
            method : 'GET',
            url : 'house2'
          }).then(function successCallback(response) {
            $scope.house = response.data.house;
          }, function errorCallback(response) {
            console.log(response.statusText);
          });
        }
     
        function _success(response) {
          _refreshPageData();
          _clearForm();
        }
     
        function _error(response) {
          console.log(response.statusText);
           
        }
     
        //Clear the form
        function _clearForm() {
          
          $scope.form.description = "";
          $scope.form.price = "";
          $scope.form.houseType = "";
          $scope.form.houseStatus = "";
          $scope.form.housePattern = "";
          $scope.form.houseCity = "";
          $scope.form.houseArea = "";
          $scope.form.address = "";
          $scope.form.houseSize = "";
          $scope.form.houseOwner = "";
          $scope.form.createdDT = "";
          $scope.form.updatedDT = "";
          $scope.form.gmapURL = "";
          $scope.form.houseId = -1;
          
 
        };
      }
      
      );
