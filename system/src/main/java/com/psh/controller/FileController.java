package com.psh.controller;

import com.psh.hik.domain.BaseResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

import static com.psh.hik.common.Constant.*;

@RestController()
@RequestMapping("/file")
@Api(value = "文件相关操作", tags = {"文件相关操作"})
public class FileController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 上传多个文件
     *
     * @return
     */
    @PostMapping("/uploadList")
    @ApiOperation(value = "上传多个文件")
    public BaseResultModel uploadList(@RequestParam(value = "fileList") MultipartFile[] fileList) {
        try {
            for (int i = 0; i < fileList.length; i++) {
                redisTemplate.opsForValue().increment(FILE);
                //调用业务 文件拷贝
                //处理文件名 UUID拼串=uuid+"."+扩展名
                String de = FILEHOME + FilenameUtils.getExtension(fileList[i].getOriginalFilename());
                //调用业务 文件拷贝
                File file = new File(de);
                fileList[i].transferTo(file);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return BaseResultModel.success();
    }

    /**
     * 上传文件
     *
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件")
    public BaseResultModel upload(MultipartFile file) {
        String name = null;
        try {
            Long l = redisTemplate.opsForValue().increment(FILE);
            //调用业务 文件拷贝
            //处理文件名 "."+扩展名
            name = l + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            //调用业务 文件拷贝
            File newFile = new File(FILEHOME, name);
            file.transferTo(newFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return BaseResultModel.success(FILEURL + name);
    }


    /**
     * @param path     指想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:将输入流中的数据循环写入到响应输出流中，而不是一次性读取到内存
     */
    @GetMapping("/downloadLocal")
    public void downloadLocal(String path, HttpServletResponse response) {
        if (path.indexOf("http")>=0){
            path = FILEHOME+path.substring(path.lastIndexOf("/"));
        }
        InputStream inputStream = null;
        // 读到流中
        try {
            inputStream = new FileInputStream(path);// 文件的存放路径
            response.reset();
            response.setContentType("application/octet-stream");
            String filename = new File(path).getName();
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
