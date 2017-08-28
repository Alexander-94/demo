import javax.swing.*;

/**
 * Created by Alexa on 17.08.2017.
 */
public class MainWindow extends JFrame {

    //конструктор
    public MainWindow(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        add(new GameField()); // добавл€ем экземпл€р класса игрового пол€ в окно
        setVisible(true);


    }

    public static void main(String[] args) {


        //создаем экземпл€р класса MainWindow
        //то откуда программа будет начинать работу
        MainWindow mw = new MainWindow();

//        int x = 42;
//        int y = 0;
//        int n = x/y;
// try {
//     System.out.println("1");
//     throw new RuntimeException();
// } finally {
//     System.out.println("2");
// }
    //    throw new ArithmeticException();


    }


}
