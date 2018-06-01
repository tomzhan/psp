package com.third.fpaltform.service;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.HardwareVersionEntity;
import com.third.fpaltform.entity.PartEntity;
import com.third.fpaltform.entity.PartModelEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.HardwareVersionRepository;
import com.third.fpaltform.repository.PartModelRepository;
import com.third.fpaltform.repository.PartRepository;

@Component
public class HardwareVersionService 
{
	private static Logger logger = LoggerFactory.getLogger(DivisionService.class);

	@Resource
	private HardwareVersionRepository hardwareVersionRepository;
	
	@Resource
	private PartModelRepository partModelRepository;
	
	@Resource
	private PartRepository partRepository;
	
	/**
	 * 保存版本信息
	 * @param partModelUid
	 * @param hver
	 * @param sver
	 * @param signCode
	 * @param fileName
	 * @param filePath
	 * @param operator 
	 * @param description 
	 */
	@Transactional
	public void save(String partModelUid, String hver, String sver, String signCode, String fileName, String filePath, String operator, String description) 
	{
		PartModelEntity partModel = partModelRepository.findOne(Integer.parseInt(partModelUid));
		
		if(partModel == null)
		{
			throw new ServiceException("部件型号信息不存在");
		}
		
		hardwareVersionRepository.updateHardwareVersion(partModel.getUid());
		
		HardwareVersionEntity h = new HardwareVersionEntity();
		h.setFileName(fileName);
		h.setHver(hver);
		h.setOperator(operator);
		h.setPartModel(partModel);
		h.setSignCode(signCode);
		h.setStatus(HardwareVersionEntity.STATUS_YES);
		h.setSver(sver);
		h.setUploadTime(new Date());
		h.setUrl(filePath);
		h.setDescription(description);
		
		h = hardwareVersionRepository.save(h);
		
	}

	/**
	 * 获取版本信息
	 * @param barcode
	 * @param type
	 * @return
	 */
	public HardwareVersionEntity findHardwareVersion(String barcode, Integer type) 
	{
		PartEntity part = partRepository.findPart(barcode, type);
		
		if(part == null)
		{
			throw new ServiceException("部件型号信息不存在");
		}
		
		
		HardwareVersionEntity hardwareVersion = hardwareVersionRepository.findHardwareVersion(part.getModelId());
		return hardwareVersion;
	}

	/**
	 * 部件版本信息更新
	 * @param barcode
	 * @param type
	 * @return
	 */
	public void updateHardwareVersion(String barcode, Integer type) 
	{
		PartEntity part = partRepository.findPart(barcode, type);
		
		if(part == null)
		{
			throw new ServiceException("部件型号信息不存在");
		}
		
		HardwareVersionEntity hardwareVersion = hardwareVersionRepository.findHardwareVersion(part.getModelId());
		
		part.setSver(hardwareVersion.getSver());
		part.setHver(hardwareVersion.getHver());
		
		partRepository.save(part);
	}

}
