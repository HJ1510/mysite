package com.bitacademy.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.exception.FileUploadServiceException;

@Service
@PropertySource("classpath:/com/bitacademy/mysite/config/web/fileupload.properties")
public class FileUploadService {
	
	@Autowired
	private Environment env;

	public String restore(MultipartFile multipartFile) throws FileUploadServiceException {
		String url = null;
		try {			
			if (multipartFile.isEmpty()) {
				return url;
			}
			
			File restoreDirectory = new File(env.getProperty("fileupload.uploadLocation"));
			if (!restoreDirectory.exists()) {
				restoreDirectory.mkdirs();
			}
	
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1); // 확장자
			String restoreFilename = generatrSaveFilename(extName);
//			Long fileSize = multipartFile.getSize();
	
//			System.out.println("########" + originalFilename);
//			System.out.println("########" + restoreFilename);
//			System.out.println("########" + fileSize);
			byte[] data = multipartFile.getBytes();
			
			OutputStream os =new FileOutputStream(env.getProperty("fileupload.uploadLocation") + "/" + restoreFilename);
			os.write(data);
			os.close();
			
			url=env.getProperty("fileupload.resourceMapping")+"/" + restoreFilename;
			
		} catch (IOException e) {
			throw new FileUploadServiceException(e.toString());
		}
//		System.out.println("url:"+url);
		return url;
	}

	private String generatrSaveFilename(String extName) {
		String filename="";
		
		Calendar calender = Calendar.getInstance();
		filename += calender.get(Calendar.YEAR);
		filename += calender.get(Calendar.MONTH);
		filename += calender.get(Calendar.DATE);
		filename += calender.get(Calendar.HOUR);
		filename += calender.get(Calendar.MINUTE);
		filename += calender.get(Calendar.SECOND);
		filename += calender.get(Calendar.MILLISECOND);
		filename += ("."+extName);
		
		return filename;
	}

}
