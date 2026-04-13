package frc.robot.commands.auto;

import java.util.Set;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.commands.auto.Limelight.RotateToHub;
import frc.robot.commands.teleop.MoveIntakeArm;
import frc.robot.commands.teleop.MoveIntake;
import frc.robot.commands.teleop.MoveShooterFeeder;
import frc.robot.commands.teleop.SetShooterVelocity;
import frc.robot.commands.teleop.SetIntakeArm;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class BasicCommands {
    
    public static void setCommands(OdometryHelper odometry, ShooterMotor shootermotor, IntakeArm intakeArm, ShooterFeeder shooterFeeder, Intake intake) {
        
        NamedCommands.registerCommand("Shooter short", new SetShooterVelocity(shootermotor,PhysicalConstants.ShooterMotorConstants.shootingVel).withTimeout(6));
        NamedCommands.registerCommand("Feeder", new MoveShooterFeeder(shooterFeeder,PhysicalConstants.ShooterFeederConstants.feedingPos).withTimeout(6));
        NamedCommands.registerCommand("intake", new MoveIntake(intake,PhysicalConstants.IntakeConstants.intakeNeg));//.withTimeout(1));
        NamedCommands.registerCommand("IntakeArmDown", new MoveIntakeArm(intakeArm, PhysicalConstants.IntakeArmConstants.intakeArmNeg));//.withTimeout(1));
        NamedCommands.registerCommand("IntakeArmHold", new MoveIntakeArm(intakeArm, PhysicalConstants.IntakeArmConstants.intakeArmPosauto));//.withTimeout(1));
        //NamedCommands.registerCommand("IntakeArmHold", new SetIntakeArm(PhysicalConstants.IntakeArmConstants.outPosition));//.withTimeout(1));
        NamedCommands.registerCommand("IntakeArmUp", new MoveIntakeArm(intakeArm, PhysicalConstants.IntakeArmConstants.intakeArmPos).withTimeout(6));
        NamedCommands.registerCommand("HubLock", new RotateToHub(odometry).withTimeout(1));
        
    
}
}
