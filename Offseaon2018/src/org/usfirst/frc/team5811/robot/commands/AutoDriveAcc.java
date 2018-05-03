package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.Encoders;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveAcc extends Command {
	double duration, direction;

	// DriveTrain driveSUB = Robot.driveSUB;
	// Encoders encoders = Robot.encoders;
	public AutoDriveAcc(double durationInput, double direction) {

		this.duration = durationInput;
		this.direction = direction;

		// input duration length here, not sure how to do it yet.
		// Automatically assign values through group
		// initialize();
	}

	protected void intialize() {
		System.out.println("acc init");
		//// System.out.println("Initialized.");
		//// System.out.println("This be running");
		// execute();
	}

	protected void execute() {
		////// System.out.println("does this work?");
		System.out.println("how fast?");
		Robot.driveSUB.autoDriveAcc(this.duration, Math.abs(Robot.encoders.getLeftVal()), this.direction);
		Robot.ledsub.light_blue();

		//// System.out.println("Accelerating");
		// System.out.print("duration: ");
		//// System.out.println(duration);
		//// System.out.println(Math.abs(Robot.encoders.getRightVal()));
	}

	protected boolean isFinished() {
		// ////System.out.println("Calling isFinished");
		if (Math.abs(Robot.encoders.getLeftVal()) > this.duration) {
			return true;
		} else {
			return false;
		}
	}
}
