package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnAuto extends CommandGroup {
	double accelAngle, flatAngle, decelAngle, direction;
	double angle;

	double accelFactor, decelFactor, flatFactor, totalAngle;
	double accelAngleTrue, flatAngleTrue, decelAngleTrue;

	// NavX navX = Robot.navx;
	public TurnAuto(double angle, double direction) {
		this.accelFactor = 0.20; // Split the angle of the turn up into proportions of accel, flat, decel
		this.flatFactor = 0.3;
		this.decelFactor = 0.50;
		this.direction = direction;

		this.totalAngle = angle;
		//
		this.accelAngle = this.totalAngle * this.accelFactor;
		this.flatAngle = this.totalAngle * this.flatFactor;
		this.decelAngle = this.totalAngle * this.decelFactor;

		this.accelAngleTrue = this.accelAngle;
		this.flatAngleTrue = this.accelAngle + this.flatAngle;
		this.decelAngleTrue = this.accelAngle + this.flatAngle + this.decelAngle; // Isn't this just "angle"?

		Robot.driveSUB.fullReset();
//		addSequential(new ResetEverything());
//		addSequential(new SetCurrentAngle());

		addSequential(new AutoTurnAcc(this.accelAngleTrue, this.direction));
		// NavX.reset();
		addSequential(new AutoTurnFlat(this.flatAngleTrue, this.direction));
		// NavX.reset();
		addSequential(new AutoTurnDec(this.decelAngleTrue, this.direction));
		addSequential(new FullStop(5));
//		addSequential(new ResetEverything());
//		Robot.driveSUB.fullReset();
		// addSequential (new AutoAngleCorrect(this.decelAngleTrue));
		// NavX.reset();
		//// System.out.println("TURN AUTO STOP");

	}

}
