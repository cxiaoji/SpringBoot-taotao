package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.Item;
import com.itxj.pojo.Page;
import com.itxj.service.SearchServie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *   query          page           totalPages
 * 查询的条件    solr查询的数据        共多少页
 */


@Controller
public class SearchController {

	@Reference
	private SearchServie searchServie;
	
	@RequestMapping("/search")
	public String search(Model model,String q, @RequestParam(defaultValue = "1") Integer page){
		System.out.println("q=" + q);
		Page<Item> itemPage = searchServie.SearchItem(q, page);
		//System.out.println("跳转搜索系统页面==" + q);

		model.addAttribute("query",q);
		model.addAttribute("page",itemPage);
		model.addAttribute("totalPages",itemPage.getLast());

        System.out.println("devtools测试中......05");

        return "search";
	}
}
