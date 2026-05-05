public class Main {
   public static void main(String[] args){
      int direction = 1;
      int pos = 1;
      
      for (int i = 1; i <= 1000; i++){
         if(pos >= 27 || pos <= 0){
            direction = direction * -1;
         }
         pos = pos + direction;
         printAmountOfChars(pos);
         System.out.println();
         wait(100);
      }
   }

   private static void printAmountOfChars(Integer amountOfChars){
      int max = 30;
      for (int i = 0; i < max; i++){
         if(i < amountOfChars || i > amountOfChars + 3){
            System.out.print("#");
         }else{
            System.out.print(" ");
         }
      }
   }


   public static void wait(int ms){
      try{
         Thread.sleep(ms);
      }catch(InterruptedException ex){
         Thread.currentThread().interrupt();
      }
   }
}


