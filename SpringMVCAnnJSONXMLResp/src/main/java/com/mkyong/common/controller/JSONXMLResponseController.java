package com.mkyong.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkyong.common.model.Coffee;
import com.mkyong.common.model.Shop;

@Controller
@RequestMapping("/kfc")
public class JSONXMLResponseController {

	@RequestMapping(value = "/JSON/{name}", method = RequestMethod.GET ,headers = "Accept=application/json")
	public @ResponseBody
	Shop getShopInJSON(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });

		return shop;

	}
	
	@RequestMapping(value = "/XML/{name}", method = RequestMethod.GET ,headers = "Accept=application/xml")
	public @ResponseBody
	Coffee getShopInXML(@PathVariable String name) {

		Coffee coff= new Coffee();
		coff.setName(name);
		coff.setQuanlity(2);

		return coff;

	}

}