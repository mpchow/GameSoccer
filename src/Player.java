import java.util.concurrent.TimeUnit;

public class Player {
private int Xdir;
private int Ydir;
private int Xlocation;
private int Ylocation;

   public Player(int Xdir, int Ydir, int Xlocation, int Ylocation) {
       this.Xdir = Xdir;
       this.Ydir = Ydir;
       this.Xlocation = Xlocation;
       this.Ylocation = Ylocation;
   }

   public int moveRight() {
       if(Xlocation >= 1300) {
           Xlocation = 1300;
       }
       else {
           Xlocation += Xdir;
       }

       return Xlocation;
   }

   public int moveLeft() {
       if(Xlocation < 0 ) {
           Xlocation = 0;
       }
       else{
           Xlocation -= Xdir;
       }

       return Xlocation;
   }

   public int jump() {
       //use a switch statement or else if
       if (Ylocation > 475) {
           Ylocation -= Ydir;
       }

       return Ylocation;
   }

   public int fall() {
       while (Ylocation < 575) {
           Ylocation +=Ydir;
       }
       return Ylocation;
   }


}
