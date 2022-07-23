package com.psh.controller;


import com.psh.config.EsConfig;
import com.psh.hik.domain.BaseResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
@Api(value="索引级别操作，相当于库、表,维护人:makejava",tags ={"索引级别操作，相当于库、表"})
public class EsIndexController {

//    @Autowired
//    private EsConfig es;
    @Resource
    private RestHighLevelClient re;

    /**
     * 创建索引
     */
    @ApiOperation(value="创建索引")
    @GetMapping("/create_index")
    public BaseResultModel create(String index){
        //创建索引
        CreateIndexRequest request =new CreateIndexRequest(index);
        try {
            //采用默认配置
            CreateIndexResponse createIndexResponse = re.indices().create(request, RequestOptions.DEFAULT);
            //响应状态
            Boolean boo= createIndexResponse.isAcknowledged();
            if (boo){
                return  BaseResultModel.success();
            }else {
                return  BaseResultModel.fail("创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  BaseResultModel.success();
    }


    /**
     * 查询索引
     */
    @ApiOperation(value="查询索引")
    @GetMapping("/select_index")
    public BaseResultModel select(String index) throws Exception{
        GetIndexRequest request =new GetIndexRequest(index);
        GetIndexResponse getIndexResponse = re.indices().get(request, RequestOptions.DEFAULT);
//        Map<String, List<AliasMetadata>> aliases = getIndexResponse.getAliases();//user
//        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
        return  BaseResultModel.success();
    }


    /**
     * 删除索引
     */
    @ApiOperation(value="删除索引")
    @DeleteMapping("/delete_index")
    public BaseResultModel delete(String index) throws Exception{
        //获取es对象
        DeleteIndexRequest request =new DeleteIndexRequest(index);
        AcknowledgedResponse delete = re.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.toString());
        return  BaseResultModel.success();
    }

}
