# Java REST API example with MongoDB.
 
MongoDB is a popular NoSQL database that is widely used in modern web applications. When using MongoDB with Spring Boot, you can use Spring Data MongoDB, which provides a set of abstractions and utilities that make it easier to work with MongoDB in a Spring application.

CRUD operations are the most basic operations that you can perform on a database. In MongoDB, CRUD operations refer to the following operations: Create, Read, Update, and Delete. Let's go through each operation and see how you can perform it with Spring Boot.

# Project Structure.

![test](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/0.PNG?raw=true)
# Creating server objects

Request Method – POST

Request URL – localhost:8080/api/v1/createServer

Data is passed in json format to create a server object.
```java
@PostMapping("/createServer")
	public Server createServer(@RequestBody Server server) {
		return serverRepository.save(server);
	}

```

![2](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/1.png?raw=true)

# Get all server objects

Request Method – GET

Request URL – localhost:8080/api/v1/getAllServers, localhost:8080/api/v1/server/{id}

If no arguments are provided,it returns all servers.One server is returned when the server id is supplied as a parameter, and 404 error code if no server is present.

```java
@GetMapping("/getAllServers")
	public List<Server> getAllServers() {
		return serverRepository.findAll();
	}
 ```
 ```java

@GetMapping("/server/{id}")
public ResponseEntity<Server> getServerById(@PathVariable(value = "id") Long serverId)
			throws ResourceNotFoundException {
		Server server = serverRepository.findById(serverId)
				.orElseThrow(() -> new ResourceNotFoundException("Server not found for this id :: " + serverId));
		return ResponseEntity.ok().body(server);
	}


```

# Get all server objects at once
![2](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/2.png?raw=true)

# Get server object by id
![2](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/5.PNG?raw=true)

# Server object not found
![2](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/6.PNG?raw=true)



# Updating server object

Request Method – PUT

Request URL – localhost:8080/api/v1/updateServers/{id}

The parameters of the server object can be altered by passing the values.

```java
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


```

![2](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/3.png?raw=true)


# Deleting server object

Request Method – DELETE

Request URL – localhost:8080/api/v1/deleteServers/{id}

Server objects can be deleted by passing the parameters

```java
@DeleteMapping("/deleteServers/{id}")
public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long serverId) throws ResourceNotFoundException {
		Server server = serverRepository.findById(serverId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + serverId));

		serverRepository.delete(server);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}



```

![2](https://github.com/rock-man-ctrl/mongodb_curd_operations/blob/main/screenshots/4.png?raw=true)

# Conclusion

I created a Rest CRUD API in MongoDB using Spring Boot, Spring Data MongoDB, and Maven to create, read, update, and delete server objects.

You can also see that MongoRepository provides a great way to perform CRUD operations and create custom finder methods without having to write boilerplate code.

