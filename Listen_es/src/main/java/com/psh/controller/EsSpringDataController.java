package com.psh.controller;

import com.psh.entity.Users;
import com.psh.hik.domain.BaseResultModel;
import com.psh.service.EsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es-spring_data")
public class EsSpringDataController {

    //注意：
    //① ElasticsearchRestTemple是ElasticsearchOperations的子类的子类
    //② 在ES7.x以下的版本使用的是ElasticsearchTemple，7.x以上版本已弃用ElasticsearchTemple，使用ElasticsearchRestTemple替代
    //关于索引的操作，不应该通过spring，更多的是关于文档的操作
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private EsService esService;

    /**
     * 全量查询
     */
    @ApiOperation(value="全量查询")
    @GetMapping("/select_All_doc")
    public BaseResultModel selectAll() throws Exception{
        return  BaseResultModel.success(esService.findAll());
    }

    /**
     * 按id查询
     */
    @ApiOperation(value="按id查询")
    @GetMapping("/select_id_doc")
    public BaseResultModel selectById(Users users) throws Exception{
        Long id =Long.valueOf(users.getId());
        return  BaseResultModel.success(esService.findById(id));
    }

    /**
     * 创建索引
     */
    @ApiOperation(value="创建索引")
    @GetMapping("/create_index")
    public BaseResultModel create(String index){
//        elasticsearchOperations.indexOps()
        elasticsearchOperations.createIndex(index);
//        elasticsearchOperations.putMapping(Users.class);
        return  BaseResultModel.success("创建成功");
    }

    /**
     * 删除索引
     */
    @ApiOperation(value="删除索引")
    @GetMapping("/delete_index")
    public BaseResultModel delete(String index){
        elasticsearchOperations.deleteIndex(index);
        return  BaseResultModel.success("创建成功");
    }

    /**
     * 插入数据
     */
    @ApiOperation(value="插入数据")
    @PostMapping("/add")
    public BaseResultModel add(@RequestParam("index") String index,@RequestBody  Users users){
        if ("user".equals(index)){
            esService.save(users);
        }
        return  BaseResultModel.success("创建成功");
    }

}
