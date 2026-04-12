package frc.robot.commands.auto;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.commands.auto.Limelight.RotateToHub;
import frc.robot.commands.teleop.MoveIntakeArm;
import frc.robot.commands.teleop.MoveIntake;
import frc.robot.commands.teleop.MoveShooterFeeder;
import frc.robot.commands.teleop.SetShooterVelocity;
import frc.robot.subsystems.IntakeArm;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.OdometryHelper;
import frc.robot.subsystems.ShooterFeeder;
import frc.robot.subsystems.ShooterMotor;

public class BasicCommands {
    
    public static void setCommands(OdometryHelper odometry, ShooterMotor shooterMotor, IntakeArm intakerotate, ShooterFeeder shooterFeeder, Intake intake) {
        
        NamedCommands.registerCommand("Shooter short", new SetShooterVelocity(PhysicalConstants.ShooterMotorConstants.shootingVel));
        NamedCommands.registerCommand("Feeder", new MoveShooterFeeder(PhysicalConstants.ShooterFeederConstants.feedingPos));
        NamedCommands.registerCommand("intake", new MoveIntake(PhysicalConstants.IntakeConstants.intakeNeg));
        NamedCommands.registerCommand("IntakeArmDown", new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmNeg));
        NamedCommands.registerCommand("IntakeArmHold", new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmNegauto));
        NamedCommands.registerCommand("IntakeArmUp", new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmPos));
        NamedCommands.registerCommand("HubLock", new RotateToHub(odometry));
        
    
}
}
