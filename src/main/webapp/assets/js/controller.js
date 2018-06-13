var app = angular.module('movieApp',[]);

app.controller('movieController', ['$scope','MovieCRUDService',function ($scope, MovieCRUDService) {
	$scope.updateMovie = function () {
		MovieCRUDService.updateMovie($scope.movie.title,$scope.movie.director,$scope.movie.releaseDate,$scope.movie.type)
          .then(function success(response){
              $scope.successmessage = 'Movie updated successful!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error for updating movie!' + response;
              $scope.successmessage = '';
          });
    }
    
    $scope.getMovie = function () {
        var title = $scope.movie.title;
        MovieCRUDService.getMovie($scope.movie.title)
          .then(function success(response){
              $scope.movie = response.data;
              $scope.movie.title = title;
              $scope.successmessage='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.successmessage = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Movie not found!';
              }
              else {
                  $scope.errorMessage = "Error for getting movie!" + response ;
                  $scope.successmessage = response;
              }
          });
    }
    
    $scope.addMovie = function () {
        if ($scope.movie != null && $scope.movie.title) {
        	MovieCRUDService.addMovie($scope.movie.title,$scope.movie.director,$scope.movie.releaseDate,$scope.movie.type)
              .then (function success(response){
                  $scope.successmessage = 'Movie added successful!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error for adding movie!';
                  $scope.successmessage = response;
            });
        }
        else {
            $scope.errorMessage = 'Please enter a title!';
            $scope.successmessage = '';
        }
    }
    
    $scope.deleteMovie = function () {
    	MovieCRUDService.deleteMovie($scope.movie.title)
          .then (function success(response){
              $scope.message = 'Movie deleted successful!';
              $scope.movie = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error for deleting movie!';
              $scope.successmessage=response;
          })
    }
    
    $scope.getAllMovies = function () {
    	MovieCRUDService.getAllMovies()
          .then(function success(response){
              $scope.movies = response.data._embedded.movies;
              $scope.successmessage='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.successmessage=response;
              $scope.errorMessage = 'Error for getting all movies!';
          });
    }
}]);

app.service('MovieCRUDService',['$http', function ($http) {
	
    this.getMovie = function getMovie(title){
        return $http({
          method: 'GET',
          url: 'http://localhost:8080/movie/'+title
        });
	}
	
    this.addMovie = function addMovie(title, director, releaseDate, type){
        return $http({
          method: 'POST',
          url: 'http://localhost:8080/movie/',
          data: {title:title, director:director, releaseDate:releaseDate, type:type}
        });
    }
	
    this.deleteMovie = function deleteMovie(title){
        return $http({
          method: 'DELETE',
          url: 'http://localhost:8080/movie/'+title
        })
    }
	
    this.updateMovie = function updateMovie(title, director, releaseDate, type){
        return $http({
          method: 'PUT',
          url: 'http://localhost:8080/movie/',
          data: {title:title, director:director, releaseDate:releaseDate, type:type}
        })
    }
	
    this.getAllMovies = function getAllMovies(){
        return $http({
          method: 'GET',
          url: 'http://localhost:8080/movie/list',
          headers: {'Content-Type': 'application/json'}
        });
    }

}]);
