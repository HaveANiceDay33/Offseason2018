package org.usfirst.frc.team5811.robot.subsystems;

import org.usfirst.frc.team5811.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Encoders extends Subsystem {
	public Encoder driveR = RobotMap.driveEncR;
	public Encoder driveL = RobotMap.driveEncL;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public double getRightVal() {
		//// System.out.println("Encoder val: "+driveR.get());
		return driveR.get();
	}

	public double getLeftVal() {
		////// System.out.println("right fright");
		return driveL.get();
	}

	public void reset() {
		driveR.reset();
		driveL.reset();
	}
}
