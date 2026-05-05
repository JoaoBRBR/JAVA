package projects;

import drawer.Canvas;
import drawer.Colors;

public class Test {
    int x = 0;
    int dir = 1;
    int y = 0;
    int dirY = 1;
    float a = 0;
    float radius = 8;

    public static void main(String[] args){
        Test test = new Test();
        test.start();
    }

    private void start() {
        Canvas drawer = new Canvas();
        drawer.startCanvas(80, 35, true);

        while(true){
            drawer.drawCanvas();


            drawer.setColor(Colors.GREEN);
            for(int i = 0; i < 10; i++){
                drawer.drawChar('.', (int) Math.round(Math.random() * (drawer.width -1)) , (int) Math.round(Math.random() * (drawer.height-1)));
            }

            drawer.setColor(Colors.BRIGHT_RED);
            drawer.rect(2,2,5,10,"#");

            drawer.setColor(Colors.BRIGHT_RED);
            drawer.circle(20, 2, 10, "O");
            

            drawer.setColor(Colors.GREEN);
            drawer.line(40,25,x,y);

            a += 0.1;
            int cX = (int) Math.round(Math.sin(a)*radius);
            int cY = (int) Math.round(Math.cos(a)*radius/2);
            drawer.setColor(Colors.RED);
            drawer.line(25,25,25 + cX,25 + cY);
            
            drawer.setColor(Colors.RED);
            drawer.drawChar('X', x, 10 + cY);
            x += dir;
            y += dirY;
            if(x == 0 || x == drawer.width - 1){
                dir *= -1;
            }
            if(y == 0 || y == drawer.height - 1){
                dirY *= -1;
            }

            drawer.delay(10);
            
        }
    }
}