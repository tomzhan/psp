package com.third.fpaltform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DictionaryEntity;
import com.third.fpaltform.repository.DictionaryRepository;

@Component
public class DictionaryService 
{
	
	@Resource
	private DictionaryRepository dictionaryRepository;

	public List<DictionaryEntity> getDictionary(Integer id) 
	{
		List<DictionaryEntity> dictionarys = dictionaryRepository.findByUid(id);
		
		for(DictionaryEntity d : dictionarys)
		{
			d.setId(d.getDictionaryMultiKeys().getUid());
			d.setCode(d.getDictionaryMultiKeys().getCode());
			d.setDictionaryMultiKeys(null);
		}
		
		return dictionarys;
	}


}
