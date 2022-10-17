package ZHC.UI;

import javax.swing.*;

public class registerJFrame extends JFrame
{
    public registerJFrame()
    {
        this.setSize(488,500);
        //界面标题
        this.setTitle("注册");
        //界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
