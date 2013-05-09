package jp.co.jti.ims.tc.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running Spring Boot Application");
		if (args.length > 0 ) {
			for (String arg : args) {
				System.out.println("Command line argument: " + arg);
			} 
			this.invokeClass(args);
		}
		
	}

	private void invokeClass(String[] args) {
		try {
			String className = "jp.co.jti.ims.tc.batch." + args[0];

			// Create an instance of the required class. 
			Class<?> c = Class.forName(className);
			Object object = Class.forName(className).getDeclaredConstructor().newInstance();

			// Ask Spring to populate its dependencies.
			this.applicationContext.getAutowireCapableBeanFactory().autowireBean(object);

			// Get main function of the class.
			Class[] argTypes = new Class[] { String[].class };
			Method main = c.getDeclaredMethod("main", argTypes);

			// Prepare parameters to pass into main function of the class.
			String[] mainArgs = Arrays.copyOfRange(args, 1, args.length);

			// Invoke main function of the class.
			System.out.format("invoking %s.main()%n", c.getName());
			main.invoke(object, (Object) mainArgs);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
