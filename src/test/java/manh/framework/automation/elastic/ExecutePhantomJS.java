package manh.framework.automation.elastic;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExecutePhantomJS {

    public static void DashboardImageCapture(JSONObject json) throws IOException

    {


        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\anagpurkar\\Desktop\\Artifact_id_1\\src\\test\\resources\\jsonOutput.json"));
        writer.write(json.toString());
        writer.close();


        Runtime.getRuntime().exec("cmd.exe /c start cmd /k \" executephantom.sh \"");
        // Runtime.getRuntime().exec("taskkill /IM cmd.exe");

    }

    public static void CloseCommandPrompt() throws IOException {
        {
            Runtime.getRuntime().exec("taskkill /IM cmd.exe");
        }
    }

}
