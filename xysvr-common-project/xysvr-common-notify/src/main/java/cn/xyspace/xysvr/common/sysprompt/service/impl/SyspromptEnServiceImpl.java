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
import cn.xyspace.xysvr.common.sysprompt.dao.ISyspromptEnMybatisDao;
import cn.xyspace.xysvr.common.sysprompt.entity.SyspromptEn;
import cn.xyspace.xysvr.common.sysprompt.form.manager.SysprompUpdatetMgrForm;
import cn.xyspace.xysvr.common.sysprompt.service.ISyspromptEnService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 英文系统提示业务逻辑实现。
 *
 * @author Tanrongrong(2015年1月24日 下午3:22:26)
 *
 * @since 1.0.0
 *
 * @version 1.0.0
 *
 */
@Service
public class SyspromptEnServiceImpl implements ISyspromptEnService {

    private ISyspromptEnMybatisDao syspromptEnMybatisDao = MybatisDaoProxyFactory.createProxy(ISyspromptEnMybatisDao.class);

    private Clock clock = Clock.DEFAULT;

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public List<SyspromptEn> findsAll() {
        List<SyspromptEn> list = this.syspromptEnMybatisDao.selects();

        return list;
    }

    @Override
    public PageInfo<SyspromptEn> mgrFinds(String pageNumber, String pageSize, Integer code) {
        int thisPageNumber = StringUtils.isNumeric(pageNumber) ? Integer.parseInt(pageNumber) : 1; // 页码
        int thisPageSize = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : Config.PAGE_SIZE; // 本页显示记录数

        PageHelper.startPage(thisPageNumber, thisPageSize); // 调用分页方法 紧跟其后的一个select被分页

        List<SyspromptEn> list = this.syspromptEnMybatisDao.query(code);

        return new PageInfo<SyspromptEn>(list);
    }

    @Override
    public SyspromptEn findById(String id) {
        SyspromptEn entity = this.syspromptEnMybatisDao.selectById(id);

        return entity;
    }

    @Override
    public boolean update(SysprompUpdatetMgrForm form) {
        String id = form.getId();
        String content = form.getContent();
        Long mtime = this.clock.getCurrentTimeInMillis(); // 系统提示修改时间

        return this.syspromptEnMybatisDao.update(id, content, mtime) > 0;
    }

}
