package com.ekankhek.ekankhek.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekankhek.ekankhek.domain.Datauploads;
import com.ekankhek.ekankhek.helper.CommonHelper;
import com.ekankhek.ekankhek.service.DatauploadsService;


@Controller
public class FileDownloadController {

	@Autowired
	DatauploadsService dus;
	
	@GetMapping( value = "/dw/{key}")
	@ResponseBody
    public ResponseEntity<Resource> serveLargeFile(HttpServletRequest request, @PathVariable String key) {
	
			System.out.println(" UUID IS "+key);
			Datauploads du = dus.findByCode(key);
			
			if(du!=null) {
				String filename = du.getFilename();
				System.out.println(filename);
				File file = new File(CommonHelper.root_path+filename);
				if(file.exists()) {
					 InputStreamResource resource = null;
					 try {
							resource = new InputStreamResource(new FileInputStream(file));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
				        return ResponseEntity.ok()
				                // Content-Disposition
				                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				                // Content-Type
				                .contentType(mediaType)
				                // Contet-Length
				                .contentLength(file.length())
				                .body(resource);
				}
			}
		return null;
	}
}
