package utils;

public class DataWriterFactory {

    public static void writeValue(String type, String folderDotFile, String sectionOrSheet, String rowKeyOrKey, String columnOrSubKey, String value) {
        switch (type.toLowerCase()) {
            case "yaml" -> YamlDataWriter.writeValue(sectionOrSheet, rowKeyOrKey, columnOrSubKey, value, folderDotFile);
            case "json" -> JsonDataWriter.writeValue(sectionOrSheet, rowKeyOrKey, columnOrSubKey, value, folderDotFile);
            case "properties" -> PropertiesWriter.writeValue(rowKeyOrKey, value, folderDotFile);
            case "excel_sql" -> ExcelSQLWriter.writeValue(folderDotFile, sectionOrSheet, "TestCaseID", rowKeyOrKey, columnOrSubKey, value);
            case "excel_matrix" -> ExcelMatrixWriter.writeValue(folderDotFile, sectionOrSheet, rowKeyOrKey, columnOrSubKey, value);
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
