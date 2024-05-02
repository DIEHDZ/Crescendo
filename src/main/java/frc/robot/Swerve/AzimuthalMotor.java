package frc.robot.Swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class AzimuthalMotor extends SubsystemBase {
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    private CANPIDController pidController;
    private double targetAngle;

    public AzimuthalMotor(int motorId, MotorType motorType, double p, double i, double d) {
        motor = new CANSparkMax(motorId, motorType);
        encoder = motor.getEncoder();
        pidController = motor.getPIDController();
        pidController.setP(p);
        pidController.setI(i);
        pidController.setD(d);

        motor.restoreFactoryDefaults();
        motor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        Shuffleboard.getTab("Swerve Drive")
            .addNumber("Azimuthal Angle Current " + motorId, this::getCurrentAngle)
            .withWidget("Dial")
            .withProperties(Map.of("min", -180, "max", 180));
        Shuffleboard.getTab("Swerve Drive")
            .addNumber("Azimuthal Angle Target " + motorId, () -> this.targetAngle)
            .withWidget("Dial")
            .withProperties(Map.of("min", -180, "max", 180));
    }

    public void update() {
        double currentAngle = getCurrentAngle();
        double output = pidController.calculate(currentAngle, targetAngle);
        motor.setVoltage(-output);
    }

    public void setTargetAngle(double angle) {
        this.targetAngle = angle % 360;
        if (this.targetAngle > 180) {
            this.targetAngle -= 360;
        } else if (this.targetAngle < -180) {
            this.targetAngle += 360;
        }
    }

    public double getCurrentAngle() {
        return encoder.getPosition();
    }
}
