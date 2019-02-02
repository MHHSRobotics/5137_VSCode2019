/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static DoubleSolenoid frontLiftPiston = RobotMap.frontLiftPiston;
  public static DoubleSolenoid backLiftPiston = RobotMap.backLiftPiston;

  @Override
  public void initDefaultCommand() {
  }

  public static void liftRobot() {
    frontLiftPiston.set(DoubleSolenoid.Value.kForward); //CHANGES NEEDED
    backLiftPiston.set(DoubleSolenoid.Value.kForward); //CHANGES NEEDED
  }

  public static void lowerFront() {
    frontLiftPiston.set(DoubleSolenoid.Value.kReverse); //CHANGES NEEDED
  }

  public static void lowerBack() {
    backLiftPiston.set(DoubleSolenoid.Value.kReverse); //CHANGES NEEDED
  }
}
