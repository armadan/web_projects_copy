package com.library.gcit.entity;

public class Publisher implements BaseEntity {

	private int publisherid;
	private String publishername;
	private String publisheraddress;
	private String publisherphone;

	public Publisher() {
	}

	public Publisher(String publishername, String publisheraddress,
			String publisherphone) {
		this.publishername = publishername;
		this.publisheraddress = publisheraddress;
		this.publisherphone = publisherphone;
	}

	public String getPublisheraddress() {
		return publisheraddress;
	}

	public void setPublisheraddress(String publisheraddress) {
		this.publisheraddress = publisheraddress;
	}

	public String getPublisherphone() {
		return publisherphone;
	}

	public void setPublisherphone(String publisherphone) {
		this.publisherphone = publisherphone;
	}

	public int getPublisherid() {
		return publisherid;
	}

	public void setPublisherid(int publisherid) {
		this.publisherid = publisherid;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

}
