package com.psh.controller;

import com.psh.entity.ImportantEvents;
import com.psh.entity.request.ReqImportantEventsAdd;
import com.psh.entity.request.ReqImportantEventsUpdate;
import com.psh.entity.request.ReqImportantEventsQuery;
import com.psh.entity.response.ResImportantEvents;
import com.psh.service.ImportantEventsService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.psh.hik.domain.BaseResultModel;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;

/**
* @Author makejava
* @Date 2022-02-19
* @Time 15:48:52
*重要事件控制层
*/
@RestController
@RequestMapping("/importantEvents")
@Api(value="重要事件接口管理,，维护人:makejava",tags ={"重要事件接口管理"})
public class ImportantEventsController {
    
    @Autowired
    private ImportantEventsService importantEventsService;

    /**
     * 根据主键查询
     *
     * @param id 重要事件主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取重要事件信息")
    @ApiImplicitParam(name="id",value="重要事件主键",dataType="Long", paramType = "query")
    @GetMapping("/getById")
    public BaseResultModel getById(@RequestParam("id") Long id) {
        return importantEventsService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增重要事件")
    public BaseResultModel insert(@RequestBody ReqImportantEventsAdd req) {
        return importantEventsService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 重要事件主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改重要事件")
    @ApiImplicitParam(name="id",value="重要事件主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update")
    public BaseResultModel update(@RequestParam("id") Long id,@RequestBody ReqImportantEventsUpdate req) {
        return importantEventsService.update(id, req);
    }
    
    /**
     * 分页查询
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "分页查询目录管理")
    @ApiImplicitParams({
        @ApiImplicitParam(name="pageNum",value="页码",dataType="Integer", paramType = "query"),
        @ApiImplicitParam(name="pageSize",value="条数",dataType="Integer", paramType = "query")
    })
    @GetMapping("/list")
    public BaseResultModel<IPage<ResImportantEvents>> list(ReqImportantEventsQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResImportantEvents> page = new Page<>(pageNum,pageSize);
        return importantEventsService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 重要事件主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除重要事件")
    @DeleteMapping("/deleteOne")
    public BaseResultModel deleteOne(@RequestParam Long id){
        return importantEventsService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除重要事件")
    @DeleteMapping("/deleteList")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return importantEventsService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<ImportantEvents>> getAll() {
        QueryWrapper<ImportantEvents> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(importantEventsService.list(query));
    }

}