package utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.*;

public class ExcelSQLQueryUtils {

	public static String getValue( String sheetName, String rowKeyColumn, String rowKeyValue,
			String targetColumn,String fileDotPath) {
		String filePath =fileDotPath.replace(".", "/") + ".xlsx";

		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(filePath);

			String query = String.format("SELECT %s FROM %s WHERE %s='%s'", targetColumn, sheetName, rowKeyColumn,
					rowKeyValue);

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
