package com.psh.controller;

import com.psh.entity.TscmUser;
import com.psh.entity.request.ReqTscmUserAdd;
import com.psh.entity.request.ReqTscmUserUpdate;
import com.psh.entity.request.ReqTscmUserQuery;
import com.psh.entity.response.ResTscmUser;
import com.psh.service.TscmUserService;
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
* @Date 2022-01-27
* @Time 21:07:43
*用户表控制层
*/
@RestController
@RequestMapping("/tscmUser")
@Api(value="用户表接口管理,，维护人:makejava",tags ={"用户表接口管理"})
public class TscmUserController {
    
    @Autowired
    private TscmUserService tscmUserService;

    /**
     * 根据主键查询
     *
     * @param id 用户表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="根据主键获取用户表信息")
    @ApiImplicitParam(name="id",value="用户表主键",dataType="Long", paramType = "query")
    @GetMapping("/getById")
    public BaseResultModel getById(@RequestParam("id") Long id) {
        return tscmUserService.getOneById(id);
    }
    
    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value="新增用户表")
    public BaseResultModel insert(@RequestBody ReqTscmUserAdd req) {
        return tscmUserService.insert(req);
    }
    
    /**
     * 修改
     *
     * @param req 实体类
     * @param id 用户表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="修改用户表")
    @ApiImplicitParam(name="id",value="用户表主键",dataType="Long", paramType = "query")
    @PutMapping(value = "/update")
    public BaseResultModel update(@RequestParam("id") Long id,@RequestBody ReqTscmUserUpdate req) {
        return tscmUserService.update(id, req);
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
    public BaseResultModel<IPage<ResTscmUser>> list(ReqTscmUserQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResTscmUser> page = new Page<>(pageNum,pageSize);
        return tscmUserService.page(page,req);
    }

    /**
     * 单个删除
     *
     * @param id 用户表主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value="单个删除用户表")
    @DeleteMapping("/deleteOne")
    public BaseResultModel deleteOne(@RequestParam Long id){
        return tscmUserService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
     @ApiOperation(value="批量删除用户表")
    @DeleteMapping("/deleteList")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids){
        return tscmUserService.deleteBatch(ids);
    }
    
    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value="查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<TscmUser>> getAll() {
        QueryWrapper<TscmUser> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(tscmUserService.list(query));
    }

}