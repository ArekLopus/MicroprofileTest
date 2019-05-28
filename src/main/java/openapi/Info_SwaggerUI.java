package openapi;

//		Adding Swagger UI

//	Easy way - MicroProfile Extensions
//	https://www.phillip-kruger.com/post/microprofile_openapi_swaggerui/
//-You can use this useful library that will add it automatically:
//	https://github.com/microprofile-extensions/openapi-ext/blob/master/swagger-ui/README.md
//	https://mvnrepository.com/artifact/org.microprofile-ext.openapi-ext/swagger-ui

//<dependency>
//    <groupId>org.microprofile-ext.openapi-ext</groupId>
//    <artifactId>swagger-ui</artifactId>
//    <version>1.0.1</version>
//    <scope>runtime</scope>
//</dependency>

//-Adds SwaggerUI at /openapi-ui/index.html of the application path 
// so: your_scheme://your_domain:your_port/your_context_root/your_application_path/openapi-ui/
//	http://localhost:8080/MicroprofileTest/res/openapi-ui/index.html


//		Custom version 
//	https://www.kodnito.com/posts/documenting-rest-api-using-microprofile-openapi-swagger-ui-payara-micro/
//	https://rmannibucau.metawerx.net/post/microprofile-openapi-swagger-ui


public class Info_SwaggerUI {}