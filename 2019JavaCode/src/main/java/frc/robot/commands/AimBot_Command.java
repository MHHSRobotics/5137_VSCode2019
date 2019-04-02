/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AimBot_Command extends Command {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  int pipelineNumber;
  boolean runOnce;

  public AimBot_Command() {
    requires(Robot.driveBase_Subsystem);
    requires(Robot.aiming_PIDSubsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  public AimBot_Command(int target) {
    requires(Robot.driveBase_Subsystem);
    requires(Robot.aiming_PIDSubsystem);
    pipelineNumber = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // Robot.driveBase_Subsystem.VisionDrive(pipelineNumber);
    
    if (runOnce == false) {
    table.getEntry("pipeline").setNumber(0); //sets pipeline number 1-9 
    // Robot.aiming_PIDSubsystem.enable();
    Robot.driveBase_Subsystem.m_pidController.enable();
    runOnce = true;
    }
    // Robot.aiming_PIDSubsystem.myUsePIDOutput(Robot.aiming_PIDSubsystem.pidOutput);
  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.driveBase_Subsystem.stop();
    table.getEntry("pipeline").setNumber(1); //sets pipeline number 1-9 
    // Robot.aiming_PIDSubsystem.disable();
    Robot.driveBase_Subsystem.m_pidController.disable();
    runOnce = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
