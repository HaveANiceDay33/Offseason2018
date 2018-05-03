package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnDec extends Command {
	double count;
	double finalAngSeg, direction;
	double recip;
	// double rotationPos = 0;
	// float error;
	// DriveTrain driveSUB = Robot.driveSUB;
	// NavX navX = Robot.navx;

	public AutoTurnDec(double angleInput, double direction) {
		this.finalAngSeg = angleInput;
		this.direction = direction;

		// count = this.finalAngSeg;

		// input duration length here, not sure how to do it yet.
		// Automatically assign values through group
	}

	protected void intialize() {
		 System.out.println("Initialized.");

	}

	protected void execute() {
		Robot.driveSUB.autoTurnDec(this.finalAngSeg, Math.abs(Robot.navx.grabValues()), direction);
		Robot.ledsub.orange();
		//// System.out.println("Angle: "+ Math.abs(Robot.navx.grabValues()));
		// System.out.println("decellerating");

		// error = (float) (rotationPos - NavX.grabValues());
		// count -=1;
		//// System.out.println("Deccelerating");
		// //System.out.print("count: ");
		// ////System.out.println(count);
		// //System.out.print("duration: ");
		// ////System.out.println(finalAngSeg);
		//// System.out.println("ANGLE: "+Math.abs(NavX.grabValues()));
	}

	protected boolean isFinished() {
		if (Math.abs(Robot.navx.grabValues()) > this.finalAngSeg) {
			Robot.navx.reset(); // commented these out to test the autoAngleCorrect method
			Robot.encoders.reset();
			return true;
		} else {
			return false;
		}
	}
}
