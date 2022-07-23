//package com.psh.controller;
//
//import cn.hutool.json.JSONObject;
//import com.psh.config.EsConfig;
//import com.psh.entity.Users;
//import com.psh.hik.domain.BaseResultModel;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.Fuzziness;
////import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/doc")
//@Api(value="文档接口管理,，维护人:makejava",tags ={"文档接口管理，相当于表"})
//public class EsDocController {
//
//    @Autowired
//    private EsConfig es;
//    private SearchSourceBuilder sb;
//
//    /**
//     * 插入数据
//     */
//    @ApiOperation(value="插入数据")
//    @PostMapping("/add_doc")
//    public BaseResultModel add() throws Exception{
////        构建es对象
//        RestHighLevelClient re =es.getEs();
//        IndexRequest indexRequest=new IndexRequest();
//        indexRequest.index("user").id("100");
//        Users users =new Users();
//        users.setAccount("1234");
//        users.setPassword("123456");
//        users.setDeleted("0");
//        users.setDescribed("es测试数据");
//
//        String str =json(users);
//        //向请求体中设置数据，及数据格式
////        indexRequest.source(str, XContentType.JSON);
//
//        IndexResponse response = re.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println(response.getResult());
//        return  BaseResultModel.success();
//    }
//
//    /**
//     * 批量插入数据
//     */
//    @ApiOperation(value="批量插入数据")
//    @PostMapping("/add——batch_doc")
//    public BaseResultModel addBatch() throws Exception{
//        //构建es对象
//        RestHighLevelClient re =es.getEs();
//        BulkRequest request =new BulkRequest();
//        for (int a =0;a<100;a++){
//            if (a%2==0){
//                request.add(new IndexRequest().index("user").id((100+a)+"").source(XContentType.JSON,"account","100"+(a*5),"deleted","1","described",2));
//            }else if(a%5==0){
//                request.add(new IndexRequest().index("user").id((100+a)+"").source(XContentType.JSON,"account","100"+(a*5),"deleted","1","username","张三","described",5));
//            }else if(a%3==0){
//                request.add(new IndexRequest().index("user").id((100+a)+"").source(XContentType.JSON,"account","100"+(a*5),"deleted","0","described",3));
//
//            }else {
//                request.add(new IndexRequest().index("user").id((100+a)+"").source(XContentType.JSON,"account","100"+(a*5),"deleted","0","described",0));
//            }
//        }
//
//        BulkResponse response = re.bulk(request, RequestOptions.DEFAULT);
//        System.out.println("执行时间："+response.getTook());
//        System.out.println(response.getItems());
//        return  BaseResultModel.success();
//    }
//
//
//
//    /**
//     * 更新数据
//     */
//    @ApiOperation(value="更新数据")
//    @PostMapping("/update_doc")
//    public BaseResultModel update() throws Exception{
////        构建es对象
//        RestHighLevelClient re =es.getEs();
//        UpdateRequest updateRequest=new UpdateRequest();
//        updateRequest.index("user").id("100");
//        //更改一条数据
//        updateRequest.doc(XContentType.JSON,"account","6666");
//
//        UpdateResponse update = re.update(updateRequest, RequestOptions.DEFAULT);
//        System.out.println(update.getResult());
//        return  BaseResultModel.success();
//    }
//
//    /**
//     * 查询数据
//     */
//    @ApiOperation(value="查询数据")
//    @GetMapping("/select_doc")
//    public BaseResultModel select() throws Exception{
////        构建es对象
//        RestHighLevelClient re =es.getEs();
//        GetRequest request =new GetRequest();
//        request.index("user").id("100");
//        GetResponse response = re.get(request, RequestOptions.DEFAULT);
//        String str= response.getSourceAsString();
//        return  BaseResultModel.success(str);
//    }
//
//    /**
//     * 全量查询
//     */
//    @ApiOperation(value="全量查询")
//    @GetMapping("/select_All_doc")
//    public BaseResultModel selectAll(Users users) throws Exception{
//        //构建es对象
//        RestHighLevelClient re =es.getEs();
//
//        SearchRequest request =new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder sb =new SearchSourceBuilder().query(
//                QueryBuilders.matchAllQuery());
//        request.source(sb);
//
//        SearchResponse response = re.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//        return  BaseResultModel.success(hits);
//    }
//
//    /**
//     * 条件查询数据
//     */
//    @ApiOperation(value="条件查询数据")
//    @GetMapping("/select_search_doc/{pageNum}/{pageSize}")
//    public BaseResultModel select(Users users, @PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize){
//        //构建es对象
//        RestHighLevelClient re =es.getEs();
//
//        SearchRequest request =new SearchRequest();
//        request.indices("user");
//
//        //查询条件
//        SearchSourceBuilder sb =new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //精确查询
//        if (users!=null&&users.getAccount()!=null&&!"".equals(users.getAccount().trim())){
//            RangeQueryBuilder account = QueryBuilders.rangeQuery("account");
//            account.gte(users.getAccount());//大于等于
//            account.lte("10000000"); //小于等于
//            sb.query(account);
//        }
//        //must必须满足，notMust不满足，should可能满足
//        boolQueryBuilder.must(QueryBuilders.matchQuery("deleted",users.getDeleted()));
//        if (users!=null&&users.getUsername()!=null&&!"".equals(users.getUsername().trim())){
//            boolQueryBuilder.must(QueryBuilders.matchQuery("username",users.getUsername()));
//        }
//        //模糊查询
//        if (users!=null&&users.getDescribed()!=null&&!"".equals(users.getDescribed().trim())){
//            //差一个字符可以匹配出来，fuzziness(Fuzziness.ONE)为便宜距离
//            FuzzyQueryBuilder described = QueryBuilders.fuzzyQuery("described", users.getDescribed()).fuzziness(Fuzziness.ONE);
//            sb.query(described);
//        }
//
//
//        sb.query(boolQueryBuilder);
//        sb.from((pageNum-1)*pageSize);//从第几条数据开始
//        sb.size(pageSize);//每页多少条数据
//        //排序,
//        // Elasticsearch异常[类型=非法\参数\异常，原因=文本字段未针对需要每个文档字段数据
//        // （如聚合和排序）的操作进行优化，因此默认情况下禁用这些操作。请改用关键字字段。
//        // 或者，在[帐户]上设置fielddata=true通过取消反转索引加载字段数据。
//        // 请注意，这可能会占用大量内存。]
//        //sb.sort("account", SortOrder.DESC);
//        String[] exInCould={};
//        String[] inCould ={"account","deleted","username","described"};
//        //包含，排除
//        sb.fetchSource(inCould,exInCould);
//
//        request.source(sb);
//
//        SearchResponse response = null;
//        try {
//            response = re.search(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        SearchHits hits = response.getHits();
//        return  BaseResultModel.success(hits);
//    }
//
//
//    /**
//     * 全量查询,部分高亮显示
//     */
//    @ApiOperation(value="全量查询,部分高亮显示")
//    @GetMapping("/select_hight_doc")
//    public BaseResultModel selectHightAll(Users users){
//        //构建es对象
//        RestHighLevelClient re =es.getEs();
//
//        SearchRequest request =new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder sb =new SearchSourceBuilder();
//
//        if (users!=null&&users.getAccount()!=null){
//            TermQueryBuilder account = QueryBuilders.termQuery("account", users.getAccount());
//            sb.query(account);
//            //增加前、后标签
//            //。。。。。
//        }
//        sb.from(1);//从第几条数据开始
//        sb.size(20);//每页多少条数据
//        request.source(sb);
//
//        SearchResponse response = null;
//        try {
//            response = re.search(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        SearchHits hits = response.getHits();
//        return  BaseResultModel.success(hits);
//    }
//
//
//    /**
//     * 删除数据
//     */
//    @ApiOperation(value="删除数据")
//    @GetMapping("/delete_doc")
//    public BaseResultModel delete() throws Exception{
//        //构建es对象
//        RestHighLevelClient re =es.getEs();
//        DeleteRequest request =new DeleteRequest();
//        request.index("user").id("100");
//        DeleteResponse response = re.delete(request, RequestOptions.DEFAULT);
//        if (response.isFragment()){
//            return  BaseResultModel.success();
//        }else {
//            return  BaseResultModel.fail("删除失败");
//        }
//
//    }
//
//    /**
//     * 批量删除数据
//     */
//    @ApiOperation(value="批量删除数据")
//    @PostMapping("/delete——batch_doc")
//    public BaseResultModel deleteBatch() throws Exception{
//        //构建es对象
//        RestHighLevelClient re =es.getEs();
//        BulkRequest request =new BulkRequest();
//        for (int a =0;a<100;a=a+2){
//            request.add(new DeleteRequest().index("user").id((100+a)+""));
//        }
//
//        BulkResponse response = re.bulk(request, RequestOptions.DEFAULT);
//        System.out.println("执行时间："+response.getTook());
//        System.out.println(response.getItems());
//        return  BaseResultModel.success();
//    }
//
//
//
//
//
//    //json转换,es中的数据必须是json格式
//    String json(Object obj){
////        JSONObject jsonObject = JSONUtil.parseObj(obj);
//        JSONObject js =new JSONObject(obj);
//        return js.toString();
//    }
//
//}
