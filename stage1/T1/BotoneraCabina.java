import java.util.ArrayList;
import java.util.Scanner;

public class BotoneraCabina extends Botonera{
   private Boton[] botones;
   public BotoneraCabina(int n_pisos, ControlUnit cu, int location) {
      super(cu, location);
      botones = new Boton[n_pisos];
      for (int i=0; i < n_pisos; i++) 
         botones[i] = new Boton();
   }
   public boolean setRequest(String s_piso){
      Scanner s = new Scanner (s_piso);
      int piso=0;
      if (s.hasNextInt())
         piso = s.nextInt();
      else return false;
      if ((piso > 0) && (piso <= botones.length))
         botones[piso-1].turnON();
      else return false;
      elevatorRequested(piso);
      return true;
   }
   public void resetFloorRequest(int i) {
      botones[i-1].turnOFF();
   }
   public boolean isFloorRequested(int i){
      return botones[i-1].getState();
   }
   public String toString() {
      String s="";
      for(Boton b: botones)
         s+=b.toString();
      return s;
   }
}