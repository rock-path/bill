package com.psh.controller;

import com.psh.entity.BillTaskTime;
import com.psh.entity.request.ReqBillTaskTimeAdd;
import com.psh.entity.request.ReqBillTaskTimeUpdate;
import com.psh.entity.request.ReqBillTaskTimeQuery;
import com.psh.entity.response.ResBillTaskTime;
import com.psh.hik.domain.BaseResultModel;
import com.psh.service.BillTaskTimeService;
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
* @Date 2022-06-12
* @Time 13:48:54
*动态定时任务执行时间控制层
*/
@RestController
@RequestMapping("/billTaskTime")
@Api(value="动态定时任务执行时间接口管理,，维护人:psh",tags ={"动态定时任务执行时间接口管理"})
public class BillTaskTimeController {
    
    @Autowired
    private BillTaskTimeService billTaskTimeService;


    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增动态定时任务执行时间")
    public BaseResultModel insert(@RequestBody ReqBillTaskTimeAdd req) {
        return billTaskTimeService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 动态定时任务执行时间主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改动态定时任务执行时间")
    @ApiImplicitParam(name="id",value="动态定时任务执行时间主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update/{id}")
    public BaseResultModel update(@PathVariable("id") Long id,@RequestBody ReqBillTaskTimeUpdate req) {
        return billTaskTimeService.update(id, req);
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
    public BaseResultModel<IPage<ResBillTaskTime>> list(ReqBillTaskTimeQuery req, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        Page<ResBillTaskTime> page = new Page<>(pageNum,pageSize);
        return billTaskTimeService.page(page,req);
    }


    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除动态定时任务执行时间")
    @DeleteMapping("/delete")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billTaskTimeService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillTaskTime>> getAll() {
        QueryWrapper<BillTaskTime> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billTaskTimeService.list(query));
    }

}
