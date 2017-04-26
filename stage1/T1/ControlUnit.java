/**
La causalidad de eventos es la siguiente: el motor hace mover la cabina,
ésta presiona un sensor, y luego el sensor
reporta este evento a la unidad de control. La unidad de control es
responsable de informar la llegada a un nuevo piso y chequear y atender
posibles solicitudes de ese piso.
En la operación de un ascensor, la Unidad de Control no requiere
pedir servicios a un sensor, pues es éste quien informa 
a la unidad de control de un cambio (la señal del sensor es una
entrada de la Unidad de Control y no una salida). Se ha puesto
una referencia a sensores en la unidad de control
para imprimir el estado de éstos.
La Unidad de Control tiene una referencia a la cabina para informar 
a las personas en su interior del número de piso en que van.
*/

public class ControlUnit {
   private Motor motor;
   private Cabina cabina;
   private Sensor[] sensores;
   private Botonera[] botoneras;
      
   public ControlUnit(Motor m,Cabina ca, Sensor[] s, Botonera[] b){
      motor = m;
      cabina = ca;
      sensores = s;
      botoneras = b;
   }
   public void elevatorRequested(int locationRequest){
      if (motor.getState() == Motor.STOPPED) {
         int cabinaLocation = cabina.readFloorIndicator();
         if (locationRequest >  cabinaLocation)
            motor.lift();
         else if (locationRequest < cabinaLocation)
            motor.lower();
         else{ // the cabina is already in that floor.
            checkAndAttendUpRequest(cabinaLocation);
            checkAndAttendDownRequest(cabinaLocation);
         }
      }
   }
   
   // print state of cabina, sensors, cabina requests, up requests and down requests      

   private void printElevatorState(){
      System.out.print(cabina.readFloorIndicator()+"\t");
      for (Sensor s: sensores)
         System.out.print(s.isActivated()?"1":"0");
      System.out.print("\t");
      System.out.print((BotoneraCabina)botoneras[0]+"\t");
      for (int i=1; i < botoneras.length; i++) 
         if (botoneras[i] instanceof UpRequest) {
            UpRequest boton = (UpRequest) botoneras[i];
            System.out.print(boton.isUpRequested()?"1":"0");
         }   
      System.out.print("-\t-");
      for (int i=1; i < botoneras.length; i++) 
         if (botoneras[i] instanceof DownRequest) {
            DownRequest boton = (DownRequest) botoneras[i];
            System.out.print(boton.isDownRequested()?"1":"0");
         }   
      System.out.println();   
   }
   private void checkAndAttendUpRequest(int floor) {
      if (botoneras[floor] instanceof UpRequest){
         UpRequest boton = (UpRequest) botoneras[floor];
         if (boton.isUpRequested()) {
            boton.resetUpRequest();
            printElevatorState();
            motor.pause();
         }            
      }
   }
   private void checkAndAttendDownRequest(int floor) {
      if (botoneras[floor] instanceof DownRequest){
         DownRequest boton = (DownRequest) botoneras[floor];
         if (boton.isDownRequested()) {
            boton.resetDownRequest();
            printElevatorState();
            motor.pause();
         }            
      }
   }
   private void checkAndAttendCabinaRequest(int floor) {
      BotoneraCabina bc = (BotoneraCabina) botoneras[0];
      if (bc.isFloorRequested(floor)) {
            bc.resetFloorRequest(floor);
            printElevatorState();
            motor.pause();
      }            
   }
   public void activateSensorAction(int currentFloor){
      cabina.setFloorIndicator(currentFloor);
      printElevatorState();
      checkAndAttendCabinaRequest(currentFloor);
      switch (motor.getState()){
         case Motor.UP: checkAndAttendUpRequest(currentFloor);
                        if(!areThereHigherRequests(currentFloor)){
                           checkAndAttendDownRequest(currentFloor);
                           if (areThereLowerRequests(currentFloor))
                              motor.lower();
                           else
                              motor.stop();
                         }
                         break;
         case Motor.DOWN:  checkAndAttendDownRequest(currentFloor);
                           if(!areThereLowerRequests(currentFloor)){
                              checkAndAttendUpRequest(currentFloor);
                              if (areThereHigherRequests(currentFloor))
                                 motor.lift();
                              else
                                 motor.stop();
                           }
      }
   }
   private boolean areThereHigherRequests(int currentFloor) {
      BotoneraCabina bc = (BotoneraCabina) botoneras[0];
      for (int i=currentFloor+1; i < botoneras.length; i++) {
         if(botoneras[i] instanceof UpRequest){
            UpRequest boton = (UpRequest) botoneras[i];
            if (boton.isUpRequested()) 
               return true;
         }
         if(botoneras[i] instanceof DownRequest){
            DownRequest boton = (DownRequest) botoneras[i];
            if (boton.isDownRequested()) 
               return true;
         }
         if (bc.isFloorRequested(i))
            return true;
      }
      return false;
   }
   
   private boolean areThereLowerRequests(int currentFloor) {
      BotoneraCabina bc = (BotoneraCabina) botoneras[0];
      for (int i=currentFloor-1; i > 0; i--) {
         if(botoneras[i] instanceof UpRequest){ 
            UpRequest boton = (UpRequest) botoneras[i];
            if (boton.isUpRequested()) 
               return true;
         }
         if(botoneras[i] instanceof DownRequest){ 
            DownRequest boton = (DownRequest) botoneras[i];
            if (boton.isDownRequested()) 
               return true;
         }
         if (bc.isFloorRequested(i))
            return true;
      }
      return false;
   }
}