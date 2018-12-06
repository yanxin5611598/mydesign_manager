package com.yx.mydesign.service.client;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yx.mydesign.bean.UserCheckResult;
import com.yx.mydesign.mapper.UserCheckResultMapper;


@Service
public class StoreOrSelectService {
	@Autowired
	private UserCheckResultMapper userCheckResultMapper;
	@Value("${radarImage.savePath}")//读取配置文件中的图片保存路径-----service.properties中的adImage.savePath
	private String radarImageSavePath;
	/**保存雷达图
	 * */
	public boolean saveRadarImage(String picInfo,String imageName){
		if(picInfo == null){
			System.out.println("saveRadarImage Fail");
			return false;
		}
		//获取服务器根路径到/static/images/的目录路径
        String radarImageFullSavePath= radarImageSavePath+imageName;
        System.out.println(radarImageFullSavePath);
        // 传递过程中  "+" 变为了 " ".
        String newPicInfo = picInfo.replaceAll(" ", "+");
        decodeBase64(newPicInfo, new File(radarImageFullSavePath));
        //log.warn("从echarts中生成图片的的路径为:{}", picPath);
        return true;
    }
	/**
     * 解析Base64位信息并输出到某个目录下面.
     * @param base64Info base64串
     * @param picPath 生成的文件路径
     * @return 文件地址
     */
    private File decodeBase64(String base64Info, File picPath) {
        if (StringUtils.isEmpty(base64Info)) {
            return null;
        }

        // 数据中：data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABI4AAAEsCAYAAAClh/jbAAA ...  在"base64,"之后的才是图片信息
        String[] arr = base64Info.split("base64,");

        // 将图片输出到系统某目录.
        OutputStream out = null;
        try {
            // 使用了Apache commons codec的包来解析Base64
            byte[] buffer = Base64.decodeBase64(arr[1]);
            out = new FileOutputStream(picPath);
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            //log.error("解析Base64图片信息并保存到某目录下出错!", e);
        } finally {
            IOUtils.closeQuietly(out);
        }

        return picPath;
    }
    /**
     * 创建一条记录，用于保存用户名和对应的雷达图所在服务器的路径
     * @param imageInfo */
    public boolean createRecord(String username,String imagename, String imageInfo){
    	UserCheckResult record = new UserCheckResult();
    	record.setUsername(username);
    	record.setImagepath(radarImageSavePath+imagename);
    	record.setImageinfo(imageInfo);
    	int result = userCheckResultMapper.insert(record);
    	if(result == 0){
    		return false;
    	}
    	return true;
    }
}
