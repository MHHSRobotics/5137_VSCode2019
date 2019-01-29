package org.usfirst.frc.team5137.robot;

import org.usfirst.frc.team5137.commandGroups.ClimbPrep;
import org.usfirst.frc.team5137.commands.IntakeVerb;
import org.usfirst.frc.team5137.commands.LowerIntake;
import org.usfirst.frc.team5137.commands.LowerLift;
import org.usfirst.frc.team5137.commands.Outtake;
import org.usfirst.frc.team5137.commands.RaiseIntake;
import org.usfirst.frc.team5137.commands.RaiseLift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * Everything to do with the controller and its buttons.
 */
public class OI {
	
	// I don't think these have to be public but I know it works.
	public Joystick jackBlack;
	
	public JoystickButton raiseLiftButton;
	public JoystickButton lowerLiftButton;
	public JoystickButton raiseIntakeButton;
	public JoystickButton lowerIntakeButton;
	public JoystickButton intakeButton;
	public JoystickButton outtakeButton;
	public JoystickButton climbPrepButton;
	public JoystickButton encoderTestButton;
	
	//public JoystickButton pivotTestButton;
	
	public OI() {
		jackBlack = new Joystick(0);
		
		raiseLiftButton = new JoystickButton(jackBlack, 3); // X
		raiseLiftButton.whileHeld(new RaiseLift());
		
		lowerLiftButton = new JoystickButton(jackBlack, 4); // Y
		lowerLiftButton.whileHeld(new LowerLift());
		
		raiseIntakeButton = new JoystickButton(jackBlack, 5); // left bumper
		raiseIntakeButton.whileHeld(new RaiseIntake());
		
		lowerIntakeButton = new JoystickButton(jackBlack, 6); // right bumper
		lowerIntakeButton.whileHeld(new LowerIntake());
		
		intakeButton = new JoystickButton(jackBlack, 1); // A
		intakeButton.whileHeld(new IntakeVerb());
		
		outtakeButton = new JoystickButton(jackBlack, 2); // B
		outtakeButton.whileHeld(new Outtake());
		
		climbPrepButton = new JoystickButton(jackBlack, 8); // menu
		climbPrepButton.whenPressed(new ClimbPrep()); 
		
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
