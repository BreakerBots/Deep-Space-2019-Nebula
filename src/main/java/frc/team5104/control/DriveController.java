package frc.team5104.control;

import frc.team5104.subsystem.drive.Drive;
import frc.team5104.subsystem.drive.DriveSystems;
import frc.team5104.subsystem.drive.RobotDriveSignal;
import frc.team5104.subsystem.drive.RobotDriveSignal.DriveUnit;
import frc.team5104.util.BezierCurve;
import frc.team5104.util.BezierCurveInterpolator;
import frc.team5104.util.Deadband;
import frc.team5104.util.Deadband.deadbandType;

public class DriveController {
	//Variables
	private static final BezierCurve _driveCurve = new BezierCurve(.2, 0, .2, 1);
	private static final double _driveCurveChange = 1.0;
	private static final BezierCurveInterpolator vTeleopLeftSpeed  = new BezierCurveInterpolator(_driveCurveChange, _driveCurve);
	private static final BezierCurveInterpolator vTeleopRightSpeed = new BezierCurveInterpolator(_driveCurveChange, _driveCurve);
	
	private static BezierCurve turnCurve = new BezierCurve(0, 0.4, 1, 0.2);
	private static final double _turnCurveSpeedAdjust = 0.5;
	
	//Main Handle Function
	public static void handle() {
		//Get inputs
		double turn = Controls.Drive._turn.getAxis();
		double forward = Controls.Drive._forward.getAxis() - Controls.Drive._reverse.getAxis();

		//Apply controller deadbands
		turn = -Deadband.get(turn,  0.1, deadbandType.slopeAdjustment);
		forward = Deadband.get(forward, 0.01, deadbandType.slopeAdjustment);
		
		//Apply bezier curve
		turnCurve.x1 = (1 - Math.abs(forward)) * (1 - _turnCurveSpeedAdjust) + _turnCurveSpeedAdjust;
		turn = turnCurve.getPoint(turn);
		
		//Apply inertia affect
		vTeleopLeftSpeed.setSetpoint(forward - turn);
		vTeleopRightSpeed.setSetpoint(forward + turn);
		RobotDriveSignal signal = new RobotDriveSignal(
			vTeleopLeftSpeed.update(), 
			vTeleopRightSpeed.update(), 
			DriveUnit.percentOutput
		);
		
		//Apply drive straight effects
		signal = Drive.applyDriveStraight(signal);
		
		//Apply min speed
		signal = Drive.applyMotorMinSpeed(signal);
		
		//Set talon speeds
		Drive.set(signal);
		
		//Shifting
		if (Controls.Drive._shift.getPressed())
			DriveSystems.shifters.toggle();
	}
}
