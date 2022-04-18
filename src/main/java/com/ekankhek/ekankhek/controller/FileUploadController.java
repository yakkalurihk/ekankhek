package com.ekankhek.ekankhek.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ekankhek.ekankhek.domain.Datauploads;
import com.ekankhek.ekankhek.domain.User;
import com.ekankhek.ekankhek.helper.CommonHelper;
import com.ekankhek.ekankhek.service.DatauploadsService;

@Controller
public class FileUploadController {
	
	@Autowired
	DatauploadsService dus;
	
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody Response<String> upload(HttpServletRequest request) {
    	SimpleDateFormat sdf = CommonHelper.getDateFromat("MMHHmmss");
    	String buffer = sdf.format((new Date()));
    	HashMap<String,String> map = new HashMap<String,String>();
		try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                // Inform user about invalid request
                Response<String> responseObject = new Response<String>(false, "Not a multipart request.", "");
                return responseObject;
            }

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();

            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
            	
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                
                InputStream stream = item.openStream();
                if (!item.isFormField()) {
                    String filename = buffer+"_"+item.getName();
                    map.put(name, filename);
                    System.out.println(filename);
                    // Process the input stream
                    OutputStream out = new FileOutputStream(CommonHelper.root_path+filename);
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                }else {
                	String formFieldValue = Streams.asString(stream);
                	 map.put(name, formFieldValue);
                }
            }
        } catch (FileUploadException e) {
            return new Response<String>(false, "File upload error", e.toString());
        	
        } catch (IOException e) {
            return new Response<String>(false, "Internal server IO error", e.toString());
        } catch(Exception e) {
        	e.printStackTrace();
        	 return new Response<String>(false, "Unknown Error", e.toString());
        }

        try {
        	System.out.println(map);
        	if(!map.isEmpty()) {
        		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        		Datauploads data = new Datauploads();
        		data.setDescription(map.get("descriptionFile"));
        		data.setFilename(map.get("file"));
        		data.setTitle(map.get("title"));
        		data.setUser(user);
        		dus.save(data);
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        return new Response<String>(true, "Success", "");
        
    }

}

class Response<T> {
    /** Boolean indicating if request succeeded **/
    private boolean status;

    /** Message indicating error if any **/
    private String message;

    /** Additional data that is part of this response **/
    private T data;

    public Response(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}