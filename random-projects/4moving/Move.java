import java.io.IOException;
public class Move{
    int width = 40;
    int height = 20;
    int gx = (int) Math.round(width / 2);
    int gy = (int) Math.round(height / 2);
    String[] guy = {" O ",
                    "(M)",
                    "/ \\"}; 
                    
    public static void main(String[] args) throws IOException{
        Move move = new Move();
        move.hideCursor();
        move.start();
    }

    private void start() throws IOException{
        while(true){
            mover(readKeyboard());
            drawFrame();
        }
    }


    private void drawFrame(){
        clearScreen();
        StringBuilder frame = new StringBuilder("");
        for(int k = 0; k < width + 2; k++){
            frame.append("_");
        }
        frame.append("\n");
        for(int i = 0; i < height; i++){
            frame.append("|");
            int stopperSize = 0;
            for(int j = 0; j < width; j++){
                if(stopperSize != 0){
                    stopperSize--;
                    continue;
                }
                if(i == gy && j == gx){
                    frame.append(guy[0]);
                    stopperSize = guy[0].length() - 1;
                }else if(i == gy + 1 && j == gx){
                    frame.append(guy[1]);
                    stopperSize = guy[1].length() - 1;
                }else if(i == gy + 2 && j == gx){
                    frame.append(guy[2]);
                    stopperSize = guy[1].length() - 1;
                }else{
                    frame.append(" ");
                }
            } 
            frame.append("|");
            frame.append("\n");
        }
        for(int k = 0; k < width + 2; k++){
            frame.append("T");
        }
        frame.append("\n");
        System.out.println(frame.toString());
    } 

    private static void clearScreen(){
        System.out.print("\033[H");
        // try {
        //     if (System.getProperty("os.name").contains("Windows")) {
        //         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //     } else {
        //         new ProcessBuilder("clear").inheritIO().start().waitFor();
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    public void wait(int ms){
        try{
            Thread.sleep(ms);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public void mover(String direction){
        if (direction == null) {
            return;
        }
        switch (direction) {
            case "UP": gy = gy - 1; break;
            case "DOWN": gy = gy + 1; break;
            case "RIGHT": gx = gx + 1; break;
            case "LEFT": gx = gx - 1; break;
        }
        if (gy < 0) {
            gy = 0;
        }
        if (gy > height - 3) {
            gy = height - 3;
        }
        if (gx < 0) {
            gx = 0;
        }
        if (gx > width - 1) {
            gx = width - 1;
        }
    }

    public String readKeyboard() throws IOException{
        if (System.in.available() > 0) {
            int code = System.in.read();
            if (code == 27) {
                if (System.in.available() >= 2) {
                    int next1 = System.in.read();
                    int next2 = System.in.read();

                    if (next1 == 91) {
                        switch (next2) {
                            case 65: return "UP";
                            case 66: return "DOWN";
                            case 67: return "RIGHT";
                            case 68: return "LEFT";
                        }
                    }
                }
                return "ESC";
            }

            char tecla = (char) code;
            
            switch (tecla) {
                case 'w': case 'k': return "UP";
                case 's': case 'j': return "DOWN";
                case 'a': case 'h': return "LEFT";
                case 'd': case 'l': return "RIGHT";
                case 'q':          return "QUIT";
                case ' ':          return "SPACE";
                default:           return String.valueOf(tecla);
            }
        }
        return null;
    }


    private void hideCursor(){
        System.out.print("\033[?25l");
        System.out.flush();
    }
}