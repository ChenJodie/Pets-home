package com.helps.pets.home.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/helps")
public class BaseController {
    private static final Logger log = LogManager.getLogger(BaseController.class);

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object root() {
        return "redirect:welcome";
    }

    @ResponseBody
    @RequestMapping(value="/welcome",method=RequestMethod.GET)
    public Object welcome() {
        return "welcome";
    }

//    @RequestMapping(value="/bugreport",method=RequestMethod.POST)
//    public String bugreport(String bugDesc, String exceptionDesc){
//        String errorMsg = "快驴系统有人汇报bug了：\n" + exceptionDesc + "\n" + bugDesc;
//        log.info(errorMsg);
////        DaxiangUtil.push("xianfu.help@meituan.com", errorMsg, "guofeipeng@meituan.com", "pengpeng04@meituan.com");
//        return "redirect:/welcome";
//    }

}
