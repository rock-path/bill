package com.psh.controller;

import com.psh.entity.BillLogs;
import com.psh.entity.BillRecord;
import com.psh.entity.request.ReqBillLogsAdd;
import com.psh.entity.request.ReqBillLogsUpdate;
import com.psh.entity.request.ReqBillLogsQuery;
import com.psh.entity.response.ResBillLogs;
import com.psh.hik.domain.BaseResultModel;
import com.psh.service.BillLogsService;
import com.psh.util.Export;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
* @Author psh
* @Date 2022-05-19
* @Time 20:21:34
*操作日志控制层
*/
@RestController
@RequestMapping("/billLogs")
@Api(value="操作日志接口管理,，维护人:psh",tags ={"操作日志接口管理"})
public class BillLogsController {
    
    @Autowired
    private BillLogsService billLogsService;

    /**
     * 根据主键查询
     *
     * @param id 操作日志主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取操作日志信息")
    @ApiImplicitParam(name="id",value="操作日志主键",dataType="Long", paramType = "query")
    @GetMapping("/getById/{id}")
    public BaseResultModel getById(@PathVariable("id") Long id) {
        return billLogsService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增操作日志")
    public BaseResultModel insert(@RequestBody ReqBillLogsAdd req) {
        return billLogsService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 操作日志主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改操作日志")
    @ApiImplicitParam(name="id",value="操作日志主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update/{id}")
    public BaseResultModel update(@PathVariable("id") Long id,@RequestBody ReqBillLogsUpdate req) {
        return billLogsService.update(id, req);
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
    public BaseResultModel<IPage<ResBillLogs>> list(ReqBillLogsQuery req, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        Page<ResBillLogs> page = new Page<>(pageNum,pageSize);
        return billLogsService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 操作日志主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除操作日志")
    @DeleteMapping("/delete/{id}")
    public BaseResultModel deleteOne(@PathVariable Long id){
        return billLogsService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除操作日志")
    @DeleteMapping("/delete")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billLogsService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillLogs>> getAll() {
        QueryWrapper<BillLogs> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billLogsService.list(query));
    }


    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        // 查询数据信息
        List<BillLogs> list = billLogsService.list();
        try {
            String fileName = URLEncoder.encode("操作日志列表","UTF-8");
            Export.exportExcel(response,fileName, list,BillLogs.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
