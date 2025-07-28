package utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.*;

public class ExcelSQLWriter {
    public static void writeValue(String folderDotFile, String sheetName, String rowKeyColumn, String rowKeyValue, String targetColumn, String value) {
        try {
            String filePath = "src/test/resources/" + folderDotFile.replace(".", "/") + ".xlsx";
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);

            String updateQuery = String.format("UPDATE %s SET %s='%s' WHERE %s='%s'",
                    sheetName, targetColumn, value, rowKeyColumn, rowKeyValue);

            connection.executeUpdate(updateQuery);
            connection.close();
        } catch (FilloException e) {
            throw new RuntimeException("Error updating Excel (SQL) file: " + e.getMessage(), e);
        }
    }
}
