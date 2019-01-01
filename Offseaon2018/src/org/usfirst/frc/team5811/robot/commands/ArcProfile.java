package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import java.util.ArrayList;

/**
 *
 */
public class ArcProfile extends Command {

    public ArcProfile() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSUB);
        requires(Robot.encoders);
    }

    
//	ArrayList<Double> accelerations = new ArrayList();
//	ArrayList<Double> velocities = new ArrayList();
//	ArrayList<Double> positions = new ArrayList();
	ArrayList<Double> voltagesLeft = new ArrayList();
	ArrayList<Double> voltagesRight = new ArrayList();
	
    int index = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
//    	In real life, profile generation should run earlier and trajectory data should be stored somewhere else
//    	I am just putting it here for easier testing
    	double pathRadius = 0.4064; // m
    	double radialDistance = Math.PI*2; // m   //Target distance to travel
    	double velMax = 1.0; 	// m/s
    	double accMax = 1.0; // m/s^2
    	double angVelMax = 1.0; // rad/s
    	double angAccMax = 1.0; // rad/s^2
    	double dt = 0.02; // s
    	
    	
    	double time, pos, vel, acc, ang, angVel, angAcc;
    	time = pos = vel = acc = ang = angVel = angAcc = 0.0;
    	
    	// Unify constraints from user specified path following constants
    	if(angVelMax < velMax / pathRadius) { //Velocity 
    		velMax = angVelMax * pathRadius; 
    	}
    	System.out.println("Max Velocity: " + velMax);
    	if(angAccMax < accMax / pathRadius) { //Acceleration
    		accMax = angAccMax * pathRadius; 
    	}
    	System.out.println("Max Acceleration: " + accMax);
    	
    	
    	while(vel < velMax) { // solve entire acceleration portion, may not use all of these points
    		acc = accMax; 
    		vel = vel + acc*dt;
    		pos = pos + vel*dt;
    		
    		angAcc = angAccMax;
    		angVel = angVel + angAcc*dt;
    		ang = ang + angVel*dt;
    		
    		voltagesLeft.add(Robot.driveSUB.solveChassisDynamics(pathRadius, vel, acc, true));
    		voltagesRight.add(Robot.driveSUB.solveChassisDynamics(pathRadius, vel, acc, false));
    		System.out.println(time + " " + voltagesLeft.get(index) + " " + voltagesRight.get(index));
    		
    		index++;
    		time += dt;
    	}
    	
    	System.out.println("Max velocity reached");
    	
    	
    	//timestamp = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	for(int i = 0; i<index; i++) {
    		Robot.driveSUB.voltageDrive(voltagesLeft.get(i), voltagesRight.get(i));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
