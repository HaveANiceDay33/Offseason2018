package org.usfirst.frc.team5811.robot;

import org.usfirst.frc.team5811.robot.commands.ArcadeDrive;
import org.usfirst.frc.team5811.robot.commands.ArcadeSpeedMod;
import org.usfirst.frc.team5811.robot.commands.AutoRampDeploy;
import org.usfirst.frc.team5811.robot.commands.CurveOuttakeLeft;
import org.usfirst.frc.team5811.robot.commands.CurveOuttakeRight;
import org.usfirst.frc.team5811.robot.commands.GrabNavX;
import org.usfirst.frc.team5811.robot.commands.HaltIntake;
import org.usfirst.frc.team5811.robot.commands.IntakeInward;
import org.usfirst.frc.team5811.robot.commands.ArmsToggle;
import org.usfirst.frc.team5811.robot.commands.PivotManual;
import org.usfirst.frc.team5811.robot.commands.PosDown;
import org.usfirst.frc.team5811.robot.commands.PosStore;
import org.usfirst.frc.team5811.robot.commands.PosSwitch;
import org.usfirst.frc.team5811.robot.commands.RampExtend;

import org.usfirst.frc.team5811.robot.commands.SmartShoot;
import org.usfirst.frc.team5811.robot.commands.VisionCube;
import org.usfirst.frc.team5811.robot.commands.WeakShot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick joy1 = new Joystick(0);
	JoystickButton a = new JoystickButton(joy1, 2);
	JoystickButton b = new JoystickButton(joy1, 3);
	AxisButton leftJoyY = new AxisButton(joy1, 1);
	AxisButton leftJoyX = new AxisButton(joy1, 0);
	AxisButton rightJoyY = new AxisButton(joy1, 3);
	AxisButton rightJoyX = new AxisButton(joy1, 2);
	AxisButton righttrigger = new AxisButton(joy1, 4);
	DPadButton DPUp = new DPadButton(joy1, 0);
	DPadButton DRight = new DPadButton(joy1, 90);
	DPadButton DDown = new DPadButton(joy1, 180);
	DPadButton DLeft = new DPadButton(joy1, 270);

	//
	// Joystick joy2 = new Joystick(1);
	// JoystickButton aManip = new JoystickButton(joy2, 2);
	// JoystickButton bManip = new JoystickButton(joy2, 3);
	// JoystickButton yManip = new JoystickButton(joy2, 4);
	// JoystickButton xManip = new JoystickButton(joy2, 1);
	// ComboButton RLbumpers = new ComboButton(joy2, 5, 6);

	public Joystick joy2 = new Joystick(1);
	JoystickButton aManip = new JoystickButton(joy2, 2);
	JoystickButton bManip = new JoystickButton(joy2, 3);
	JoystickButton yManip = new JoystickButton(joy2, 4);
	JoystickButton xManip = new JoystickButton(joy2, 1);
	ComboButton RLbumpers = new ComboButton(joy2, 5, 6);
	AxisButton manipLeftJoyY = new AxisButton(joy2, 1);
	DPadButton manipDPUp = new DPadButton(joy2, 0);
	DPadButton manipDRight = new DPadButton(joy2, 90);
	DPadButton manipDDown = new DPadButton(joy2, 180);
	DPadButton manipDLeft = new DPadButton(joy2, 270);

	int dPadVals = joy1.getPOV();

	public OI() {

		// righttrigger.whileHeld(new GrabNavX());
		leftJoyY.whileHeld(new ArcadeDrive());
		rightJoyX.whileHeld(new ArcadeDrive());
		leftJoyX.whileHeld(new ArcadeDrive());
		rightJoyY.whileHeld(new ArcadeDrive());
//		a.toggleWhenPressed(new VisionCube());
		b.toggleWhenPressed(new ArcadeSpeedMod());
		
//		DRight.whileHeld(new CurveOuttakeRight());
//		DLeft.whileHeld(new CurveOuttakeLeft());
		//DDown.toggleWhenPressed(new HaltIntake());


		// b.whenPressed(new GrabNavX());
		//
		//
		// aManip.whenPressed(new IntakeInward());
		// bManip.whenPressed(new HaltIntake());
		// yManip.whileHeld(new SmartShoot());
		// RLbumpers.whenPressed(new RampExtend());

		xManip.toggleWhenPressed(new IntakeInward());
		bManip.whileHeld(new WeakShot());
		yManip.whileHeld(new SmartShoot());
		RLbumpers.toggleWhenPressed(new AutoRampDeploy()); // FIX COMBO BUTTONS

		aManip.toggleWhenPressed(new ArmsToggle());
		manipLeftJoyY.whileHeld(new PivotManual());
		manipDPUp.toggleWhenPressed(new PosStore());
		manipDRight.toggleWhenPressed(new PosSwitch());
		manipDDown.toggleWhenPressed(new PosDown());
		manipDLeft.toggleWhenPressed(new PosSwitch());

		// bManip.whenPressed(new HaltIntake());

	}

	public double getLeftY() {
		return joy1.getRawAxis(1);
	}

	public double getRightY() {
		return joy1.getRawAxis(3);
	}

	public double getLeftX() {
		return joy1.getRawAxis(0);
	}

	public double getRightX() {
		return joy1.getRawAxis(2);
	}

	public double getManipLeftY() {
		return joy2.getRawAxis(1);
	}

	public int getPov() {
		return joy2.getPOV();
	}

}
