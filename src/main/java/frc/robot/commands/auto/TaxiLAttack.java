package frc.robot.commands.auto;
import static frc.robot.Constants.PhysicalConstants.IntakeArmConstants.intakeArmNeg;

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
                  new TimedDrive(1, 2, 0.4,0)),
                  new WaitCommand(0.5),
                new ParallelCommandGroup(
                  new TimedDrive(1, -2, 0.3,0),
                  new TimedIntakeArm(1, PhysicalConstants.IntakeArmConstants.intakeArmNeg)),
                  new WaitCommand(0.5),
                  new TimedDrive(1,2 , 0.3, 0),
                  new WaitCommand(0.5),
                new ParallelCommandGroup(
                  new TimedDrive(1, 0, -2,0)),
                  new TimedDrive(1, 0, 0, 2)) 
          );
                      // new ParallelCommandGroup(
              //   new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmPos),
              //   new TimedDrive(2, 0, 0,0.58),
              //   new TimedShoot(3, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel)),
              // new ParallelCommandGroup(
              //     new TimedShoot(10, PhysicalConstants.ShooterMotorConstantsauto2.shootingVel),
              //     new TimedFeed(10, PhysicalConstants.ShooterFeederConstants.feedingauto)));   
            }
      }