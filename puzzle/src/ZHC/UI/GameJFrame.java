package ZHC.UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener
{
    //创建二位数组
    int[][]data=new int[4][4];

    //记录0的位置
    int x,y;

    //定义一个变量记录当前展示图片路径
    String path="puzzle\\image\\animal\\animal3\\";

    //统计步数
    int StepCount=0;

    //定义一个二维数组，存储正确的数据
    int[][]win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };



    JMenu changeImage=new JMenu("更换图片");

    //选项下面条目对象
    JMenuItem closGameItem=new JMenuItem("关闭游戏");
    JMenuItem replayItem=new JMenuItem("重新开始");
    JMenuItem reloginItem=new JMenuItem("重新登录");
    JMenuItem BeautyItem=new JMenuItem("美女");
    JMenuItem AnimalsItem=new JMenuItem("动物");
    JMenuItem SoprtItem=new JMenuItem("运动");

    JMenuItem accountItem=new JMenuItem("公众号");
    public GameJFrame()
    {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJFrameBar();

        //初始化数据（打乱）
        initData();

        //初始化图片（根据大乱之后结果加载图片）
        initImage();

        this.setVisible(true);//展示界面 true开启，false关闭
    }

    private void initData() {
        int[] tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r=new Random();
        for (int i=0;i<tempArr.length;i++)
        {
            int index=r.nextInt(tempArr.length);
            int temp=tempArr[i];
            tempArr[i]=tempArr[index];
            tempArr[index]=temp;
        }
        int index=0;
        for (int i = 0; i < 4; i++)
        {
            for(int j=0;j<4;j++)
            {
                data[i][j]=tempArr[index++];
                if (data[i][j]==0)
                {
                    x=i;
                    y=j;
                }
            }
        }
    }

    private void initImage() {
        //路径分为两种
        //绝对路径
        //相对路径：相对当前项目而言
        this.getContentPane().removeAll();

        if(victor())
        {
            //胜利
            JLabel winJLable=new JLabel(new ImageIcon("puzzle\\image\\win.png"));
            winJLable.setBounds(203,283,197,73);
            this.getContentPane().add(winJLable);
        }


        JLabel Step=new JLabel("步数："+StepCount);
        Step.setBounds(50,30,100,20);
        this.getContentPane().add(Step);

        //添加游戏图片
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                //从二维数组获取当前要加载图片的序号
                int number = data[i][j];
                //创建一个Jlabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path+ number + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 83, i * 105 + 134, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(1));//1凸起来 0凹下去
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }
            //添加图背景片
            ImageIcon bg=new ImageIcon("puzzle\\image\\background.png");
            JLabel background =new JLabel(bg);
            background.setBounds(40,40,508,560);
            //把背景添加到当前界面中
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
    }

    private void initJFrameBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar=new JMenuBar();

        //创建菜单选项（功能  关于我们）
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于我们");

        //美女，运动，动物添加到更换图片
        changeImage.add(BeautyItem);
        changeImage.add(SoprtItem);
        changeImage.add(AnimalsItem);

        //将条目添加到选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(reloginItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(closGameItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closGameItem.addActionListener(this);
        accountItem.addActionListener(this);
        BeautyItem.addActionListener(this);
        SoprtItem.addActionListener(this);
        AnimalsItem.addActionListener(this);

        //将菜单中两个选项添加到菜单里
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //界面宽高
        this.setSize(603,680);
        //界面标题
        this.setTitle("拼图单机版 1.0");
        //界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //游戏关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置,只有取消了，才会安装x，y轴方式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松，调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();//z 90
        if(code==90)
        {
            //把界面中所有图片删除
            this.getContentPane().removeAll();
            //加载第一张图片
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            ImageIcon bg=new ImageIcon("puzzle\\image\\background.png");
            JLabel background =new JLabel(bg);
            background.setBounds(40,40,508,560);
            //把背景添加到当前界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码
        if(victor())
            return;

        //对上下左右进行判断
        //左37 上38 右39 下40
        int code=e.getKeyCode();
        if(code==37)
        {
            if (y==3)
                return;
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            StepCount++;
            initImage();
        }else if (code==38)
        {
            if(x==3)
                return;
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            StepCount++;
            initImage();
        }else if(code==39)
        {
            if(y==0)
                return;
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            StepCount++;
            initImage();
        }else if(code==40)
        {
            if(x==0)
                return;
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            StepCount++;
            initImage();
        }else if(code==90)
        {
            //清屏
            initImage();
        }
        //z 88
        else if (code==88)
        {
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断data是否和win相同
    public boolean victor(){
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                if(data[i][j]!=win[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    //更换图片
    public void chaneImage(int index)
    {
        Random r=new Random();
        if(index==1)//美女
        {
            int num=r.nextInt(10);
            num++;
            path="puzzle\\image\\girl\\girl"+num+"\\";
        } else if (index==2)//运动
        {
            int num=r.nextInt(9);
            num++;
            path="puzzle\\image\\sport\\sport"+num+"\\";
        }else if(index==3)//动物
        {
            int num=r.nextInt(7);
            num++;
            path="puzzle\\image\\animal\\animal"+num+"\\";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击条目对象
        Object obj=e.getSource();
        //
        if(obj==replayItem)
        {
            //再次打乱
            initData();
            //计步器清零
            StepCount=0;
            //重新加载图片
            initImage();

        }else if(obj==reloginItem)
        {
            //关闭游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if(obj==closGameItem)
        {
            //直接关闭虚拟机
            System.exit(0);
        }else if(obj==accountItem)
        {
            //创建一个弹窗对象
            JDialog jDialog=new JDialog();
            //创建容器
            JLabel jLabel=new JLabel(new ImageIcon("puzzle\\image\\WeChat.jpg"));
            jLabel.setBounds(0,0,350,350);
            jDialog.getContentPane().add(jLabel);
            //设置弹窗大小
            jDialog.setSize(344,344);
            //置顶弹窗
            jDialog.setAlwaysOnTop(true);
            //剧中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭无法操作下面界面
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);
        }else if(obj==BeautyItem)
        {
            //选择图片
            chaneImage(1);
            //再次打乱
            initData();
            //计步器清零
            StepCount=0;
            //重新加载图片
            initImage();
        } else if (obj==AnimalsItem)
        {
            //选择图片
            chaneImage(3);
            //再次打乱
            initData();
            //计步器清零
            StepCount=0;
            //重新加载图片
            initImage();
        } else if (obj==SoprtItem)
        {
            //选择图片
            chaneImage(2);
            //再次打乱
            initData();
            //计步器清零
            StepCount=0;
            //重新加载图片
            initImage();
        }
    }
}
