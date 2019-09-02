package cn.xt.game;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.input.KeyCode;
import sun.awt.WindowClosingListener;
import javax.swing.JFrame;//AWT和Swing是java中常见的GUI（图形用户界面）技术
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {//飞机游戏主窗口
    //将图片放入bin目录下
    Image planeImage = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");
    Image enemyImage = GameUtil.getImage("images/enemy.jpg");
    Enemy enemy = new Enemy(enemyImage,700,700);
    Plane plane = new Plane(planeImage,500,500);

    Shell[] shells = new Shell[50];
    Bullet[] bullet = new Bullet[4];
    Explode bao,zha;
    Date startTime = new Date();
    Date endTime1,endTime2;
    int time1,time2; //游戏持续时间
    int quan = 0;//已经建好的子弹数
    @Override
    public void paint(Graphics g) {//自动被调用 g相当于一支画笔
        super.paint(g);
        g.drawImage(bg,0,0,null);
        plane.drawSelf(g);
        enemy.drawSelf(g);
        for(int i=0;i<bullet.length;i++){
        if(!bullet[i].r&&!bullet[i].l&&!bullet[i].u&&!bullet[i].d) {
            bullet[i] = new Bullet((int) enemy.x, (int) enemy.y);
        }
        if(bullet[i].u||bullet[i].d||bullet[i].l||bullet[i].r){
            boolean ping = bullet[i].getRect().intersects(plane.getRect());
            if(ping){
                plane.live = false;
                if(bao == null)
                { bao = new Explode(plane.x,plane.y);
                    endTime2 = new Date();
                    time2 = (int)((endTime2.getTime()-startTime.getTime())/1000);
                }
                bao.draw(g);
            }
            if(!plane.live&&(time2<time1||time1 == 0)){

                Color c = g.getColor();
                Font f = g.getFont();
                Font h = new Font("宋体",Font.BOLD,30);
                g.setFont(h);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("玩家一胜利， 玩家二生存时间："+time2+"秒",100,250);
                g.setColor(c);
                g.setFont(f);
            }
        }
        bullet[i].drawSelf(g);}
        for(int i = 0;i<shells.length;i++){
            shells[i].draw(g);
            boolean ping = shells[i].getRect().intersects(plane.getRect());
            boolean pang = shells[i].getRect().intersects(enemy.getRect());
            boolean qiu = plane.getRect().intersects(enemy.getRect());
            if(ping){
                plane.live = false;
                if(bao == null)
                { bao = new Explode(plane.x,plane.y);
                    endTime2 = new Date();
                    time2 = (int)((endTime2.getTime()-startTime.getTime())/1000);
                }
                bao.draw(g);
            }
            if(!plane.live&&(time2<time1||time1 == 0)){

                Color c = g.getColor();
                Font f = g.getFont();
                Font h = new Font("宋体",Font.BOLD,30);
                g.setFont(h);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("玩家一胜利， 玩家二生存时间："+time2+"秒",100,250);
                g.setColor(c);
                g.setFont(f);
            }
            if(pang||qiu){
                enemy.live = false;
                if(zha == null)
                { zha = new Explode(enemy.x,enemy.y);
                    endTime1 = new Date();
                    time1 = (int)((endTime1.getTime()-startTime.getTime())/1000);
                }

                zha.draw(g);
            }
            if(!enemy.live&&(time1<time2||time2==0)){

                Color c = g.getColor();
                Font f = g.getFont();
                Font h = new Font("宋体",Font.BOLD,30);
                g.setFont(h);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("玩家二胜利， 玩家一生存时间："+time1+"秒"+time2+"+"+time1,100,250);
                g.setColor(c);
                g.setFont(f);
            }

        }
    }
    //内部类
    class PaintThread extends Thread{//帮助我们反复重画窗口
        @Override
        public void run() {
            super.run();
            while(true){
                repaint();//重画
                try {
                    Thread.sleep(40);//四十毫秒一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //键盘监听内部类
    class  KeyMonitor extends KeyAdapter{//重写按下和抬起键盘两个动作
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            plane.addDirection(e);
            enemy.addDirection(e);
            for(int i=0;i<bullet.length;i++)
            {
            if(!bullet[i].r&&!bullet[i].l&&!bullet[i].u&&!bullet[i].d&&bullet[i].x==enemy.x&&bullet[i].y==enemy.y)
            { bullet[i].addDirection(e);
            break;}}



        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            plane.minusDirection(e);
            enemy.minusDirection(e);
            for(int i=0;i<bullet.length;i++)
            bullet[i].minusDirection(e);
        }
    }


    public void launchFrame(){//初始化窗口
        this.setTitle("飞机游戏");//窗口名称
        this.setVisible(true);//设置窗口可见
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGTH);
        this.setLocation(500,100);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();//重画窗口启动
        addKeyListener(new KeyMonitor());//增加键盘的监听
        for(int i=0;i<shells.length;i++){
            shells[i] = new Shell();//初始化50个炮弹
        }
        for(int i=0;i<bullet.length;i++){
            bullet[i] = new Bullet(100,100);
        }

    }
    public static void main(String[] args){

        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGTH);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }//解决窗口闪烁问题
}
