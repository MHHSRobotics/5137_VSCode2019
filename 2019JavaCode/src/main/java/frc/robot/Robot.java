/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.IterativeRobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import frc.robot.subsystems.Aiming_PIDSubsystem;
import frc.robot.subsystems.CargoBox_Subsystem;
import frc.robot.subsystems.DriveBase_Subsystem;
import frc.robot.subsystems.Lift_Subsystem;
import frc.robot.subsystems.Pincher_Subsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;

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
  public static Lift_Subsystem lift_Subsystem;
  public static Aiming_PIDSubsystem aiming_PIDSubsystem;

  public static boolean liftMode = false;
  public static boolean manualControl = false;

	public static OI oi;

  public static UsbCamera frontCamera;
  public static UsbCamera frontCamera2;
  public static Object imgLock;

  public static double centerX = 0.0;
  public static double targetX;
  public static double targetY;
  public static double targetArea;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  

  Ultrasonic leftUltrasonic = new Ultrasonic(1,0); //01
  Ultrasonic rightUltrasonic = new Ultrasonic(3,4); //34

  public static double leftDistanceIN;
  public static double rightDistanceIN;


  public static double goodValue = 0;
  public static boolean checkInit = true; //move these ^^ three out of periodic and into the instantiating area

  public static boolean limelightLED = false;

  int ultraCounter = 0;

  NetworkTable table;

  // private final SendableChooser<String> Chooser = new SendableChooser<>();

  /*
   * The following function "robotInit()" is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {

    RobotMap.init();

    driveBase_Subsystem = new DriveBase_Subsystem();
	  pincher_Subsystem = new Pincher_Subsystem();
    cargoBox_Subsystem = new CargoBox_Subsystem();
    lift_Subsystem = new Lift_Subsystem();
    aiming_PIDSubsystem = new Aiming_PIDSubsystem();
    
		oi = new OI(); // gotta go after all the subsystems!


    frontCamera = CameraServer.getInstance().startAutomaticCapture();
		frontCamera.setResolution(144, 60);
    frontCamera.setFPS(60); 


    frontCamera2 = CameraServer.getInstance().startAutomaticCapture();
		frontCamera2.setResolution(144, 60);
    frontCamera2.setFPS(60); 

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    rightUltrasonic.setAutomaticMode(true); // turns on automatic mode for right ultrasonic
    leftUltrasonic.setAutomaticMode(true); // turns on automatic mode for left ultrasonic

    table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("pipeline").setNumber(1); //sets pipeline number 1-9

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
    
    //Ultrasonic code. Finds and outputs the distance between the sensor and the robot.
    rightDistanceIN = rightUltrasonic.getRangeInches();
    leftDistanceIN = leftUltrasonic.getRangeInches();
    // System.out.println("Left Ultrasonic Reading:" + leftDistanceIN);
    // if (checkInit == true) {
    //   goodValue = rightDistanceIN;
    //   checkInit = false;
    // }

    // if (Math.abs(goodValue - rightDistanceIN) < 10 ) {
    //   goodValue = rightDistanceIN;
    // }

    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ta = table.getEntry("ta");
    

    // table.getEntry("stream").setNumber(0); //0 is standard (2 cameras side by side)
    // 1 is the secondary camera in the corner, 2 is reversed

  //read values periodically
  targetX = tx.getDouble(0.0);
  targetArea = ta.getDouble(0.0);
  
  // System.out.println("Robot." + targetX);

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

    teleopPeriodic();

    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();	
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}