public class BotoneraUltimoPiso extends Botonera implements DownRequest {
   private Boton down;
   public BotoneraUltimoPiso(ControlUnit cu, int location){
      super(cu, location);
      down = new Boton();
   }
   public boolean setRequest(String s_down) {
      boolean result = s_down.equals("D");
      if (result) {
         down.turnON();
         elevatorRequested();
      }
      return result;
   }
   public void resetDownRequest() {
      down.turnOFF();
   }
   public boolean isDownRequested() {
      return down.getState();
   }
}