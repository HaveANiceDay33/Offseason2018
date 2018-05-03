package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.OI;
import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.RobotMap;
import org.usfirst.frc.team5811.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	// DriveTrain driveSUB = Robot.driveSUB;
	// Joysticks joysticks = Robot.joysticks;
	// OI oi = Robot.oi;
	public ArcadeDrive() {
		requires(Robot.driveSUB);
		setInterruptible(false);
	}
	
	
	protected void execute() {

		Robot.driveSUB.arcadeDrive(Robot.oi.getRightX(), Robot.oi.getLeftY());
		
		//// System.out.println("Throttle " + Robot.oi.getLeftY());
		//// System.out.println("Turn " + Robot.oi.getRightX());

	}

	protected boolean isFinished() {

		return false;
	}
	
	protected void end() {
		Robot.driveSUB.motorReset();
	}
	
	protected void interrupted() {
		end();
	}

}
