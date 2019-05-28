package openapi;

//	On Payara http://localhost:8080/openapi shows wrongly servers (http - 8181 and https - 8080):
//	- url: http://192.168.0.46:8181/MicroprofileTest
//	  description: Default Server.
//	- url: https://192.168.0.46:8080/MicroprofileTest
//	  description: Default Server.
//and it doesnt work properly for org.microprofile-ext.openapi-ext - swagger-ui (Works ok on WLP)

//-As a hot fix it can be overridden as a config property
//	mp.openapi.servers=http://localhost:8080/MicroprofileTest/
//after that:
//	servers:
//	- url: http://localhost:8080/MicroprofileTest/

public class Info_Problems {}