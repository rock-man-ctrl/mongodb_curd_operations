package com.kaiburr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaiburr.exception.ResourceNotFoundException;
import com.kaiburr.model.Server;
import com.kaiburr.repository.ServerRepository;

@RestController
@RequestMapping("/api/v1")
public class ServerController {
	@Autowired
	private ServerRepository serverRepository;

	@GetMapping("/getAllServers")
	public List<Server> getAllServers() {
		return serverRepository.findAll();
	}

	@GetMapping("/server/{id}")
	public ResponseEntity<Server> getServerById(@PathVariable(value = "id") Long serverId)
			throws ResourceNotFoundException {
		Server server = serverRepository.findById(serverId)
				.orElseThrow(() -> new ResourceNotFoundException("Server not found for this id :: " + serverId));
		return ResponseEntity.ok().body(server);
	}

	@PostMapping("/createServer")
	public Server createServer(@RequestBody Server server) {
		return serverRepository.save(server);
	}

	@PutMapping("/updateServers/{id}")
	public ResponseEntity<Server> updateServer(@PathVariable(value = "id") Long serverId,
			 @RequestBody Server serverDetails) throws ResourceNotFoundException {
		Server server = serverRepository.findById(serverId)
				.orElseThrow(() -> new ResourceNotFoundException("Server not found for this id :: " + serverId));

		server.setId(serverDetails.getId());
		server.setName(serverDetails.getName());
		server.setLanguage(serverDetails.getLanguage());
		server.setFramework(serverDetails.getFramework());

		Server updatedServer = serverRepository.save(server);
		return ResponseEntity.ok(updatedServer);
	}

	
	@DeleteMapping("/deleteServers/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long serverId)
			throws ResourceNotFoundException {
		Server server = serverRepository.findById(serverId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + serverId));

		serverRepository.delete(server);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
