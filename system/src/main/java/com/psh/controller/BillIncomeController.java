package com.psh.controller;

import com.psh.entity.BillIncome;
import com.psh.entity.request.ReqBillIncomeAdd;
import com.psh.entity.request.ReqBillIncomeUpdate;
import com.psh.entity.request.ReqBillIncomeQuery;
import com.psh.entity.response.ResBillIncome;
import com.psh.service.BillIncomeService;
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
* @Date 2022-01-29
* @Time 21:39:19
*收入表控制层
*/
@RestController
@RequestMapping("/billIncome")
@Api(value="收入表接口管理,，维护人:makejava",tags ={"收入表接口管理"})
public class BillIncomeController {
    
    @Autowired
    private BillIncomeService billIncomeService;

    /**
     * 根据主键查询
     *
     * @param id 收入表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取收入表信息")
    @ApiImplicitParam(name="id",value="收入表主键",dataType="Long", paramType = "query")
    @GetMapping("/getById")
    public BaseResultModel getById(@RequestParam("id") Long id) {
        return billIncomeService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增收入表")
    public BaseResultModel insert(@RequestBody ReqBillIncomeAdd req) {
        return billIncomeService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 收入表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改收入表")
    @ApiImplicitParam(name="id",value="收入表主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update")
    public BaseResultModel update(@RequestParam("id") Long id,@RequestBody ReqBillIncomeUpdate req) {
        return billIncomeService.update(id, req);
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
    public BaseResultModel<IPage<ResBillIncome>> list(ReqBillIncomeQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResBillIncome> page = new Page<>(pageNum,pageSize);
        return billIncomeService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 收入表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除收入表")
    @DeleteMapping("/deleteOne")
    public BaseResultModel deleteOne(@RequestParam Long id){
        return billIncomeService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除收入表")
    @DeleteMapping("/deleteList")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billIncomeService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillIncome>> getAll() {
        QueryWrapper<BillIncome> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billIncomeService.list(query));
    }

}