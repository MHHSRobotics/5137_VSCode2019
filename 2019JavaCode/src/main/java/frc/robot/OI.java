package frc.robot;

import frc.robot.commands.AimBot_Command;
import frc.robot.commands.BackLift_Command;
import frc.robot.commands.CargoBoxHeld_Command;
import frc.robot.commands.CargoBoxReleased_Command;
import frc.robot.commands.CargoBox_Command;
import frc.robot.commands.FrontLift_Command;
import frc.robot.commands.GetDistance;
import frc.robot.commands.LiftDrive_Command;
import frc.robot.commands.LiftDrive_Command;
import frc.robot.commands.LiftRobot_Command;
import frc.robot.commands.LowerBack_Command;
import frc.robot.commands.LowerFront_Command;
import frc.robot.commands.PincherBite_Command;
import frc.robot.commands.PincherSlide_Command;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/*
 * Everything to do with the controller and its buttons.
 */
public class OI {
	
	// I don't think these have to be public but I know it works.
	public static Joystick jackBlack;
	public static Joystick whiteKnight;

	public JoystickButton getDistanceButton;
	public JoystickButton bitePincherButton;
	public JoystickButton slidePincherButton;
	public JoystickButton liftRobotButton;
	public JoystickButton liftFrontButton;
	public JoystickButton liftBackButton;
	public JoystickButton lowerFrontButton;
	public JoystickButton lowerBackButton;
	public JoystickButton driveLiftButton;
	public JoystickButton aimBotButton;
	public JoystickButton cargoBoxButton;
	public JoystickButton cargoBoxHeldButton;
	public POVButton upDPadButton;
	public POVButton downDPadButton;
	public POVButton leftDPadButton;
	public POVButton rightDPadButton;


	// public JoystickButton raiseIntakeButton;
	// public JoystickButton lowerIntakeButton;
	// public JoystickButton intakeButton;
	// public JoystickButton outtakeButton;
	// public JoystickButton climbPrepButton;
	// public JoystickButton encoderTestButton;
	// public JoystickButton pivotTestButton;
	
	public OI() {

		//The code below is for two XBox controllers. Add a /* at the beginning
		//and */ at the end to deactivate this code, and remove them to activate.
		//Drive Base and Lift are port 0; everything else is port 1.

		//START
		// First Competition Controls:

		jackBlack = new Joystick(0);
		whiteKnight = new Joystick(1);

		// Trigger Mapping for XBox Controllers **NEEDS TO BE TESTED**
		// if (jackBlack.getRawAxis(2) > .70) {
		// 	new PincherBite_Command();
		// }

		// DPad Mapping for XBox controllers **NEEDS TO BE TESTED**
		// upDPadButton = new POVButton(whiteKnight, 0);
		// upDPadButton.whenPressed(new PincherBite_Command());

		// Rumble Mapping for XBox controllers **NEEDS TO BE TESTED**
		// jackBlack.setRumble(RumbleType.kLeftRumble, 1.0); //1.0 is rumble, 0.0 is off
		// jackBlack.setRumble(RumbleType.kRightRumble, 1.0);

		bitePincherButton = new JoystickButton(whiteKnight, 1); //A, 1
		bitePincherButton.whenPressed(new PincherBite_Command());
		
		slidePincherButton = new JoystickButton(whiteKnight, 4); //Y, 4
		slidePincherButton.whenPressed(new PincherSlide_Command());

		// cargoBoxButton = new JoystickButton(whiteKnight, 2); //B, 2
		// cargoBoxButton.whenPressed(new CargoBox_Command());

		cargoBoxHeldButton = new JoystickButton(whiteKnight, 2); //B, 2
		cargoBoxHeldButton.whileHeld(new CargoBoxHeld_Command());
		cargoBoxHeldButton.whenReleased(new CargoBoxReleased_Command());

		aimBotButton = new JoystickButton(jackBlack, 1); //A, 1
		aimBotButton.whileHeld(new AimBot_Command());

		// liftRobotButton = new JoystickButton(jackBlack, 4); //Y, 4
		// liftRobotButton.whileHeld(new LiftRobot_Command());

		liftFrontButton = new JoystickButton(jackBlack, 5); //left bumper, 5
		liftFrontButton.whileHeld(new FrontLift_Command());

		liftBackButton = new JoystickButton(jackBlack, 6); //right bumper, 6
		liftBackButton.whileHeld(new BackLift_Command());
		
		lowerFrontButton = new JoystickButton(jackBlack, 3); //X, 3
		lowerFrontButton.whileHeld(new LowerFront_Command());
		
		lowerBackButton = new JoystickButton(jackBlack, 2); //B, 2
		lowerBackButton.whileHeld(new LowerBack_Command());

		// SINGLE DRIVER CONROLS---------------------------------------

		// liftRobotButton = new JoystickButton(jackBlack, 4); //Y, 0 4
		// liftRobotButton.whileHeld(new LiftRobot_Command());
		
		// lowerFrontButton = new JoystickButton(jackBlack, 3); //X, 0
		// lowerFrontButton.whileHeld(new LowerFront_Command());
		
		// lowerBackButton = new JoystickButton(jackBlack, 2); //B, 0 2
		// lowerBackButton.whileHeld(new LowerBack_Command());

		// bitePincherButton = new JoystickButton(jackBlack, 5); //Left Bumper, 1
		// bitePincherButton.whenPressed(new PincherBite_Command());
		
		// slidePincherButton = new JoystickButton(jackBlack, 6); //Right Bumper, 1
		// slidePincherButton.whenPressed(new PincherSlide_Command());

		// cargoBoxButton = new JoystickButton(jackBlack, 1); //A, 1
		// cargoBoxButton.whenPressed(new CargoBox_Command());

		// aimBotButton = new JoystickButton(jackBlack, 7); //select button
		// aimBotButton.whileHeld(new AimBot_Command());

		// driveLiftButton = new JoystickButton(jackBlack, 8); //start button
		// driveLiftButton.whileHeld(new LiftDrive_Command());

		//Other Controls:
		//Drive Straight = Left Analog Up/Down, 0
		//Turn = Right Analog Left/Right, 0
		//Drive Lift = Right Analog Up/Down, 0



		//END

	}
	
}
