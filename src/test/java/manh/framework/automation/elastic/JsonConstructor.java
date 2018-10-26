package manh.framework.automation.elastic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonConstructor {

    private ArrayList< Object > dashboardInfo = new ArrayList < Object > ();
    private String dashboardName;
    private String dashboardId;


    public ArrayList<Object> getDashboardInfo() {
        return dashboardInfo;
    }

    public void setDashboardInfo(ArrayList<Object> dashboardInfo) {
        this.dashboardInfo = dashboardInfo;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public void JsonBuilder(){
        JSONObject json=new JSONObject();

        try {
            //json.put("dashboardInfo",getDashboardInfo());
            json.put("dashboardName",getDashboardName());
            json.put("dashboardID",getDashboardId());

        System.out.println(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





}
