package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import Contract.IModel;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

import static java.lang.Math.abs;

public class ArduinoToJava implements SerialPortEventListener
{
    SerialPort serialPort;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {"/dev/tty.usbserial-A9007UX1", // Mac OS X
                                                "/dev/ttyUSB0", // Linux
                                                "COM4", // Windows
    };
    
    private final static ArduinoToJava instance = new ArduinoToJava();
    private BufferedReader input;
    private String temp;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    private IModel model;
    private Point lastPoint;
    
    public static ArduinoToJava getInstance(){
        return instance;
    }
    
    public void setModel(final IModel newModel){
        this.model = newModel;
    }
    
    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        lastPoint = this.model.getVehicles().get(0).getSection().getOrigin();
        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }
        
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                                                  TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);
            
            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
    
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                int previousTotalDistance = 0;
                int mode = 0;
                int parkIndex = 0;
                int sectionIndex = 0;
                int parkSize = 0;
                int sectionTime = 0;
                int sectionSize = 0;
                int directionTaken = 2;
                String actualPoint = "";
                
                
                if (input.ready()) {
                    mode = Integer.parseInt(input.readLine());
                    if (mode == 0){
                        sectionTime = Integer.parseInt(input.readLine());
                        sectionSize = Integer.parseInt(input.readLine());
                        directionTaken = Integer.parseInt(input.readLine());
                    } else if(mode == 1){
                        sectionTime = Integer.parseInt(input.readLine());
                        sectionSize = Integer.parseInt(input.readLine());
                        directionTaken = Integer.parseInt(input.readLine());
                        actualPoint = input.readLine();
                    } else if (mode == 2) {
                        
                        this.model.getVehicles().get(0).setDistanceOnSection(Integer.parseInt(input.readLine()));
                        this.model.getVehicles().get(0).setTotalDistance(this.model.getVehicles().get(0).getDistanceOnSection()+this.model.getVehicles().get(0).getTotalDistance());
                        this.model.getVehicles().get(0).setTimeOnSection(Integer.parseInt(input.readLine()));
                        this.temp = input.readLine();
                        this.model.addNewParking(new Vector(lastPoint,new Point('.', (int) Math.round(lastPoint.getX() + 0.36254 * getFactorX() * this.model.getVehicles().get(0).getDistanceOnSection()), (int) Math.round(lastPoint.getY() + (0.235 * getFactorY() * this.model.getVehicles().get(0).getDistanceOnSection())))));
                        switch (this.temp){
                            case "building":
                                this.model.getParkings().get(this.model.getParkings().size()-1).setTag(1);
                            break;
                            case "hole":
                                this.model.getParkings().get(this.model.getParkings().size()-1).setTag(2);
                                break;
                            default:
                            System.err.println("INCORRECT TYPE IN TRANSMISSION");
                                break;
                        }
                        this.lastPoint = this.model.getParkings().get(this.model.getParkings().size()-1).getDestination();
                        this.model.getVehicles().get(0).setSpeed((float)this.model.getVehicles().get(0).getDistanceOnSection()/this.model.getVehicles().get(0).getTimeOnSection());
                        System.out.println(input.readLine());
                        this.model.getVehicles().get(0).setLastDirection(Integer.parseInt(input.readLine()));
                        this.model.getVehicles().get(0).setSection(convertToSection(this.model.getVehicles().get(0),this.model.getVehicles().get(0).getLastDirection()));
                        this.model.getVehicles().get(0).setFacing(this.model.getVehicles().get(0).getSection().getDestination());
                        
                        
                    }
                    System.out.println("==========================");
                }
                
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }
    
    private Vector convertToSection(final Vehicle vehicle, final int instruction){
        
        if (vehicle.getSection().getOrigin().getX()-vehicle.getSection().getDestination().getX() ==0){
            if (vehicle.getSection().getOrigin().getY() < vehicle.getSection().getDestination().getY())
            {
                switch (instruction)
                {
                    case 0:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()){
                                if (section.getDestination().getX() > vehicle.getSection().getDestination().getX()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()){
                                if (section.getDestination().getX() < vehicle.getSection().getDestination().getX()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 2:
                        Vector section = getStraightVerticalVector(vehicle);
                        if (section != null)
                            return section;
                        break;
                    default:
                        System.err.println("BAD_INSTRUCTION_FAULT");
                        break;
                }
            } else if(vehicle.getSection().getOrigin().getY() > vehicle.getSection().getDestination().getY()){
                switch (instruction)
                {
                    case 0:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()){
                                if (section.getDestination().getX() < vehicle.getSection().getDestination().getX()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()){
                                if (section.getDestination().getX() > vehicle.getSection().getDestination().getX()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 2:
                        Vector section = getStraightVerticalVector(vehicle);
                        if (section != null)
                            return section;
                        break;
                    default:
                        System.err.println("BAD_INSTRUCTION_FAULT");
                        break;
                }
                }
            
            }
                     else if (vehicle.getSection().getOrigin().getY()-vehicle.getSection().getDestination().getY() ==0){
            if (vehicle.getSection().getOrigin().getX() < vehicle.getSection().getDestination().getX())
            {
                switch (instruction)
                {
                    case 0:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()&& vehicle.getSection().getOrigin() != section.getDestination()){
                                if (section.getDestination().getY() < vehicle.getSection().getDestination().getY()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()&& vehicle.getSection().getOrigin() != section.getDestination()){
                                if (section.getDestination().getY() > vehicle.getSection().getDestination().getY()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 2:
                        Vector section = getStraightHorizontalVector(vehicle);
                        if (section != null)
                            return section;
                        break;
                    default:
                        System.err.println("BAD_INSTRUCTION_FAULT");
                        break;
                }
            } else if(vehicle.getSection().getOrigin().getX() > vehicle.getSection().getDestination().getX()){
                switch (instruction)
                {
                    case 0:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()&& vehicle.getSection().getOrigin() != section.getDestination()){
                                if (section.getDestination().getY() > vehicle.getSection().getDestination().getY()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 1:
                        for (Vector section : Map.getInstance().getConnections()){
                            if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()&& vehicle.getSection().getOrigin() != section.getDestination()){
                                if (section.getDestination().getY() < vehicle.getSection().getDestination().getY()){
                                    this.model.getVehicles().get(0).setLastTurnedIntersection(section.getOrigin());
                                    return section;
                                }
                            }
                        }
                        break;
                    case 2:
                        Vector section = getStraightHorizontalVector(vehicle);
                        if (section != null)
                            return section;
                        break;
                    default:
                        System.err.println("BAD_INSTRUCTION_FAULT");
                        break;
                }
            }

        
    }
    return vehicle.getSection();
}
    private Vector getStraightVerticalVector(Vehicle vehicle)
    {
        if (vehicle.getDistanceOnSection() > abs(vehicle.getSection().getOrigin().getX()-vehicle.getSection().getDestination().getX())){
            for (Vector section : Map.getInstance().getConnections()){
                if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()&& vehicle.getSection().getOrigin() != section.getDestination()){
                    if (section.getDestination().getX() == vehicle.getSection().getDestination().getX()){
                        return section;
                    }
                }
            }
        }
        return null;
    }
    private Vector getStraightHorizontalVector(Vehicle vehicle)
    {
        if (vehicle.getDistanceOnSection() > abs(vehicle.getSection().getOrigin().getY()-vehicle.getSection().getDestination().getY())){
            for (Vector section : Map.getInstance().getConnections()){
                if(section.getOrigin().getId() == vehicle.getSection().getDestination().getId()&& vehicle.getSection().getOrigin() != section.getDestination()){
                    if (section.getDestination().getY() == vehicle.getSection().getDestination().getY()){
                        return section;
                    }
                }
            }
        }
        return null;
    }
    
    private int getFactorX(){
        if (this.model.getVehicles().get(0).getSection().getOrigin().getX() < this.model.getVehicles().get(0).getSection().getDestination().getX()){
            return 1;
        } else if(this.model.getVehicles().get(0).getSection().getOrigin().getX() > this.model.getVehicles().get(0).getSection().getDestination().getX()){
            return -1;
        }else {
            return 0;
        }
    }
    
    private int getFactorY(){
        if (this.model.getVehicles().get(0).getSection().getOrigin().getY() < this.model.getVehicles().get(0).getSection().getDestination().getY()){
            return 1;
        } else if(this.model.getVehicles().get(0).getSection().getOrigin().getY() > this.model.getVehicles().get(0).getSection().getDestination().getY()){
            return -1;
        }else {
            return 0;
        }
    }
    
    /*
    public static void main(String[] args) throws Exception {
        SerialTest main = new SerialTest();
        main.initialize();
        Thread t=new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000    seconds,
                //waiting for events to occur and responding to them    (printing incoming messages to console).
                try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
            }
        };
        t.start();
        System.out.println("Started");
        
    }
    */
}
