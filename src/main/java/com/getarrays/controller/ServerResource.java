package com.getarrays.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getarrays.enumeration.Status;
import com.getarrays.model.Response;
import com.getarrays.model.Server;
import com.getarrays.service.implemention.ServerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

	private final ServerServiceImpl serverServiceIMPL;
	
	@GetMapping("/list")
	public ResponseEntity<Collection<Server>> getServers(){
		return ResponseEntity.ok(serverServiceIMPL.list(10));
				
	
	}
	
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Server> pingServer(@PathVariable String ipAddress) throws IOException{
		Server server = serverServiceIMPL.ping(ipAddress); 
		return ResponseEntity.ok(server);
				
	}
	
	
	@PutMapping("/save")
	public ResponseEntity<Server> saveServer(@RequestBody @Valid Server server) {
		
		return ResponseEntity.ok(serverServiceIMPL.create(server));
				
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Server> getServer(@PathVariable Long id){
		
		return ResponseEntity.ok(serverServiceIMPL.get(id));
				
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable Long id) throws IOException{
		return ResponseEntity.ok(
				Response.builder()
				.statusCode(HttpStatus.OK.value())
				.status(HttpStatus.OK)
				.message("Server deleted")
				.build()				
			);
				
	}
	
	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable String  fileName) throws IOException{
		
		return Files.readAllBytes(Paths.get(System.getProperty("user/home")
				+ "Downloads/images"
				+ fileName));
				
	}
}
