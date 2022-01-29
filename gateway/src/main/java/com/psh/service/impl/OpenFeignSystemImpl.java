package com.psh.service.impl;

import com.psh.hik.domain.BaseResultModel;
import com.psh.service.OpenFeignSystem;
import org.springframework.stereotype.Component;

@Component
public class OpenFeignSystemImpl implements OpenFeignSystem {
    @Override
    public BaseResultModel getAll() {
        return new BaseResultModel("0","服务器繁忙，请稍后再试","服务器繁忙，请稍后再试");
    }
}
