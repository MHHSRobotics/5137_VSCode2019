package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A Simple Driver for the Limelight
 */

public class Limelight{

    /**
     * An enum storing potential LED states.
     */
    public static enum LED_STATE{
        ON, OFF, BLINK, MANUAL;
    }

    /**
     * An enum storing potential Camera states.
     */
    public static enum CAM_MODE{
        VISION, DRIVER;
    }

    public static enum TARGET_MODE{
        LEFT, RIGHT, CENTER;
    }

    private static final int kFOVvert = 41;
    private static final int kFOVhor = 54;

    private static final int kResVert = 240;
    private static final int kResHor = 320;

    private static final double kCamOffset = 27d/2d;


    private static final int kHeightTarget = 6; //actual height of the target
    private static final int kHeight = 6;
    
    private static final int kRollingHeightFilter = 5;
    
    private final String mTableName;

    public Limelight(String tableName){
        mTableName = tableName;
    }  

    /**
     * @return if the camera has any valid targets
     */
    public boolean hasTarget(){
        if ( get("tv") == 1 ) {
            return true;
        } else { return false;
        }
    }
    
    public double tX(){
        return get("tx");
    }

    public double tY(){
        return get("ty");
    }

    public double tArea(){
        return get("ta");
    }

    public double getTheta(){
        return Math.acos(kCamOffset * getAbsDist());
    }

    public double getAbsDist(){
        //         h
        // d = -----------
        //      2tan( (h * vFOV) / ( 2 * resVert) )

        return (kHeightTarget / (2 * Math.tan((kHeight * kFOVvert) / (2 *kResVert))));
    }

    public void setLED(LED_STATE state){
        switch(state){
            case MANUAL:
                set("ledMode", 0);
                break;
            case ON:
                set("ledMode", 3);
                break;
            case OFF:
                set("ledMode", 1);
                break;
            case BLINK:
                set("ledMode", 2);
                break;
        }
    }

    public Command setLEDCommand(LED_STATE state){
        return new Command(){
        
            @Override
            protected boolean isFinished() {
                setLED(state);
                return true;
            }
        };
    }

    public void setCamMode(CAM_MODE mode){
        switch (mode){
            case VISION:
                set("camMode", 0);
                break;
            case DRIVER:
                set("camMode", 1);
                break;
        }
    }

    public void setUSBCam(boolean isPrimary){
        set ("stream", (isPrimary) ? 2 : 0);
    }

    public void setTargetMode(TARGET_MODE mode){
        if (mode == TARGET_MODE.CENTER){
            set("pipeline", 0);
        }
        if (mode == TARGET_MODE.LEFT){
            set("pipeline", 1);
        }
        if (mode == TARGET_MODE.RIGHT){
            set("pipeline", 2);
        }
    }

    private double get(String varName){
        return NetworkTableInstance.getDefault().getTable(mTableName).getEntry(varName).getDouble(0);
    }

    private Double[] getArray(String varName){
        return NetworkTableInstance.getDefault().getTable(mTableName).getEntry(varName).
        getDoubleArray(new Double[]{0d,0d});
    }
    
    private void set(String varName, double value){
        NetworkTableInstance.getDefault().getTable(mTableName).getEntry(varName).setNumber(value);
    }
}