package cn.xt.game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

public class Plane extends GameObject {
    boolean left,up,right,down;
    boolean live = true;

    public void drawSelf(Graphics g){
        if(live){
        g.drawImage(img,(int)x,(int)y,null);
        //if(x>10&&x<&&&&){

        if(left&&x>10)
            x-=speed;
        if(right&&x<Constant.GAME_WIDTH-width-10)
            x+=speed;
        if(up&&y>40)
            y-=speed;
        if(down&&y<Constant.GAME_HEIGTH-height-10)
            y+=speed;}

    }
    public Plane(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 7;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

    }
    public Plane(){

    }
//按下键增加相应方向
    public void addDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
                case KeyEvent.VK_RIGHT:
                right = true;
                break;
                case KeyEvent.VK_DOWN:
                down = true;
                break;
        }

    }
    //抬起键取消增加相应方向
    public void minusDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }

    }

}
