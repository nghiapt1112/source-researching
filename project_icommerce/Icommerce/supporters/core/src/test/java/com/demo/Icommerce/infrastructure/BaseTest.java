package com.demo.Icommerce.infrastructure;

import com.demo.Icommerce.infrastructure.util.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @RunWith(SpringRunner.class)
 * @ContextConfiguration(classes = {ApplicationTest.class, MongoConfig.class, EmailConfig.class})
 * @WebFluxTest
 * @WebAppConfiguration
 */

//@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest
//@ContextConfiguration(classes = {ApplicationTest.class, MongoConfiguration.class, EmailConfig.class})
//@WebAppConfiguration
@AutoConfigureMockMvc
public abstract class BaseTest {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    protected Variable storedVariables;

    @Autowired
    protected MockMvc mvc;

    @BeforeEach
    public void init() {
        this.storedVariables = new Variable();
    }

    protected void testFile(String fileName) {
        try (InputStream stream = new ClassPathResource(fileName).getInputStream()) {
            this.execFile(stream, 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execFile(InputStream stream, int sec) throws IOException, InterruptedException {
        String fileContent = IOUtils.toString(stream, Charset.defaultCharset());
        ArrayList<TestCase> testCases = JsonUtils.toCollection(fileContent, ArrayList.class, TestCase.class);

        for (TestCase testCase : testCases) {
            Thread.sleep(1000 * sec);
            String tcInfo = this.storedVariables.parseReqFromVariables(testCase.getDesc());
            testCase.setDesc(tcInfo);
            LOGGER.info("{} is Executing.", tcInfo);
            long start = System.currentTimeMillis();
            testCase
                    .injectDependencies(mvc, storedVariables)
                    .executeTest();
            LOGGER.info("Testcase {} DONE in {} (s)", tcInfo, (System.currentTimeMillis() - start) / 1000.0);
        }
    }

    protected BaseTest testFolder(String folderPath) {
        this.testFolder(folderPath, 0);
        return this;
    }

    /**
     * @param folderPath
     * @param sec        :       sleep between each testCases.
     */
    protected BaseTest testFolder(String folderPath, int sec) {
        try {
            File[] files = new File(BaseTest.class.getResource(folderPath).getFile()).listFiles();
            if (Objects.isNull(files)) {
                LOGGER.error("Folder is empty");
                return this;
            }
            for (File file : files) {
                this.execFile(new FileInputStream(file), sec);
            }
            return this;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void withNewVariableStore() {
        this.storedVariables = null;
        this.storedVariables = new Variable();
    }

    @AfterEach
    public void tearDown() {
        storedVariables.printAll();
        storedVariables.clear();
        storedVariables = null;
    }
}