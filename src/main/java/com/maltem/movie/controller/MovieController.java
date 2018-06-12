package com.maltem.movie.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.maltem.movie.entity.Movie;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	/**
	 * load the data from movies.json file
	 */
	public List<Movie> loadJsonFromFile() {
		ObjectMapper mapper = new ObjectMapper();
		List<Movie> movies = new ArrayList<Movie>();
		try {
			logger.info("start loading the json file");
			TypeReference<List<Movie>> mapType = new TypeReference<List<Movie>>() {};
			InputStream is = TypeReference.class.getResourceAsStream("/static/movies.json");
			movies = mapper.readValue(is, mapType);
			logger.info("Json size : {}" ,movies.size());
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
	  * list all the movie data
	  * @return
	  */
	 @RequestMapping(value="list", method=RequestMethod.GET, consumes="application/json") 
	 public List<Movie> listAll (){
		 List<Movie> movies = loadJsonFromFile();
		 logger.info("List all the movies");
		 return movies;
	 }
	 
	 /**
	  * get the movie base on name
	  * @param name
	  * @return
	  */
	 @RequestMapping(value="{title}", method=RequestMethod.GET, consumes="application/json") 
	 public Movie getMovieByTitle (@PathVariable String title){
		 logger.info("Get the movie with title {}", title);
		 if (title == null || "".equals(title)) {
			 return null;
		 }
		 
		 List<Movie> movies = loadJsonFromFile();
		 
		 for (Movie movie : movies) {
			 if (title.equals(movie.getTitle())) {
				 return movie;
			 }
		 }
		 return null;
	 }
	 
	 /**
	  * Delete the movie base on title
	  * @param title
	  * @return
	  */
	 @RequestMapping(value="{title}", method=RequestMethod.DELETE, consumes="application/json") 
	 public Movie deleteMovieByTitle (@PathVariable String title){
		 logger.info("Delete the movie with title {}", title);
		 if (title == null || "".equals(title)) {
			 return null;
		 }
		 
		 List<Movie> movies = loadJsonFromFile();
		 Movie removeMovie = null;
		 for (Movie movie : movies) {
			 if (title.equals(movie.getTitle())) {
				 removeMovie = movie;
			 }
		 }
		 if (removeMovie != null) {
			 movies.remove(removeMovie);

			 ObjectMapper mapper = new ObjectMapper();
			 ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			 
			 try {
				writer.writeValue(new File("C:\\huanping\\MovieDemo\\src\\main\\resources\\static\\movies.json"), movies);
			 } catch (JsonGenerationException e1) {
				e1.printStackTrace();
			 } catch (JsonMappingException e1) {
				e1.printStackTrace();
			 } catch (IOException e1) {
				e1.printStackTrace();
			 }
		 }
		
		 return removeMovie;
	 }
	 
	 /**
	  * Add the new movie into list
	  * @param movie
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.POST, consumes="application/json") 
	 public Movie addMovie (@RequestBody Movie movie){
		 logger.info("Add the movie {}", movie);
		 if (movie == null) {
			 return null;
		 }
		 
		 List<Movie> movies = loadJsonFromFile();
		 for (Movie submovie : movies) {
			 if (movie.equals(submovie)) {
				 return movie;
			 }
		 }
		 movies.add(movie);
		 ObjectMapper mapper = new ObjectMapper();
		 ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		 try {
			writer.writeValue(new File("C:\\huanping\\MovieDemo\\src\\main\\resources\\static\\movies.json"), movies);
		 } catch (JsonGenerationException e1) {
			e1.printStackTrace();
		 } catch (JsonMappingException e1) {
			e1.printStackTrace();
		 } catch (IOException e1) {
			e1.printStackTrace();
		 }
		 return movie;
	 }
	 
	 /**
	  * Update the movie
	  * @param movie
	  * @return
	  */
	 @RequestMapping(method=RequestMethod.PUT, consumes="application/json") 
	 public Movie updateMovie (@RequestBody Movie movie){
		 logger.info("Add the movie {}", movie);
		 if (movie == null) {
			 return null;
		 }
		 
		 List<Movie> movies = loadJsonFromFile();
		 Movie removeMovie = null;
		 for (Movie submovie : movies) {
			 if (movie.getTitle().equals(submovie.getTitle())) {
				 removeMovie = submovie;
			 }
		 }
		 movies.remove(removeMovie);
		 movies.add(movie);
		 ObjectMapper mapper = new ObjectMapper();
		 ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		 try {
			writer.writeValue(new File("C:\\huanping\\MovieDemo\\src\\main\\resources\\static\\movies.json"), movies);
		 } catch (JsonGenerationException e1) {
			e1.printStackTrace();
		 } catch (JsonMappingException e1) {
			e1.printStackTrace();
		 } catch (IOException e1) {
			e1.printStackTrace();
		 }
		 return movie;
	 }
	 
}
