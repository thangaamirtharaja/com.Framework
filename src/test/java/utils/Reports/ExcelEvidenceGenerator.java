package utils.Reports;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelEvidenceGenerator {
    private static Workbook workbook;
    private static String filePath;
    private static Sheet currentSheet;

    // 🔹 Start scenario evidence (create/open daily Excel + create sheet if not exists)
    public static void startDocument(String scenarioName) {
        try {
            // Daily filename
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            filePath = "evidence/TestEvidence_" + date + ".xlsx";

            File file = new File(filePath);

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                fis.close();
            } else {
                workbook = new XSSFWorkbook();
            }

            // Clean scenario name for sheet (Excel sheet name cannot exceed 31 chars)
            String cleanScenario = scenarioName.replaceAll("[^a-zA-Z0-9]", " ");
            if (cleanScenario.length() > 25) {
                cleanScenario = cleanScenario.substring(0, 25);
            }

            // Check if sheet exists
            currentSheet = workbook.getSheet(cleanScenario);
            if (currentSheet == null) {
                currentSheet = workbook.createSheet(cleanScenario);

                // Add header row
                Row header = currentSheet.createRow(0);
                header.createCell(0).setCellValue("Step");
                header.createCell(1).setCellValue("Status");
                header.createCell(2).setCellValue("Screenshot Path");
            }

        } catch (Exception e) {
            System.err.println("❌ Error starting scenario evidence: " + e.getMessage());
        }
    }

    // 🔹 Add a step row
    public static void addStep(String stepDescription, String status, String screenshotPath) {
        try {
            int lastRowNum = currentSheet.getLastRowNum();
            Row row = currentSheet.createRow(lastRowNum + 1);

            row.createCell(0).setCellValue(stepDescription);
            row.createCell(1).setCellValue(status);
//            row.createCell(2).setCellValue(screenshotPath != null ? screenshotPath : "No Screenshot");
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                File imgFile = new File(screenshotPath);
                if (imgFile.exists()) {
                    InputStream inputStream = new FileInputStream(imgFile);

                    // Convert image into bytes
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                    inputStream.close();

                    // Create helper & drawing patriarch
                    CreationHelper helper = workbook.getCreationHelper();
                    Drawing<?> drawing = currentSheet.createDrawingPatriarch();

                    // Anchor defines where the image will be inserted
                    ClientAnchor anchor = helper.createClientAnchor();
                    anchor.setCol1(2); // column C (0-based index → 2 = 3rd column)
                    anchor.setRow1(row.getRowNum()); // same row as step

                     Picture pict = drawing.createPicture(anchor, pictureIdx);

                    // Adjust row height and column width to fit image
                    row.setHeightInPoints(100); // adjust as needed
                    currentSheet.setColumnWidth(2, 40 * 256); // 40 characters wide

                    // Resize picture to fit inside the adjusted cell
                    pict.resize(); // Resize image to fit cell (can adjust scaling)
                } else {
                    row.createCell(2).setCellValue("Screenshot not found");
                }
            } else {
                row.createCell(2).setCellValue("No Screenshot");
            }
        } catch (Exception e) {
            System.err.println("❌ Error adding step: " + e.getMessage());
        }
    }

    // Save the daily Excel file
    public static void endDocument() {
        try {
            File folder = new File("evidence");
            if (!folder.exists()) folder.mkdirs();

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            workbook.close();
            System.out.println("✅ Evidence saved: " + filePath);
        } catch (Exception e) {
            System.err.println("❌ Error saving evidence: " + e.getMessage());
        }
    }
}
