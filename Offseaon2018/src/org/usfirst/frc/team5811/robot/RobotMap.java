package org.usfirst.frc.team5811.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class RobotMap {
	public static Victor motor0 = new Victor(0);
	public static Victor motor1 = new Victor(1);
	public static Victor motor2 = new Victor(2);
	public static Victor motor3 = new Victor(3);
	public static Victor motor4 = new Victor(4);
	public static Victor motor5 = new Victor(5);
	public static Victor motor6 = new Victor(6);
	public static Victor motor7 = new Victor(7);

	public static AnalogInput ai = new AnalogInput(0);
	public static DigitalInput di1 = new DigitalInput(9);
	public static DigitalOutput di2 = new DigitalOutput(8);

	public static Compressor cp = new Compressor(0);

	public static PowerDistributionPanel PDP = new PowerDistributionPanel();

	public static Potentiometer pivot = new AnalogPotentiometer(ai, 3600, -1360);//recal

	public static DoubleSolenoid intakeArmsRight = new DoubleSolenoid(2, 3);
	public static DoubleSolenoid intakeArmsLeft = new DoubleSolenoid(0, 1);

	public static I2C arduino = new I2C(I2C.Port.kMXP, 58);
	public static AHRS navx = new AHRS(I2C.Port.kMXP);

	public static UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	public static UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture();

	public static Encoder driveEncL = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	public static Encoder driveEncR = new Encoder(2, 3, false, Encoder.EncodingType.k4X); // false because the booleans
																							// have to be reverse
																							// direction
	public static Ultrasonic ultra = new Ultrasonic(di2, di1);
}
