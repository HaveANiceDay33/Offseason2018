package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.Command;

public class PosSwitch extends Command {
	// Pivot pivot = Robot.pivot;
	double angle = 80;
	boolean complete = false;
	int state = 2;

	public PosSwitch() {
		requires(Robot.pivot);
	}

	protected void execute() {
		this.complete = Robot.pivot.moveToSwitch(-Robot.pivot.getAngle());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.pivot.setMotor(0);
		System.out.println("ENDING SWITCH");
	}

	protected void interrupted() {
	}

}
