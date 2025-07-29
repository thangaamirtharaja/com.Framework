package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelMatrixWriter {
    public static void writeValue(String folderDotFile, String sheetName, String rowKey, String colHeader, String value) {
        String filePath = folderDotFile.replace(".", "/") + ".xlsx";
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int colIndex = -1;

            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().trim().equalsIgnoreCase(colHeader)) {
                    colIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (colIndex == -1) throw new RuntimeException("Column header not found: " + colHeader);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(rowKey)) {
                    Cell targetCell = row.getCell(colIndex);
                    if (targetCell == null) targetCell = row.createCell(colIndex);
                    targetCell.setCellValue(value);
                    break;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error updating Excel (Matrix): " + e.getMessage(), e);
        }
    }
}
