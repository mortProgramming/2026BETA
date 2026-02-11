package frc.robot.commands.auto;
import javax.imageio.plugins.tiff.TIFFField;
import frc.robot.commands.auto.timedDrive;
import frc.robot.commands.teleop.moveShooterMotor;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
    public class Taxi extends SequentialCommandGroup {
        public Taxi() {
        addCommands(new SequentialCommandGroup(new timedDrive(3, 1, 0,3), new moveShooterMotor(1), new timedDrive(3, 1, 0,3)));
    }
}
