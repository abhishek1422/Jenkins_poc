package manh.framework.automation.elastic;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

public class MultipleImagetoSinglePDF {



    public static void main(String[] args) {


        // File f = new File("C:/Program Files (x86)/Jenkins/workspace/BuildandReport_gradle");

       /* File[] f   = new File("C:/Program Files (x86)/Jenkins/workspace/BuildandReport_gradle").listFiles(new FilenameFilter() { @Override public boolean accept(File dir, String name) { return name.endsWith(".jpg"); } });


    ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));

        for (File fis:files) {

            System.out.println(fis);*/

        Document doc = new Document();

        {
            File file = new File("C:\\Protractor");
            File[] files = file.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    if(name.toLowerCase().endsWith(".jpg")){
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            Document document = new Document();
            int count=1;
            for(File f:files){

                System.out.println(count);
                count++;
                System.out.println(f.getName());
                try {
                    String input = "C:\\Protractor\\"+f.getName(); // .gif and .jpg are ok too!
                    String output = "C:\\Protractor\\New folder\\"+f.getName().replace("jpg","pdf");
                    FileOutputStream fos = new FileOutputStream(output);
                    PdfWriter writer = PdfWriter.getInstance(document, fos);
                    writer.open();
                    document.open();
                    document.add(Image.getInstance(input));

                    System.out.println("Image converted to PDF");

                }


                catch (Exception e) {
                    e.printStackTrace();
                }



            }
            document.close();
            System.exit(0);
        }





    }

}


