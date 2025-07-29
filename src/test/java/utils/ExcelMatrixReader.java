package utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelMatrixReader {

	public static String getValue( String sheetName, String rowKey, String colHeader,String fileDotPath) {
		String filePath =  fileDotPath.replace(".", "/") + ".xlsx";

		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			Row headerRow = sheet.getRow(0);

			int colIndex = -1;
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().trim().equalsIgnoreCase(colHeader)) {
					colIndex = cell.getColumnIndex();
					break;
				}
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row.getCell(0).getStringCellValue().equalsIgnoreCase(rowKey)) {
					return row.getCell(colIndex).getStringCellValue();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error reading Excel matrix file: " + e.getMessage(), e);
		}
		return null;
	}
}
