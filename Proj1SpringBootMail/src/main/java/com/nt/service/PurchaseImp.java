package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
@Service("order")
public class PurchaseImp implements IPurchaseOrder {
	@Autowired
	public JavaMailSender sender;
	@Value("${spring.mail.username}")
    private String fromMail;
	//public double bill=0.0;
	@Override
	public String purchase(String[] items, double[] prices, String[] toMails) throws Exception{
	 double bill=0.0;
		for(double p:prices) 
			bill=bill+p;
		
		String msg= Arrays.toString(items)+"items are purchased at prices"+Arrays.toString(prices)+"having total bill amt"+bill;
		String status=sendMail(toMails,msg);
		return msg +"-->"+status;
	}
	
	public String sendMail(String []toMails,String msg) throws Exception{
	MimeMessage mm=sender.createMimeMessage();//create emply mail message
	MimeMessageHelper helper=new MimeMessageHelper(mm, true);//add inputs to mail msg
	helper.setFrom(fromMail);
	helper.setCc(toMails);
	helper.setSubject("your purchase details");
	helper.setSentDate(new Date());
	helper.setText(msg);
	helper.addAttachment("dress.avif", new ClassPathResource("dress.avif"));
	helper.addAttachment("lipstick.jfif", new ClassPathResource("lipstick.jfif"));
	helper.addAttachment("necklace.jfif", new ClassPathResource("necklace.jfif"));
	sender.send(mm);//send mail
	return "mail sent";
	}

}
