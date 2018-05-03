package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5811.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAuto extends CommandGroup {
	double accelDistance, flatDistance, decelDistance, direction;
	double accelFactor, decelFactor, flatFactor, totalPulses;
	double accelDistanceTrue, flatDistanceTrue, decelDistanceTrue;

	public DriveAuto(double totalInches, double direction) {
		// System.out.println("goes all the way here");

		this.accelFactor = 0.1;
		this.flatFactor = 0.6;
		this.decelFactor = 0.3;
		this.totalPulses = totalInches*Robot.driveSUB.inchToPulse;// total pulses is equal to an amount of inches multiplied by a constant

		this.accelDistance = this.totalPulses * this.accelFactor;
		this.flatDistance = this.totalPulses * this.flatFactor;
		this.decelDistance = this.totalPulses * this.decelFactor;

		this.accelDistanceTrue = this.accelDistance;
		this.flatDistanceTrue = this.accelDistance + this.flatDistance;
		this.decelDistanceTrue = this.accelDistance + this.flatDistance + this.decelDistance;
		
		//System.out.println(this.accelDistance + this.flatDistanceTrue + this.decelDistanceTrue);
		
		this.direction = direction;
		Robot.driveSUB.fullReset();
		
//		addSequential(new ResetEverything());
		addSequential(new SetCurrentAngle());
		addSequential(new AutoDriveAcc(this.accelDistanceTrue, this.direction));
		addSequential(new AutoDriveFlat(this.flatDistanceTrue, this.direction));
		addSequential(new AutoDriveDec(this.decelDistanceTrue, this.direction));
		addSequential(new FullStop(5));
		System.out.println("End drive auto");
//		Robot.driveSUB.fullReset();
//		addSequential(new ResetEverything());
		
		// resetting encoder hardware
		// reseting angle holding variables

	}

}
