package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionAuto extends CommandGroup {

    public VisionAuto() {
    	requires(Robot.driveSUB);
    	requires(Robot.navx);
    	requires(Robot.encoders);
    	
    	addSequential(new VisionCube(), 15);
    }
}
