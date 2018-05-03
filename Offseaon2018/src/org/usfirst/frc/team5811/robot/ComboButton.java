package org.usfirst.frc.team5811.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ComboButton extends Button {
	Joystick joy1;
	JoystickButton button1Raw;
	JoystickButton button2Raw;
	int button1;
	int button2;

	public ComboButton(Joystick joystick, int button1ID, int button2ID) {
		joy1 = joystick;
		button1 = button1ID;
		button2 = button2ID;
		button1Raw = new JoystickButton(joy1, button1);
		button2Raw = new JoystickButton(joy1, button2);
	}

	public boolean get() {
		if (button1Raw.get() == true && button2Raw.get() == true) {
			return true;
		} else {
			return false;
		}

	}

}
