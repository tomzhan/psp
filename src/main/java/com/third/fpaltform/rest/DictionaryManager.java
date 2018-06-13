package com.third.fpaltform.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.entity.DictionaryEntity;
import com.third.fpaltform.service.DictionaryService;

@RestController
@RequestMapping(value = "/rest")
public class DictionaryManager 
{
	private static Logger logger = LoggerFactory.getLogger(DictionaryManager.class);

	@Resource
	private DictionaryService dictionaryService;
	
	/**
	 * 查询数据字典数据
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/dictionary", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result getDictionary(@RequestParam(value = "id", required = true) Integer id)
	{
		List<DictionaryEntity> dictionarys = dictionaryService.getDictionary(id);
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(dictionarys);
		result.setInfo("数据字典查询成功");
		return result;
	}

}
