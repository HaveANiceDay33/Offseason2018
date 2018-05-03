package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnAcc extends Command {
	double currentAngle;
	double finalAngSeg, direction;
	// DriveTrain driveSUB = Robot.driveSUB;
	// NavX navX = Robot.navx;

	public AutoTurnAcc(double angleInput, double direction) {

		this.finalAngSeg = angleInput;
		this.direction = direction;
		this.currentAngle = 0;
		// input finalAngSeg length here, not sure how to do it yet.
		// Automatically assign values through group
	}

	protected void intialize() {
		 System.out.println("Initialized.");

	}

	protected void execute() {
		// currentAngle = NavX.grabValues();
		Robot.driveSUB.autoTurnAcc(this.finalAngSeg, Math.abs(Robot.navx.grabValues()), this.direction);
		Robot.ledsub.light_blue();

		// System.out.println("Angle: "+ Math.abs(Robot.navx.grabValues()));
		//// System.out.print("count: ");
		////// System.out.println(count);
		//// System.out.print("finalAngSeg: ");
		////// System.out.println(finalAngSeg);
		//// System.out.println("ANGLE: "+Math.abs(NavX.grabValues()));
	}

	protected boolean isFinished() {
		// ////System.out.println("Calling isFinished");
		if (Math.abs(Robot.navx.grabValues()) > this.finalAngSeg) {
			return true;
		} else {

			return false;
		}
	}
}
