package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.ReaderFile;
import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class runProfile extends CommandGroup {

    public runProfile() {
		requires(Robot.driveSUB);
		requires(Robot.navx);//
		requires(Robot.encoders);
    	for(int steps = 0; steps < Robot.data.size(); steps++) {
    		addSequential(new TurnAuto(Double.parseDouble(((ReaderFile) Robot.data.get(steps)).angle), 0.9*Math.signum(Double.parseDouble(((ReaderFile) Robot.data.get(steps)).angle))));
    		addSequential(new FullStop(35));
    		addSequential(new FullAutoReset());
    		addSequential(new DriveAuto(Double.parseDouble(((ReaderFile) Robot.data.get(steps)).distance), 0.9*Math.signum(Double.parseDouble(((ReaderFile) Robot.data.get(steps)).distance))));
    		addSequential(new FullStop(35));
    		addSequential(new FullAutoReset());
    		System.out.println("I am on step: "+steps);
    		if(((ReaderFile) Robot.data.get(steps)).type.equals("END")) {
    			break;
    		}
    	}
    	addSequential(new FullStop(1));
    }
}
