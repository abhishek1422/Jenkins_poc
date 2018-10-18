package manh.framework.automation.elastic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class KibanaDashBoardTest {

    public static void main(String[] args) throws Exception {
        ElasticSearchQuery elasticSearchQuery=new ElasticSearchQuery();
        try {

            ArrayList<String> id= new ArrayList<>() ;
            id.add("EI-PAYLOAD-WAIT-TIME-STATISTICS");
            id.add("EI-JOURNAL-ENTRY-COUNT-STATISTICS");
            id.add("EI-JOURNAL-ENTRY-COUNT-STATISTICS-BY-ENDPOINT");

            for (String str: id) {

                //String str="Batch Monitor";
                System.out.println(elasticSearchQuery.kibanaDashBoradID_Rest(str));

                String json = elasticSearchQuery.kibanaDashBoradID_Rest(str);

                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\anagpurkar\\Desktop\\test_automation-master\\src\\test\\resources\\jsonOutput.json"));
                writer.write(json);

                writer.close();


                /*JSONObject jsonObject = new JSONObject(json);
                JSONObject hits = (JSONObject) jsonObject.get("hits");
                JSONArray hitsInner = (JSONArray) hits.get("hits");
                JSONObject idValue = (JSONObject) hitsInner.get(0);
                System.out.println(idValue.get("_id"));*/

                ExecutePhantomJS.DashboardImageCapture();
                ExecutePhantomJS.CloseCommandPrompt();
            }
            Thread.sleep(90000);
            ImagetoPDF.PDFConvertor();
        }
        catch (Exception e){
            System.out.println("Exception in main thread");
            e.printStackTrace();
        }
    }
}
