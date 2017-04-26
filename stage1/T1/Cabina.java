/**
Cabina: La Cabina es el carro del ascensor que sube y baja con personas.
La cabina tiene una botonera, una referencia
a la "caja ascensor" donde se encuentra, su posición actual, un indicador 
del piso en que se encuentra (requerido para mostrar piso actual) y una 
referencia al último sensor que activó y que al dejarlo debe desactivarlo.
La causa única de su movimiento es la invocación al método move.
 
Ante un movimiento, la cabina pregunta a la caja del ascensor por sensores en su nueva
posición.  De haber sensor, lo activa. Si no hay sensor se preocupa de desactivar
el último activado.
*/
import java.util.Random;

public class Cabina {
   private BotoneraCabina botonera; 
   private CajaAscensor shaft;
   private float position;  // in meters
   private int floorIndicator;
   private Sensor lastSensor=null;

   public Cabina (CajaAscensor caja) {
      Random generator = new Random();
      shaft = caja;
      position = generator.nextFloat();   //it starts between 0 an 1 [m]
      floorIndicator=1;   
   }   
   public Cabina (BotoneraCabina bc, CajaAscensor caja) {
      this(caja);
      botonera = bc;
   }
   
   public void setBotonera( BotoneraCabina bc){
      botonera = bc;
   }

   public void move(float delta) {
      Sensor sensor;
      position+=delta;
      sensor=shaft.findSensor(position);
      if (sensor != null){  // there is a sensor
         if(sensor !=lastSensor) {  // it is not the same
            sensor.activateAction();
            lastSensor = sensor;
         }
      }else  if (lastSensor!= null){ // deactivate previous
                lastSensor.deactivateAction(); 
                lastSensor=null;
             }  
   }
   public float getPosition(){
      return position;
   }
   public void setFloorIndicator (int floor){
      floorIndicator = floor;
   }
   public int readFloorIndicator() {
      return floorIndicator;
   }
}
