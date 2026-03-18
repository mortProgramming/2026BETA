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
    public class TaxiCenterDepot extends SequentialCommandGroup {
        public TaxiCenterDepot() {
               addCommands(
           new ParallelCommandGroup(
             new TimedIntake(20,PhysicalConstants.IntakeConstants.intakeNeg),
             new SequentialCommandGroup(
               new TimedDrive(2, -1, -1,-0),
               new ParallelCommandGroup(
                 new TimedDrive(2, 0, 0,-1),
                 new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmPos)
               ),
                 new SequentialCommandGroup(
                     new TimedDrive(2, 0.75, 0,-0),
                     new TimedDrive(2, -0.75, 0,-0),
                     new TimedDrive(2, 0, 0,1),
                         new ParallelCommandGroup(
                             new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmNeg),
                             new SequentialCommandGroup(
                                 new ParallelCommandGroup(
                                     new TimedDrive(2, 0, 0,0.58),
                                     new TimedShoot(3, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel)),
                                 new ParallelCommandGroup(
                                     new TimedShoot(10, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel),
                                     new TimedFeed(10, PhysicalConstants.ShooterFeederConstants.feedingPos))
                                     )
                 )
     )
     )
        )
            )
               ;
    }
}
