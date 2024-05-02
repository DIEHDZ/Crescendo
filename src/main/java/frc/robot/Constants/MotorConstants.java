package frc.robot.Constants;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.MotorType;

public class MotorConstants {
    public static final int FRONT_LEFT_MOTOR_ID = 1;
    public static final int FRONT_RIGHT_MOTOR_ID = 2;
    public static final int BACK_LEFT_MOTOR_ID = 3;
    public static final int BACK_RIGHT_MOTOR_ID = 4;
    public static final MotorType MOTOR_TYPE = MotorType.kBrushless;
    public static final IdleMode DEFAULT_IDLE_MODE = IdleMode.kBrake;
}
