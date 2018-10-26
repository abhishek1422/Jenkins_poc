package manh.framework.automation.elastic;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class KibanaDashBoardTest {

    public static void main(String[] args) throws Exception {

        ElasticSearchQuery elasticSearchQuery=new ElasticSearchQuery();
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("C:\\Users\\anagpurkar\\Desktop\\Artifact_id_1\\src\\test\\resources\\dashboard.properties");
        prop.load(input);
        input.close();

        ArrayList<String> dashName = new ArrayList<>();
        int i=1;
        for (String property: prop.stringPropertyNames()
             ) {



           dashName.add(prop.getProperty("dashboardName"+i));
            i++; }


        try {

           JSONObject json =elasticSearchQuery.kibanaDashBoradID_Rest(dashName);
           ExecutePhantomJS.DashboardImageCapture(json);
           ExecutePhantomJS.CloseCommandPrompt();
            }
           // Thread.sleep(90000);
            //ImagetoPDF.PDFConvertor();

        catch (Exception e){
            System.out.println("Exception in main thread");
            e.printStackTrace();
        }
    }
}
