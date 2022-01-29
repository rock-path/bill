package com.psh.service;

import com.psh.hik.domain.BaseResultModel;
import com.psh.service.impl.OpenFeignSystemImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "system",fallback = OpenFeignSystemImpl.class)
public interface OpenFeignSystem {

    @ApiOperation(value="查询所有用户信息")
    @GetMapping("/users/getAll")
    BaseResultModel getAll() ;

}
