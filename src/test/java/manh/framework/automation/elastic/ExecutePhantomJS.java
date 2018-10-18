package manh.framework.automation.elastic;

import java.io.IOException;

public class ExecutePhantomJS {

    public static void DashboardImageCapture() throws IOException

    {

        Runtime.getRuntime().exec("cmd.exe /c start cmd /k \" executephantom.sh \"");
        // Runtime.getRuntime().exec("taskkill /IM cmd.exe");

    }

    public static void CloseCommandPrompt() throws IOException {
        {
            Runtime.getRuntime().exec("taskkill /IM cmd.exe");
        }
    }

}
