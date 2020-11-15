package com.soro.entity;

/*Represente les caracteristiques des images uploader */
public class FileResponse {
	
	private String name;
	private String url;
	private String type;
	private long size;
	public FileResponse(String name, String url, String type, long size) {
		super();
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
	}
	
	
	
}
