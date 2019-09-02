package cn.xt.game;


import java.awt.Color;
import java.awt.Graphics;
//炮弹类
public class Shell extends GameObject{
    double degree;
    public Shell(){
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 2;

        degree = Math.random()*Math.PI*2;//产生零到2PI的随机数
    }
    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval((int)x,(int)y,width,height);
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);
        if(x<10||x>Constant.GAME_WIDTH-width-10){
            degree  = Math.PI - degree;
        }

        if(y<40||y>Constant.GAME_HEIGTH-height-10){
            degree  = - degree;
        }
        g.setColor(c);
        }
}
