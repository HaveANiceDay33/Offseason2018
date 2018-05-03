package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitchExchange extends CommandGroup {
	int waitTime = 1;
	double driveTime1Right = 27.6; //was 3000 pulses, now inches
	double driveDirection1Right = -0.45;
	double turnAngle1Right = 35;
	double turnDirection1Right = 0.8;
	double DT2Right = 68; //was 7000 pulses, now inches
	double DD2Right = -0.45;
	double TA2Right = 40;
	double TD2Right = -0.9;
	double DT3Right = 100; //was 3000 pulses, now inches
	double DD3Right = -0.45;
    public AutoRightSwitchExchange() {
		requires(Robot.driveSUB);
		requires(Robot.navx);
		requires(Robot.encoders);
		System.out.println("randomness");
		addSequential(new FullAutoReset());
		addSequential(new DriveAuto(driveTime1Right, driveDirection1Right));
		System.out.println("First move");
		addSequential(new FullStop(waitTime));

		addSequential(new TurnAuto(turnAngle1Right, turnDirection1Right));
		addSequential(new FullStop(waitTime));

		addSequential(new DriveAuto(DT2Right, DD2Right));
		addSequential(new FullStop(waitTime));

		addSequential(new TurnAuto(TA2Right, TD2Right));
		addSequential(new FullStop(waitTime));

		addSequential(new DriveAuto(DT3Right, DD3Right), 1.5);
		addSequential(new FullStop(waitTime));
		addSequential(new SmartShoot(), 1);
		
		//go back
		
		addSequential(new TurnAuto(0.01, 0), 0.05);
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(30, 0.9), 1.5);
		addSequential(new FullStop(waitTime));
		
		addSequential(new TurnAuto(40, 0.9));
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(DT2Right+12, 0.9));
		addSequential(new FullStop(waitTime));
		
		addSequential(new TurnAuto(110, 0.9));
		addSequential(new FullStop(waitTime));
		addSequential(new OpenArms());
		addSequential(new PosDown());
		addSequential(new DriveAuto(36, 0.5));
		addSequential(new FullStop(waitTime));
		addSequential(new ArmsClose());
		addSequential(new IntakeInward(), 2);
		
		addSequential(new DriveAuto(36, -0.7));
		addSequential(new FullStop(waitTime));
		
//		addSequential(new TurnAuto(160, 0.9));
//		addSequential(new FullStop(waitTime));
    }
}
