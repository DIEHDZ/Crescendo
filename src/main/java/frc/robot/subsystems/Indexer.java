package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class Indexer extends SubsystemBase {
    private CANSparkMax controller;

    public Indexer() {
        // Assume similar motor configuration to other subsystems
        controller = new CANSparkMax(8, MotorType.kBrushless);  // Change the ID based on your actual setup

        // Motor basic configurations
        controller.restoreFactoryDefaults();
        controller.setIdleMode(CANSparkMax.IdleMode.kBrake);  // Set motor to brake mode
        controller.setInverted(false);  // Adjust this based on your robot's configuration

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
