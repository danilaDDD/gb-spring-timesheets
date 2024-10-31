package ru.gb.danila.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.danila.timesheet.annotations.TestRecoverComponent;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		TestRecoverComponent component = ctx.getBean(TestRecoverComponent.class);

		component.returnVoidWithException("");
		System.out.printf("return 0 with return int with error %s", component.returnIntWithIOException());
		System.out.printf("return null with return String %s", component.returnStringWithException(0));
	}



}
