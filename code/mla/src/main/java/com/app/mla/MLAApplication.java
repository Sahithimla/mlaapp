package com.app.mla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author Sahithi
 *
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.app.mla"})
@EnableTransactionManagement
public class MLAApplication {

	public static void main(String[] args) {
		SpringApplication.run(MLAApplication.class, args);
		System.out.println("MLA application started....................---------------------------------");
	}

}
