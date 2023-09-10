package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.service.IPurchaseOrder;
import com.nt.service.PurchaseImp;

@SpringBootApplication
public class Proj1SpringBootMailApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(Proj1SpringBootMailApplication.class, args);
		IPurchaseOrder po=ctx.getBean("order",PurchaseImp.class);
		try {
			po.purchase(new String[] {"dress", "jewellary","lipstick"},new double[] {1400.0,4500.0,400.0},
					new String[] {"bishnupriyasena2@gmail.com","aradyapinky1612@gmail.com","subhashreesena971@gmail.com",
							
					"monalisapinky1997@gmail.com"});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//(ConfigurableApplicationContext(ctx)).close();
	}

}
