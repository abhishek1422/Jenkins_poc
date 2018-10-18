package manh.framework.automation.elastic;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class ImagetoPDF {

    public static void PDFConvertor() {
        Document document = new Document();
        String input = "C:/Users/anagpurkar/Desktop/test_automation-master/dashboardEI-JOURNAL-ENTRY-COUNT-STATISTICS-BY-ENDPOINT.jpg"; // .gif and .jpg are ok too!
        String output = "C:/Users/anagpurkar/Desktop/test_automation-master/dashboard.pdf";
        try {
            FileOutputStream fos = new FileOutputStream(output);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            document.add(Image.getInstance(input));
            document.close();
            writer.close();
            System.out.println("Image converted to PDF");
            System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}