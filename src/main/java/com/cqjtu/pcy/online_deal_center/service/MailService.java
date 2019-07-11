package com.cqjtu.pcy.online_deal_center.service;

import groovy.text.TemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.naming.Context;

public interface MailService {
    /**
     * 发送邮件
     * @param mailTo 邮件收件人
     * @param title 邮件主题
     * @param content 邮件验证码
     */
    void sendHtmlMail(String mailTo, String title, String content);
    /**
     * 发送带有附件的邮件
     * @param mailTo 邮件接收地址
     * @param title 邮件标题
     * @param content 邮件内容
     * @param filePath 文件地址
     * @param fileName 文件名称
     * @param  fileType 文件后缀名
     */
    void sendAttachMail(String mailTo,String title,String content,String filePath,String fileName,String fileType);
}

