package frc.robot.commands.auto.Limelight;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.RobotContainer;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.OdometryHelper;
import frc.robot.subsystems.Vision;
import static edu.wpi.first.units.Units.MetersPerSecond;
import frc.robot.Constants.TunerConstants;

public class RotateToHub extends Command {

    private final CommandSwerveDrivetrain drivetrain;
    private final OdometryHelper odometryHelper;
    private final boolean holdContinuously;

    // PD gains — tune kP first, then add a small kD if it oscillates
    private static final double kP            = 0.3;
    private static final double kD            = 0.001;
    private static final double MAX_ROT_SPEED = 4.0;   // rad/s — lower if too aggressive
    private static final double DEADBAND_DEG  = 3;   // degrees of acceptable error
    private static final double FINISHED_DEG  = 3;   // degrees to consider "locked on"

    private double previousError = 0.0;

    // Swerve request — robot-centric so X/Y are robot-relative, rotation is field-relative error
    private final SwerveRequest.RobotCentric driveRequest = new SwerveRequest.RobotCentric()
        .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    // Default constructor: holds continuously (use for teleop button hold)
    public RotateToHub(OdometryHelper odometryHelper) {
        this(odometryHelper, true);
    }

    // Use holdContinuously=false for autonomous (finishes when locked on)
    public RotateToHub(OdometryHelper odometryHelper, boolean holdContinuously) {
        this.drivetrain = RobotContainer.getSwerveDrivetrain();
        this.odometryHelper = odometryHelper;
        this.holdContinuously = holdContinuously;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        previousError = 0.0;
    }

    @Override
    public void execute() {
    Pose2d robotPose = odometryHelper.getPose();

    // Compute the field-relative angle from the robot to the hub
    Translation2d hubTarget = odometryHelper.getHubTarget().getTranslation();
    Translation2d robotToHub = hubTarget.minus(robotPose.getTranslation());
    Rotation2d fieldAngleToHub = robotToHub.getAngle();

    // Wrap error to [-180, 180] to always take the shortest path
    double headingErrorDeg = Math.IEEEremainder(
        fieldAngleToHub.minus(robotPose.getRotation()).getDegrees(),
        360.0
    );

    // PD controller for rotation
    double rotationSpeed;
    if (Math.abs(headingErrorDeg) < DEADBAND_DEG ) {
        rotationSpeed = 0.0;
    } else {
        double derivative = headingErrorDeg - previousError;
        rotationSpeed = (kP * headingErrorDeg) + (kD * derivative);
        rotationSpeed = Math.max(3, Math.min(2, rotationSpeed));
    }
    previousError = headingErrorDeg;

    // Allow driver to still strafe/drive while locking on
    double maxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
    double xSpeed = -RobotContainer.getDriverController().getLeftY() * maxSpeed;
    double ySpeed = -RobotContainer.getDriverController().getLeftX() * maxSpeed;

    // Copy to final vars so the lambda can capture them
    final double finalX = xSpeed;
    final double finalY = ySpeed;
    final double finalRot = rotationSpeed;

    // Apply the request
    drivetrain.applyRequest(() ->
        driveRequest
            .withVelocityX(finalX)
            .withVelocityY(finalY)
            .withRotationalRate(finalRot)
    ).execute();

    // Telemetry for tuning
    SmartDashboard.putNumber("Heading Error to Hub (deg)", headingErrorDeg);
    SmartDashboard.putNumber("Field Angle to Hub (deg)", fieldAngleToHub.getDegrees());
    SmartDashboard.putNumber("Rotation Speed Output", rotationSpeed);
    //  SmartDashboard.putNumber("Tag ID", getTagId());
    // SmartDashboard.putNumber("X Degrees", getTX());
    // SmartDashboard.putBoolean("Tag Detected?", hasTag());
    // SmartDashboard.putBoolean("AprilTag Detected", getTagId() != -1); // ADD THIS LINE
} 

    @Override
    public void end(boolean interrupted) {
        previousError = 0.0;
        // Stop the drivetrain
        drivetrain.applyRequest(() ->
            driveRequest
                .withVelocityX(0)
                .withVelocityY(0)
                .withRotationalRate(0)
        ).execute();
    }

    @Override
    public boolean isFinished() {
        // In teleop hold mode, never finish — command ends when button is released
        if (holdContinuously) return false;

        // In auton mode, finish when heading error is within tolerance
        Pose2d robotPose = odometryHelper.getPose();
        Translation2d hubTarget = odometryHelper.getHubTarget().getTranslation();
        Translation2d robotToHub = hubTarget.minus(robotPose.getTranslation());
        Rotation2d fieldAngleToHub = robotToHub.getAngle();

        double headingErrorDeg = Math.IEEEremainder(
            fieldAngleToHub.minus(robotPose.getRotation()).getDegrees(),
            360.0
        );

        return Math.abs(headingErrorDeg) < FINISHED_DEG;
    }
}