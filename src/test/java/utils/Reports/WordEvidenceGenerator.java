package utils.Reports;

import org.apache.poi.xwpf.usermodel.*;
import org.junit.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WordEvidenceGenerator {
    private static XWPFDocument document;
    private static String filePath;

    public static void startDocument(String scenarioName) {
        try {
            document = new XWPFDocument();

            // Create directory if not exists
            File folder = new File("screenshots");
            if (!folder.exists()) folder.mkdirs();

            // Filename with timestamp
            String timestamp = new SimpleDateFormat("HH.mm_dd_MM_yyyy").format(new Date());
            String cleanScenario = scenarioName.replaceAll("[^a-zA-Z0-9]", " ");
            String fileName = cleanScenario + "_" + timestamp + ".docx";

            filePath = "evidence/" + fileName;

            // Add title
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Test Evidence: " + scenarioName);
            titleRun.setBold(true);
            titleRun.setFontSize(16);
            titleRun.addBreak();
        } catch (Exception e) {
            System.err.println("❌ Error creating Word document: " + e.getMessage());
        }
    }

    public static void addStep(String stepDescription, String status, String screenshotPath) {
        try {
            // Step paragraph
            XWPFParagraph stepPara = document.createParagraph();
            stepPara.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun stepRun = stepPara.createRun();
            stepRun.setText("Step: " + stepDescription + " [" + status + "]");
            stepRun.setFontSize(12);
            stepRun.setBold(true);
            stepRun.addBreak();

            // Screenshot handling
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                File imgFile = new File(screenshotPath);
                if (imgFile.exists()) {
                    BufferedImage image = ImageIO.read(imgFile);
                    if (image != null) {
                        int widthPx = Math.min(500, image.getWidth());
                        int heightPx = (widthPx * image.getHeight()) / image.getWidth();
                        int widthEmu = widthPx * 9525;
                        int heightEmu = heightPx * 9525;

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(image, "png", baos);
                        baos.flush();

                        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                        XWPFParagraph imgPara = document.createParagraph();
                        imgPara.setAlignment(ParagraphAlignment.LEFT);
                        XWPFRun imgRun = imgPara.createRun();
                        imgRun.addPicture(bais, Document.PICTURE_TYPE_PNG, imgFile.getName(), widthEmu, heightEmu);
                        baos.close();
                        bais.close();
                    } else {
                        Assert.assertTrue(" ImageIO.read returned null for file: "+screenshotPath, false);

                    }
                } else {
                    Assert.assertTrue(" Screenshot file does not exist: "+screenshotPath, false);
                }
            } else {
                Assert.assertTrue(" Screenshot path is null or empty "+screenshotPath, false);

            }

        } catch (Exception e) {
            System.err.println("❌ Exception while adding step: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void endDocument() {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            document.write(out);
            document.close();
            System.out.println("✅ Word report saved: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Error saving Word document: " + e.getMessage());
        }
    }
}
