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
public class Pincher_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid pincherBitePiston = RobotMap.pincherBitePiston;
  DoubleSolenoid pincherSlidePiston = RobotMap.pincherSlidePiston;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void openPincher() {
		pincherBitePiston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closePincher() {
		pincherBitePiston.set(DoubleSolenoid.Value.kReverse);
	}
  
  public void extendSlidePiston() {
		pincherSlidePiston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractSlidePiston() {
		pincherSlidePiston.set(DoubleSolenoid.Value.kReverse);
	}

  public void stop() {

    pincherBitePiston.set(DoubleSolenoid.Value.kOff);
    pincherSlidePiston.set(DoubleSolenoid.Value.kOff);

  }
}
