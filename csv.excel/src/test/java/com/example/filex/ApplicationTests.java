package com.example.filex;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
//		try (InputStream stream = new ClassPathResource(fileName).getInputStream()) {
//			this.execFile(stream, 0);
//		} catch (IOException | InterruptedException e) {
////            e.printStackTrace();
//		}
//		String fileContent = IOUtils.toString(stream, Charset.defaultCharset());
//		ArrayList<TestCase> testCases = JsonUtils.toCollection(fileContent, ArrayList.class, TestCase.class);
	}

}
