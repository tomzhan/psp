package com.third.fpaltform.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.third.fpaltform.common.ExcelUtil;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.PartRepository;

@Component
public class PartService 
{
	
	@Autowired
	private PartRepository partRepository;

	/**
	 * 部件信息导入
	 * @param inputStream
	 * @return
	 */
	public List<Map<String, Object>> partImport(InputStream inputStream) 
	{
		List<Object> list = ExcelUtil.readExcelToObjectByPath(inputStream, null, 0, 0);
		
		if(list == null && list.size() == 0)
		{
			throw new ServiceException("Excel为空");
		}
		
		return null;
	}

}
