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
	public static DoubleSolenoid frontLiftPiston;
	public static DoubleSolenoid backLiftPiston;
	public static DoubleSolenoid cargoBoxPiston;
	
	public static SpeedControllerGroup thisIsJustATestIgnoreThis;
	
	public static void init() { //CHANGES NEEDED for everything
		leftDriveMotor = new Spark(7); // 2017 and 2018 bot are 1, 2019 is 7
		leftDriveMotor.setInverted(true);
		
		rightDriveMotor = new Spark(9); // 2018 bot is 2, 2017 bot is 1, 2019 is 9
		rightDriveMotor.setInverted(true);

		liftMotor = new Spark(8); //that one tiny wheel on our inspector gadget bot
		liftMotor.setInverted(true);
		
		hotWheels = new DifferentialDrive(leftDriveMotor, rightDriveMotor);
		
		// liftMotor = new Spark(3);
		// liftMotor.setInverted(true);
		
		// rotateIntakeMotor = new Spark(4); 
				// intakeMotor = new Spark(5); 
		// intakeMotor.setInverted(true);
		
		// leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
		// leftEncoder.setDistancePerPulse(.1173); 
		// leftEncoder.setMinRate(10);
		
		// rightEncoder = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
		// rightEncoder.setDistancePerPulse(.1173);
		// rightEncoder.setMinRate(10);
		
		// upperLimitSwitch = new DigitalInput(9);
		// lowerLimitSwitch = new DigitalInput(8);


		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		
		// first pcm is 0, second is 1

		pincherBitePiston = new DoubleSolenoid(0, 0, 1);
		pincherSlidePiston = new DoubleSolenoid(0, 2, 3);
		frontLiftPiston = new DoubleSolenoid(0, 6, 7);
		backLiftPiston = new DoubleSolenoid(0, 4, 5);
		cargoBoxPiston = new DoubleSolenoid(1, 0, 1); //on pcm 1
		
	}
	
}
