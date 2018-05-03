package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class FullStop extends Command {
	int count;
	int duration;

	// DriveTrain driveSUB = Robot.driveSUB;
	public FullStop(int duration) {
		this.duration = duration;
	}

	protected void intialize() {
		count = 0;
	}

	protected void execute() {
		Robot.driveSUB.fullStop();
		count++;
	}

	protected boolean isFinished() {
		if (count > duration) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
		System.out.println("Finish full stop");
	}
}
