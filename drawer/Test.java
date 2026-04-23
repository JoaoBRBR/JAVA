public class Test {
    int x = 0;
    int dir = 1;

    public static void main(String[] args){
        Test test = new Test();
        test.start();
    }

    private void start() {
        Drawer drawer = new Drawer();
        drawer.startCanvas(50, 20, true);
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

            drawer.setColor(Colors.BRIGHT_GREEN);
            drawer.rect(2,2,10,4,"O");

            // drawer.setColor(Colors.BRIGHT_RED);
            // drawer.circle(20, 10, 5, "O");

            drawer.delay(100);
        }
    }
}