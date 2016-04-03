/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.sysprompt.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springside.modules.utils.Clock;

import cn.xyspace.xysvr.common.core.dao.factory.MybatisDaoProxyFactory;
import cn.xyspace.xysvr.common.core.sysconfig.Config;
import cn.xyspace.xysvr.common.sysprompt.dao.ISyspromptZhcnMybatisDao;
import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptZhcn;
import cn.xyspace.xysvr.common.sysprompt.form.manager.SysprompUpdatetMgrForm;
import cn.xyspace.xysvr.common.sysprompt.service.ISyspromptZhcnService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 中文系统提示业务逻辑实现。
 * 
 * @author Tanrongrong(2015年1月24日 下午2:36:48)
 *
 * @since 1.0.0
 * 
 * @version 1.0.0
 */
@Service
public class SyspromptZhcnServiceImpl implements ISyspromptZhcnService {

    private ISyspromptZhcnMybatisDao syspromptZhcnMybatisDao = MybatisDaoProxyFactory.createProxy(ISyspromptZhcnMybatisDao.class);

    private Clock clock = Clock.DEFAULT;

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public List<SyspromptZhcn> findsAll() {
        List<SyspromptZhcn> list = this.syspromptZhcnMybatisDao.selects();

        return list;
    }

    @Override
    public PageInfo<SyspromptZhcn> mgrFinds(String pageNumber, String pageSize, Integer code) {
        int thisPageNumber = StringUtils.isNumeric(pageNumber) ? Integer.parseInt(pageNumber) : 1; // 页码
        int thisPageSize = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : Config.PAGE_SIZE; // 本页显示记录数

        PageHelper.startPage(thisPageNumber, thisPageSize); // 调用分页方法 紧跟其后的一个select被分页

        List<SyspromptZhcn> list = this.syspromptZhcnMybatisDao.query(code);

        return new PageInfo<SyspromptZhcn>(list);
    }

    @Override
    public SyspromptZhcn findById(String id) {
        SyspromptZhcn entity = this.syspromptZhcnMybatisDao.selectById(id);

        return entity;
    }

    @Override
    public boolean update(SysprompUpdatetMgrForm form) {
        String id = form.getId();
        String content = form.getContent();
        Long mtime = this.clock.getCurrentTimeInMillis(); // 系统提示修改时间

        return this.syspromptZhcnMybatisDao.update(id, content, mtime) > 0;
    }

}
