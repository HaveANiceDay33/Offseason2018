package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TESTAUTO extends CommandGroup {

    public TESTAUTO() {

		requires(Robot.driveSUB);
		requires(Robot.navx);
		requires(Robot.encoders);
    	
		
		addSequential(new SmartShoot(), 2);
		
    	addSequential(new DriveAuto(30, 0.6));
    	addSequential(new FullStop(1));
    	
    	addSequential(new TurnAuto(90, -0.9));
    	addSequential(new FullStop(1));
    	
    	addSequential(new DriveAuto(30, 0.6));
    	addSequential(new FullStop(1));
    	
    	addSequential(new DriveAuto(30, -0.6));
    	addSequential(new FullStop(1));
    	
    	addSequential(new TurnAuto(90, 0.9));
    	addSequential(new FullStop(1));
    	
    	addSequential(new DriveAuto(30, -0.6));
    	addSequential(new FullStop(1));
    	
	    	
//    	addSequential(new DriveAuto(30, 0.6));
//    	addSequential(new FullStop(1));
		

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
