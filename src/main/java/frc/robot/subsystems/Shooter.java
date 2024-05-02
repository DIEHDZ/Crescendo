package frc.robot.subsystems;
import  frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class Shooter extends SubsystemBase {
    private CANSparkMax controllerBottom;
    private CANSparkMax controllerTop;
    private CANPIDController pidControllerBottom;
    private CANPIDController pidControllerTop;
    private CANEncoder encoderBottom;
    private CANEncoder encoderTop;

    public Shooter() {
        controllerBottom = new CANSparkMax(3, MotorType.kBrushless);
        controllerTop = new CANSparkMax(4, MotorType.kBrushless);
        
        // Assuming PID and encoder settings from constants are similar to what you'd configure
        controllerBottom.restoreFactoryDefaults();
        controllerTop.restoreFactoryDefaults();
        
        controllerBottom.setIdleMode(CANSparkMax.IdleMode.kBrake);
        controllerTop.setIdleMode(CANSparkMax.IdleMode.kBrake);
        
        controllerBottom.setInverted(true);
        controllerTop.setInverted(false);

        pidControllerBottom = controllerBottom.getPIDController();
        pidControllerTop = controllerTop.getPIDController();

        // Example PID Coefficients
        pidControllerBottom.setP(0.1);
        pidControllerBottom.setI(0.0);
        pidControllerBottom.setD(0.01);

        pidControllerTop.setP(0.1);
        pidControllerTop.setI(0.0);
        pidControllerTop.setD(0.01);

        encoderBottom = controllerBottom.getEncoder();
        encoderTop = controllerTop.getEncoder();
    }

    public void setVelocity(double percentageBottom, double percentageTop) {
        pidControllerBottom.setReference(percentageBottom, CANSparkMax.ControlType.kVelocity);
        pidControllerTop.setReference(percentageTop, CANSparkMax.ControlType.kVelocity);
    }

    public void stop() {
        controllerBottom.set(0);
        controllerTop.set(0);
    }

    public Command setVelocityCommand(double percentageBottom, double percentageTop) {
        return new InstantCommand(() -> setVelocity(percentageBottom, percentageTop), this);
    }

    public Command stopCommand() {
        return new InstantCommand(this::stop, this);
    }
}
