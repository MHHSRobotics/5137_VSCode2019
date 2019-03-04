package frc.robot.subsystems;

import frc.robot.commands.ArcadeDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveBase_Subsystem extends Subsystem {
	// Lines 14-19 are used to call all required bits into the subsystem and give them names to respond to
	Spark leftDriveMotor = RobotMap.leftDriveMotor;
	Spark rightDriveMotor = RobotMap.rightDriveMotor;
	DifferentialDrive hotWheels = RobotMap.hotWheels;
	private double previousDriveSpeed = 0;
	
	public static double driveSpeed = 0; // for DisplayValues


	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
	
	//	An algorithm developed by the fantastic Sarah C. Lincoln that adjusts the joysticks
	//	to run scaled to the deadZone
	public double adjustJoystickValue(double joystick, double deadZone) {
		double adjustedJoystick;
		if (Math.abs(joystick) < deadZone) {
			adjustedJoystick = 0;
		} else {
			adjustedJoystick = ((1 / (1 - deadZone)) * (joystick - deadZone));
		}
		return adjustedJoystick;
	}
	
	/* 
	 * Arcade Drive is a form of driving...
	 * That allows one joystick on a controller to control both forwards/backwards and left and right (via SlideDrive)
	 * and delegates rotation to a different joystick
	 */
	public void VisionDrive() {
    
		double centerX = Robot.centerX;
		double targetX = Robot.targetX;
		double rightDistanceIN = Robot.rightDistanceIN;

		double gearDownOne = 10.00;
		// double gearDownTwo = 20.00;
		// double gearDownThree = 10.00;
		// double gearDownFour = 10.00;
		double stopDistance = 7.00;

		Robot.limelightLED = true;
        
        if (centerX - targetX > 3 && rightDistanceIN >= gearDownOne) {
		RobotMap.hotWheels.arcadeDrive(-.3, .65);
		}
		else if (centerX - targetX < -3 && rightDistanceIN >= gearDownOne) {
		RobotMap.hotWheels.arcadeDrive(-.3, -.65);
		}
		else if (rightDistanceIN >= gearDownOne) {
			RobotMap.hotWheels.arcadeDrive(-.65, 0);
			System.out.print("gear down first");
			}
		else if (rightDistanceIN <= stopDistance) {
			RobotMap.hotWheels.arcadeDrive(0, 0);
			System.out.print("stopped");
			}
    }

	public void rampArcadeDrive(Joystick jackBlack) {

		double driveJoystick = jackBlack.getRawAxis(1); // same for 2019 & 2018
		double turnJoystick = jackBlack.getRawAxis(4); // 0 for big ancient joystick, 4 for xbox
		
		double newDriveSpeed;
		newDriveSpeed = accelerate(driveJoystick, previousDriveSpeed, .4, .05);
		driveSpeed = newDriveSpeed; // to print to SmartDashboard
		previousDriveSpeed = newDriveSpeed;
		if (Robot.liftMode == true) {
			hotWheels.arcadeDrive(newDriveSpeed/1.5, -turnJoystick/1.3); //negative turn for 2018 robot
		} else {
				hotWheels.arcadeDrive(newDriveSpeed, -turnJoystick/1.3); //negative turn for 2018 robot
		}
		// System.out.println(newDriveSpeed + " " + -turnJoystick + " ");
		
	}

	public double accelerate(double joystickValue, double previousSpeed, double minSpeed, double incValue) {
		int delay = 25;
		double newSpeed;
		
		// effectively a deadzone with range +-minValue
		if (Math.abs(joystickValue) > minSpeed && Math.abs(previousSpeed) < minSpeed) {
			newSpeed = Math.signum(joystickValue) * minSpeed;
		} else {
			newSpeed = previousSpeed;
		}
		
		if (previousSpeed < joystickValue) newSpeed += incValue;
		else if (previousSpeed > joystickValue) newSpeed -= incValue;
		else newSpeed = previousSpeed; // necessary??
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {

		}
		
		return newSpeed;
	}

	public void driveStraight(double speed) {
		hotWheels.arcadeDrive(-speed, 0);
	}
	
	// CW is positive, CCW is negative
	public void pivot(double speed) {
		hotWheels.arcadeDrive(0, -speed); 
	}
	
	public void stop() {
		hotWheels.arcadeDrive(0, 0);
		Robot.limelightLED = false;
	}



		//public void arcadeDrive(Joystick jackBlack) {
	   /*
        * x' = a(x^b) + (1-a)x
        *
        * x' is the scaled output value
        * a is the vertical/horizontal stretch
        * x is the raw value input from the axis
        * as a approaches 0, the ramp approaches a linear (y=x) ramp
        * as a approaches 1 the ramp approaches an exponential ramp
        * increasing the b makes the ramp's severity lower
        * (it takes more to move the stick for it to reach its full value)
        * (looks like a x^b)
        * decreasing the b between 1 and 0 makes the ramp's severity higher
        * (looks like a root x )
        * don't make a negative. that is NOT GOOD
        */
        
		/*
        double rawArcade = jackBlack.getRawAxis(1);
        double rawSlide = jackBlack.getRawAxis(0);
        double rawTurn = jackBlack.getRawAxis(4);
        */
        
        // dead zone included
      /*  double rawDrive = adjustJoystickValue(jackBlack.getRawAxis(1), .2);
        double rawTurn = adjustJoystickValue(jackBlack.getRawAxis(4), .2); 
        
        double sensitivityDrive = .95;
        double sensitivityTurn = .95;
        
        double exponentialDrive;
        double exponentialTurn;
        
        double sensitivityExponent = 3;
        
        exponentialDrive = (sensitivityDrive*(Math.pow(rawDrive, sensitivityExponent))) + ((1-sensitivityDrive)*rawDrive);
        exponentialTurn = -1*(sensitivityTurn*(Math.pow(rawTurn, sensitivityExponent))) + ((1-sensitivityTurn)*rawTurn);
        
        hotWheels.arcadeDrive(exponentialDrive, exponentialTurn);
	}
	*/

}
