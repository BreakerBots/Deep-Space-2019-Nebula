package frc.team5104.main;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import frc.team5104.auto.AutoSelector;
import frc.team5104.auto.BreakerPathScheduler;
import frc.team5104.subsystem.BreakerSubsystemManager;
import frc.team5104.subsystem.drive.DriveManager;
import frc.team5104.subsystem.drive.DriveSystems;
import frc.team5104.subsystem.drive.Odometry;
import frc.team5104.teleop.BreakerTeleopController;
import frc.team5104.util.console;

/* Breakerbots Robotics Team 2018
 *  ____                 _             _           _       
 * | __ ) _ __ ___  __ _| | _____ _ __| |__   ___ | |_ ___ 
 * |  _ \| '__/ _ \/ _` | |/ / _ \ '__| '_ \ / _ \| __/ __|
 * | |_) | | |  __/ (_| |   <  __/ |  | |_) | (_) | |_\__ \
 * |____/|_|  \___|\__,_|_|\_\___|_|  |_.__/ \___/ \__|___/ 
 */
/**
 * Fallthrough from <strong>Breaker Robot Controller</strong>
 */
public class Robot extends BreakerRobotController.BreakerRobot {
	public Robot() {
		BreakerSubsystemManager.throwSubsystems(
			// new DriveManager(),
			// new VisionManager()
		);
		
//		CameraServer.getInstance().startAutomaticCapture();
	}
	
	//Main
	public void mainEnabled() {
		BreakerSubsystemManager.enabled(mode);
		console.logFile.start();
		Odometry.reset();
	}
	
	public void mainDisabled() {
		BreakerSubsystemManager.disabled();
		console.logFile.end();
	}
	
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	NetworkTableEntry ts = table.getEntry("ts");

	public void mainLoop() {
		if (enabled) {
			BreakerSubsystemManager.update();
			console.log(DriveSystems.encoders.getString(), DriveSystems.gyro.getRawAngle());
		}
		//console.log(Odometry.getPosition().toString(), "A: " + DriveSystems.gyro.getAngle());
	}

	//Auto
	public void autoEnabled() {
//		BreakerPathScheduler.set(
////			AutoSelector.getAuto()
// 			AutoSelector.Paths.Curve.getPath()
//		);
	}
	
	public void autoLoop() {
		BreakerPathScheduler.update();
	}
	
	//Teleop
	public void teleopLoop() {
		BreakerTeleopController.update();
	}
}
