package ru.gb.danila.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
		initialData(ctx);
	}

	private static void initialData(ApplicationContext ctx) {
		TimesheetService timesheetService = ctx.getBean(TimesheetService.class);
		ProjectService projectService = ctx.getBean(ProjectService.class);

		for (int i = 1; i <= 4 ; i++) {
			projectService.create(new Project((long)i, String.format("Project # %s", i)));
		}

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			Timesheet timesheet = new Timesheet(
					(long) i,
					ThreadLocalRandom.current().nextLong(1, 5),
					ThreadLocalRandom.current().nextInt(100, 10000),
					createdAt.plusDays(i)
			);

			timesheetService.create(timesheet);
		}
	}

}
