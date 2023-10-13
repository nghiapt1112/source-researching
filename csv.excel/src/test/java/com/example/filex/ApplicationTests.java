package com.example.filex;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;
import com.example.filex.excel2.CheckDuplicateDBAction;
import com.example.filex.excel2.CheckDuplicateDTOAction;
import com.example.filex.excel2.ParseDTOAction;
import com.example.filex.excel2.PrintValueAction;
import com.example.filex.excel2.StylingAction;
import com.example.filex.excel2.WriteErrorAction;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
class ApplicationTests {

//	@Test
	void contextLoads() {
//		try (InputStream stream = new ClassPathResource(fileName).getInputStream()) {
//			this.execFile(stream, 0);
//		} catch (IOException | InterruptedException e) {
////            e.printStackTrace();
//		}
//		String fileContent = IOUtils.toString(stream, Charset.defaultCharset());
//		ArrayList<TestCase> testCases = JsonUtils.toCollection(fileContent, ArrayList.class, TestCase.class);
	}

	@Test
	public void readExcelBinh() {
		List<ExcelError> excelErrors = new ArrayList<>();

		List<BaseDTO> dtos = new ArrayList<>();

		ParseDTOAction parseAction = new ParseDTOAction("/Users/ptnghia1/data/sources/nghia/source-researching/csv.excel/src/main/resources/userImport.xlsx");
		parseAction.next(new CheckDuplicateDTOAction());
		parseAction.next(new CheckDuplicateDBAction());
		parseAction.next(new PrintValueAction());
//        parseAction.next(new ExportAction());
//		parseAction.next(new StylingAction());
		parseAction.next(new WriteErrorAction());
		parseAction.execute(dtos, excelErrors);

	}

}
