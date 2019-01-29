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
    	double pathRadius = -1.5; //6.1516;//0.4064; // m
    	double radialDistance = Math.PI*2; // m   //Target distance to travel
    	double velMax = 2.0; 	// m/s
    	double accMax = 2.0; // m/s^2
    	double angVelMax = 2.0; // rad/s
    	double angAccMax = 2.0; // rad/s^2
    	double dt = 0.02; // s
    	
    	
    	double time, pos, vel, acc, ang, angVel, angAcc;
    	time = pos = vel = acc = ang = angVel = angAcc = 0.0;
    	
    	System.out.println("Pre simulation update. Time: " + time);
    	// Unify constraints from user specified path following constants
    	if(angVelMax < velMax / pathRadius) { //Velocity 
    		velMax = angVelMax * pathRadius; 
    		System.out.println("Maximum Velocity adjusted to: " + velMax);
    	}
    	System.out.println("Max Velocity: " + velMax);
    	if(angAccMax < accMax / pathRadius) { //Acceleration
    		accMax = angAccMax * pathRadius; 
    		System.out.println("Maximum Acceleration adjusted to: " + accMax);
    	}
    	System.out.println("Max Acceleration: " + accMax);
    	
    	System.out.println("Beginning profile generation");
    	while(vel < velMax) { // solve entire acceleration portion, may not use all of these points
    		acc = accMax; 
    		vel = vel + acc*dt;
    		pos = pos + vel*dt;
    		
    		angAcc = angAccMax*Math.signum(pathRadius); //accounts for direction of curvature
    		angVel = angVel + angAcc*dt;
    		ang = ang + angVel*dt;
    		
//    		voltagesLeft.add(Robot.driveSUB.solveChassisDynamics(pathRadius, vel, acc, true));
//    		voltagesRight.add(Robot.driveSUB.solveChassisDynamics(pathRadius, vel, acc, false));
    		voltagesLeft.add(Robot.driveSUB.solveScrubbyChassisDynamics(pathRadius, vel, acc, angVel, true));
    		voltagesRight.add(Robot.driveSUB.solveScrubbyChassisDynamics(pathRadius, vel, acc, angVel, false));
    		System.out.println(time + " " + vel + " " + angVel + " " + acc + " " + angAcc + " " + voltagesLeft.get(index) + " " + voltagesRight.get(index));
    		
    		index++;
    		time += dt;
    	}
    	System.out.println("Max velocity reached");
    	
    	while(time<2.5) {
    		voltagesLeft.add(Robot.driveSUB.solveScrubbyChassisDynamics(pathRadius, vel, 0, angVel, true));
    		voltagesRight.add(Robot.driveSUB.solveScrubbyChassisDynamics(pathRadius, vel, 0, angVel, false));
    		System.out.println(time + " " + vel + " " + angVel + " " + acc + " " + angAcc + " " + voltagesLeft.get(index) + " " + voltagesRight.get(index));
    		
    		index++;
    		time += dt;
    	}
    	
    	
    	
    	
    	//timestamp = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    int i = 0;
    boolean done = false;
    protected void execute() {
    	if(i<index) {
    		Robot.driveSUB.voltageDrive(voltagesLeft.get(i), voltagesRight.get(i));
    		i++;
    	}else {
    		Robot.driveSUB.fullStop();
    		done = true;
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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