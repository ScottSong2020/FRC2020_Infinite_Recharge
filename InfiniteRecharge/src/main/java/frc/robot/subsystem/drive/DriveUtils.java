package frc.robot.subsystem.drive;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import frc.robot.config.Config;

public class DriveUtils {
    private static Config config;
    public static void setConfig(Config c) {
        config = c;

        WHEEL_CIRCUMFERENCE_INCHES = 2*Math.PI*c.drive.wheelRadius_in;
        KINEMATICS = new DifferentialDriveKinematics(c.drive.trackWidth_in * DriveConstants.METERS_PER_INCH);
    }



    public static double WHEEL_CIRCUMFERENCE_INCHES;
    public static DifferentialDriveKinematics KINEMATICS;

    // The motor controllers we use (TalonSRX) return velocity in terms of native ticks per 100 ms 
    // and expect commands to be similarly dimensioned.
    // Even though this is a utility package, we provide the convenient conversion to/from
    // inches per second to the native ticks per 100 ms.
    // NOTE: Integer truncation is assumed for a maximum reduction of 10 ticks per second.
    // For 8192 ticks per rev that is error of < 0.07 RPM
    public static int ipsToTicksP100(double ips) {
        double rps = ips / WHEEL_CIRCUMFERENCE_INCHES;
        return (int) (config.drive.ticksPerRevolution * rps / 10.0);
    }

    public static double ticksP100ToIps(int ticksP100) {
        double rps = ticksP100 * 10.0 / (config.drive.ticksPerRevolution);
        return rps * WHEEL_CIRCUMFERENCE_INCHES;
    }
}