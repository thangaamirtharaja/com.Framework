package utils;

public abstract class DataReaderAndWriter implements DataReadAndWrite {
    //	String type, String folderDotFile, String sheetOrSection, String rowKeyOrKey String columnOrSubKey
    @Override
    public String readvalue(String type, Object... args) {
        return switch (type.toLowerCase()) {
            case "yaml" -> YamlDataReader.getValue((String) args[0], (String) args[1], (String) args[2], (String) args[3]);
            case "json" -> JsonDataReader.getValue((String) args[0], (String) args[1], (String) args[2], (String) args[3]);
            case "properties" -> PropertiesReader.getValue(null, (String) args[1], null, (String) args[3]);
            case "excel_sql" -> ExcelSQLQueryUtils.getValue( (String) args[0], "UniqueID", (String) args[1], (String) args[2],(String) args[3]);
            case "excel_matrix" -> ExcelMatrixReader.getValue( (String) args[0], (String) args[1], (String) args[2],(String) args[3]);
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        };
    }
    @Override
    public void writevalue(String type, Object... args) {
        switch (type.toLowerCase()) {
            case "yaml" -> YamlDataWriter.writeValue((String) args[0], (String) args[1], (String) args[2],(String) args[3], (String) args[4]);
            case "json" -> JsonDataWriter.writeValue((String) args[0], (String) args[1], (String) args[2],(String) args[3], (String) args[4]);
            case "properties" -> PropertiesWriter.writeValue(null, (String) args[1], null, (String) args[3],(String) args[4]);
            case "excel_sql" -> ExcelSQLWriter.writeValue( (String) args[0], "UniqueID", (String) args[1], (String) args[2], (String) args[3],(String) args[4]);
            case "excel_matrix" -> ExcelMatrixWriter.writeValue( (String) args[0], (String) args[1], (String) args[2],(String) args[3],(String) args[4]);
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}