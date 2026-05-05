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
    ///////////////////
    //PRIVATE METHODS//
    ///////////////////

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