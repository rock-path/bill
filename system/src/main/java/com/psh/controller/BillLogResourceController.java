package com.psh.controller;

import com.psh.entity.BillLogResource;
import com.psh.entity.request.ReqBillLogResourceAdd;
import com.psh.entity.request.ReqBillLogResourceUpdate;
import com.psh.entity.request.ReqBillLogResourceQuery;
import com.psh.entity.response.ResBillLogResource;
import com.psh.hik.domain.BaseResultModel;
import com.psh.service.BillLogResourceService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;

/**
* @Author psh
* @Date 2022-05-18
* @Time 21:29:51
*日志资源控制层
*/
@RestController
@RequestMapping("/billLogResource")
@Api(value="日志资源接口管理,，维护人:psh",tags ={"日志资源接口管理"})
public class BillLogResourceController {
    
    @Autowired
    private BillLogResourceService billLogResourceService;

    /**
     * 根据主键查询
     *
     * @param id 日志资源主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取日志资源信息")
    @ApiImplicitParam(name="id",value="日志资源主键",dataType="Long", paramType = "query")
    @GetMapping("/getById/{id}")
    public BaseResultModel getById(@PathVariable("id") Long id) {
        return billLogResourceService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增日志资源")
    public BaseResultModel insert(@RequestBody ReqBillLogResourceAdd req) {
        return billLogResourceService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 日志资源主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改日志资源")
    @ApiImplicitParam(name="id",value="日志资源主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update/{id}")
    public BaseResultModel update(@PathVariable("id") Long id,@RequestBody ReqBillLogResourceUpdate req) {
        return billLogResourceService.update(id, req);
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
    @GetMapping("/list/{pageNum}/{pageSize}")
    public BaseResultModel<IPage<ResBillLogResource>> list(ReqBillLogResourceQuery req, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        Page<ResBillLogResource> page = new Page<>(pageNum,pageSize);
        return billLogResourceService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 日志资源主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除日志资源")
    @DeleteMapping("/delete/{id}")
    public BaseResultModel deleteOne(@PathVariable Long id){
        return billLogResourceService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除日志资源")
    @DeleteMapping("/delete")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billLogResourceService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillLogResource>> getAll() {
        QueryWrapper<BillLogResource> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billLogResourceService.list(query));
    }

}
