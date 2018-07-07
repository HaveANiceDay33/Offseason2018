package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainExperiment extends Command {

	double testVoltage = 3.0;
	double testDuration = 3.0;
	double startTimestamp, currentTimestamp, previousTimestamp;
	double acceleration, velocity, position, previousVelocity = 0, previousPosition = 0;
	double twoPreviousVelocity = 0, twoPreviousTimestamp = 0;
	double dt, dt2;
	
    public DrivetrainExperiment() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSUB);
        requires(Robot.encoders);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTimestamp = Timer.getFPGATimestamp();
    	previousTimestamp = startTimestamp;
    	Robot.encoders.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSUB.voltageDrive(testVoltage, testVoltage);
    	currentTimestamp = Timer.getFPGATimestamp() - startTimestamp;
    	dt = currentTimestamp - previousTimestamp;
    	dt2 = currentTimestamp - twoPreviousTimestamp;
    	
    	position = Robot.encoders.getLeftVal()/Robot.driveSUB.inchToPulse;
    	velocity = (position - previousPosition) / dt;
    	acceleration = (velocity - previousVelocity) / dt;
    	
    	
    	twoPreviousTimestamp = previousTimestamp;
    	twoPreviousVelocity = previousVelocity;
    	
    	previousTimestamp = currentTimestamp;
    	previousPosition = position;
    	previousVelocity = velocity;
    	
    	System.out.println(currentTimestamp + " " + acceleration + " " + velocity + " " + position);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(currentTimestamp > testDuration) {
        	return true;
        }else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSUB.fullStop();
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
