package com.psh.controller;

import com.psh.entity.BillRecord;
import com.psh.entity.request.ReqBillRecordAdd;
import com.psh.entity.request.ReqBillRecordUpdate;
import com.psh.entity.request.ReqBillRecordQuery;
import com.psh.entity.response.ResBillRecord;
import com.psh.service.BillRecordService;
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
* @Time 21:26:44
*消费记录表控制层
*/
@RestController
@RequestMapping("/billRecord")
@Api(value="消费记录表接口管理,，维护人:makejava",tags ={"消费记录表接口管理"})
public class BillRecordController {
    
    @Autowired
    private BillRecordService billRecordService;

    /**
     * 根据主键查询
     *
     * @param id 消费记录表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取消费记录表信息")
    @ApiImplicitParam(name="id",value="消费记录表主键",dataType="Long", paramType = "query")
    @GetMapping("/getById")
    public BaseResultModel getById(@RequestParam("id") Long id) {
        return billRecordService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增消费记录表")
    public BaseResultModel insert(@RequestBody ReqBillRecordAdd req) {
        return billRecordService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 消费记录表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改消费记录表")
    @ApiImplicitParam(name="id",value="消费记录表主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update")
    public BaseResultModel update(@RequestParam("id") Long id,@RequestBody ReqBillRecordUpdate req) {
        return billRecordService.update(id, req);
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
    public BaseResultModel<IPage<ResBillRecord>> list(ReqBillRecordQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResBillRecord> page = new Page<>(pageNum,pageSize);
        return billRecordService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 消费记录表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除消费记录表")
    @DeleteMapping("/deleteOne")
    public BaseResultModel deleteOne(@RequestParam Long id){
        return billRecordService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除消费记录表")
    @DeleteMapping("/deleteList")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billRecordService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillRecord>> getAll() {
        QueryWrapper<BillRecord> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billRecordService.list(query));
    }

}