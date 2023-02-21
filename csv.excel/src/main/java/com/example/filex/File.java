package com.example.filex;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class File {
    public void read(String path){

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

//        for (TestCase testCase : testCases) {
//            Thread.sleep(1000 * sec);
//            String tcInfo = this.storedVariables.parseReqFromVariables(testCase.getDesc());
//            testCase.setDesc(tcInfo);
//            LOGGER.info("{} is Executing.", tcInfo);
//            long start = System.currentTimeMillis();
//            testCase
//                    .injectDependencies(mvc, storedVariables)
//                    .executeTest();
//            LOGGER.info("Testcase {} DONE in {} (s)", tcInfo, (System.currentTimeMillis() - start) / 1000.0);
//        }
    }

}
