//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
//import org.apache.poi.xssf.eventusermodel.XSSFReader;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
//import org.xml.sax.Attributes;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.xml.sax.XMLReader;
//import org.xml.sax.helpers.DefaultHandler;
//import org.xml.sax.helpers.XMLReaderFactory;
//
//import java.io.InputStream;
//
//public class ReadLargeExcelFile {
//    public static void main(String[] args) throws Exception {
//        String excelFilePath = "path/to/your/excel/file.xlsx";
//
//        try (OPCPackage opcPackage = OPCPackage.open(excelFilePath)) {
//            ReadOnlySharedStringsTable sharedStrings = new ReadOnlySharedStringsTable(opcPackage);
//            XSSFReader reader = new XSSFReader(opcPackage);
//            XMLReader sheetParser = XMLReaderFactory.createXMLReader();
//            ContentHandler handler = new ContentHandler(sharedStrings);
//            sheetParser.setContentHandler(handler);
//
//            for (InputStream sheet : reader.getSheetsData()) {
//                InputSource sheetSource = new InputSource(sheet);
//                sheetParser.parse(sheetSource);
//                sheet.close();
//            }
//        }
//    }
//
//    private static class ContentHandler extends DefaultHandler {
//        private final ReadOnlySharedStringsTable sharedStrings;
//        private String lastContents;
//        private boolean nextIsString;
//
//        ContentHandler(ReadOnlySharedStringsTable sharedStrings) {
//            this.sharedStrings = sharedStrings;
//        }
//
//        @Override
//        public void startElement(String uri, String localName, String name, Attributes attributes) {
//            if ("v".equals(name)) {
//                nextIsString = "s".equals(attributes.getValue("t"));
//                lastContents = "";
//            }
//        }
//
//        @Override
//        public void endElement(String uri, String localName, String name) {
//            if ("v".equals(name)) {
//                if (nextIsString) {
//                    int index = Integer.parseInt(lastContents);
//                    lastContents = new XSSFRichTextString(sharedStrings.getEntryAt(index)).toString();
//                }
//                System.out.print(lastContents + "\t");
//            }
//        }
//
//        @Override
//        public void characters(char[] ch, int start, int length) {
//            lastContents += new String(ch, start, length);
//        }
//    }
//}
