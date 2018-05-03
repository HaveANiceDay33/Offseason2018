package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSwitchExchange extends CommandGroup {
	int waitTime = 1;
	double driveTime1Left = 27.6; //was 3000 pulses, now inches
	double driveDirection1Left = -0.45;
	double turnAngle1Left = 45;
	double turnDirection1Left = -0.9;
	double DT2Left = 74; //was 7500 pulses, now inches
	double DD2Left = -0.45;
	double TA2Left = 35;
	double TD2Left = 0.9;
	double DT3Left = 100; //was 3000 pulses, now inches
	double DD3Left = -0.45;
    public AutoLeftSwitchExchange() {
		requires(Robot.driveSUB);
		requires(Robot.navx);
		requires(Robot.encoders);
		System.out.println("randomness");
		addSequential(new FullAutoReset());
		addSequential(new DriveAuto(driveTime1Left, driveDirection1Left));
		System.out.println("First move");
		addSequential(new FullStop(waitTime));

		addSequential(new TurnAuto(turnAngle1Left, turnDirection1Left));
		addSequential(new FullStop(waitTime));

		addSequential(new DriveAuto(DT2Left, DD2Left));
		addSequential(new FullStop(waitTime));

		addSequential(new TurnAuto(TA2Left, TD2Left));
		addSequential(new FullStop(waitTime));

		addSequential(new DriveAuto(DT3Left, DD3Left), 1.5);
		addSequential(new FullStop(waitTime));
		addSequential(new SmartShoot(), 1);
		//go back
		
		addSequential(new TurnAuto(0.01, 0), 0.05);
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(30, 0.9), 1.5);
		addSequential(new FullStop(waitTime));
		
		addSequential(new TurnAuto(40, -0.9));
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(DT2Left+12, .9));
		addSequential(new FullStop(waitTime));
		
		addSequential(new TurnAuto(110, -0.9));
		addSequential(new FullStop(waitTime));
		addSequential(new OpenArms());
		addSequential(new PosDown());
		addSequential(new DriveAuto(36, 0.5));
		addSequential(new FullStop(waitTime));
		
		addSequential(new ArmsClose());
		addSequential(new IntakeInward(), 2);
		
		addSequential(new TurnAuto(0.01, 0), 0.05);
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(36, -0.7));
		addSequential(new FullStop(waitTime));
		
//		addSequential(new TurnAuto(-160, 0.9));
//		addSequential(new FullStop(waitTime));
    }
}
