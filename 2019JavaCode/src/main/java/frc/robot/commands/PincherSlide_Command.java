/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Pincher_Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class PincherSlide_Command extends Command {
  public PincherSlide_Command() {
    requires(Robot.pincher_Subsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  boolean isFinished = false;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Pincher_Subsystem.getSlidePincherStatus() != DoubleSolenoid.Value.kForward) { //CHANGES NEEDED
      System.out.println("extending");
      Pincher_Subsystem.extendSlidePiston();
      SmartDashboard.putString("SLIDEPincher Status:", "IN IN IN RETRACTED!!!!");
      OI.jackBlack.setRumble(RumbleType.kLeftRumble, 1.0); //1.0 is rumble, 0.0 is off
		  OI.jackBlack.setRumble(RumbleType.kRightRumble, 1.0);
    }
    else {
      System.out.println("retracting");
      Pincher_Subsystem.retractSlidePiston();
      SmartDashboard.putString("SLIDEPincher Status:", "OUT OUT OUT EXTENDED!!!!");
      OI.jackBlack.setRumble(RumbleType.kLeftRumble, 1.0); //1.0 is rumble, 0.0 is off
		  OI.jackBlack.setRumble(RumbleType.kRightRumble, 1.0);
    }
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
    OI.jackBlack.setRumble(RumbleType.kLeftRumble, 0.0); //1.0 is rumble, 0.0 is off
    OI.jackBlack.setRumble(RumbleType.kRightRumble, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.pincher_Subsystem.stop();
    OI.jackBlack.setRumble(RumbleType.kLeftRumble, 0.0); //1.0 is rumble, 0.0 is off
    OI.jackBlack.setRumble(RumbleType.kRightRumble, 0.0);
  }
}
