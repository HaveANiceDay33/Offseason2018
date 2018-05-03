package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.Ramp;

import edu.wpi.first.wpilibj.command.Command;

public class RampExtend extends Command {
	// Ramp ramp = Robot.ramp;
	public RampExtend() {
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.ramp.extend();
	}

	protected void interrupted() {
		end();
	}

	protected void end() {
		Robot.ramp.retract();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
