package com.ekankhek.ekankhek.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ekankhek.ekankhek.domain.Datauploads;
import com.ekankhek.ekankhek.domain.User;
import com.ekankhek.ekankhek.helper.CommonHelper;
import com.ekankhek.ekankhek.service.DatauploadsService;

@Controller
public class IndexController {

	@Autowired
	DatauploadsService dus;
	
	@RequestMapping("/login.html")
	public String showLogin() {
		return "login";
	}
	@RequestMapping("/login")
	public String logout() {
		return "login";
	}
	@RequestMapping("/")
	public String index(ModelMap map) {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			map.addAttribute("username", user.getName());
			List<Datauploads> files = dus.findByUsername(user);
			map.addAttribute("fileData", files);
			map.addAttribute("base_url", CommonHelper.base_url);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("/upload.html")
	public String upload() {
		return "upload";
	}
	
	@RequestMapping(value="/delete",method= {RequestMethod.POST})
	public String delete(@RequestParam("deleteFile") String deleteFile) {
		try {
			Datauploads dp = dus.findById(Long.valueOf(deleteFile));
			String filename = dp.getFilename();
			dus.delete(dp);
			
			File f = new File(CommonHelper.root_path+filename);
			f.delete();
		}catch(Exception e) {
			return "redirect:/?q=Failed To Delete";
		}
		return "redirect:/?q=Record Deleted";
	}
}
