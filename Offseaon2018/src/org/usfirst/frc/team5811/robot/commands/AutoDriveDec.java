package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.Encoders;
import org.usfirst.frc.team5811.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveDec extends Command {
	double duration, direction;
	double currentLeft, currentRight;


	// DriveTrain driveSUB = Robot.driveSUB;
	// Encoders encoders = Robot.encoders;
	public AutoDriveDec(double duration, double direction) {
		this.duration = duration;
		this.direction = direction;
		// input duration length here, not sure how to do it yet.
		// Automatically assign values through group
	}

	protected void intialize() {
		// count = this.duration;
		System.out.println("dec init");


	}

	protected void execute() {
//		currentLeft = Robot.driveSUB.monitorCurrentDriveLeft();
//		currentRight = Robot.driveSUB.monitorCurrentDriveRight();


		Robot.driveSUB.autoDriveDec(duration, Math.abs(Robot.encoders.getLeftVal()), direction);
		Robot.ledsub.orange();
		// System.out.println("Deccelerating");
		// System.out.print("duration: ");
		//// System.out.println(duration);
		// System.out.println(Math.abs(Robot.encoders.getRightVal()));

	}

	protected boolean isFinished() {
		if (Math.abs(Robot.encoders.getLeftVal()) > this.duration) {
			Robot.navx.reset(); // commented these out to test the autoAngleCorrect method
			Robot.encoders.reset();
			return true;
		} else {
			return false;
		}
	}
}
