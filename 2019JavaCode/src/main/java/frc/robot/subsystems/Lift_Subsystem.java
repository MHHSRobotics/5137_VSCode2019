/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static DoubleSolenoid frontLiftPiston = RobotMap.frontLiftPiston;
  public static DoubleSolenoid backLiftPiston = RobotMap.backLiftPiston;
  public static Spark liftMotor = RobotMap.liftMotor;
  public static Joystick jackBlack;

  // static int liftDelayCounter = 0;

  @Override
  public void initDefaultCommand() {
  }

  public static void liftRobot() {
    frontLiftPiston.set(DoubleSolenoid.Value.kForward);  //deploys front lift
    // if (liftDelayCounter >= 5) {
    backLiftPiston.set(DoubleSolenoid.Value.kForward);  //deploys back lift
    // }
    Robot.liftMode = true;
    System.out.println("Lifting Robot");
    // liftDelayCounter++;
  }

  public static void liftFront() {
    frontLiftPiston.set(DoubleSolenoid.Value.kForward);  //deploys first lift
    // Robot.liftMode = true;
    System.out.println("Lifting Front");
  }

  public static void liftBack() {
    backLiftPiston.set(DoubleSolenoid.Value.kForward);  //deploys back lift
    System.out.println("Lifting back");
  }

  public static void lowerFront() {
    frontLiftPiston.set(DoubleSolenoid.Value.kReverse); //retracts front lift
    System.out.println("Retracting Front");
  }

  public static void lowerBack() {
    backLiftPiston.set(DoubleSolenoid.Value.kReverse); //CHANGES NEEDED
    Robot.liftMode = false;
    System.out.println("Retracting Back");
    
  }

  public static void driveLift() {
    // if (Robot.liftMode == true){
    //   if (Robot.manualControl == true) {
    // double driveLift = jackBlack.getRawAxis(5); //Right Analog Up/Down
    // liftMotor.set(-driveLift); //CHANGES NEEDED
    // System.out.println("driving manually");
    //   }
    liftMotor.set(1.0);
    System.out.println("driving automatically");
    // }
  }

  public static void stop() {
    frontLiftPiston.set(DoubleSolenoid.Value.kOff);
    backLiftPiston.set(DoubleSolenoid.Value.kOff);
    liftMotor.set(0.0);
    // liftDelayCounter = 0;
  }
}
