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
    public class TaxiRSafe extends SequentialCommandGroup {
        public TaxiRSafe() {
               addCommands(
          new ParallelCommandGroup(
            new TimedIntake(8,PhysicalConstants.IntakeConstants.intakeNeg),
            new SequentialCommandGroup(
                new ParallelCommandGroup(
              new TimedDrive(1.5, 0, 2,0),
              new TimedIntakeArm(2, PhysicalConstants.IntakeArmConstants.intakeArmPos)
                ),
                new TimedDrive(1, 0, 0.8, 0),
                new TimedDrive(1, 0, 0, 2),
                new TimedDrive(0.5, 0, -1.6, 0),
                new TimedDrive(1.5,0,-2,0),
                new TimedDrive(1, 0, -2,-0),
              new ParallelCommandGroup(
                new TimedDrive(1, 0, 0,-0.84),
                new TimedShoot(2, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel)),
              new ParallelCommandGroup(
                  new TimedShoot(3, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel),
                  new TimedFeed(3, PhysicalConstants.ShooterFeederConstants.feedingPos))
        ),
                  new ParallelCommandGroup(
                  new TimedShoot(3, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel),
                  new TimedFeed(3, PhysicalConstants.ShooterFeederConstants.feedingPos)),
                  new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmNeg)
        )
                        

            );
          
               
        }
      }