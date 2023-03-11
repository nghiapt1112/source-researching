package com.example.filex.document;

import java.util.HashMap;
import java.util.Map;

public class DocumentFactory {
    private static Map<RequestType, DocumentExporter> exporterByType = new HashMap<>(4);

    public static void register(RequestType type, DocumentExporter exporter) {
        exporterByType.put(type, exporter);
    }

    public static DocumentExporter get(RequestType type){
        return exporterByType.get(type);
    }
}
