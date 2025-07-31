package utils.Data_Read_Write;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.*;
import utils.Interface.GetValue;

public class ExcelSQLQueryUtils implements GetValue {

    public String getValue(String section, String key, String subKey, String fileDotPath) {
        String sheetName;
        String rowKeyValue;
        String targetColumn;
        String filePath = fileDotPath.replace(".", "/") + ".xlsx";
        try {
            sheetName = section;
            rowKeyValue = key;
            targetColumn = subKey;
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(filePath);
            String query = String.format("SELECT %s FROM %s WHERE %s='%s'", targetColumn, sheetName, "UniqueID", rowKeyValue);
            Recordset rs = connection.executeQuery(query);
            String value = null;
            if (rs.next()) {
                value = rs.getField(targetColumn);
            }
            rs.close();
            connection.close();
            return value;
        } catch (FilloException e) {
            throw new RuntimeException("Error querying Excel file: " + e.getMessage(), e);
        }
    }
}
