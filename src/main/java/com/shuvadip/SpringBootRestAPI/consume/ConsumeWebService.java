package com.shuvadip.SpringBootRestAPI.consume;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shuvadip.SpringBootRestAPI.model.Product;

@RestController
public class ConsumeWebService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/template/products")
	public String getProductList(){
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> httpEntity = new HttpEntity<String> (httpHeaders);
		
		return restTemplate.exchange("http://localhost:8080/products",HttpMethod.GET,httpEntity,String.class).getBody();
	}
	
	 @RequestMapping(value = "/template/products", method = RequestMethod.POST)
	   public String createProducts(@RequestBody Product product) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	   }
	   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.PUT)
	   public String updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products/"+id, HttpMethod.PUT, entity, String.class).getBody();
	   }
	   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.DELETE)
	   public String deleteProduct(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }

}
