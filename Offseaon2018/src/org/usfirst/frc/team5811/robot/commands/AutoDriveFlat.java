package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.Encoders;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveFlat extends Command {
	double duration, direction;

	// DriveTrain driveSUB = Robot.driveSUB;
	// Encoders encoders = Robot.encoders;
	public AutoDriveFlat(double duration, double direction) {
		requires(Robot.driveSUB);
		this.duration = duration;
		this.direction = direction;
		// System.out.println("This be running");

		// input duration length here, not sure how to do it yet.
		// Automatically assign values through group
	}

	protected void intialize() {
		System.out.println("initialized");

	}

	protected void execute() {
		Robot.driveSUB.autoDriveFlat(this.direction);
		Robot.ledsub.autoColor();
		// System.out.println("This be not running");

		// System.out.println("Flat");
		// System.out.print("duration: ");
		//// System.out.println(duration);
		// System.out.println(Math.abs(Robot.encoders.getRightVal()));

	}

	protected boolean isFinished() {
		//// System.out.println("This be very not running");

		if (Math.abs(Robot.encoders.getLeftVal()) > this.duration) {
			return true;
		} else {
			return false;
		}
	}
}
