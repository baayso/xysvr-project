/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.tool.mail.service;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.xyspace.xysvr.common.core.utils.Constants;

/**
 * 发送邮件工具类。
 * 
 * @author ChenFangjie(2015年4月29日 下午3:13:04)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Inject
    private JavaMailSender sender;

    @Inject
    private TaskExecutor taskExecutor;

    /**
     * 异步发送HTML邮件。
     * 
     * @param sendTo
     * @param subject
     * @param htmlText
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void sendAsyncHtmlMail(String sendTo, String subject, String htmlText) {
        this.taskExecutor.execute(() -> {
            try {
                MimeMessage msg = this.sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(msg, false, Constants.UTF_8);
                helper.setFrom(Constants.FROM_EMAIL);
                helper.setTo(sendTo);
                helper.setSubject(subject);
                helper.setText(htmlText, true);

                this.sender.send(msg);
            }
            catch (Exception e) {
                logger.error("发送邮件失败！", e);
            }
        });
    }

}
