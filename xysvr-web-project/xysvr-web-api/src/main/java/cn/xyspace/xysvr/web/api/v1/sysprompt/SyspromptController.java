/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.api.v1.sysprompt;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptEn;
import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptZhcn;
import cn.xyspace.xysvr.common.sysprompt.service.ISyspromptEnService;
import cn.xyspace.xysvr.common.sysprompt.service.ISyspromptZhcnService;

/**
 * 中文系统提示 RESTful API。
 * 
 * @author TanRongrong(2015年1月24日 下午2:51:45)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "/api/v1/sysprompt")
public class SyspromptController {

    // private static final Logger logger = LoggerFactory.getLogger(SyspromptController.class);

    @Inject
    private ISyspromptZhcnService syspromptZhcnService;

    @Inject
    private ISyspromptEnService syspromptEnService;

    /**
     * 获取所有中文系统提示列表。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/zhcn", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public List<SyspromptZhcn> list() {
        List<SyspromptZhcn> list = this.syspromptZhcnService.findsAll();
        return list;
    }

    /**
     * 获取所有英文系统提示列表。
     *
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping(value = "/en", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
    public List<SyspromptEn> enList() {
        List<SyspromptEn> list = this.syspromptEnService.findsAll();
        return list;
    }

}
