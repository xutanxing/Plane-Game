package cn.xt.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Bullet extends GameObject{
    boolean right,left,up,down;
    boolean u,d,l,r;
    static int z = 0;
    public Bullet(int x,int y){
        width = 10;
        height = 10;
        this.x = x;
        this.y = y;
        speed = 10;

    }

    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 71:
                left = true;
                l = true;
                break;
            case 89:
                up = true;
                u = true;
                break;
            case 74:
                right = true;
                r = true;
                break;
            case 72:
                down = true;
                d = true;
                break;
        }
    }
    public void times(KeyEvent e){
        if(e.getKeyCode()==71|e.getKeyCode()==89|e.getKeyCode()==74|e.getKeyCode()==72)
            z++;
    }
    public void minusDirection(KeyEvent e){
        switch(e.getKeyCode()){
                case 71:
                    left = false;
                    break;
                case 89:
                    up = false;
                    break;
                case 74:
                    right = false;
                    break;
                case 72:
                    down = false;
                    break;
        }
    }
    public void drawSelf(Graphics g){
        if(l|r|u|d){
            Color c = g.getColor();
            g.setColor(Color.yellow);
            g.fillOval((int)x,(int)y,width,height);
            if(l)x-=speed;
            if(r)x+=speed;
            if(u)y-=speed;
            if(d)y+=speed;
            if(x<10||x>Constant.GAME_WIDTH-width-10){
                u=d=l=r=false;
                z--;
            }

            if(y<40||y>Constant.GAME_HEIGTH-height-10){
                u=d=l=r=false;
                z--;
            }
            g.setColor(c);
        }
    }
}