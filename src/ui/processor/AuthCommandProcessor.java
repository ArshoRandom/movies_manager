package ui.processor;

import auth.Login;
import auth.Register;
import exceptions.InvalidCommandException;
import exceptions.ModelNotFoundException;
import models.user.User;
import util.Questionnaire;
import util.color.Color;
import util.color.ColorChanger;
import util.validators.ValidatorUtils;


public class AuthCommandProcessor implements CommandsProcessor {


    private Questionnaire questionnaire;

    public AuthCommandProcessor() {
        this.questionnaire = Questionnaire.getInstance();
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
                username = questionnaire.askQuestion("Enter username");
                password = questionnaire.askQuestion("Enter password");
                Login.login(username, password);
                ColorChanger.changeColor(Color.GREEN);
                break;
            case 2:
                System.out.println();
                username = questionnaire.askQuestion("Enter username");
                password = questionnaire.askQuestion("Enter password");
                String name = questionnaire.askQuestion("Enter name");
                String surname = questionnaire.askQuestion("Enter surname");
                String email = questionnaire.askQuestion("Enter email");
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
