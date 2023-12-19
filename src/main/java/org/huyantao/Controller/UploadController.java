package org.huyantao.Controller;

import org.huyantao.pojo.Result;
import org.huyantao.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author:deconglin
 * @date:2023-08-07-14:53
 * @description:
 */
@Slf4j
@RestController
public class UploadController {

    //todo 文件的本地存储 文件大小默认是1MB
    //todo 一般不会会使用这种方法 云~~~~~
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传:{},{},{}",username,age,image);
//
//        //获取原始的文件名的拓展名就可以实现 文件名唯一uuid(通用唯一识别码)+文件的拓展名
//        String originalFilename = image.getOriginalFilename();
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新的文件名：{}",newFileName);
//        //将文件存储在服务器的磁盘目录下 E:\BaiduNetdiskDownload\image
//        image.transferTo(new File("E:\\BaiduNetdiskDownload\\image\\"+newFileName));
//        return Result.success();
//    }

    @Autowired
    private AliOSSUtils aliOSSUtils;
    //todo 下面使用阿里云实现
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传:{}",image.getOriginalFilename());

        //调用阿里云oss工具类进行文件上传
        String url= aliOSSUtils.upload(image);
        log.info("文件上传完成,文件上传的url为:{}",url);
        return Result.success(url);
    }
}
