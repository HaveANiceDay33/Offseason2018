package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoNoGoTest extends CommandGroup {

	public GoNoGoTest() {
		String gameData;
		char firstLetter;
		int waitTime = 35;
		double driveTime1Right =  100.001; //originally 8500 pulses converted to 78.233
		double DD1R = -0.55;

		requires(Robot.driveSUB);
		requires(Robot.navx);
		requires(Robot.encoders);

		addSequential(new DriveAuto(driveTime1Right, DD1R), 5);
		addSequential(new FullStop(waitTime));
		addSequential(new SmartShoot(), 1);
		// Add pivot and launch commands here

	}
}
