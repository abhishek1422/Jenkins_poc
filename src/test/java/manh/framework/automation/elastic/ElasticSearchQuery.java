package manh.framework.automation.elastic;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.concurrent.TimeUnit;

public class ElasticSearchQuery{
    ElasticConnectionFactory elasticConnectionFactory =new ElasticConnectionFactory();
    RestHighLevelClient client;
    QueryBuilder query;
    AggregationBuilder aggs;
    String traceId;
    String errorMessage;

    public ElasticSearchQuery() throws Exception{
        this.client =elasticConnectionFactory.initiateConnection();
        //this.client=elasticConnectionFactory.getTransportClient();
    }

    /*public String searchElasticIndex(){
        String output=null;
        try{
            SearchResponse response = client.prepareSearch().setQuery(QueryBuilders.matchQuery("make", "Honda")).setSize(5).execute().actionGet();
            output = response.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public String elasticMatchAll(){

         SearchResponse response = client
                .prepareSearch("fluentd-2018.08.25")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));
        return response.toString();
    }




    public String elasticBoolMustQuery(){

        this.query = QueryBuilders
                .boolQuery()
                .must(
                        QueryBuilders
                            .matchQuery("name", "accounting")

                )
                .must(
                        QueryBuilders
                                .matchQuery("room", "e3")

                )
                .mustNot(
                        QueryBuilders
                                .matchQuery("professor.name","bill")
                )
        .should(
                QueryBuilders
                        .matchQuery("name","computer")
        )
        .minimumShouldMatch(1)




         SearchResponse response = client
                .prepareSearch("courses")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));

         return response.toString();

    }

    public String elasticExistQuery(){

        this.query = QueryBuilders.existsQuery("professor.email");

        SearchResponse response = client
                .prepareSearch("courses")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));

        return response.toString();

    }

    public String elasticMultiMatchQuery(){

        String[] fieldNames={"name","professor.department"};
        this.query = QueryBuilders
                    .multiMatchQuery("accounting",fieldNames);

        SearchResponse response = client
                .prepareSearch("courses")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));

        return response.toString();

    }

    public String elasticMatchPhraseQuery(){

        this.query = QueryBuilders
                .matchPhraseQuery("course_description","Tax Act 200 is an intermediate course");

        SearchResponse response = client
                .prepareSearch("courses")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));

        return response.toString();

    }

    public String elasticMatchPhrasePrefixQuery(){

        this.query = QueryBuilders
                .matchPhrasePrefixQuery("course_description","Tax Act 200 is an intermediate cour");

        SearchResponse response = client
                .prepareSearch("courses")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));

        return response.toString();

    }

    public String elasticMatchFilter(){

        this.query = QueryBuilders.boolQuery().filter(
                QueryBuilders.matchQuery("name","accounting")
        );
        this.query = QueryBuilders.boolQuery().filter(QueryBuilders.boolQuery().
                must(QueryBuilders.matchQuery("name","accounting")));

        SearchResponse response = client
                .prepareSearch("courses")
                //.setSearchType(SearchType.DEFAULT)
                .setQuery(query)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(100))
                .get(TimeValue.timeValueMillis(200));

        return response.toString();

    }

    public String elasticAggregations(){

        //this.aggs =AggregationBuilders.terms("popular_cars").field("make.keyword");
        //this.aggs=AggregationBuilders.avg("avg_price").field("price");
        this.aggs=AggregationBuilders.stats("stats_price").field("price");

        this.query = QueryBuilders.boolQuery().filter(QueryBuilders.boolQuery().
                must(QueryBuilders.matchQuery("make","bmw")));

        SearchResponse response = client
                .prepareSearch("vehicles")
                .setTypes("cars")
                .setSearchType(SearchType.DEFAULT)
                //.setQuery(query)
                .addAggregation(aggs)
                .setFrom(0)
                .setSize(10)
                .setTimeout(TimeValue.timeValueMillis(10000))
                .get(TimeValue.timeValueMillis(20000));

        return response.toString();

    }*/

    public String elasticMatchAll_Rest() throws Exception {

        SearchRequest req = new SearchRequest("fluent-2018.10.03.06");

        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        searchBuilder.query(QueryBuilders.matchAllQuery());
        searchBuilder.from(0);
        searchBuilder.size(10);
        searchBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        req.source(searchBuilder);

        SearchResponse response=client.search(req);
        /*JSONObject responseJSON = new JSONObject(response.toString());


        JSONObject json = new JSONObject(response);
        JSONObject hits = json.getJSONObject("hits");
        JSONArray hitsArray = hits.getJSONArray("hits");
        //ArrayList<Object> coalitionList = new ArrayList<Object>();
        System.out.println("No. of hits : "+hitsArray.length());
        for (int i=0; i<hitsArray.length(); i++) {
            ArrayList<String> fileNameIdArrList = new ArrayList<String>();
            JSONObject h = hitsArray.getJSONObject(i);
            JSONObject sourceJObj = h.getJSONObject("_source");
            //System.out.println("sourceJObj :"+i+" "+sourceJObj);

            String trace_id = sourceJObj.getString("trace_id");
            //System.out.println("trace_id :"+i+" "+trace_id);
            this.traceId=trace_id;

            String errorMessage = sourceJObj.getString("message");
            //System.out.println("errorMessage :"+i+" "+errorMessage);
            this.errorMessage=errorMessage;

        }*/

        return response.toString();

    }

    public String elasticBoolMustQuery_Rest() throws Exception {

        this.query = QueryBuilders
                .boolQuery()
                .must(
                        QueryBuilders
                                .matchQuery("log_level", "DEBUG")

                )
                .must(
                        QueryBuilders
                                .matchQuery("component_name", "com-manh-cp-dcorder")

                )
                .mustNot(
                        QueryBuilders
                                .matchQuery("component_name", "abc")
                )
                .should(
                        QueryBuilders
                                .matchQuery("TZ", "America/New_York")
                )
                .minimumShouldMatch(1);

        SearchRequest req = new SearchRequest("fluentd-2018.10.05");


        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        searchBuilder.query(query);
        searchBuilder.from(0);
        searchBuilder.size(10);
        //searchBuilder.terminateAfter(10);
        req.source(searchBuilder);

        SearchResponse response = client.search(req);
        //JSONObject responseJSON = new JSONObject(response.toString());
        /*BufferedInputStream br = new BufferedInputStream(response.getgetEntity().getContent());

        String res = "";
        while (br.available()>0) {
            res += (char)br.read();
            // System.out.println(res);
        }*/

        /*if (br.available()==0){
            System.out.println("No Response");
        }

        br.close();*/

        /*JSONObject json = new JSONObject(response);
        JSONObject hits = json.getJSONObject("hits");
        JSONArray hitsArray = hits.getJSONArray("hits");
        //ArrayList<Object> coalitionList = new ArrayList<Object>();
        System.out.println("No. of hits : " + hitsArray.length());
        for (int i = 0; i < hitsArray.length(); i++) {
            ArrayList<String> fileNameIdArrList = new ArrayList<String>();
            JSONObject h = hitsArray.getJSONObject(i);
            JSONObject sourceJObj = h.getJSONObject("_source");
            //System.out.println("sourceJObj :"+i+" "+sourceJObj);

            String trace_id = sourceJObj.getString("trace_id");
            //System.out.println("trace_id :"+i+" "+trace_id);
            this.traceId = trace_id;

            String errorMessage = sourceJObj.getString("message");
            //System.out.println("errorMessage :"+i+" "+errorMessage);
            this.errorMessage = errorMessage;
        }*/
        //System.out.println(responseJSON);
        //elasticConnectionFactory.closeElasticConnection();
        return response.toString();

    }

    public String kibanaDashBoradID_Rest(String var) throws Exception {

        this.query = QueryBuilders
                .boolQuery()
                .must(
                        QueryBuilders
                                .matchQuery("_type", "dashboard")

                )
                .must(
                        QueryBuilders
                                .matchQuery("_id", var)

                );

        SearchRequest req = new SearchRequest(".kibana");


        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
        searchBuilder.query(query);
        searchBuilder.from(0);
        searchBuilder.size(1);
        req.source(searchBuilder);

        SearchResponse response = client.search(req);
        return response.toString();

    }

}
