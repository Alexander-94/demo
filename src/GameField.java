import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.nio.charset.Charset;

/**
 * Created by Alexa on 17.08.2017.
 */
public class GameField extends JPanel implements ActionListener{

    //задаем пол€ класса дл€ параметров игры
    private final int SIZE = 320; //px
    private final int DOT_SIZE = 16; //сколько px занимает 1 €чейка змеи
    private final int ALL_DOTS = 400; //сколько всего €чеек пол€ 320/16=20; 20*20=400
    private Image dot;   //поле Image под 1 €чейку, поле
    private Image apple; //поле Image под 1 €чейку, €блоко
    private int appleX;  //позици€ ’ €блока
    private int appleY;  //позици€ Y €блока
    private int[] x = new int[ALL_DOTS]; //хранить все положени€ змейки X
    private int[] y = new int[ALL_DOTS]; //хранить все положени€ змейки Y
    private int dots; // размер змейки
    private Timer timer; //стандартный свинговый таймер
    private boolean left = false; //пол€ дл€ направлени€ движени€ змейки
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true; // в игре или уже нет

    //создаем конструктор
    public GameField(){
        setBackground(Color.lightGray);
        loadImages(); // вызываем метод загрузки изображений
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }
    //метод инициализации игры вначале
    public void initGame(){
        dots = 3; // начальное количество точек змейки
        for (int i = 0; i < dots; i++){
            //начальные значени€ дл€ каждой из позиций X и Y
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(250,this); // дл€ таймера нужен implements ActionListener
        timer.start();
        //вызываем метод дл€ создани€ €блока
        createApple();
    }

    //метод дл€ создани€ €блока
    public void createApple(){
         appleX = new Random().nextInt(20)*DOT_SIZE; // RND от 0 до 19
         appleY = new Random().nextInt(20)*DOT_SIZE; // RND от 0 до 19
    }

    //метод дл€ загрузки картинок
    public void loadImages(){
        ImageIcon iaa = new ImageIcon("apple.png"); // загрузка картинки €блока
        apple = iaa.getImage(); //инициализаци€ объекта типа Image
        ImageIcon iid = new ImageIcon("dot.png"); // загрузка картинки точки
        dot = iid.getImage(); //инициализаци€ объекта типа Image
    }

    //переопределение метода перерисовки игрового пол€
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            //перерисовываем всю змейку
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i],y[i],this);
            }
        } else{
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str,125,SIZE/2);
        }
    }

    public void move(){
        //логическа€ перерисовка точек массива X и Y (кроме головы)
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        //ƒл€ головы (направление)
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        }
        if(up){
            y[0] -= DOT_SIZE;
        }
        if(down){
            y[0] += DOT_SIZE;
        }
//        InputStream a = new InputStream() {
//            @Override
//            public int read() throws IOException {
//                return 0;
//            }
//        }
    }

    //метод проверки пересечени€ головы змеи с €блоком
    public void checkApple(){
        if(x[0]==appleX && y[0]==appleY){
            dots++;
            createApple();
        }
    }

    //метод проверки на пересечение змеей стенок/змеи
    public void checkCollisions(){
        //проверка пересечени€ змеи
        for (int i = dots; i > 0; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        //проверка выхода за пределы игрового пол€
        if(x[0] > SIZE){
            inGame = false;
        }
        if(x[0] < 0){
            inGame = false;
        }
        if(y[0] > SIZE){
            inGame = false;
        }
        if(y[0] < 0){
            inGame = false;
        }

    }

    //ћетод интерфейса ActionListener, вызываетс€ по тику таймера, каждые 250 миллисекунд
    @Override
    public void actionPerformed(ActionEvent e) {
        //если в игре
        if(inGame){
            //метод проверки пересечени€ €блока
            checkApple();
            //метод проверки на пересечение стенок
            checkCollisions();
            move();

        }
        //перерисовка всех компонентов
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }
}
