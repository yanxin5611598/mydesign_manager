package com.yx.mydesign.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

import com.yx.mydesign.mapper.AdvertMapper;
import com.yx.mydesign.utils.FileUtil;
import com.yx.mydesign.bean.Advert;
import com.yx.mydesign.dto.AdvertDto;

@Service
public class AdvertService {
	@Autowired 
	AdvertMapper advertMapper;
	@Value("${adImage.savePath}")//读取配置文件中的图片保存路径-----service.properties中的adImage.savePath
	private String adImageSavePath;
	@Value("${adImage.url}")
	private String adImageUrl;
	/**用于查询当前数据库中存在的所有广告信息*/
	public List<Advert> getAllAdvert(){
		return advertMapper.selectByPage(null);
	}
	/**用于添加新的广告信息到数据库中*/
	/*public void addAdvert(Advert advert){
		advertMapper.insert(advert);
	}*/
	public boolean addAdvert(AdvertDto advertDto){
		Advert advert = new Advert();
		advert.setTitle(advertDto.getTitle());
		advert.setLink(advertDto.getLink());
		advert.setWeight(advertDto.getWeight());
		if(advertDto.getImgFile()!=null && advertDto.getImgFile().getSize()>0){
			String fileName = System.currentTimeMillis() + "_" + advertDto.getImgFile().getOriginalFilename();
			File file = new File(adImageSavePath + fileName);//adImageSavePath + fileName----文件保存的路径
			File fileFolder = new File(adImageSavePath);
			//判断文件夹是否存在
			if(!fileFolder.exists()){
				fileFolder.mkdirs();//文件夹不存在进行创建操作---可创建多级目录的文件
			}
			try {
				advertDto.getImgFile().transferTo(file);//transferTo方法用于转存文件到指定的路径下
				advert.setImgFileName(fileName);
				System.out.println(fileName);
				System.out.println(advert.toString());
				advertMapper.insert(advert);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
	}
	public String returnURL(){
		return adImageUrl;
	}
	/**
     * 分页搜索广告列表
     * @param adDto 查询条件(包含分页对象)
     * @return 广告列表
     */
	public List<AdvertDto> searchByPage(AdvertDto advertDto) {
		List<AdvertDto> result = new ArrayList<AdvertDto>();
		Advert condition = new Advert();
		BeanUtils.copyProperties(advertDto, condition);
		List<Advert> advertList = advertMapper.selectByPage(condition);
		for (Advert ad : advertList) {
			AdvertDto advertDtoTemp = new AdvertDto();
			result.add(advertDtoTemp);
			System.out.println(adImageUrl);
			advertDtoTemp.setImg(adImageUrl + ad.getImgFileName());
			BeanUtils.copyProperties(ad, advertDtoTemp);
		}
		return result;
	}
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		advertMapper.deleteByPrimaryKey(id);
	}
	/**修改页面初始化信息获取*/
	public Advert getAdvertById(Integer id) {
		Advert advert = advertMapper.selectByPrimaryKey(id);
		if(advert!=null){
			return advert;
		}
		return null;
	}
	/**广告修改的真正实现逻辑*/
	public boolean advertModify(AdvertDto advertDto) {
		Advert advert = new Advert();
		BeanUtils.copyProperties(advertDto, advert);
		String fileName = null;
		System.out.println(advert.toString());
		if(!advertDto.getImgFile().isEmpty()){
			//上传的文件不为空
			try {
				System.out.println(adImageSavePath);
				fileName = FileUtil.save(advertDto.getImgFile(), adImageSavePath);
				advert.setImgFileName(fileName);
				System.out.println(advert.getImgFileName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		int updateCount = advertMapper.updateByPrimaryKey(advert);
		if(updateCount!=1){
			return false;
		}
		if (fileName != null) {
			return FileUtil.delete(adImageSavePath + advertDto.getImgFileName());
		}
		return true;
	}
}
