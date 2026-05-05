package drawer;
import drawer.Colors;

public class Canvas{
    public int width;
    public int height;
    public boolean border;
    int borderSpace = 0; 
    String[][] frame;
    String currentColor = Colors.RESET.getCode();

    public void startCanvas(int width, int height, boolean border){
        this.width = width;
        this.height = height;
        this.border = border;
        if(border){
            borderSpace = 2;
        }
        frame = new String[height][width];
        clearFrame();
        clearScreen();
        hideCursor();
    }

    
    public void drawCanvas(){
        clearScreen();
        StringBuilder canvasFrame = new StringBuilder("");
        drawTopBorder(canvasFrame);
        for(int i = 0; i < height; i++){
            drawSideBorder(canvasFrame);
            for(int j = 0; j < width; j++){
                canvasFrame.append(frame[i][j]);
            } 
            drawSideBorder(canvasFrame);
            canvasFrame.append("\n");
        }
        drawTopBorder(canvasFrame);
        System.out.println(canvasFrame.toString());
        clearFrame();
    } 

    public void clearFrame(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                frame[i][j] = " ";
            }
        }
    }

    public void drawChar(char letter, int x, int y){
        frame[y][x] = currentColor + String.valueOf(letter);
    }

    public void rect(int x, int y, int width, int height, String letter){
        for(int i = y; i < y + height; i++){
            for(int j = x; j < x + width; j++){
                frame[i][j] = currentColor + letter;
            }
        }
    }

    public void circle(int x, int y, int diameter, String letter){
        float radius = diameter / 2;
        for(int i = y; i < diameter + y + 1; i++){
            for(int j = x ; j < diameter + x + 1; j++){
                float xCenter = x + radius;
                float yCenter = y + radius;
                if(((i - yCenter) * (i - yCenter)) * 4 + (j - xCenter) * (j - xCenter) <= radius * radius){
                    frame[i][j] = currentColor + letter;
                }  
            }
        } 
    }

    // must be improved
    public void line(int x1, int y1, int x2, int y2){
        float notZero = (x2 - x1);
        boolean perfectVertical = false;
        if(notZero == 0){
            notZero = 0001;
            perfectVertical = true;
        }
        float m = (y2 - y1) / notZero;
        float b = y1 - m * x1;
        char inclination = getCharInclination(m);
        boolean checkVertical = true;
        if(inclination == '-'){
            checkVertical = false;
        }
        int sx,sy,bx,by;
        if(x1 < x2){
            sx = x1;
            bx = x2;
        }else{
            sx = x2;
            bx = x1;
        }
        if(y1 < y2){
            sy = y1;
            by = y2;
        }else{
            sy = y2;
            by = y1;
        }
        for(int i = sy; i < by; i++){
            int myX = (int) Math.round((i - b) / m);
            if(perfectVertical){
                frame[i][x1] = currentColor + inclination;
            }else{
                for(int j = sx; j < bx; j++){
                    int myY = (int) Math.round(m * j + b);
                    if((checkVertical && j == myX) || (!checkVertical && i == myY)){
                        frame[i][j] = currentColor + inclination;
                    }
                }
            }
        }
    }

    public void delay(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setColor(Colors newColor){
        currentColor = newColor.getCode();
    }

    public void setColorRgb(int r, int g, int b) {
        currentColor = "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }

    public static double map(double value,
                            double fromMin, double fromMax,
                            double toMin, double toMax) {
        return (value - fromMin) * (toMax - toMin) / (fromMax - fromMin) + toMin;
    } 
    ///////////////////
    //PRIVATE METHODS//
    ///////////////////

    private char getCharInclination(float module){
        if(module >= -0.2 && module <= 0.2){
            return '-';
        }else if(module > 0.2 && module <= 2){
            return '\\';
        }else if(module < -0.2 && module >= -2){
            return '/';
        }else{
            return '|';
        }
    }

    private static void clearScreen(){
        System.out.print("\033[H");
    }

    private void drawTopBorder(StringBuilder frame){
        if(border){
            frame.append(Colors.RESET.getCode());
            for(int k = 0; k < width + borderSpace; k++){
                frame.append("_");
            }
            frame.append("\n");
        }
    }

    private void drawSideBorder(StringBuilder frame){
        frame.append(Colors.RESET.getCode());
        if(border){
            frame.append("|");
        }
    }
    
    private void hideCursor(){
        System.out.print("\033[?25l");
        System.out.flush();
    }
}