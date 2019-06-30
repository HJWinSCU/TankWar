import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    static JFrame frame = new JFrame("主菜单");
    public static boolean printable = true;
    public static void main(String[] args) {
        frame.setLocation(700,350);
        frame.setSize(20,20);
        frame.setContentPane(new MainMenu());
        frame.pack();
        frame.setLocation(500,200);
        frame.setSize(500,500);
        frame.setVisible(true);

    }
    public MainMenu() {
        this.setLayout(new GridLayout(4, 4));
        this.setBackground(Color.white);
        this.label = new JLabel("坦克大战");
        this.label.setFont(new Font("黑体", Font.PLAIN, 32));
        this.button1=new JButton("开始游戏");
        this.button1.setHorizontalTextPosition(2);
        this.button1.setVerticalTextPosition(3);
        this.button1.setFont(new Font("黑体", Font.PLAIN, 32));
   //     this.button1.setBackground(new Color(251, 254, 245));
        this.button1.setForeground(Color.black);


        this.button2=new JButton("游戏帮助");

        this.button2.setSize(10,10);
        this.button2.setFont(new Font("黑体", Font.PLAIN, 32));
        this.button2.setHorizontalTextPosition(2);
        this.button2.setVerticalTextPosition(3);
     //   this.button2.setBackground(new Color(251, 254, 245));
        this.button2.setForeground(Color.black);

        this.button3=new JButton("退出游戏");
        this.button3.setFont(new Font("黑体", Font.PLAIN, 32));
        this.button3.setSize(10,10);
        this.button3.setHorizontalTextPosition(2);
        this.button3.setVerticalTextPosition(3);
     //   this.button3.setBackground(new Color(251, 254, 245));
        this.button3.setForeground(Color.black);

        JPanel panel = new JPanel();

        panel.add(this.label);
        panel.setOpaque(false);
        this.add(panel);
        this.add(this.button1);
        this.add(this.button2);
        this.add(this.button3);
        this.button1.addActionListener(new MainMenu.Button1Listener());
        this.button2.addActionListener(new MainMenu.Button2Listener());
        this.button3.addActionListener(new MainMenu.Button3Listener());
    }

    class Button1Listener implements ActionListener {
        Button1Listener() {
        }

        public void actionPerformed(ActionEvent event) {
            new TankClient();
            frame.setVisible(false);

        }

    }

    class Button2Listener implements ActionListener {
        Button2Listener() {
        }

        public void actionPerformed(ActionEvent event) {
            printable = false;
            JOptionPane.showMessageDialog(null, "用→ ← ↑ ↓控制方向，F键盘发射，R重新开始！",
                    "游戏帮助", JOptionPane.INFORMATION_MESSAGE);
            printable = true;
        }

    }

    class Button3Listener implements ActionListener {
        Button3Listener() {
        }

        public void actionPerformed(ActionEvent event) {
            printable = false;
            Object[] options = {"确定", "取消"};
            int response = JOptionPane.showOptionDialog(MainMenu.this, "您确认要退出吗", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                System.out.println("退出");
                System.exit(0);
            } else {
                printable = true;

            }
        }

    }

}