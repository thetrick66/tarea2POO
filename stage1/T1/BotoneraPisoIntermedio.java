public class BotoneraPisoIntermedio extends Botonera implements DownRequest, UpRequest {
   private Boton up, down;
   public BotoneraPisoIntermedio (ControlUnit cu, int location) {
      super (cu, location);
      up = new Boton();
      down = new Boton();
   }
   
   public boolean setRequest(String up_down){
      if (up_down.equals("U"))
         up.turnON();
      else if (up_down.equals("D"))
         down.turnON();
      else
         return false;
      elevatorRequested();
      return true;
   }
   // UpRequest interface implementation
   public void resetUpRequest(){
      up.turnOFF();
   }
   public boolean isUpRequested(){
      return up.getState();
   }

   // DownResquest interface implementation
   public void setDownRequest(){
      down.turnON();
   }
   public void resetDownRequest(){
      down.turnOFF();
   }
   public boolean isDownRequested(){
      return down.getState();
   }
}
   
