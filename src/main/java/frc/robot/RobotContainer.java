// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Swerve.SwerveDrive;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
    private final XboxController controller = new XboxController(0);
    private final SwerveDrive drivetrain = new SwerveDrive();
    private final Shooter shooter = new Shooter();
    private final Timer timer = new Timer();

    public RobotContainer() {
        configureButtonBindings();
        configureSubsystems();
    }

    private void configureSubsystems() {
        // Initialize subsystems and devices
        SmartDashboard.putBoolean("Climbing", false); // Example of Climbing Status
    }

    private void configureButtonBindings() {


    }

    public Command getAutonomousCommand() {
        // Setup autonomous routine using commands
        return new SequentialCommandGroup(
            new RunCommand(() -> SwerveDrive.drive(0.5, 0.0, 0.0), drivetrain).withTimeout(2),
            new InstantCommand(() -> SwerveDrive.stop(), drivetrain)
        );
    }
    
    public void robotInit() {
        // Called during robot initialization
        configureSubsystems();
    }

    public void robotPeriodic() {
        // Called periodically during all robot modes
    }

    public void disabledInit() {
        // Called once each time the robot enters the disabled state
    }

    public void disabledPeriodic() {
        // Called periodically during the disabled state
    }

    public void autonomousInit() {
        // Called once each time the robot enters the autonomous state
    }

    public void autonomousPeriodic() {
        // Called periodically during autonomous
    }

    public void teleopInit() {
        // Called once each time the robot enters the teleop state
    }

    public void teleopPeriodic() {
        // Called periodically during teleop
    }

    public void testInit() {
        // Called once each time the robot enters the test state
    }

    public void testPeriodic() {
        // Called periodically during test
    }
}

