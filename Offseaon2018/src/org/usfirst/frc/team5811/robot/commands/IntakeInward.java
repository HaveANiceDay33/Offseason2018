package org.usfirst.frc.team5811.robot.commands;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.RobotMap;
import org.usfirst.frc.team5811.robot.subsystems.Intake;
import org.usfirst.frc.team5811.robot.subsystems.LEDS;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeInward extends Command {
	int cyclesOn, cyclesSpike;
	double currentLeft, currentRight;
	Command command;

	public IntakeInward() {
		requires(Robot.intake);
		setInterruptible(true);

	}

	@Override
	protected void initialize() {
		cyclesOn = 0;
		cyclesSpike = 0;
		Robot.intake.intakeLeftIn();
		Robot.intake.intakeRightIn();
	}

	@Override
	protected void execute() {
		currentLeft = Robot.driveSUB.monitorCurrentIntakeLeft(); // update current readings for intake motors
		currentRight = Robot.driveSUB.monitorCurrentIntakeRight();

		Robot.ledsub.colorInward(); // LEDs rainbow while intaking
		System.out.println("cycles " + cyclesOn);
		cyclesOn++; // increment the cycles that the intake has been running
		
		System.out.println(currentLeft + " " + currentRight);
		
		if (cyclesOn > Intake.intSpikeWait) { // if the intake has been on long enough to avoid the initial spike
			if (currentLeft > Intake.currentThreshold || currentRight > Intake.currentThreshold) { // if one of the
																									// motors is drawing
																									// too much current
				cyclesSpike++;
				System.out.println("current too high" + cyclesSpike);
				if(cyclesSpike > Intake.timeout-1) {
					for(int i = 0; i < 200; i++) {
						Robot.ledsub.flash();
					}
				}
			} else {
				Robot.intake.intakeLeftIn();
				Robot.intake.intakeRightIn();
			}

		}
	}

	protected void end() {
		
		Robot.intake.haltLeft();
		Robot.intake.haltRight();
	
		
		Robot.ledsub.off();

		// command = new PosExchange(); //not working yet too annoying when we didn't
		// have a cube and the intake raised itself up too high.
		// command.start(); //A sensor to detect possession of a cube could solve this

	}

	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {

		return (cyclesSpike > Intake.timeout);
	
	}

}
