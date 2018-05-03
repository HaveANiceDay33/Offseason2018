package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OutsideSwitchrightAutoExtended extends CommandGroup {

	public OutsideSwitchrightAutoExtended() {
		int waitTime = 1;
		double driveTime1Right = 150;
		double driveTime2Right = 60;
		double driveTime3Right = 30;
		double driveTime4Right = 80;
		double driveTime5Right = 8; //small inching
		double driveTime6Right = 30;
		double DD1R = -0.7;
		double DD2R = -0.6;
		double DD3R = 0.6;
		double DD4R = -0.7;
		double DD5R = 0.7;
		double DD6R = 0.7;
		double TA1 = 85;
		double TD1 = -0.9;
		double TA2 = 45;
		double TD2 = 0.9;
		double TA3 = 40;
		double TD3 = 0.9;

		requires(Robot.driveSUB);
		requires(Robot.navx);
		requires(Robot.encoders);

		addSequential(new DriveAuto(driveTime1Right, DD1R), 7);
		addSequential(new FullStop(waitTime));

		addSequential(new TurnAuto(TA1, TD1), 3);
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(driveTime2Right, DD2R), 1);
		addSequential(new FullStop(waitTime));

		addSequential(new SmartShoot(), 1);
		addSequential(new FullStop(waitTime));
		
		addSequential(new TurnAuto(0.01, 0), 0.05);
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(driveTime3Right, DD3R),1);
		addSequential(new FullStop(waitTime));

		addSequential(new TurnAuto(TA2, TD2), 3);
		addSequential(new FullStop(waitTime));

		addSequential(new DriveAuto(driveTime4Right, DD4R), 7);
		addSequential(new FullStop(waitTime));
		
		
		addSequential(new OpenArms());
		addSequential(new PosDown());
		
		addSequential(new TurnAuto(TA3, TD3), 3);
		addSequential(new FullStop(waitTime));
		
		addSequential(new DriveAuto(driveTime5Right, DD5R),2);
		addParallel(new IntakeInward());
		
		addSequential(new FullStop(waitTime));
		addSequential(new ArmsClose());
		addParallel(new IntakeInward(),2);
		
		addSequential(new PosSwitch());
		addSequential(new DriveAuto(driveTime6Right, DD6R), 1);
		addSequential(new FullStop(waitTime));
		addSequential(new SmartShoot(), 1);
		addSequential(new PosStore());
	}
}
