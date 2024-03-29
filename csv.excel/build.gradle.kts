plugins {
	java
	id("org.springframework.boot") version "3.0.3"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	implementation("commons-io:commons-io:2.11.0")



	// Apache POI for Excel processing
//	implementation ("org.apache.poi:poi:5.0.0") // Ensure you use the latest version

	// Apache POI for .xlsx format
//	implementation ("org.apache.poi:poi-ooxml:5.0.0") // Ensure you use the latest version

	// XMLBeans for XML processing
	implementation ("org.apache.xmlbeans:xmlbeans:5.0.2") // Ensure you use the latest version

	// SAX API for XML parsing
	implementation ("xml-apis:xml-apis:1.4.01")

	implementation( "org.apache.poi:poi-ooxml:5.2.3")
	implementation( "org.apache.poi:poi:5.2.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
