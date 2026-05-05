package projects;

import drawer.Canvas;
import drawer.Colors;

public class Test {
    int x = 0;
    int dir = 1;

    public static void main(String[] args){
        Test test = new Test();
        test.start();
    }

    private void start() {
        Canvas drawer = new Canvas();
        drawer.startCanvas(50, 25, true);
        while(true){
            drawer.drawCanvas();
            drawer.setColor(Colors.RED);
            drawer.drawChar('X', x, 10);
            x += dir;
            if(x == 0 || x == drawer.width - 1){
                dir *= -1;
            }

            drawer.setColor(Colors.GREEN);
            for(int i = 0; i < 10; i++){
                drawer.drawChar('.', (int) Math.round(Math.random() * (drawer.width -1)) , (int) Math.round(Math.random() * (drawer.height-1)));
            }

            drawer.setColor(Colors.BRIGHT_RED);
            drawer.rect(2,2,5,10,"#");

            drawer.setColor(Colors.BRIGHT_RED);
            drawer.circle(20, 2, 10, "O");

            drawer.delay(100);
        }
    }
}