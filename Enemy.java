package cn.xt.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Enemy extends Plane {



    public Enemy(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

    }
    public void addDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case 65:
                left = true;
                break;
            case 87:
                up = true;
                break;
            case 68:
                right = true;
                break;
            case 83:
                down = true;
                break;
        }

    }
    //抬起键取消增加相应方向
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 65:
                left = false;
                break;
            case 87:
                up = false;
                break;
            case 68:
                right = false;
                break;
            case 83:
                down = false;
                break;
        }
    }
}