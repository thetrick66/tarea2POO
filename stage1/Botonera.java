import javax.swing.*;

public abstract class Botonera {
   private ControlUnit controlUnit;
   private int location;
   protected JLabel floorIndication;

   public Botonera(ControlUnit cu, int location){
      controlUnit = cu;
      this.location = location; // 0 means in cabina
      // to be coded 
   }
   protected void elevatorRequested(){
      controlUnit.elevatorRequested(location);
   }
   protected void elevatorRequested(int destination) {
      controlUnit.elevatorRequested(destination);
   }
   public abstract boolean setRequest(String s); // it comes form T1, it's not needed here
   public abstract JComponent getView();
   public void updateFloorIndication(int floor){
      floorIndication.setText(Integer.toString(floor));
   }

}