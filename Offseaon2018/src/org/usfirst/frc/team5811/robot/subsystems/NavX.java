package org.usfirst.frc.team5811.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.RobotMap;

public class NavX extends Subsystem {

	public static AHRS navX = RobotMap.navx;

	public NavX() {
		// try {
		// navX = new AHRS(I2C.Port.kMXP);
		// ////System.out.println("NavX instantiated");
		// //ahrs = new AHRS(I2C.Port.kMXP); //WE WILL NEED I2C IN THE FUTURE.
		// // RIGHT NOW WE WILL STICK WITH USB
		// } catch (RuntimeException ex) {
		// ////System.out.println("NavX instantiation error");
		// DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(),
		// true);
		// }
	}

	@Override
	protected void initDefaultCommand() {

	}

	public float grabValues() {
		return (float) navX.getAngle();
		////// System.out.println(angle);
	}

	public void reset() {
		navX.reset();
	}

}
