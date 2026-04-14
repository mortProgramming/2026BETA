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
        
        NamedCommands.registerCommand("Shooter short", new SetShooterVelocity(PhysicalConstants.ShooterMotorConstants.shootingVel).withTimeout(1));
        NamedCommands.registerCommand("Feeder", new MoveShooterFeeder(PhysicalConstants.ShooterFeederConstants.feedingPos).withTimeout(1));
        NamedCommands.registerCommand("intake", new MoveIntake(PhysicalConstants.IntakeConstants.intakeNeg));//.withTimeout(1));
        NamedCommands.registerCommand("IntakeArmUp", new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmNeg).withTimeout(0.75));
        NamedCommands.registerCommand("IntakeArmHold", new MoveIntakeArm(0.2));//.withTimeout(1));
        //NamedCommands.registerCommand("IntakeArmHold", new SetIntakeArm(PhysicalConstants.IntakeArmConstants.outPosition));//.withTimeout(1));
        NamedCommands.registerCommand("IntakeArmDown", new MoveIntakeArm(PhysicalConstants.IntakeArmConstants.intakeArmPos).withTimeout(0.8));
        NamedCommands.registerCommand("HubLock", new RotateToHub(odometry).withTimeout(1));
        
    
}
}
