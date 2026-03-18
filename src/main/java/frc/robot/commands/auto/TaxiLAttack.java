package frc.robot.commands.auto;
import javax.imageio.plugins.tiff.TIFFField;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.commands.teleop.MoveIntakeArm;
import frc.robot.commands.teleop.MoveShooterMotor;
import frc.robot.commands.teleop.SetIntakeArm;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants2;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
    public class TaxiLAttack extends SequentialCommandGroup {
        public TaxiLAttack() {
               addCommands(
          new SequentialCommandGroup(
              new TimedDrive(2, 0, 2,-0),
                new ParallelCommandGroup(
                  new TimedDrive(2, 2.5, 1.5,0)),
                new ParallelCommandGroup(
                  new TimedDrive(2, -2.5, 0,0)),
                new ParallelCommandGroup(
                  new TimedDrive(2, 2.5, -1.5,0)) 
                  ),
                  new ParallelCommandGroup(
                  new TimedDrive(2, -2.5, 0,0)),
                  new TimedDrive(2, 0, -2,-0),
                new ParallelCommandGroup(
            new TimedIntake(20,PhysicalConstants.IntakeConstants.intakeNeg),
            new SequentialCommandGroup(
              new TimedDrive(2, 0, 1,-0),
              new ParallelCommandGroup(
                new TimedIntakeArm(0.8, PhysicalConstants.IntakeArmConstants.intakeArmPos),
                new TimedDrive(2, 0, 0,0.58),
                new TimedShoot(3, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel)),
              new ParallelCommandGroup(
                  new TimedShoot(10, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel),
                  new TimedFeed(10, PhysicalConstants.ShooterFeederConstants.feedingPos))
               )));
        }
      }