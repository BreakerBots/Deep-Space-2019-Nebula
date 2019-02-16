/*BreakerBots Robotics Team 2019*/
package frc.team5104.vision;

import frc.team5104.main.BreakerRobotController;
import frc.team5104.main.BreakerRobotController.RobotMode;
import frc.team5104.subsystem.drive.Drive;
import frc.team5104.subsystem.drive.RobotDriveSignal;

public class VisionManager {
	static RobotMode exitState;
	
	public static void init() {
		VisionSystems.init();
		VisionSystems.networkTable.setEntry("pipeline", 1);
	}
	
	public static void start() {
		Vision.reset();
	}
	
	public static void update() {
		if (VisionMovement.isFinished())
			BreakerRobotController.setMode(exitState);
		else {
			RobotDriveSignal signal = Vision.getNextSignal();
			Drive.applyMotorMinSpeedRough(signal);
			Drive.set(signal);
		}
	}

	public static void stop() {
		
	}
}
