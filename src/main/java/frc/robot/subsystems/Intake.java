package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class Intake extends SubsystemBase {
    private CANSparkMax controller;

    public Intake() {
        // Assuming motor configuration similar to what you provided
        controller = new CANSparkMax(7, MotorType.kBrushless);  // Motor ID and type based on your configuration needs

        // Motor basic configurations
        controller.restoreFactoryDefaults();
        controller.setIdleMode(CANSparkMax.IdleMode.kBrake);  // Set motor to brake mode
        controller.setInverted(true);  // Set motor inversion if needed

        // Additional configurations can be set here based on your system's needs
    }

    public void setPercentage(double percentage) {
        controller.set(percentage);
    }

    public void stop() {
        controller.set(0);
    }

    public Command setPercentageCommand(double percentage) {
        return new InstantCommand(() -> setPercentage(percentage), this);
    }

    public Command stopCommand() {
        return new InstantCommand(this::stop, this);
    }
}
