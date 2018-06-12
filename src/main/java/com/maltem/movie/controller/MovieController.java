package com.maltem.movie.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maltem.movie.entity.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
	 /**
	  * list all the movie data
	  * @return
	  */
	 @RequestMapping(value="list", method=RequestMethod.GET, consumes="application/json") 
	 public List<Movie> listAll (){
		 ObjectMapper mapper = new ObjectMapper();
		 List<Movie> movies = new ArrayList<Movie>();
		try {
			TypeReference<List<Movie>> mapType = new TypeReference<List<Movie>>() {};
			InputStream is = TypeReference.class.getResourceAsStream("/static/movies.json");
			movies = mapper.readValue(is, mapType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return movies;
	 }
	 
	 /**
	  * get the movie base on name
	  * @param name
	  * @return
	  */
	 @RequestMapping(value="get/{name}", method=RequestMethod.GET, consumes="application/json") 
	 public Movie getMovieByName (@PathVariable String name){
		 Movie movie = new Movie();
		 movie.setTitle("Ocean's 8");
		 movie.setDirector("Gary Ross");
		 Calendar cal = Calendar.getInstance();
		 cal.set(2018, 06, 13);
		 movie.setReleaseDate(cal.getTime());
		 movie.setType("action");
		 //String jsonInString = mapper.writeValueAsString(obj);
		 return movie;
	 }
	 
	 
}
