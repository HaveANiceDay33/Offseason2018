package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class HaltIntake extends Command {
	// Intake intake = Robot.intake;
	public HaltIntake() {
		// setInterruptible(true);
		// requires(Robot.intake);
		// this.start();
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.intake.haltLeft();
		Robot.intake.haltRight();

	}

	protected void interrupted() {
		end();
	}

	protected void end() {
		// Robot.intake.halt();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
}
