package frc.robot.commands.auto;

import javax.imageio.plugins.tiff.TIFFField;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.commands.teleop.MoveIntakeArm;
import frc.robot.commands.teleop.MoveShooterMotor;
import frc.robot.commands.teleop.SetIntakeArm;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants2;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
    public class TaxiRightPark extends SequentialCommandGroup {
        public TaxiRightPark() {
               addCommands(
          new SequentialCommandGroup(
              new TimedDrive(2, 0, -2.5,-0),
                  new TimedDrive(2, -1.25, -0.6,0)));
        }
      }