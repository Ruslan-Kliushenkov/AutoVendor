import control.UserControl;

public class App {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать");
        run();
    }
    public static void run(){

        UserControl start = new UserControl();
        start.cmdList();
    }
}
