package com.psh.controller;

import com.psh.entity.BillPassword;
import com.psh.entity.request.ReqBillPasswordAdd;
import com.psh.entity.request.ReqBillPasswordUpdate;
import com.psh.entity.request.ReqBillPasswordQuery;
import com.psh.entity.response.ResBillPassword;
import com.psh.hik.domain.BaseResultModel;
import com.psh.service.BillPasswordService;
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
 * @Date 2022-05-28
 * @Time 10:31:33
 * 账号密码控制层
 */
@RestController
@RequestMapping("/billPassword")
@Api(value = "账号密码接口管理,，维护人:psh", tags = {"账号密码接口管理"})
public class BillPasswordController {

    @Autowired
    private BillPasswordService billPasswordService;

    /**
     * 根据主键查询
     *
     * @param id 账号密码主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "根据主键获取账号密码信息")
    @ApiImplicitParam(name = "id", value = "账号密码主键", dataType = "Long", paramType = "query")
    @GetMapping("/getById/{id}")
    public BaseResultModel getById(@PathVariable("id") Long id) {
        return billPasswordService.getOneById(id);
    }


    /**
     * 修改
     *
     * @param req 实体类
     * @param id  账号密码主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "修改账号密码")
    @ApiImplicitParam(name = "id", value = "账号密码主键", dataType = "Long", paramType = "query")
    @PutMapping(value = "/update/{id}")
    public BaseResultModel update(@PathVariable("id") Long id, @RequestBody ReqBillPasswordUpdate req) {
        return billPasswordService.update(id, req);
    }



    /**
     * 逻辑删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("/delete")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids) {
        return billPasswordService.deleteBatch(ids);
    }

    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillPassword>> getAll() {
        QueryWrapper<BillPassword> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billPasswordService.list(query));
    }


    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "新增账号密码")
    public BaseResultModel insert(@RequestBody ReqBillPasswordAdd req) {
        return billPasswordService.insert(req);
    }

    /**
     * 分页查询
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "分页查询目录管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "Integer", paramType = "query")
    })
    @GetMapping("/list")
    public BaseResultModel<IPage<ResBillPassword>> list(ReqBillPasswordQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResBillPassword> page = new Page<>(pageNum, pageSize);
        return billPasswordService.page(page, req);
    }



}
