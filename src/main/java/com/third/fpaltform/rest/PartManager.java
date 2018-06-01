package com.third.fpaltform.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.third.fpaltform.common.ExcelHeader;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.common.OperationLog;
import com.third.fpaltform.entity.Diagnosis;
import com.third.fpaltform.exception.RestException;
import com.third.fpaltform.exception.ServiceExceptionHandle;
import com.third.fpaltform.service.PartService;

@RestController
@RequestMapping(value = "/rest")
public class PartManager 
{
	@Resource
	private PartService partService;
	
	@Resource
	private ServiceExceptionHandle serviceExceptionHandle;
	
	/**
	 * 部件信息导入
	 * @param request
	 * @param response
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/part/import", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Result partImport(HttpServletRequest request,HttpServletResponse response) 
	{
		Result result = new Result();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		
		 //取得当前上传文件的文件名称  
        String fileName = file.getOriginalFilename();  
        
        if(fileName != null && !"".equals(fileName))
        {
        	if(!fileName.endsWith(ExcelHeader.FILE_TYPE_XLSX) && !fileName.endsWith(ExcelHeader.FILE_TYPE_XLS))
        	{
				throw new RestException("文件格式错误");
        	}
        }
        
        List<Map<String, Object>> list = null;
        
        try {
        	list = partService.partImport(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("700000");
		result.setInfo(serviceExceptionHandle.genInfoById("700000"));
		return result;
	}

}
