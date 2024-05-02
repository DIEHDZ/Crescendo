package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class Climber extends SubsystemBase {
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    public Climber() {
        // Initialize motors with IDs based on your setup, assumed to be 9 and 10 for example
        leftMotor = new CANSparkMax(9, MotorType.kBrushless);
        rightMotor = new CANSparkMax(10, MotorType.kBrushless);

        // Motor configuration
        leftMotor.restoreFactoryDefaults();
        rightMotor.restoreFactoryDefaults();

        leftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
    }

    public void setPercentage(double leftPercentage, double rightPercentage) {
        leftMotor.set(leftPercentage);
        rightMotor.set(rightPercentage);
    }

    public void stop() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    public Command setPercentageCommand(double leftPercentage, double rightPercentage) {
        return new InstantCommand(() -> setPercentage(leftPercentage, rightPercentage), this);
    }

    public Command stopCommand() {
        return new InstantCommand(this::stop, this);
    }
}
