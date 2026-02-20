// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.*;
// import frc.robot.Constants.PhysicalConstants.ShooterFeederConstants;
// import frc.robot.Constants.PhysicalConstants.IntakeConstants;
// import frc.robot.Constants.PhysicalConstants.IntakeArmConstants;
import static edu.wpi.first.units.Units.*;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import frc.robot.commands.teleop.SetIntakeArm;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.commands.teleop.MoveIntake;
import frc.robot.commands.teleop.MoveShooterMotor;
// import frc.robot.commands.teleop.SetClimber;
import frc.robot.Constants.TunerConstants;
import frc.robot.commands.auto.Taxi;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.commands.teleop.MoveShooterFeeder;
import frc.robot.commands.teleop.MoveIntakeArm;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.ShooterFeeder;
import frc.robot.subsystems.ShooterMotor;

import static frc.robot.Constants.PhysicalConstants.IntakeArmConstants.intakeArmPos;
import static frc.robot.Constants.PhysicalConstants.IntakeArmConstants.intakeArmNeg;

import static frc.robot.Constants.PhysicalConstants.IntakeConstants.intakePos;
import static frc.robot.Constants.PhysicalConstants.IntakeConstants.intakeNeg;

import static frc.robot.Constants.PhysicalConstants.ShooterFeederConstants.feedingPos;
import static frc.robot.Constants.PhysicalConstants.ShooterFeederConstants.feedingNeg;

import static frc.robot.Constants.PhysicalConstants.ShooterMotorConstants.shootingPos;
import static frc.robot.Constants.PhysicalConstants.ShooterMotorConstants.shootingNeg;





public class RobotContainer {
    private final Intake m_intake = Intake.getInstance();
    private final IntakeArm m_intakeArm = IntakeArm.getInstance();
    private final ShooterFeeder m_shooterFeeder = ShooterFeeder.getInstance();
    private final ShooterMotor m_shooterMotor = ShooterMotor.getInstance();
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController joystick = new CommandXboxController(0);

    public static final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    public RobotContainer() {
        configureBindings();
    }
    public void configureBindings() {
        joystick.leftBumper().whileTrue(new MoveIntake(PhysicalConstants.IntakeConstants.intakeNeg));
        joystick.rightBumper().whileTrue(new MoveIntake(PhysicalConstants.IntakeConstants.intakePos));

        joystick.rightTrigger().whileTrue(new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmNeg));
        joystick.leftTrigger().whileTrue(new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmPos));

        joystick.a().whileTrue(new MoveShooterMotor(PhysicalConstants.ShooterMotorConstants.shootingPos));
        joystick.b().whileTrue(new MoveShooterMotor(PhysicalConstants.ShooterMotorConstants.shootingNeg));

        // joystick.x().whileTrue(new SetClimber(PhysicalConstants.ClimberConstants.restPos)); //rest
        // joystick.y().whileTrue(new SetClimber(PhysicalConstants.ClimberConstants.climbPos)); //climb

        joystick.x().whileTrue(new MoveShooterFeeder(PhysicalConstants.ShooterFeederConstants.feedingPos));
        joystick.y().whileTrue(new MoveShooterFeeder(PhysicalConstants.ShooterFeederConstants.feedingNeg));    

        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand( 
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.
        final var idle = new SwerveRequest.Idle();
        RobotModeTriggers.disabled().whileTrue(
            drivetrain.applyRequest(() -> idle).ignoringDisable(true)
        );

        joystick.pov(180).whileTrue(drivetrain.applyRequest(() -> brake));

        joystick.pov(0).onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

        // joystick.b().whileTrue(drivetrain.applyRequest(() ->
        //     point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))
        // ));
        

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
       /*  joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));
        */
        // Reset the field-centric heading on left bumper press.

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        // Simple drive forward auton
        final var idle = new SwerveRequest.Idle();
        return Commands.sequence(
            // Reset our field centric heading to match the robot
            // facing away from our alliance station wall (0 deg).
            drivetrain.runOnce(() -> drivetrain.seedFieldCentric(Rotation2d.kZero)),
            // Then slowly drive forward (away from us) for 5 seconds.
            drivetrain.applyRequest(() ->
                drive.withVelocityX(0.5)
                    .withVelocityY(0)
                    .withRotationalRate(0)
            )
            .withTimeout(5.0),
            // Finally idle for the rest of auton
            drivetrain.applyRequest(() -> idle)
        );
    }
}
