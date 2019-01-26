/*BreakerBots Robotics Team 2019*/
package frc.team5104.auto;

import frc.team5104.main._RobotConstants.Loops;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.FitMethod;

public class _AutoConstants {
	
	//Trajectory Generation
	public static final double _maxVelocity = 10;//16.455; 			  //(Feet) [Tune] in ft/s
	public static final double _maxAcceleration = 10;//21.442; 		  //(Feet) [Tune] in ft/s/s
	public static final double _maxJerk = 0.5;//0.297; 				  //(Feet) [Tune] in ft/s/s/s
	public static final FitMethod _fitMethod = Trajectory.FitMethod.HERMITE_CUBIC; //(Other) [Choose] What curve to Gen trajectory in (use Hermite Cubic)
	public static final int 	  _samples	 = Trajectory.Config.SAMPLES_LOW; //(Other) [Choose] Affects generation speed and quality of Trajectory Generation
	public static final double 	  _dt 		 = Loops._robotHz; //(hz) delta time of Trajectory (time between each point)
	
	//Trajectory Folowing
	public static final double _tfB    = 0.2; //(None) [Tune/Choose] (Range: Great Than Zero) Increases/Decreases Correction
	public static final double _tfZeta = 0.5; //(None) [Tune/Choose] (Range: Zero to One) Increases/Decreases Dampening
}
