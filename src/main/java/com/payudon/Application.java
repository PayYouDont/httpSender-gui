package com.payudon;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

import com.payudon.gui.HttpSendJFrame;
@SpringBootApplication
@ServletComponentScan
public class Application {
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
		ApplicationContext ac = builder.headless(false).run(args);
		ac.getBean(HttpSendJFrame.class).start();
	}
}
