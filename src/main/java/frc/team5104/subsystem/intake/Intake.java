/*BreakerBots Robotics Team 2019*/
package frc.team5104.subsystem.intake;

import frc.team5104.subsystem.BreakerSubsystem;

public class Intake extends BreakerSubsystem.Actions {
	public static void up() {
		IntakeSystems.Arm.up();
	}
	
	public static void down() {
		IntakeSystems.Arm.down();
	}
}
