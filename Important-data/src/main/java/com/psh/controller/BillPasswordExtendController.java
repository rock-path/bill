package com.psh.controller;

import com.github.pagehelper.util.StringUtil;
import com.psh.entity.BillPasswordExtend;
import com.psh.entity.request.ReqBillPasswordExtendAdd;
import com.psh.entity.request.ReqBillPasswordExtendUpdate;
import com.psh.entity.request.ReqBillPasswordExtendQuery;
import com.psh.entity.response.ResBillPasswordExtend;
import com.psh.hik.domain.BaseResultModel;
import com.psh.service.BillPasswordExtendService;
import lombok.val;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author psh
 * @Date 2022-05-28
 * @Time 21:47:07
 * 账号密码富文本大附件控制层
 */
@RestController
@RequestMapping("/billPasswordExtend")
@Api(value = "账号密码富文本大附件接口管理,，维护人:psh", tags = {"账号密码富文本大附件接口管理"})
public class BillPasswordExtendController {

    @Autowired
    private BillPasswordExtendService billPasswordExtendService;


    /**
     * 修改
     *
     * @param req 实体类
     * @param id  账号密码富文本大附件主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "修改账号密码富文本大附件")
    @ApiImplicitParam(name = "id", value = "账号密码富文本大附件主键", dataType = "Long", paramType = "query")
    @PutMapping(value = "/update/{id}")
    public BaseResultModel update(@PathVariable("id") Long id, @RequestBody ReqBillPasswordExtendUpdate req) {
        return billPasswordExtendService.update(id, req);
    }


    /**
     * 单个删除
     *
     * @param id 账号密码富文本大附件主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "单个删除账号密码富文本大附件")
    @DeleteMapping("/delete/{id}")
    public BaseResultModel deleteOne(@PathVariable Long id) {
        return billPasswordExtendService.deleteOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "批量删除账号密码富文本大附件")
    @DeleteMapping("/delete")
    public BaseResultModel deleteBatch(@RequestBody List<Long> ids) {
        return billPasswordExtendService.deleteBatch(ids);
    }

    /**
     * 查询全部接口
     *
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "查询全部接口")
    @GetMapping("/getAll")
    public BaseResultModel<List<BillPasswordExtend>> getAll() {
        QueryWrapper<BillPasswordExtend> query = new QueryWrapper<>();
        query.eq("deleted", "0");
        return BaseResultModel.success(billPasswordExtendService.list(query));
    }


    /**
     * 新增
     *
     * @param req 实体类
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "新增账号密码富文本大附件")
    public BaseResultModel insert(@RequestBody ReqBillPasswordExtendAdd req) {
        return billPasswordExtendService.insert(req);
    }


    /**
     * 新增
     *
     * @return BaseResultModel对象
     */
    @PostMapping(value = "/test_add")
    @ApiOperation(value = "新增账号密码富文本大附件")
    public BaseResultModel testAdd(String described) {
        System.out.println(described);
        return BaseResultModel.success();
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
    public BaseResultModel<IPage<ResBillPasswordExtend>> list(ReqBillPasswordExtendQuery req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ResBillPasswordExtend> page = new Page<>(pageNum, pageSize);
        return billPasswordExtendService.page(page, req);
    }


    /**
     * 根据主键查询
     *
     * @param id 账号密码富文本大附件主键
     * @return BaseResultModel对象
     */
    @ApiOperation(value = "根据主键获取账号密码富文本大附件信息")
    @ApiImplicitParam(name = "id", value = "账号密码富文本大附件主键", dataType = "Long", paramType = "query")
    @GetMapping("/getById")
    public BaseResultModel getById(@RequestParam("id") Long id) {
        return billPasswordExtendService.getOneById(id);
    }


//    /**
//     * 添加base64图片
//     * 处理含有base64位格式的图片，保存为图片并替代为地址
//     * @param content
//     */
//    public String handlerBase64Content(HttpServletRequest request,String parentName,String content) throws Exception{
//        if(StringUtil.isNotEmpty(content)){
//            //获取src值
//            List<String> srcvalues=getImgSrc(content);
//            String temp=null;
//            for(String src:srcvalues){
//                if(src.startsWith("data:image/")){
//                    temp=uploadBase64File(request,src,parentName);
//                    content=content.replace(src,"SWphotoUrl"+temp);
//                }else if(src.contains(parentName)){
//                    temp=src.substring(src.indexOf(parentName));
//                    content=content.replace(src,"SWphotoUrl"+temp);
//                }else{
//                    //其他的属于网络图片，不用处理
//                }
//
//            }
//        }
//        return content;
//    }
//
//    /**
//     * 获取文本中的img标签的src属性值
//     * @param htmlStr
//     * @return
//     */
//    public  List<String> getImgSrc(String htmlStr) {
//        String img = "";
//        Pattern p_image;
//        Matcher m_image;
//        List<String> pics = new ArrayList<String>();
//        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
//        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
//        m_image = p_image.matcher(htmlStr);
//        while (m_image.find()) {
//            img = img + "," + m_image.group();
//            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
//            while (m.find()) {
//                pics.add(m.group(1));
//            }
//        }
//        return pics;
//    }
//
//
//    private String uploadBase64File(HttpServletRequest request,String imgStr, String parentName) throws Exception{
//        String path = request.getSession().getServletContext().getRealPath("")+"/";
//        String photoName = GenerateImage(request, imgStr, parentName);
//        InputStream is=new FileInputStream(path+photoName);
//        String fileName=uploadFile(parentName,is);
//        File file=new File(path+photoName);
//        if(file.exists()) file.delete();
//        return fileName;
//    }
//    //base64字符串转化成图片
//    private String GenerateImage(HttpServletRequest request, String imgStr, String parentName) throws Exception{
//        FileHelper fileHelper=new FileHelper();
//        //对字节数组字符串进行Base64解码并生成图片
//        String imagePath = request.getServletContext().getRealPath("photo")+"/"+parentName;
//        fileHelper.mkdir(imagePath);
//        //String newfilename=System.currentTimeMillis()+"";
//        String newfilename=UUID.randomUUID().toString()+"";
//        String filePath=imagePath+"/"+newfilename+".jpg";
//        BASE64Decoder decoder = new BASE64Decoder();
//        //Base64解码
//        imgStr=imgStr.substring(imgStr.indexOf("base64,")+7);
//        byte[] b = decoder.decodeBuffer(imgStr);
//        for(int i=0;i<b.length;++i)
//        {
//            if(b[i]<0)
//            {//调整异常数据
//                b[i]+=256;
//            }
//        }
//        //生成jpeg图片
//        fileHelper.createNewFile(filePath);
//        OutputStream out = new FileOutputStream(filePath);
//        out.write(b);
//        out.flush();
//        out.close();
//        imgStr=null;
//        return "photo/"+parentName+"/"+newfilename+".jpg";
//    }
//    /**
//     * 上传文件
//     * @param ftpPath
//     * @param file
//     * @return
//     * @throws Exception
//     */
//    private String uploadFile(String ftpPath,InputStream file) throws Exception{
//        String fileName=UUID.randomUUID().toString()+".jpg";
//        boolean flag= FtpUtil.uploadFile(S.ftpHost,S.ftpUserName,S.ftpPassword,S.ftpPort,ftpPath,fileName,file);
//        if(flag){
//            return ftpPath+"/"+fileName;
//        }else{
//            return null;
//        }
//
//
//    }
//
//    /**
//     * Description: 向FTP服务器上传文件
//     * @param ftpHost FTP服务器hostname
//     * @param ftpUserName 账号
//     * @param ftpPassword 密码
//     * @param ftpPort 端口
//     * @param ftpPath  FTP服务器中文件所在路径 格式： ftptest/aa
//     * @param fileName ftp文件名称
//     * @param input 文件流
//     * @return 成功返回true，否则返回false
//     */
//    public static boolean uploadFile(String ftpHost, String ftpUserName,
//                                     String ftpPassword, int ftpPort, String ftpPath,
//                                     String fileName,InputStream input) {
//        boolean success = false;
//        FTPClient ftpClient = null;
//        try {
//            int reply;
//            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
//            reply = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftpClient.disconnect();
//                return success;
//            }
//            ftpClient.setControlEncoding("UTF-8"); // 中文支持
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
//            //切换到上传目录
//            if (!ftpClient.changeWorkingDirectory(ftpPath)) {
//                //如果目录不存在创建目录
//                String[] dirs = ftpPath.split("/");
//                String tempPath = "/";
//                for (String dir : dirs) {
//                    if (null == dir || "".equals(dir)) continue;
//                    tempPath += "/" + dir;
//                    if (!ftpClient.changeWorkingDirectory(tempPath)) {
//                        if (!ftpClient.makeDirectory(tempPath)) {
//                            return success;
//                        } else {
//                            ftpClient.changeWorkingDirectory(tempPath);
//                        }
//                    }
//                }
//            }
//            ftpClient.changeWorkingDirectory(ftpPath);
//            ftpClient.setBufferSize(1024*1024);
//            ftpClient.storeFile(fileName, input);
//
//            input.close();
//            ftpClient.logout();
//            success = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (ftpClient.isConnected()) {
//                try {
//                    ftpClient.disconnect();
//                } catch (IOException ioe) {
//                }
//            }
//        }
//        return success;
//    }
//

}
