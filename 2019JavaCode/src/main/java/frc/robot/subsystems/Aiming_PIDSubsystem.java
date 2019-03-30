/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Aiming_PIDSubsystem extends PIDSubsystem {
  /**
   * Add your docs here.
   */

  public double pidOutput;

  public Aiming_PIDSubsystem() {
    // Intert a subsystem name and PID values here
    super("Aiming_PIDSubsystem", 3.0, 0.001, 0.0);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.

    setSetpoint(0.0);
    enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    double centerX = Robot.centerX;
    double targetX = Robot.targetX;
    double rightDistanceIN = Robot.rightDistanceIN;

    System.out.print("TargetX" + targetX);

    return centerX - targetX;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    System.out.print("PIDOutput:" + output);
    
    RobotMap.hotWheels.arcadeDrive(0, output);
    pidOutput = output;
  }

  // public void myUsePIDOutput(double output) {
  //   // Use output to drive your system, like a motor
  //   // e.g. yourMotor.set(output);
  //   usePIDOutput(output);    
  //   System.out.print("aiming");
  // }
}
