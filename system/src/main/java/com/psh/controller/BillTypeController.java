package com.psh.controller;

import com.psh.entity.BillType;
import com.psh.entity.request.ReqBillTypeAdd;
import com.psh.entity.request.ReqBillTypeUpdate;
import com.psh.entity.request.ReqBillTypeQuery;
import com.psh.entity.response.ResBillType;
import com.psh.service.BillTypeService;
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
* @Date 2022-01-28
* @Time 21:26:56
*消费类型表控制层
*/
@RestController
@RequestMapping("/billType")
@Api(value="消费类型表接口管理,，维护人:makejava",tags ={"消费类型表接口管理"})
public class BillTypeController {
    
    @Autowired
    private BillTypeService billTypeService;

    /**
     * 根据主键查询
     *
     * @param id 消费类型表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取消费类型表信息")
    @ApiImplicitParam(name="id",value="消费类型表主键",dataType="Long", paramType = "query")
    @GetMapping("/getById")
    public BaseResultModel getById(@RequestParam("id") Long id) {
        return billTypeService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增消费类型表")
    public BaseResultModel insert(@RequestBody ReqBillTypeAdd req) {
        return billTypeService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 消费类型表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改消费类型表")
    @ApiImplicitParam(name="id",value="消费类型表主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update")
    public BaseResultModel update(@RequestParam("id") Long id,@RequestBody ReqBillTypeUpdate req) {
        return billTypeService.update(id, req);
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
    public BaseResultModel<IPage<ResBillType>> list(ReqBillTypeQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResBillType> page = new Page<>(pageNum,pageSize);
        return billTypeService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 消费类型表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除消费类型表")
    @DeleteMapping("/deleteOne")
    public BaseResultModel deleteOne(@RequestParam Long id){
        return billTypeService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除消费类型表")
    @DeleteMapping("/deleteList")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billTypeService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillType>> getAll() {
        QueryWrapper<BillType> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billTypeService.list(query));
    }

}