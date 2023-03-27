package com.kaiburr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servers")
public class Server {

	@Id
	private long id;
	private String name;
	private String language;
	private String framework;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", name=" + name + ", language=" + language + ", framework=" + framework + "]";
	}

	public Server(long id, String name, String language, String framework) {

		this.id = id;
		this.name = name;
		this.language = language;
		this.framework = framework;
	}

	public Server() {

	}

}
