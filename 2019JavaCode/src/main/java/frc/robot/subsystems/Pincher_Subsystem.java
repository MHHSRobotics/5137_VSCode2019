/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;





/**
 * Add your docs here.
 */
public class Pincher_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static DoubleSolenoid pincherBitePiston = RobotMap.pincherBitePiston;
  public static DoubleSolenoid pincherSlidePiston = RobotMap.pincherSlidePiston;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static DoubleSolenoid.Value getBitePincherStatus() {
    System.out.println(pincherBitePiston.get());
    return pincherBitePiston.get();
  }

  public static DoubleSolenoid.Value getSlidePincherStatus() {
    System.out.println(pincherSlidePiston.get());
    return pincherSlidePiston.get();
  }

  public static void openPincher() {
		pincherBitePiston.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void closePincher() {
		pincherBitePiston.set(DoubleSolenoid.Value.kReverse);
	}
  
  public static void extendSlidePiston() {
		pincherSlidePiston.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void retractSlidePiston() {
		pincherSlidePiston.set(DoubleSolenoid.Value.kReverse);
	}

  public void stop() {

    pincherBitePiston.set(DoubleSolenoid.Value.kOff);
    pincherSlidePiston.set(DoubleSolenoid.Value.kOff);

  }
}
