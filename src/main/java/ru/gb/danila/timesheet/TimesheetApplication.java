package ru.gb.danila.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.danila.timesheet.annotations.TestRecoverComponent;
import ru.gb.danila.timesheet.model.Project;
import ru.gb.danila.timesheet.model.Timesheet;
import ru.gb.danila.timesheet.service.ProjectService;
import ru.gb.danila.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		TestRecoverComponent component = ctx.getBean(TestRecoverComponent.class);
		System.out.printf("return 0 without int %s", component.returnIntWithIOException());
	}



}
