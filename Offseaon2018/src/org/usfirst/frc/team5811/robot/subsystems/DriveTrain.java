package org.usfirst.frc.team5811.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team5811.robot.Robot;
import org.usfirst.frc.team5811.robot.RobotMap;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Victor leftMotor1 = RobotMap.motor2;
	Victor leftMotor2 = RobotMap.motor3;
	Victor rightMotor1 = RobotMap.motor6;
	Victor rightMotor2 = RobotMap.motor7;
	PowerDistributionPanel pdp = RobotMap.PDP;

	public double autoShootPower = 0.6;

	// int angleOfTurn;

	NetworkTable table;

	public DriveTrain() {
		table = NetworkTable.getTable("SmartDashboard");
	}

	Compressor cp = RobotMap.cp;

	// NavX navX = Robot.navx;

	float rotationPos = 0;

	double kmagicnumberp = 20; // first year programmer is using correct variables instead of "magic numbers"
	double ki = .002; // integral gain for AutoAngleCorrect
	double kd = 0; // derivative gain for straight driving, currently not implemented
	double pTerm, dTerm;
	public double currentAngle;
	double previousAngle;
	double angleError; // used in autoAngleCorrect
	double sumOfErrors; // used in autoAngleCorrect
	double rateOfChange; // used in autoAngleCorrect
	double angleTolerance = 1; // used in autoAngleCorrect
	double rotationRateTolerance = 1; // used in autoAngleCorrect

	double arcadeSpeedModifier = 1;
	double arcadeTurnModifier = 1;

	double minVisionVal = 30;
	double maxVisionVal = 70;
	double visionTurnMult = 0.4;
	double turnMax = 0.9;
	
	public double inchToPulse = 108.6497744841; //2048 pulses for every six pi (one inch is 108... pulses)
	
	public double currentThreshold = 20;
	public int intSpikeWait = 50;

	public void fullReset() { // reseting angle storing variables
		previousAngle = 0;
		currentAngle = 0;
		angleError = 0;
		sumOfErrors = 0;
		rateOfChange = 0;
		
	}
	
	public void motorReset() {
		leftMotor1.set(0);
		leftMotor2.set(0);
		rightMotor1.set(0);
		rightMotor2.set(0);
	}

	public boolean checkCP() {
		// cp.setClosedLoopControl(true);
		return cp.enabled();
	}

	public void setCP() {
		cp.setClosedLoopControl(true);

		// cp.clearAllPCMStickyFaults();//
	}

	public void initDefaultCommand() {

	}

	public double getRight() {
		return rightMotor1.get();
	}

	public double getLeft() {
		return leftMotor1.get();
	}

	public void changeSpeed(double speed, double turn) {
		arcadeSpeedModifier = speed;
		arcadeTurnModifier = turn;
	}

	public void arcadeDrive(double turn, double throttle) {
		if (throttle >= .02) { // forward?
			turn = -turn;
		}
		if (throttle < .02) { // reverse?
			turn = turn;
		}
		if(arcadeSpeedModifier > 0.8) {
			Robot.ledsub.blue();
		}
		if(arcadeSpeedModifier < 0.8 ) {
			Robot.ledsub.color();
		}
		leftMotor1.set(-((arcadeSpeedModifier * throttle) - (arcadeTurnModifier * turnMax * turn)));
		leftMotor2.set(-((arcadeSpeedModifier * throttle) - (arcadeTurnModifier * turnMax * turn)));
		rightMotor1.set((arcadeSpeedModifier * throttle) + (arcadeTurnModifier * turnMax * turn));
		rightMotor2.set((arcadeSpeedModifier * throttle) + (arcadeTurnModifier * turnMax * turn));

		//// System.out.println(pdp.getCurrent(0)+ " "+pdp.getCurrent(1)+ "
		//// "+pdp.getCurrent(2)+ " "+pdp.getCurrent(3));

	}

	public double setCurrentAngle() {
		previousAngle = currentAngle; // setting previous angle
		currentAngle = Robot.navx.grabValues(); // getting current angle
		return currentAngle;
		// System.out.println("SET CURRENT ANGLE: " + currentAngle);
		//// System.out.println("desired angle: " + currentAngle);
	}

	public double errorCorrect(double desiredAng) {
		double error = Robot.navx.grabValues() - desiredAng;
		// System.out.print(Math.abs(error) + "\t ");
		pTerm = error / kmagicnumberp;
		dTerm = kd * (previousAngle - currentAngle);
		// System.out.println("motor delta: " + motorDelta);
		return pTerm + dTerm;

	}

	public void autoDriveAcc(double durationAccel, double encoderDistance, double direction) {
		double motorCorrect = errorCorrect(currentAngle);

		// System.out.println("Motor sped: "+((direction*(i/(durationAccel)+0.2)*0.5f) -
		// motorCorrect));
		// System.out.println(((direction*(i/(durationAccel)+0.3)) - motorCorrect) +"\t
		// " +((direction*(i/(durationAccel)+0.3)) - motorCorrect)+
		// "\t" + ((-direction*(i/(durationAccel)+0.3)) - motorCorrect)+"\t " +
		// ((-direction*(i/(durationAccel)+0.3)) - motorCorrect));
		leftMotor1.set((direction * (encoderDistance / (durationAccel) + 0.3)) - motorCorrect);
		leftMotor2.set((direction * (encoderDistance / (durationAccel) + 0.3)) - motorCorrect);
		rightMotor1.set((-direction * (encoderDistance / (durationAccel) + 0.3)) - motorCorrect);
		rightMotor2.set((-direction * (encoderDistance / (durationAccel) + 0.3)) - motorCorrect);

	}

	public void autoDriveDec(double durationDecel, double encoderDistance, double direction) {
		double motorCorrect = errorCorrect(currentAngle);
		// kp -= .1;
		// System.out.println((direction*(1-(i/(durationDecel)+0.2)*0.5f) -
		// motorCorrect) +"\t " +(direction*(1-(i/(durationDecel)+0.2)*0.5f) -
		// motorCorrect)+
		// "\t" + (-direction*(1-(i/(durationDecel)+0.2)*0.5f) - motorCorrect)+"\t " +
		// (-direction*(1-(i/(durationDecel)+0.2)*0.5f) - motorCorrect));
		// System.out.println("Motor sped:
		// "+(direction*(1-(i/(durationDecel*0.5))*0.5f)-motorCorrect));
		leftMotor1.set(direction * (1 - (encoderDistance / (durationDecel) + 0.2) * 0.5f) - motorCorrect);
		leftMotor2.set(direction * (1 - (encoderDistance / (durationDecel) + 0.2) * 0.5f) - motorCorrect);
		rightMotor1.set(-direction * (1 - (encoderDistance / (durationDecel) + 0.2) * 0.5f) - motorCorrect);
		rightMotor2.set(-direction * (1 - (encoderDistance / (durationDecel) + 0.2) * 0.5f) - motorCorrect);
	}
	public void justFreakingDrive(double speedDir) {
		leftMotor1.set(speedDir);
		leftMotor2.set(speedDir);
		rightMotor1.set(-speedDir);
		rightMotor2.set(-speedDir);
	}
	public void autoDriveFlat(double direction) {
		// kp += .1;
		double motorCorrect = errorCorrect(currentAngle);
		// System.out.println(((direction*1) - motorCorrect) +"\t " +((direction*1) -
		// motorCorrect)+
		// "\t" + ((direction*-1) - motorCorrect)+"\t " + ((direction*-1) -
		// motorCorrect));
		// System.out.println("Motor sped: "+((direction*1)-motorCorrect));
		leftMotor1.set((direction * 1) - motorCorrect);
		leftMotor2.set((direction * 1) - motorCorrect);
		rightMotor1.set((direction * -1) - motorCorrect);
		rightMotor2.set((direction * -1) - motorCorrect);
	}

	public void fullStop() {
		//// System.out.println(0);
		leftMotor1.set(0);
		leftMotor2.set(0);
		rightMotor1.set(0);
		rightMotor2.set(0);

	}

	public void autoTurnAcc(double finalAngle, double currentAngle, double direction) {

		//// System.out.println(direction*(currentAngle/finalAngle + 0.2));
		// motor0.set(direction*(i/(durationAccel*0.5))*0.5f);
		// motor1.set(direction*(i/(durationAccel*0.5))*0.5f);
		// leftMotor1.set(direction*(i/(durationAccel*0.5))*0.5f);
		// leftMotor2.set(direction*(i/(durationAccel*0.5))*0.5f);
		leftMotor1.set(direction * (currentAngle / finalAngle + 0.3) * 0.65);
		leftMotor2.set(direction * (currentAngle / finalAngle + 0.3) * 0.65);
		rightMotor1.set(direction * (currentAngle / finalAngle + 0.3) * 0.65);
		rightMotor2.set(direction * (currentAngle / finalAngle + 0.3) * 0.65);

	}

	public void autoTurnDec(double finalAngle, double currentAngle, double direction) {
		//// System.out.println(direction*(1-(currentAngle/finalAngle + 0.2)));
		// motor0.set(direction*(i/(durationDecel*0.5))*0.5f);
		// motor1.set(direction*(i/(durationDecel*0.5))*0.5f);
		// leftMotor1.set(direction*(i/(durationDecel*0.5))*0.5f);
		// leftMotor2.set(direction*(i/(durationDecel*0.5))*0.5f);
		leftMotor1.set(direction * (1 - (currentAngle / finalAngle) + 0.2) * 0.6);
		leftMotor2.set(direction * (1 - (currentAngle / finalAngle) + 0.2) * 0.6);
		rightMotor1.set(direction * (1 - (currentAngle / finalAngle) + 0.2) * 0.6);
		rightMotor2.set(direction * (1 - (currentAngle / finalAngle) + 0.2) * 0.6);
	}

	public boolean angleCorrect(double finalAngle, double currentAngle) {
		angleError = finalAngle - currentAngle;
		sumOfErrors += angleError;
		rateOfChange = currentAngle - previousAngle;

		if (sumOfErrors > 100) {
			sumOfErrors = 100;
		} else if (sumOfErrors < -100) {
			sumOfErrors = -100;
		}

		leftMotor1.set(-ki * sumOfErrors);
		leftMotor2.set(-ki * sumOfErrors);
		rightMotor1.set(-ki * sumOfErrors);
		rightMotor2.set(-ki * sumOfErrors);
		System.out.println(ki * sumOfErrors);
		System.out.println("Error " + angleError);

		if (currentAngle <= finalAngle + angleTolerance && currentAngle >= finalAngle - angleTolerance
				&& rateOfChange < rotationRateTolerance) {
			System.out.println("CONDITION SATISFIED");
			return true;
		} else {
			return false;
		}
	}

	public double returnCX() {
		return table.getNumber("cX", 0.0);
	}

	public boolean detectsCube() {
		double cX = table.getNumber("cX", 0.0);
		if (cX == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean Vision_go_Cube() {
		double cX = table.getNumber("cX", 0.0);
		double cY = table.getNumber("cY", 0.0);
		boolean MinArea = table.putNumber("MinArea", 800);
		boolean threshold = table.putNumber("threshold", 50);
		System.out.println("Threshold: " + threshold);
		System.out.println("MinAreaSet: " + MinArea);
		System.out.println("cX: " + cX);
		// System.out.println("In seek mode");

		double HasCube = table.getNumber("HasTheCube", 0.0);
		
		

		if (HasCube == 1) {
			leftMotor1.set(0);
			leftMotor2.set(0);
			rightMotor1.set(0);
			rightMotor2.set(0);
			Robot.arms.close();
			Robot.ledsub.flash();
			Robot.intake.haltRight();
			Robot.intake.haltLeft();
			return true;

		} else {
			Robot.intake.intakeRightIn();
			Robot.intake.intakeLeftIn();
			Robot.arms.open();

			if (cX == 0.0 || cY == 0.0) {
				// System.out.println("No Cube Detected!!!!!");
				leftMotor1.set(0);
				leftMotor2.set(0);
				rightMotor1.set(0);
				rightMotor2.set(0);

			} else {
				// System.out.println("Cube Detected!!!!!");

				if (cX > minVisionVal && cX < maxVisionVal) {

					leftMotor1.set(0.5);
					leftMotor2.set(0.5);
					rightMotor1.set(-0.5);
					rightMotor2.set(-0.5);
				} else if (cX <= minVisionVal) {
					double angleOfTurn = (50 - cX) * 0.4;
					System.out.print("turn right: ");
					System.out.print(angleOfTurn);
					System.out.println("degrees");

					double angleInitial = Math.abs(Robot.navx.grabValues());
					double angleFinal = Math.abs(Robot.navx.grabValues() + angleOfTurn);

					leftMotor1.set(visionTurnMult * (-((angleInitial / angleFinal) + 0.3)));
					leftMotor2.set(visionTurnMult * (-((angleInitial / angleFinal) + 0.3)));
					rightMotor1.set(visionTurnMult * (-((angleInitial / angleFinal) + 0.3)));
					rightMotor2.set(visionTurnMult * (-((angleInitial / angleFinal) + 0.3)));

					//
					// go straight
				} else {
					double angleOfTurn = (50 - cX) * 0.4;
					System.out.print("turn left: ");
					System.out.print(angleOfTurn);
					System.out.println("degrees");

					double angleInitial = Math.abs(Robot.navx.grabValues());
					double angleFinal = Math.abs(Robot.navx.grabValues() - angleOfTurn);

					leftMotor1.set(visionTurnMult * (((angleInitial / angleFinal) - 0.3)));
					leftMotor2.set(visionTurnMult * (((angleInitial / angleFinal) - 0.3)));
					rightMotor1.set(visionTurnMult * (((angleInitial / angleFinal) - 0.3)));
					rightMotor2.set(visionTurnMult * (((angleInitial / angleFinal) - 0.3)));
					// go straight
				}

			}
			return false;
		}
	}

	public void autoTurnFlat(double direction) {
		// direction = 0.20;
		//// System.out.println(direction*1);
		leftMotor1.set(direction * 1);
		leftMotor2.set(direction * 1);
		rightMotor1.set(direction * 1);
		rightMotor2.set(direction * 1);
	}

	public double monitorCurrentIntakeRight() {
		return pdp.getCurrent(14);
	}

	public double monitorCurrentIntakeLeft() {
		return pdp.getCurrent(0);
	}

	public double monitorCurrent6() {
		return pdp.getCurrent(13);
	}
	
	public double monitorCurrentDriveRight() {
		return pdp.getCurrent(2);
	}
	public double monitorCurrentDriveLeft() {
		return pdp.getCurrent(12);
	}

}
