package frc.robot.Swerve;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Translation2d;

public class SwerveDrive extends SubsystemBase {
    private SwerveModule frontRight, frontLeft, backRight, backLeft;

    public SwerveDrive() {
        frontRight = new SwerveModule(1, 2); // IDs for motors
        frontLeft = new SwerveModule(3, 4);
        backRight = new SwerveModule(5, 6);
        backLeft = new SwerveModule(7, 8);
    }

    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldOriented) {
        var swerveModuleStates = SwerveDriveKinematics.toSwerveModuleStates(
            fieldOriented ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, getRotation()) :
                            new ChassisSpeeds(xSpeed, ySpeed, rot)
        );
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, SwerveConstants.MAX_SPEED);

        frontRight.setDesiredState(swerveModuleStates[0]);
        frontLeft.setDesiredState(swerveModuleStates[1]);
        backRight.setDesiredState(swerveModuleStates[2]);
        backLeft.setDesiredState(swerveModuleStates[3]);
    }

    private Rotation2d getRotation() {
        // Get the current rotation from the gyro or other sensor
        return new Rotation2d();
    }
}
