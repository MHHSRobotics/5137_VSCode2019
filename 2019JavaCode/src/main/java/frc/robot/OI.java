package frc.robot;

import frc.robot.commands.AimBot_Command;
import frc.robot.commands.GetDistance;
import frc.robot.commands.LiftRobot_Command;
import frc.robot.commands.LowerBack_Command;
import frc.robot.commands.LowerFront_Command;
import frc.robot.commands.PincherBite_Command;
import frc.robot.commands.PincherSlide_Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * Everything to do with the controller and its buttons.
 */
public class OI {
	
	// I don't think these have to be public but I know it works.
	public Joystick jackBlack;

	public JoystickButton getDistanceButton;
	public JoystickButton bitePincherButton;
	public JoystickButton slidePincherButton;
	public JoystickButton liftRobotButton;
	public JoystickButton lowerFrontButton;
	public JoystickButton lowerBackButton;
	public JoystickButton aimBotButton;
	// public JoystickButton raiseIntakeButton;
	// public JoystickButton lowerIntakeButton;
	// public JoystickButton intakeButton;
	// public JoystickButton outtakeButton;
	// public JoystickButton climbPrepButton;
	// public JoystickButton encoderTestButton;
	// public JoystickButton pivotTestButton;
	
	public OI() {
		jackBlack = new Joystick(0);

		getDistanceButton = new JoystickButton(jackBlack, 1); //CHANGES NEEDED
		getDistanceButton.whileHeld(new GetDistance());
		
		bitePincherButton = new JoystickButton(jackBlack, 2); //CHANGES NEEDED
		bitePincherButton.whenPressed(new PincherBite_Command());
		
		slidePincherButton = new JoystickButton(jackBlack, 3); //CHANGES NEEDED
		slidePincherButton.whenPressed(new PincherSlide_Command());
		
		liftRobotButton = new JoystickButton(jackBlack, 4); //CHANGES NEEDED
		liftRobotButton.whileHeld(new LiftRobot_Command());
		
		lowerFrontButton = new JoystickButton(jackBlack, 5); //CHANGES NEEDED
		lowerFrontButton.whileHeld(new LowerFront_Command());
		
		lowerBackButton = new JoystickButton(jackBlack, 6); //CHANGES NEEDED
		lowerBackButton.whileHeld(new LowerBack_Command());

		aimBotButton = new JoystickButton(jackBlack, 7); //CHANGES NEEDED
		aimBotButton.whileHeld(new AimBot_Command());
		
	/*	outtakeButton = new JoystickButton(jackBlack, 2); // B
		outtakeButton.whileHeld(new Outtake());
		
		climbPrepButton = new JoystickButton(jackBlack, 8); // menu
		climbPrepButton.whenPressed(new ClimbPrep()); 
	*/	
		/*
		pivotTestButton = new JoystickButton(jackBlack, 7);
		pivotTestButton.whenPressed(new Pivot(1.3, .65)); // slightly more than 90 deg on concrete w/ V = 12.3 */
		
		
		
		/*
		EncoderDriveForward edf = new EncoderDriveForward(6, .6);
		ButtonPressed buttonPressed = new ButtonPressed(edf);
		ButtonReleased buttonReleased = new ButtonReleased(edf);
		encoderTestButton = new JoystickButton(jackBlack, 8); // menu
		encoderTestButton.whileHeld(buttonPressed);
		encoderTestButton.whenReleased(buttonReleased); */
	}
	
}
