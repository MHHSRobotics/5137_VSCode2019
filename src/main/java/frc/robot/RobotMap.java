package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/* The robot map is used to define and categorize all things on the robot...
 * This includes: motors, gyros and specialty things such as the
 * DifferentialDrive- This allows the drive motors (not the slidedrive) to 
 * function together and do things such as arcade drive and tank drive 
 * more readily as seen in the DriveBase code.
 */
public class RobotMap {

	public static Spark leftDriveMotor;
	public static Spark rightDriveMotor;
	public static Spark liftMotor;
	public static Spark rotateIntakeMotor;
	public static Spark intakeMotor;
	public static DifferentialDrive hotWheels;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	public static DigitalInput upperLimitSwitch;
	public static DigitalInput lowerLimitSwitch;


	public static Compressor compressor;

	public static DoubleSolenoid pincherBitePiston;
	public static DoubleSolenoid pincherSlidePiston;

	public static DoubleSolenoid cargoBoxPiston;

	public static DoubleSolenoid frontLiftPiston;
	public static DoubleSolenoid backLiftPiston;

	
	public static SpeedControllerGroup thisIsJustATestIgnoreThis;
	
	public static void init() {
		leftDriveMotor = new Spark(0); // 2017 and 2018 bot are 1
	//	leftDriveMotor.setInverted(true);
		
		rightDriveMotor = new Spark(1); // 2018 bot is 2, 2017 bot is 1
	//	rightDriveMotor.setInverted(true);
		
		hotWheels = new DifferentialDrive(leftDriveMotor, rightDriveMotor);
		
	/*	
		liftMotor = new Spark(3);
		liftMotor.setInverted(true);
		
		rotateIntakeMotor = new Spark(4); 
		intakeMotor = new Spark(5); 
		intakeMotor.setInverted(true);
		
		leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
		leftEncoder.setDistancePerPulse(.1173); 
		leftEncoder.setMinRate(10);
		
		rightEncoder = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
		rightEncoder.setDistancePerPulse(.1173);
		rightEncoder.setMinRate(10);
		
		upperLimitSwitch = new DigitalInput(9);
		lowerLimitSwitch = new DigitalInput(8);

		*/
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		
		pincherBitePiston = new DoubleSolenoid(0, 1);
		pincherSlidePiston = new DoubleSolenoid(3, 4);
		// frontLiftPiston = new DoubleSolenoid(5, 6);
		// backLiftPiston = new DoubleSolenoid(7, 8);

		// cargoBoxPiston = new DoubleSolenoid(0, 1);
		
		
	}
	
}
