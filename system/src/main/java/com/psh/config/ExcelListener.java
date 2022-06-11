package com.psh.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.psh.entity.BillRecord;
import com.psh.entity.request.ReqBillRecordAdd;
import com.psh.entity.vo.RecordImport;
import com.psh.service.BillRecordService;
import lombok.val;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ExcelListener extends AnalysisEventListener<RecordImport> {


    //一行一行读取excel内容
    @Override
    public void invoke(RecordImport demoData, AnalysisContext analysisContext) {
        System.out.println("excel内容：" + demoData);
        BillRecord billRecord =BillRecord.build(demoData.getT_id(),demoData.getR_time(),demoData.getR_number(),demoData.getDescd());
        BillRecordService bean = SpringApplicationUtils.getBean(BillRecordService.class);
        bean.save(billRecord);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

    //读取完之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读完啦、。。。，");
    }


}
