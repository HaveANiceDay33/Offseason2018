package org.usfirst.frc.team5811.robot.subsystems;

import org.usfirst.frc.team5811.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arms extends Subsystem {
	DoubleSolenoid armL = RobotMap.intakeArmsLeft;
	DoubleSolenoid armR = RobotMap.intakeArmsRight;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void close() {
		armL.set(DoubleSolenoid.Value.kForward);
		armR.set(DoubleSolenoid.Value.kForward);
		// System.out.print("Right motor power" + rightMotor.get()); //Left intake would
		// shut off when the arms are in... resolved by increasing the wait for a
		// initial current spike...
		// System.out.println(" Left motor power" + leftMotor.get());
	}

	public void open() {
		armL.set(DoubleSolenoid.Value.kReverse);
		armR.set(DoubleSolenoid.Value.kReverse);
	}
	public void openLeft() {
		armL.set(DoubleSolenoid.Value.kForward);
	}
	public void openRight() {
		armR.set(DoubleSolenoid.Value.kForward);
	}
}
