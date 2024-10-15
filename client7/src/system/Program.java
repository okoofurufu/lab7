package system;

import classes.Product;
import classes.generators.IdGenerator;
import classes.generators.ProductGenerator;

import java.util.Scanner;

public class Program {
    private static final int port = 2577;
    private static final int reconnectionTimeout = 5000;
    private static final int maxReconnectionAttempts = 5;
    public static User user = new User(null,null);
    public void execute() throws InterruptedException {
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("localhost", port, reconnectionTimeout, maxReconnectionAttempts);
        //При отправке запросов проверять только соответсвие типов вводимых данных типам ожидаемых данных
        //все команды проверять на сервере + все команды перенести на сервер, в клиенте должны быть классы reqest, response, client, program, data classes
        System.out.println("Введите register [логин] [пароль], затем введите authorization [логин] [пароль]");
        while (true) {
            String cmd = (scanner.nextLine() + " ").trim();
            input = cmd.split(" ");
            if (input[0].equals("register")){
                if (input.length>=3){
                    user.setLogin(input[1]);
                    user.setPassword(input[2]);
                    System.out.println(client.sendRequest(new Request(input,user)).getResult());
                    user = new User(null,null);
                }
                else{
                    System.out.println("Not enough info for registration");
                }
            }
            else if (input[0].equals("exit_from_account")){
                user = new User(null,null);
                System.out.println("Exit from account confirmed");
            }
            else if (input[0].equals("authorization")){
                if (input.length>=3){
                    user.setLogin(input[1]);
                    user.setPassword(input[2]);
                    System.out.println(client.sendRequest(new Request(input,user)).getResult());
                }
                else{
                    System.out.println("Not enough info for authorization");
                }
            }
            else if ((input[0].equals("add") && user.getPassword()!= null && user.getLogin()!=null)) {
                Product product = ProductGenerator.createProduct(IdGenerator.generateid());
                System.out.println(client.sendRequest(new Request(product, input, user)).getResult());
            }else if((input[0].equals("update") && user.getPassword()!= null && user.getLogin()!=null)){
                Product product = ProductGenerator.createProduct(0);
                System.out.println(client.sendRequest(new Request(product, input, user)).getResult());
            }else if(input[0].equals("exit")){
                System.out.println("пока пока");
                System.exit(1);
            }
            else if (user.getLogin()!= null && user.getPassword()!=null){
                System.out.println(client.sendRequest(new Request(input,user)).getResult());
            }
            else{
                System.out.println("Authorization is required to enter commands");
            }
        }
    }
}
