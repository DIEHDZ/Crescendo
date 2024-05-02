package frc.robot.Hardware;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import units.angle;

public class Gyroscope extends SubsystemBase {
    private AHRS gyro;
    private GyroConvention convention;

    public enum GyroConvention {
        LEFT_IS_POSITIVE,  // Rotating left increases angle
        RIGHT_IS_POSITIVE  // Rotating right increases angle
    }

    public Gyroscope() {
        gyro = new AHRS(SerialPort.Port.kMXP);  // Connect to MXP port
        convention = GyroConvention.RIGHT_IS_POSITIVE;  // Define the convention based on your robot's configuration

        // Zero the yaw when the system starts
        gyro.zeroYaw();
    }

    public double getXAngle() {
        return getConventionMultiplier() * gyro.getRoll();  // Adjust angle based on convention
    }

    public double getYAngle() {
        return getConventionMultiplier() * gyro.getPitch();
    }

    public double getZAngle() {
        return getConventionMultiplier() * gyro.getYaw();
    }

    public void resetZAngle() {
        gyro.zeroYaw();
    }

    private double getConventionMultiplier() {
        // Adjust the sign of the angle based on the convention
        return (convention == GyroConvention.LEFT_IS_POSITIVE) ? 1.0 : -1.0;
    }

    @Override
    public void periodic() {
        // Update SmartDashboard or Shuffleboard with gyro data
        SmartDashboard.putNumber("Gyroscope X", getXAngle());
        SmartDashboard.putNumber("Gyroscope Y", getYAngle());
        SmartDashboard.putNumber("Gyroscope Z", getZAngle());
    }
}

