package org.usfirst.frc.team5811.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRampDeploy extends CommandGroup {

	public AutoRampDeploy() {
		addSequential(new ArmsClose());
		addSequential(new PosStore());
		addSequential(new RampRetract());
		addSequential(new OpenArms());
		addSequential(new ArmsClose());
		addSequential(new PosSwitch());
	}
}
