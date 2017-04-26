import javax.swing.*;
import java.awt.BorderLayout;

public class BotoneraPisoIntermedio extends Botonera implements DownRequest, UpRequest {
   private Boton up, down;
   private BotoneraPisoIntermedioView view;
   public BotoneraPisoIntermedio (ControlUnit cu, int location) {
      super (cu, location);
      up = new Boton(new ImageIcon("upOff.png"), new ImageIcon("upOn.png"), this);
      down = new Boton(new ImageIcon("downOff.png"), new ImageIcon("downOn.png"), this);
      view = new BotoneraPisoIntermedioView();
   }
   
   public JPanel getView() {
      //to be coded by you
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
   class BotoneraPisoIntermedioView extends JPanel {
      BotoneraPisoIntermedioView(){
         super(new BorderLayout());
         add(floorIndication,BorderLayout.PAGE_START);
         // to be coded by you
      }
   }
}
   
