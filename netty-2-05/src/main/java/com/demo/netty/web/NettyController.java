package com.demo.netty.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/9 18:29
 */
@Controller
public class NettyController {


    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name","xiaohao");
        return "index";
    }
}
