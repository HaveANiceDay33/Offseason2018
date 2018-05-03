package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnFlat extends Command {
	double count;
	double finalAngSeg, direction, recip;

	// DriveTrain driveSUB = Robot.driveSUB;
	// NavX navX = Robot.navx;
	public AutoTurnFlat(double angleInput, double direction) {
		this.finalAngSeg = angleInput;
		this.direction = direction;

		// input finalAngSeg length here, not sure how to do it yet.
		// Automatically assign values through group
	}

	protected void intialize() {
		 System.out.println("Initialized.");

	}

	protected void execute() {
		Robot.driveSUB.autoTurnFlat(this.direction);
		Robot.ledsub.autoColor();
		//// System.out.println("Angle: "+ Math.abs(Robot.navx.grabValues()));

		//// System.out.println("Flat");
		// //System.out.print("count: ");
		// ////System.out.println(count);
		// //System.out.print("finalAngSeg: ");
		// ////System.out.println(finalAngSeg);
		//// System.out.println("ANGLE: "+NavX.grabValues());
	}

	protected boolean isFinished() {
		if (Math.abs(Robot.navx.grabValues()) > this.finalAngSeg) {
			return true;
		} else {
			return false;
		}
	}
}
