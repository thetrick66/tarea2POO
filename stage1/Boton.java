import javax.swing.*;
import java.awt.event.*;

public class Boton implements ActionListener {
   public Boton (ImageIcon imageOff, ImageIcon imageOn, Botonera b) {
      state = false;
      imgOff = imageOff;
      imgOn = imageOn;
      view = new JButton(imgOff);
      view.addActionListener(this);
      botonera = b;           
   }
   public JButton getView() {
      return view;
   }
   public void turnON(){
   // to be coded by you
   }
   public void turnOFF(){
      state = false;
      view.setIcon(imgOff);
   }
   public boolean getState(){
      return state;
   }
   public String toString() {
      return state?"1":"0";
   }
   public void actionPerformed (ActionEvent event) {
      // should make button on when it is off
   }
   
   private boolean state;
   private Botonera botonera; // to let botonera know the new request.
   private ImageIcon imgOff, imgOn;
   private JButton view;
}