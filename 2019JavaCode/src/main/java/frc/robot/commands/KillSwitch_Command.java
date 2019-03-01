/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.subsystems.CargoBox_Subsystem;
import frc.robot.subsystems.Pincher_Subsystem;

public class KillSwitch_Command extends Command {
  public KillSwitch_Command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    Scheduler.getInstance().removeAll();

    requires(Robot.cargoBox_Subsystem);
    requires(Robot.driveBase_Subsystem);
    requires(Robot.lift_Subsystem);
    requires(Robot.pincher_Subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.pincher_Subsystem.stop();
    Robot.driveBase_Subsystem.stop();
    Robot.cargoBox_Subsystem.stop();
    Robot.pincher_Subsystem.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
