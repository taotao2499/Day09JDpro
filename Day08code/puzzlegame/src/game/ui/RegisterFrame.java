package game.ui;

import javax.swing.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame(){
        //设置界面的宽高
        this.setSize(488, 500);
        this.setVisible(true);
        //设置界面标题
        this.setTitle("拼图 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中显示
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
