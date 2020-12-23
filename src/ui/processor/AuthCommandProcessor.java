package ui.processor;

import auth.Login;
import auth.Register;
import exceptions.InvalidCommandException;
import exceptions.InvalidPropertyException;
import exceptions.ModelNotFoundException;
import models.user.User;
import util.color.Color;
import util.color.ColorChanger;
import util.validators.ValidatorUtils;

import java.util.Scanner;

public class AuthCommandProcessor implements CommandsProcessor {


    private Scanner scanner;

    public AuthCommandProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void processMainCommands(String command) throws InvalidCommandException, NumberFormatException, ModelNotFoundException {

        if (!command.matches("[1-2]")) {
            throw new InvalidCommandException(command);
        }
        int commandNumber = Integer.parseInt(command);

        String username;
        String password;
        switch (commandNumber) {
            case 1:
                System.out.println("Enter username");
                username = scanner.nextLine();
                System.out.println("Enter password");
                password = scanner.nextLine();
                Login.login(username, password);
                ColorChanger.changeColor(Color.GREEN);
                break;
            case 2:
                System.out.println("Enter username");
                username = scanner.nextLine();
                System.out.println("Enter password");
                password = scanner.nextLine();
                System.out.println("Enter name");
                String name = scanner.nextLine();
                System.out.println("Enter surname");
                String surname = scanner.nextLine();
                System.out.println("Enter email");
                String email = scanner.nextLine();
                ValidatorUtils.validateProperties(name,surname,username,email,password);
                User user = new User.Builder()
                        .setUsername(username)
                        .setPassword(password)
                        .setEmail(email)
                        .setSurname(surname)
                        .setName(name).build();
                Register.register(user);

                break;
            default:
                throw new InvalidCommandException(command);
        }

    }
}
