/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Pincher_Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class PincherBite_Command extends Command {
  public PincherBite_Command() {
    requires(Robot.pincher_Subsystem);
  }

  boolean isFinished = false;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println(Pincher_Subsystem.getBitePincherStatus());
    if (Pincher_Subsystem.getBitePincherStatus() != DoubleSolenoid.Value.kForward) { //CHANGES NEEDED
      Pincher_Subsystem.closePincher();
      System.out.println("closing pincher");
      SmartDashboard.putString("Pincher Status:", "CLOSED CLOSED CLOSED");
    }
    else {
      Pincher_Subsystem.openPincher();
      System.out.println("opening pincher");
      SmartDashboard.putString("Pincher Status:", "OPEN OPEN OPEN");
    }
    System.out.println(Pincher_Subsystem.getBitePincherStatus());
    System.out.println();
    isFinished = true;
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
