package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 发送邮件
     * @param mailTo 邮件收件人
     * @param title 邮件主题
     * @param content 邮件验证码
     */
    @Override
    public void sendHtmlMail(String mailTo, String title, String content) {
        MimeMessage message=null;
        message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(mailTo);
            helper.setSubject(title);
            helper.setText(content,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);

    }

    /**
     * 发送带有附件的邮件
     * @param mailTo 邮件接收地址
     * @param title 邮件标题
     * @param content 邮件内容
     * @param filePath 文件地址
     * @param fileName 文件名称
     * @param  fileType 文件后缀名
     */
    @Override
    public void sendAttachMail(String mailTo, String title, String content, String filePath, String fileName, String fileType) {
        MimeMessage message=null;
        message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(mailTo);
            helper.setSubject(title);
            helper.setText(content,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(fileName+"."+fileType,file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);

    }
}
