import java.io.IOException;

public class Sand { 
    int width = 50;
    int height = 30;
    int sandAmount = 100;
    int maxSandAmount = 300;
    int flowSpeed = 2;
    SandParticle[] sands = new SandParticle[maxSandAmount];
    public static final String Yellow = "\u001B[33m";
    public static final String Reset = "\u001B[0m";

    public static void main(String[] args) {
        Sand sand = new Sand();
        sand.start();
    }

    private void start() {
        int count = 0;
        spawnSands();
        while(true){
            if(count > flowSpeed){
                count = 0;
                sandFlow();
            }
            count++;
            sandPhysics();
            drawFrame();
        }
    }

    private void sandFlow(){
        if(sandAmount < maxSandAmount){
            sands[sandAmount] = new SandParticle(Math.round(width / 2), 1);
            sandAmount++;
        }
    }

    private void sandPhysics(){
        for(int i = 0; i < sandAmount - 2; i++){
            boolean isBelow = isSandBelow(sands[i]);
            boolean isOnEdge = isOnEdge(sands[i]);
            if(sands[i].slide == true){
                sands[i].fallToSide(isSideFree(sands[i]));
                continue;
            }else if(sands[i].y >= height - 1 || isBelow){
                sands[i].hitFloor(sands[i].justFell == false && isBelow);
                if(isOnEdge){
                    sands[i].slide = true;
                }
            }else{
                sands[i].fall();
            }
        }
    }

    private boolean isSandBelow(SandParticle sand){
        for(int i = 0; i < sandAmount - 1; i++){
            if(sand != sands[i]){
                if(sand.y + 1 == sands[i].y && sand.x == sands[i].x){
                    return true;
                }
            }
        }
        return false;
    }

    private String isSideFree(SandParticle sand){
        boolean leftBlocked = false, rightBlocked = false;
        for(int i = 0; i < sandAmount; i++){
            if(sand != sands[i]){
                if(sand.x - 1 == sands[i].x && sand.y == sands[i].y){
                    leftBlocked = true;
                }
                if(sand.x + 1 == sands[i].x && sand.y == sands[i].y){
                    rightBlocked = true;
                }
            }
            if((rightBlocked && leftBlocked)){
                return "false";
            }
        }
        if(!leftBlocked){
            return "left";
        }
        if(!rightBlocked){
            return "right";
        }
        return "false";
    }

    private boolean isOnEdge(SandParticle sand){
        boolean leftBlocked = false, rightBlocked = false;
        boolean leftBottomBlocked = false, rightBottomBlocked = false;
        for(int i = 0; i < sandAmount; i++){
            if(sand != sands[i]){
                if(sand.x - 1 == sands[i].x && sand.y == sands[i].y){
                    leftBlocked = true;
                }
                if(sand.x + 1 == sands[i].x && sand.y == sands[i].y){
                    rightBlocked = true;
                }
                if(sand.x - 1 == sands[i].x && sand.y + 1 == sands[i].y){
                    leftBottomBlocked = true;
                }
                if(sand.x + 1 == sands[i].x && sand.y + 1 == sands[i].y){
                    rightBottomBlocked = true;
                }
            }
        }
        if(sand.y == height - 1){
            return false;
        }
        if((!leftBlocked && !leftBottomBlocked) || (!rightBlocked && !rightBottomBlocked)){
            return true;
        }
        return false;
    }


    private void spawnSands(){
        for(int i = 0; i < sandAmount; i++){
            sands[i] = new SandParticle((int) Math.round(Math.random() * width), (int) Math.round(Math.random() * height));
        }
    }

    private void drawFrame(){
        clearScreen();
        StringBuilder frame = new StringBuilder("");
        for(int i = 0; i < height; i++){
            frame.append("|");

            for(int j = 0; j < width; j++){
                boolean hasSand = false;
                String sandBody = " ";
                for(int k = 0; k < sandAmount; k++){
                    if(sands[k].x == j && sands[k].y == i){
                        hasSand = true;
                        sandBody = sands[k].body;
                    }
                }
                if(hasSand){
                    frame.append(Yellow + sandBody + Reset);
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
        // wait(50);
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


class SandParticle{
    public int x;
    public int y;
    public String body = "#";
    public boolean slide = false;
    public boolean justFell = false;

    public SandParticle(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void fall(){
        if(!slide){
            y = y + 1;
            body = "|";
        }
    }

    public void fallToSide(String direction){
        if(direction != "false"){
            int force = (int) Math.round(Math.random() * 2) + 1;
            if(direction == "left"){
                body = "<";
                x = x - force;
            }else{
                body = ">";
                x = x + force;
            }
        }
        slide = false;
    }

    public void hitFloor(boolean storedEnergy){
        justFell = true;
        if(slide == false && storedEnergy){
            slide = true;
        }
        if(!storedEnergy){
            body = "#";
        }
    }
}