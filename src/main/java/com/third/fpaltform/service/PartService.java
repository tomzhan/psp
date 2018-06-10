package com.third.fpaltform.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.third.fpaltform.common.ExcelUtil;
import com.third.fpaltform.entity.PartEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.PartRepository;

@Component
public class PartService {

	@Autowired
	private PartRepository partRepository;

	/**
	 * 部件信息导入
	 * 
	 * @param inputStream
	 * @return
	 */
	public void partImport(InputStream inputStream) {
		 List<Object> list = ExcelUtil.readExcelToObjectByPath(inputStream, null, 1, 0);
		
		 if(list == null || list.size() == 0)
		 {
			 throw new ServiceException("Excel为空");
		 }

		PartEntity part = null;
		List<PartEntity> partList = new ArrayList<PartEntity>();

		for (Object obj : list) {
			part = (PartEntity) obj;

			PartEntity part1 = partRepository.findPart(part.getBarCode(), part.getType());
			if (part1 != null) {
				throw new ServiceException("部件barcode与type信息已存在:" + part.getBarCode() + ":" + part.getType());
			}

			part.setProductDate(strToDateLong(part.getProductTime()));
			part.setOutDate(strToDateLong(part.getProductTime()));
			part.setStatus(0);

			partList.add(part);

		}

		partRepository.save(partList);
	}

	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

}
