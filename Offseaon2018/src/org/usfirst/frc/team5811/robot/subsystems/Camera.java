package org.usfirst.frc.team5811.robot.subsystems;

import org.usfirst.frc.team5811.robot.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Camera extends Subsystem {
	UsbCamera camera = RobotMap.camera;
	UsbCamera camera2 = RobotMap.camera2;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void camOn() {
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera2 = CameraServer.getInstance().startAutomaticCapture();
	}
}
