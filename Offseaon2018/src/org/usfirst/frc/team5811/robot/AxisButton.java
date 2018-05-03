package org.usfirst.frc.team5811.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {
	Joystick joy1;
	int axis1;

	public AxisButton(Joystick joystick, int axis) {
		joy1 = joystick;
		axis1 = axis;
	}

	public boolean get() {
		return (joy1.getRawAxis(axis1) > 0.02 || joy1.getRawAxis(axis1) < -0.02);
		//
	}
}
