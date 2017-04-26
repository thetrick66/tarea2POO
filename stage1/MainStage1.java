import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.*;

public class MainStage1 {
   public static void main(String[] args) {
        // Elevator's parameters
      int numPisos = 6;
      float floorHight = 3.0f;
      float cabinaSpeed = 1f;   // [m/s]
      MyElevatorFrame frame = new MyElevatorFrame(numPisos, floorHight, cabinaSpeed);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }   
}

class MyElevatorFrame extends JFrame {
   public MyElevatorFrame(int numPisos, float floorHight, float cabinaSpeed) {
      setTitle("My Elevator for ELO329");
      setSize(600,800);
      
        // Creación compoentes del ascensor
      Botonera[] botoneras = new Botonera[numPisos+1]; // index goes from 0 to numPisos.
      
      Sensor[] sensores = new Sensor[numPisos];  
      CajaAscensor shaft = new CajaAscensor(sensores);
      Cabina cabina = new Cabina(shaft);
      Motor motor = new Motor(cabina, cabinaSpeed);
      ControlUnit controlUnit = new ControlUnit(motor, cabina, sensores, botoneras);      
      BotoneraCabina bc = new BotoneraCabina(numPisos, controlUnit, 0); 
      cabina.setBotonera(bc);
      
      botoneras[0] = bc;
      botoneras[1] = new BotoneraPrimerPiso(controlUnit,1);
      for (int i=2; i< numPisos; i++)
         botoneras[i] = new BotoneraPisoIntermedio(controlUnit, i);
      botoneras[numPisos] = new BotoneraUltimoPiso(controlUnit, numPisos);

      for (int i=0; i < sensores.length; i++)
         sensores[i] = new Sensor(i*floorHight,i+1, controlUnit);

   // now we organize every object view
      Container contentPane = getContentPane();
      contentPane.setLayout(new GridLayout(1,3));
      add(new BotoneraPisosView(botoneras));
   }
   class BotoneraPisosView extends JPanel {
      BotoneraPisosView(Botonera[] botoneras){
         super (new GridLayout(botoneras.length-1,1));
         for (int i=botoneras.length-1; i >0; i--)
            add(botoneras[i].getView());      
      }
   }
}

