package com.ower.dsyz.gateway.service.impl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ower.dsyz.common.core.exception.BusinessException;
import com.ower.dsyz.common.core.response.CustomResponse;
import com.ower.dsyz.common.core.util.DateUtil;
import com.ower.dsyz.gateway.manual.dto.GatewayRequest;

@Service
public class GatewayFileUploadServiceImpl extends AbstractGatewayLevelService{

	@Value("${file.upload.store.path:d://file}")
	private String storagePath;
	
	@Override
	CustomResponse<?> resultHandle(GatewayRequest request) {
		createToken(request.getToken());
		if(request.getFiles()==null || request.getFiles().isEmpty()){
			throw new BusinessException("001424", "上传文件为空");
		}
		String pathPrefixed = this.getRealFilePath(request.getAppName());
		List<String> list = new ArrayList<String>();
		for(MultipartFile file:request.getFiles()){
			String path = pathPrefixed+"/"+System.currentTimeMillis()+"_"+file.getOriginalFilename();
			File f = new File(getStoragePath()+request.getAppName()+path);
			try {
				file.transferTo(f);
				list.add(path);
			} catch (Exception e) {
				logger.error("上传文件异常:{}",e);
			    throw new BusinessException("上传文件异常");
			}
		}
		return CustomResponse.success(list);
		
		
	}

	public String getStoragePath() {
		if(!storagePath.endsWith("/")){
			storagePath+="/";
		}
		return storagePath;
	}

	public String getRealFilePath(String store){
		String path = "/"+DateUtil.formatDate(new Date(), DateUtil.FORMAT_Y_M_D);
		File file = new File(this.getStoragePath()+store+path);
		if(!file.exists()||!file.isDirectory()){
			file.mkdirs();
		}
		return path;
	}
}
