package utils;

public class DataReaderFactory {

	public static String getValue(String type, String folderDotFile, String sheetOrSection, String rowKeyOrKey,
			String columnOrSubKey) {
		return switch (type.toLowerCase()) {
		case "yaml" -> YamlDataReader.getValue(sheetOrSection, rowKeyOrKey, columnOrSubKey, folderDotFile);
		case "json" -> JsonDataReader.getValue(sheetOrSection, rowKeyOrKey, columnOrSubKey, folderDotFile);
		case "properties" -> PropertiesReader.getValue(rowKeyOrKey, folderDotFile);
		case "excel_sql" ->
			ExcelSQLQueryUtils.getValue(folderDotFile, sheetOrSection, "TestCaseID", rowKeyOrKey, columnOrSubKey);
		case "excel_matrix" -> ExcelMatrixReader.getValue(folderDotFile, sheetOrSection, rowKeyOrKey, columnOrSubKey);
		default -> throw new IllegalArgumentException("Unsupported type: " + type);
		};
	}
}