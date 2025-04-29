package game.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {

    //JFrame界面，窗体
    //子类呢？也表示界面，窗体
    //规定：GameJFrame这个界面表示的就是游戏的主界面
    //以后跟游戏相关的所有逻辑都写在这个类中
    int[][] data = new int[4][4];


    //定义一个变量，记录空白方块的位置
    int x = 0;
    int y = 0;

    //定义一个二维数组，存储正确的数据
    int[][] win = {{1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public GameJFrame() {
        //设置界面的宽高
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();

        //设置显示，显示出来界面，默认是隐藏的，建议放在最后面
        this.setVisible(true);
    }
    //初始化数据（打乱顺序)


    private void initData() {

        //定义一个数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱数组
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            //交换数据
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //将打乱之后的数据，重新放到二维数组中
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            } else {
                data[i / 4][i % 4] = tempArr[i];
            }
        }
    }

    //初始化图片
    private void initImage() {

        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        int number = 1;
        //外循环
        for (int i = 0; i < 4; i++) {
            //内循环 表示在一行添加四足图片
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建一个标签对象
                JLabel jLabel = new JLabel(new ImageIcon("image/background.jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(0));

                //将标签对象添加到界面中
                //this.add(jLabel);
                this.getContentPane().add(jLabel);
                //将图片添加到界面中

            }

            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("image/background.jpg"));
            background.setBounds(40, 40, 508, 560);
            //将图片添加到界面中
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
        }
    }

    private void initJMenuBar() {


        //初始化菜单
        //创键整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个对象   （功能、关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        //创建关于我们下面的条目对象
        JMenuItem accountItem = new JMenuItem("公众号");

        //将条目对象添加到菜单下面
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //将菜单对象添加到菜单条下面
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
        //给整个界面添加键盘监听
        this.addKeyListener(this);

    }

    private void initJFrame() {

        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中显示
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //取消默认的居中放置
        this.setLayout(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下不松时调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //把界面中的所有图片都删除
            this.getContentPane().removeAll();
            //加载一张完整的图片
            JLabel all = new JLabel(new ImageIcon("image/background.jpg"));
            //指定图片位置
            all.setBounds(83, 134, 420, 420);
            //将图片添加到界面中
            this.getContentPane().add(all);
            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("image/background.jpg"));
            background.setBounds(40, 40, 508, 560);
            //将图片添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("向左移动");
            if (y == 3) {
                //如果是最后一列，就不能移动
                return;
            }
            data[x][y] = data[x][y+1];
            //把空白方块赋值给空白方块下方的数字
            data[x][y+1] = 0;
            y++;
            //
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 3) {
                //如果是最后一行，就不能移动
                return;
            }
            //逻辑：
            //把空白方块下方的数字往上移动
            //x，y 表示空白方块
            //x+1，y表示空白方块下方的数字
            //把空白方块下方的数字赋值给空白方块
            data[x][y] = data[x+1][y];
            //把空白方块赋值给空白方块下方的数字
            data[x+1][y] = 0;
            x++;
            //
            initImage();

        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 0) {
                //如果是第一列，就不能移动
                return;
            }
            data[x][y] = data[x][y-1];
            //把空白方块赋值给空白方块下方的数字
            data[x][y-1] = 0;
            y--;
            //
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if (x == 0) {
                //如果是第一行，就不能移动
                return;
            }
            data[x][y] = data[x-1][y];
            //把空白方块赋值给空白方块下方的数字
            data[x-1][y] = 0;
            x--;
            //
            initImage();
        }else if (code == 65) {
            initImage();
        }else if (code == 87) {

            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }

    }
    public boolean victory(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
