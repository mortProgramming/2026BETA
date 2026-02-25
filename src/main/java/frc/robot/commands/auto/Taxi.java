package frc.robot.commands.auto;
import javax.imageio.plugins.tiff.TIFFField;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.commands.teleop.MoveIntakeArm;
import frc.robot.commands.teleop.MoveShooterMotor;
import frc.robot.commands.teleop.SetIntakeArm;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
    public class Taxi extends SequentialCommandGroup {
        public Taxi() {
        addCommands(
            new ParallelCommandGroup(     
                new SetIntakeArm(PhysicalConstants.IntakeArmConstants.outPosition),  
                new TimedIntake(20, PhysicalConstants.IntakeConstants.intakePos),
                new SequentialCommandGroup(
                    new ParallelCommandGroup(          
                        new TimedDrive(3, 0, 0,3), 
                        new TimedShoot(3, PhysicalConstants.ShooterMotorConstants.shootingPos)),
                    new ParallelCommandGroup(
                        new TimedShoot(3, PhysicalConstants.ShooterMotorConstants2.shootingPos),
                        new TimedFeed(3, PhysicalConstants.ShooterFeederConstants.feedingPos))
                        // new TimedDrive(0, 0.03, 0 ,3)

            )
            )
        );
    }
}
