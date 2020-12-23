package ui.processor;

import auth.Login;
import auth.Register;
import exceptions.InvalidCommandException;
import exceptions.ModelNotFoundException;
import models.user.User;
import util.ScannerReader;
import util.color.Color;
import util.color.ColorChanger;
import util.validators.ValidatorUtils;


public class AuthCommandProcessor implements CommandsProcessor {


    private ScannerReader scanner;

    public AuthCommandProcessor() {
        this.scanner = ScannerReader.getInstance();
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
                username = scanner.readLine("Enter username");
                password = scanner.readLine("Enter password");
                Login.login(username, password);
                ColorChanger.changeColor(Color.GREEN);
                break;
            case 2:
                System.out.println();
                username = scanner.readLine("Enter username");
                password = scanner.readLine("Enter password");
                String name = scanner.readLine("Enter name");
                String surname = scanner.readLine("Enter surname");
                String email = scanner.readLine("Enter email");
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
