/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5137.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;

import org.usfirst.frc.team5137.subsystems.CargoBox_Subsystem;
import org.usfirst.frc.team5137.subsystems.DriveBase_Subsystem;
import org.usfirst.frc.team5137.subsystems.Pincher_Subsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveBase_Subsystem driveBase_Subsystem; 
  public static Pincher_Subsystem pincher_Subsystem;	
  public static CargoBox_Subsystem cargoBox_Subsystem;

	public static OI oi;

  public static UsbCamera frontCamera;
  public static UsbCamera leftCamera;
  public static UsbCamera rightCamera;
  public static Object imgLock;

  public static double centerX = 0.0;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  //  Ultrasonic ultra = new Ultrasonic(6,7); // creates the ultra object andassigns ultra to be an ultrasonic sensor which uses DigitalOutput 1 for 
       // the echo pulse and DigitalInput 1 for the trigger pulse

  /*
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {

    RobotMap.init();

    driveBase_Subsystem = new DriveBase_Subsystem();
	  pincher_Subsystem = new Pincher_Subsystem();
    cargoBox_Subsystem = new CargoBox_Subsystem();
     	
		oi = new OI(); // gotta go after all the subsystems!


    frontCamera = CameraServer.getInstance().startAutomaticCapture();
		frontCamera.setResolution(320, 240);
    frontCamera.setFPS(30); 
    
    leftCamera = CameraServer.getInstance().startAutomaticCapture();
		leftCamera.setResolution(320, 240);
    leftCamera.setFPS(30); 
    
    rightCamera = CameraServer.getInstance().startAutomaticCapture();
		rightCamera.setResolution(320, 240);
		rightCamera.setFPS(30); 


    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

  //  ultra.setAutomaticMode(true); // turns on automatic mode

  }


  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  
  //  double UltraInInches = ultra.getRangeInches();
  //  double UltraInMM = ultra.getRangeMM();
  
    // reads the range on the ultrasonic sensor
  //  System.out.println("UltraInInches:" + UltraInInches);
  //  System.out.println(UltraInMM); 
  //  System.out.print("UltraInMM");  


   NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   NetworkTableEntry tx = table.getEntry("tx");
   NetworkTableEntry ty = table.getEntry("ty");
   NetworkTableEntry ta = table.getEntry("ta");

  //read values periodically
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);

  //post to smart dashboard periodically
  SmartDashboard.putNumber("LimelightX", x);
  SmartDashboard.putNumber("LimelightY", y);
  SmartDashboard.putNumber("LimelightArea", area);
    
  if (x <= 1.0) {
  System.out.println(x);
    }
  if (y <= 1.0) {
    System.out.println(y);
    }
  if (x <= 1.0) {
    System.out.println(area);
    }


}
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();	
  }
}
