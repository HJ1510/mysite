package com.bitacademy.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.exception.FileUploadServiceException;

@Service
public class FileUploadService {
	
	private static String RESTORE_PATH = "/mysite-uploads";
	private static String URL_BASE = "/gallery/images";

	public String restore(MultipartFile multipartFile) throws FileUploadServiceException {
		String url = null;
		try {
			if (multipartFile.isEmpty()) {
				return url;
			}
			
			File restoreDirectory = new File(RESTORE_PATH);
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
			
			OutputStream os =new FileOutputStream(RESTORE_PATH + "/" + restoreFilename);
			os.write(data);
			os.close();
			
			url=URL_BASE+"/" + restoreFilename;
			
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
