package frc.robot.Swerve;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule extends SubsystemBase {
    private final CANSparkMax driveMotor;
    private final CANSparkMax azimuthMotor;
    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder azimuthEncoder;
    private final CANPIDController drivePIDController;
    private final CANPIDController azimuthPIDController;

    public SwerveModule(int driveMotorID, int azimuthMotorID) {
        driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        azimuthMotor = new CANSparkMax(azimuthMotorID, MotorType.kBrushless);

        driveMotor.restoreFactoryDefaults();
        azimuthMotor.restoreFactoryDefaults();

        driveMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        azimuthMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        driveMotor.setInverted(false);
        azimuthMotor.setInverted(true);

        driveEncoder = driveMotor.getEncoder();
        azimuthEncoder = azimuthMotor.getEncoder();

        drivePIDController = driveMotor.getPIDController();
        azimuthPIDController = azimuthMotor.getPIDController();

        // Set PID coefficients, might need to tune these values
        drivePIDController.setP(0.1);
        drivePIDController.setI(0.0);
        drivePIDController.setD(0.0);

        azimuthPIDController.setP(0.1);
        azimuthPIDController.setI(0.0);
        azimuthPIDController.setD(0.0);
    }

    public void setDesiredState(SwerveModuleState desiredState) {
        double driveSpeed = desiredState.speedMetersPerSecond; 
        Rotation2d azimuthRotation = desiredState.angle;

        // Set motor speeds and rotations
        drivePIDController.setReference(driveSpeed, CANSparkMax.ControlType.kVelocity);
        double targetAngle = azimuthRotation.getDegrees(); // convert rotation to target encoder position
        azimuthPIDController.setReference(targetAngle, CANSparkMax.ControlType.kPosition);
    }
}
