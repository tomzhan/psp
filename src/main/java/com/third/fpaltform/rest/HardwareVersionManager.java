package com.third.fpaltform.rest;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.entity.HardwareVersionEntity;
import com.third.fpaltform.exception.RestException;
import com.third.fpaltform.service.HardwareVersionService;

@RestController
@RequestMapping(value = "/rest")
public class HardwareVersionManager {
	private static Logger logger = LoggerFactory.getLogger(HardwareVersionManager.class);

	@Resource
	private HardwareVersionService hardwareVersionService;
	
	/**
	 * 上传版本信息
	 * @param request
	 * @return
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 */
	@CrossOrigin
	@RequestMapping(value = "/appVersion/appOperation", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Result hardwareVersion(HttpServletRequest request) throws JSchException, SftpException, IOException {
		Integer partModelUid = Integer.valueOf(request.getParameter("partModelUid"));
		Integer hver = Integer.valueOf(request.getParameter("hver"));
		Integer sver = Integer.valueOf(request.getParameter("sver"));
		String signCode = request.getParameter("signCode");
		String fileName = request.getParameter("fileName");
		String description = request.getParameter("description");
		

		Subject subject = SecurityUtils.getSubject();
		String operator = (String) subject.getPrincipal();

		if (null == partModelUid || null == hver || null == sver || null == signCode || null == fileName) {
			throw new RestException("000001");
		}

		String filePath = fileUpload(request);
		
		hardwareVersionService.save(partModelUid, hver, sver, signCode, fileName, filePath, operator, description);

		Result result = new Result();
		result.setStatus(result.STATUS_SUCCESS);
		result.setInfo("版本信息上传成功");
		return result;
	}

	/**
	 * 文件上传保存到本地
	 * 
	 * @param appFile
	 * @return
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 */
	private String fileUpload(HttpServletRequest request) throws JSchException, SftpException, IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 此处file指的是数据类型
		MultipartFile appFile = multipartRequest.getFile("file");
		if (appFile.isEmpty()) {
			throw new RestException("000007");
		}

		String fileName = appFile.getOriginalFilename();
		String sysPath = request.getServletContext().getContextPath() + "/upload/upgrade/";
		String filePath = sysPath + fileName;
		File tempFile = new File(sysPath + fileName);

		appFile.transferTo(tempFile);

		return filePath;
	}
	
	/**
	 * 获取版本信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/version", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result findHardwareVersion(@RequestParam(value = "barcode", required = true) String barcode,
			@RequestParam(value = "type", required = true) Integer type) 
	{
		
		HardwareVersionEntity hardwareVersion = hardwareVersionService.findHardwareVersion(barcode, type);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(hardwareVersion);
		result.setInfo("获取版本信息部件版本信息成功");
		return result;
	}
	
	/**
	 * 部件版本信息更新
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/version", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public Result updateHardwareVersion(@RequestParam(value = "barcode", required = true) String barcode,
			@RequestParam(value = "type", required = true) Integer type) 
	{
		hardwareVersionService.updateHardwareVersion(barcode, type);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setInfo("部件版本信息更新成功");
		return result;
	}
	
}
