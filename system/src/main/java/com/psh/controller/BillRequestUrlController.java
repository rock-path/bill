package com.psh.controller;

import com.psh.entity.BillRequestUrl;
import com.psh.entity.BillType;
import com.psh.entity.request.ReqBillRequestUrlAdd;
import com.psh.entity.request.ReqBillRequestUrlUpdate;
import com.psh.entity.request.ReqBillRequestUrlQuery;
import com.psh.entity.response.ResBillRequestUrl;
import com.psh.service.BillRequestUrlService;
import com.psh.util.Export;
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

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
* @Author makejava
* @Date 2022-05-15
* @Time 20:30:48
*访问记录控制层
*/
@RestController
@RequestMapping("/billRequestUrl")
@Api(value="访问记录接口管理,，维护人:makejava",tags ={"访问记录接口管理"})
public class BillRequestUrlController {
    
    @Autowired
    private BillRequestUrlService billRequestUrlService;

    /**
     * 根据主键查询
     *
     * @param id 访问记录主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取访问记录信息")
    @ApiImplicitParam(name="id",value="访问记录主键",dataType="Long", paramType = "query")
    @GetMapping("/getById/{id}")
    public BaseResultModel getById(@PathVariable("id") Long id) {
        return billRequestUrlService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增访问记录")
    public BaseResultModel insert(@RequestBody ReqBillRequestUrlAdd req) {
        return billRequestUrlService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 访问记录主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改访问记录")
    @ApiImplicitParam(name="id",value="访问记录主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update/{id}")
    public BaseResultModel update(@PathVariable("id") Long id,@RequestBody ReqBillRequestUrlUpdate req) {
        return billRequestUrlService.update(id, req);
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
    public BaseResultModel<IPage<ResBillRequestUrl>> list(ReqBillRequestUrlQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResBillRequestUrl> page = new Page<>(pageNum,pageSize);
        return billRequestUrlService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 访问记录主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除访问记录")
    @DeleteMapping("/delete/{id}")
    public BaseResultModel deleteOne(@PathVariable Long id){
        return billRequestUrlService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除访问记录")
    @DeleteMapping("/delete")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return billRequestUrlService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillRequestUrl>> getAll() {
        QueryWrapper<BillRequestUrl> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billRequestUrlService.list(query));
    }

    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        // 查询数据信息
        List<BillRequestUrl> list = billRequestUrlService.list();
        try {
            String fileName = URLEncoder.encode("访问url记录列表","UTF-8");
            Export.exportExcel(response,fileName, list,BillRequestUrl.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
