package frc.robot.Constants;

import edu.wpi.first.math.geometry.Translation2d;

public class DriveConstants {
    public static final double FULL_TURN = 360.0; // degrees
    public static final double MODULE_ANGLE_MIN = -180.0; // degrees
    public static final double MODULE_ANGLE_MAX = 180.0; // degrees
    public static final double RAMP_RATE = 0.25; // seconds

    // Module positions relative to robot center
    public static final Translation2d FRONT_LEFT_POSITION = new Translation2d(0.3, 0.3);
    public static final Translation2d FRONT_RIGHT_POSITION = new Translation2d(0.3, -0.3);
    public static final Translation2d BACK_LEFT_POSITION = new Translation2d(-0.3, 0.3);
    public static final Translation2d BACK_RIGHT_POSITION = new Translation2d(-0.3, -0.3);
}
