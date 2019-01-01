package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5811.robot.subsystems.NavX;

/**
 *
 */
public class LocalizationTest extends Command {

	double startTimestamp, currentTimestamp;
	double leftCurrent, rightCurrent;
	double leftPrevious, rightPrevious;
	double leftDelta, rightDelta;
	double startAngle, currentAngle;
	
    public LocalizationTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	System.out.println("setting requirements");
    	requires(Robot.driveSUB);
        requires(Robot.encoders);
        requires(Robot.navx);
        System.out.println("requirements set");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Initialization Start");
    	startTimestamp = Timer.getFPGATimestamp();
    	Robot.encoders.reset();
    	Robot.navx.reset();
    	leftCurrent = leftPrevious = leftDelta = 0.0; 
    	rightCurrent = rightPrevious = rightDelta = 0.0;
    	startAngle = Robot.navx.grabValues();
    	currentAngle = startAngle;
    	System.out.println("Initialization Complete");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentTimestamp = Timer.getFPGATimestamp() - startTimestamp;
    	leftCurrent = Robot.encoders.getLeftVal()/Robot.driveSUB.inchToPulse;
    	rightCurrent = Robot.encoders.getRightVal()/Robot.driveSUB.inchToPulse;
    	currentAngle = Robot.navx.grabValues();
    	
    	leftDelta = leftCurrent - leftPrevious; 
    	rightDelta = rightCurrent - rightPrevious;
    	
    	leftPrevious = leftCurrent;
    	rightPrevious = rightCurrent;
    	
    	System.out.println(currentTimestamp + " " + leftDelta + " " + rightDelta + " " + currentAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false; //(currentTimestamp > 15);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("Time Final: " + currentTimestamp + " Left Final " + leftCurrent + " Right Final: " + rightCurrent + " Angle Final: " + currentAngle);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
