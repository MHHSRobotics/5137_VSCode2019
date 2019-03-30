package frc.robot.subsystems;

import frc.robot.commands.ArcadeDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
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

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	/*---------------------------------------------*/
	// pid loop variables:

	private static final double kHoldDistance = 12.0;

	// maximum distance in inches we expect the robot to see
	private static final double kMaxDistance = 24.0;
  
	// factor to convert sensor values to a distance in inches
	private static final double kValueToInches = 0.125;
  
	// proportional speed constant
	private static final double kP = 7.0;
  
	// integral speed constant
	private static final double kI = 0.018;
  
	// derivative speed constant
	private static final double kD = 1.5;

	private final PIDController m_pidController
		= new PIDController(kP, kI, kD, , new MyPidOutput());

	/*--------------------------------------------*/


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


	private class MyPidOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
		  hotWheels.arcadeDrive(0, output);
		}
	  }


	/* 
	 * Arcade Drive is a form of driving...
	 * That allows one joystick on a controller to control both forwards/backwards and left and right (via SlideDrive)
	 * and delegates rotation to a different joystick
	 */
	public void VisionDrive(int pipeline) {
	
		/*case one is center target,
		case two is left,
		case three is right,
		*/

		double centerX = Robot.centerX;
		double targetX = Robot.targetX;
		double rightDistanceIN = Robot.rightDistanceIN;

		double gearDownOne = 10.00;
		// double gearDownTwo = 20.00;
		// double gearDownThree = 10.00;
		// double gearDownFour = 10.00;
		double stopDistance = 7.00;

		// stops limelight targeting if too close to the target

		if (rightDistanceIN >= stopDistance) {
		switch (pipeline) {

			case 1: table.getEntry("pipeline").setNumber(0); //sets pipeline number 1-9 
			 break;
			case 2: table.getEntry("pipeline").setNumber(2); //sets pipeline number 1-9
			 break;
			case 3: table.getEntry("pipeline").setNumber(3); //sets pipeline number 1-9
			 break;    
	  
		  }
		}

		// table.getEntry("pipeline").setNumber(0); //sets pipeline number 1-9

        if (centerX - targetX > 3.3 && rightDistanceIN >= gearDownOne) {
		RobotMap.hotWheels.arcadeDrive(-.3, .65);
		}
		else if (centerX - targetX < -3.3 && rightDistanceIN >= gearDownOne) {
		RobotMap.hotWheels.arcadeDrive(-.3, -.65);
		}
		else if (rightDistanceIN >= gearDownOne) {
			RobotMap.hotWheels.arcadeDrive(-.68, 0);
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
		table.getEntry("pipeline").setNumber(1); //sets pipeline number 1-9
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
