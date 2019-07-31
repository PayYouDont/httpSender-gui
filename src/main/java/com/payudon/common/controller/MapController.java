
package com.payudon.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.payudon.common.config.ApplicationContextRegister;
import com.payudon.util.JsonWrapper;

/**
 * @ClassName: MapController
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2018年8月10日 上午11:51:00
 * 
 */
@Controller
@RequestMapping("/map")
public class MapController {

	@GetMapping("/toIndex")
	public String toIndex() {
		return "index";
	}

	@RequestMapping("/getAllUrl")
	@ResponseBody
	public HashMap<String, Object> down() {
		RequestMappingHandlerMapping mapping = ApplicationContextRegister.getBean(RequestMappingHandlerMapping.class);
		// 获取url与类和方法的对应信息
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
		List<String> urlList = new ArrayList<>();
		for (RequestMappingInfo info : map.keySet()) {
			// 获取url的Set集合，一个方法可能对应多个url
			Set<String> patterns = info.getPatternsCondition().getPatterns();
			for (String url : patterns) {
				urlList.add(url);
			}
		}
		System.out.println(urlList);
		return JsonWrapper.successWrapper(urlList);
	}

	@RequestMapping("/users/{username}")
	@ResponseBody
	public String test(@PathVariable String username) {
		System.out.println(username);
		return username;
	}
}
