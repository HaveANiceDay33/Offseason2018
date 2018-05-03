package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.Command;

public class PosStore extends Command {
	// Pivot pivot = Robot.pivot;
	double angle = 150;
	boolean complete = false;
	int state = 4;

	public PosStore() {
		requires(Robot.pivot);
		setInterruptible(true);
	}

	protected void execute() {
		this.complete = Robot.pivot.moveToBack(-Robot.pivot.getAngle());
	}

	protected boolean isFinished() {
		return this.complete;
	}

	protected void end() {
		Robot.pivot.setMotor(0);
		System.out.println("ENDING STORE");
	}

	protected void interrupted() {
	}
}
