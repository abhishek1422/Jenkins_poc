package manh.framework.automation.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

//import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticConnectionFactory {
    TransportClient client;
    RestHighLevelClient restClient;
    //String searchProviderNode="10.0.0.8";
    //String searchProviderClientPort="9300";
    //int searchProviderPort=8080;
    //String searchProviderClusterName="MA-elasticsearch-cluster";

    public RestHighLevelClient initiateConnection() throws Exception{

        // on startup
        Settings settings = Settings.builder()
                //.put("cluster.name", "MA-elasticsearch-cluster")
                .put("client.transport.ignore_cluster_name",true)
                .put("client.transport.ping_timeout","10s")
                //.put("node.name","L_9EScW")
                //.put("http.port","9200-9300")
                //.put("transport.tcp.port","9300-9400")
                //.put("network.host","10.0.0.8")
                .build();
        //this.client = new PreBuiltTransportClient(settings)
        //       .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("alvd-cidock1867.manhdev.com"), 9300));

        this.restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("alvd-cidock1863.manhdev.com", 9200, "http")).build());
        //return client;
        return restClient;
    }

/*    public void closeElasticConnection()throws IOException {
        this.restClient.close();
    }*/

    /*public TransportClient getTransportClient() {
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", this.searchProviderClusterName)
                    .put("http.enabled",true)
                    .put("network.host", searchProviderNode)
                    //.put("es.logger.level", "INFO")
                    .put("transport.tcp.port", searchProviderClientPort)
                    .put("http.port", searchProviderPort)
                    .put("node.name","L_9EScW")
                    //.put("script.inline", true)
                    //.put("script.indexed", true)
                    .build();


            this.client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(this.searchProviderNode),
                            Integer.parseInt(this.searchProviderClientPort)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }*/
}
