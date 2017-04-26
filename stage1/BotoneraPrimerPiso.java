import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class BotoneraPrimerPiso extends Botonera implements UpRequest {
   private Boton up;
   private JPanel view;
   public BotoneraPrimerPiso(ControlUnit cu, int location){
      super(cu, location);
      up = new Boton(new ImageIcon("upOff.png"), new ImageIcon("upOn.png"), this);
      view = new JPanel(new BorderLayout());
      view.add(floorIndication, BorderLayout.PAGE_START);
      view.add(up.getView(), BorderLayout.CENTER);      
   }
   public boolean setRequest(String s_up) {
      boolean result = s_up.equals("U");
      if (result) {
         up.turnON();
         elevatorRequested();
      }
      return result;         
   }
   public JPanel getView() {
      return view;
   }
   public void resetUpRequest() {
      up.turnOFF();
   }
   public boolean isUpRequested() {
      return up.getState();
   }
}