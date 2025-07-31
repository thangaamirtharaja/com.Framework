package utils.Data_Read_Write;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.*;
import utils.Interface.WriteValue;

public class ExcelSQLWriter implements WriteValue {
    public void writeValue(String section, String key, String subKey, String folderDotFile, String value) {
        String sheetName;
        String rowKeyValue;
        String targetColumn;
        try {
            sheetName = section;
            rowKeyValue = key;
            targetColumn = subKey;
            String filePath =folderDotFile.replace(".", "/") + ".xlsx";
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);

            String updateQuery = String.format("UPDATE %s SET %s='%s' WHERE %s='%s'",
                    sheetName, targetColumn, value, "UniqueID", rowKeyValue);

            connection.executeUpdate(updateQuery);
            connection.close();
        } catch (FilloException e) {
            throw new RuntimeException("Error updating Excel (SQL) file: " + e.getMessage(), e);
        }
    }
}
