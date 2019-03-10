/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
// import frc.robot.commands.LiftDrive_Command;
import frc.robot.commands.LiftRobot_Command;
import frc.robot.commands.LowerBack_Command;
import frc.robot.commands.LowerFront_Command;

public class ClimbSequence_CommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */

  double leftDistanceIN = Robot.leftDistanceIN;

  public ClimbSequence_CommandGroup() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    addSequential(new LiftRobot_Command(), 5);
    // addSequential(new LiftDrive_Command(45));
    
      if (leftDistanceIN < 110) {
      addSequential(new LowerFront_Command());
      }
  

    // while (leftDistanceIN > 70) {
    //   addSequential(new LiftDrive_Command());
    //   addSequential(new LiftDrive_Command()); // make a drivestraight for drivebase
    //     if (leftDistanceIN < 75) {
    //     addSequential(new LowerBack_Command());
    //     }
    //   }
    
    


    requires(Robot.lift_Subsystem);
    requires(Robot.driveBase_Subsystem);
    


  }
}
