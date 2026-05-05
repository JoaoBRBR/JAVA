public class Gravity{
    public int frames = 1000;
    public int width = 50;
    public int height = 20;
    public double xPos = 3;
    public double yPos = height - 3;
    public double dx = 1.5;
    public double dy = -1.9;
    public double g = 0.1;
    public double energyLoss = 0.9;

    public static void main(String[] args){
        Gravity test = new Gravity();
        test.start();
    }

    private void start(){
        for(int i = 0; i <= frames; i++){
            calculatePhysics();
            drawBouncyBall(xPos, yPos);
        }
    } 

    private void calculatePhysics(){
        dy = dy + g; 
        yPos = yPos + dy;
        xPos = xPos + dx;

        if(xPos >= width || xPos <= 0){
            dx = dx * -1;
        }
        if(yPos >= height){
            yPos = height - 1;
            dy = dy * -1 * energyLoss;
        }
    }

    private void drawBouncyBall(double x, double y){
        clearScreen();
        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);
        StringBuilder frame = new StringBuilder("");
        for(int i = 0; i < height; i++){
            frame.append("|");
            for(int j = 0; j < width; j++){
                if(ix == j && iy == i){
                    frame.append("O");
                }else{
                    frame.append(" ");
                }
            }
            frame.append("|");
            frame.append("\n");
        } 
        for(int k = 0; k < width + 2; k++){
            frame.append("m");
        }
        frame.append("\n");
        System.out.println(frame.toString());
        wait(50);
    } 


    public void wait(int ms){
        try{
            Thread.sleep(ms);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    private static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
