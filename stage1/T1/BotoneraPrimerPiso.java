public class BotoneraPrimerPiso extends Botonera implements UpRequest {
   private Boton up;
   public BotoneraPrimerPiso(ControlUnit cu, int location){
      super(cu, location);
      up = new Boton();
   }
   public boolean setRequest(String s_up) {
      boolean result = s_up.equals("U");
      if (result) {
         up.turnON();
         elevatorRequested();
      }
      return result;         
   }
   public void resetUpRequest() {
      up.turnOFF();
   }
   public boolean isUpRequested() {
      return up.getState();
   }
}