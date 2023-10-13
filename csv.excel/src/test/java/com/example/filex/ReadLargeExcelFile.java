package com.example.filex;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadLargeExcelFile {
    public static void main(String[] args) throws Exception {
        String excelFilePath = "/Users/ptnghia1/data/sources/nghia/source-researching/csv.excel/src/main/resources/userImport.xlsx";

        try (OPCPackage opcPackage = OPCPackage.open(excelFilePath)) {
            ReadOnlySharedStringsTable sharedStrings = new ReadOnlySharedStringsTable(opcPackage);
            XSSFReader xssfReader = new XSSFReader(opcPackage);
            XMLReader sheetParser = XMLReaderFactory.createXMLReader();
            ContentHandler handler = new ContentHandler(sharedStrings);
            sheetParser.setContentHandler(handler);
//
//            for (InputStream sheet : xssfReader.getSheetsData()) {
//                InputSource sheetSource = new InputSource(sheet);
//                sheetParser.parse(sheetSource);
//                sheet.close();
//            }

            Iterator<InputStream> sheetsIterator = xssfReader.getSheetsData();
            while (sheetsIterator.hasNext()) {
                try (InputStream sheet = sheetsIterator.next()) {
                    InputSource sheetSource = new InputSource(sheet);
                    sheetParser.parse(sheetSource);
                }
            }

            handler.getDtoList().forEach(System.out::println);
        }
    }

    private static class ContentHandler extends DefaultHandler {
        private final ReadOnlySharedStringsTable sharedStrings;
        private String lastContents;
        private boolean nextIsString;
        private ExcelDataDTO currentDTO;
        private List<ExcelDataDTO> dtoList = new ArrayList<>();
        private int currentCellIndex;

        ContentHandler(ReadOnlySharedStringsTable sharedStrings) {
            this.sharedStrings = sharedStrings;
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes) {
            if ("row".equals(name)) {
                currentDTO = new ExcelDataDTO();
                currentCellIndex = 0;
            } else if ("v".equals(name)) {
                nextIsString = "s".equals(attributes.getValue("t"));
                lastContents = "";
            }
        }

        @Override
        public void endElement(String uri, String localName, String name) {
            if ("v".equals(name)) {
                if (nextIsString) {
                    int idx = Integer.parseInt(lastContents);
                    lastContents = sharedStrings.getItemAt(idx).getString();
                }

                // Depending on the cell index, set the appropriate DTO attribute.
                switch (currentCellIndex) {
                    case 0:
                        currentDTO.setColumn1(lastContents);
                        break;
                    case 1:
                        currentDTO.setColumn2(lastContents);
                        break;
                    // Add cases for other columns as needed.
                }
                currentCellIndex++;
            } else if ("row".equals(name)) {
                dtoList.add(currentDTO);
            }
        }

        public List<ExcelDataDTO> getDtoList() {
            return dtoList;
        }
    }

}
