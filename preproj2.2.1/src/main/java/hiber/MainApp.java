package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class, Car.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW123", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Mercedes4", 121)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Zhigul", 100)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Audi", 33)));
      userService.add(new User("sdfsd", "fsdgwr", "wtwt@mail.ru", new Car("Mercedes4", 121)));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getModel() + ", series = " + user.getCar().getSeries());
         System.out.println();
      }

      List<User> users2 = userService.getUserByCar("Mercedes4", 121);
      System.out.println("Юзеры с данной машиной, модель = Mercedes4, серия = 121");
      for (User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getModel() + ", series = " + user.getCar().getSeries());
         System.out.println();
      }


      context.close();
   }
}
