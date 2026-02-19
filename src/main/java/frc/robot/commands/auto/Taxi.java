package frc.robot.commands.auto;
import javax.imageio.plugins.tiff.TIFFField;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.commands.teleop.MoveShooterMotor;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
    public class Taxi extends SequentialCommandGroup {
        public Taxi() {
        addCommands(
            new SequentialCommandGroup(
                new TimedDrive(3, 0, 0,3), 
                new TimedShoot(3), 
                new TimedDrive(0, -3, 0,3)

            )
        );
    }
}
