package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


/**
 *
 */
public class VInterceptDetermination extends Command {
	
	double voltage = 0.0;
	double startTimestamp, currentTimestamp, previousTimestamp;
	double acceleration, velocity, position, previousVelocity = 0, previousPosition = 0;
	double dt;
	boolean upComplete = false, downComplete = false; 
	double upVoltage = 0.0, downVoltage = 0.0;
	
    public VInterceptDetermination() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSUB);
        requires(Robot.encoders);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTimestamp = Timer.getFPGATimestamp();
    	previousTimestamp = startTimestamp;
    	Robot.encoders.reset();
    	System.out.println("V intercept determination initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(!upComplete) {
    		if(velocity == 0.0) {
    			voltage += 0.01;
    		} else {
    			upComplete = true;
    			upVoltage = voltage;
    		}
    	} else {
    		if(velocity != 0.0) {
    			voltage -= 0.01;
    		} else {
    			downComplete = true;
    			downVoltage = voltage;
    		}
    	}
    	
    	Robot.driveSUB.voltageDrive(voltage, voltage);
    	
    	currentTimestamp = Timer.getFPGATimestamp() - startTimestamp;
    	dt = currentTimestamp - previousTimestamp;
    	
    	position = Robot.encoders.getLeftVal()/Robot.driveSUB.inchToPulse;
    	velocity = (position - previousPosition) / dt;
    	acceleration = (velocity - previousVelocity) / dt;
    	
    	previousPosition = position;
    	previousVelocity = velocity;
    	
    	System.out.println(voltage + " " + velocity);
    	   	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(downComplete) {
    		return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSUB.fullStop();
    	System.out.println("V Intercept Up Test = " + upVoltage);
    	System.out.println("V Intercept Down Test = " + downVoltage);
    	System.out.println("V Intercept Up-Down Test = " + (upVoltage+downVoltage)/2);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
