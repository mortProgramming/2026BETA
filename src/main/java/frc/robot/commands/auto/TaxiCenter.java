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

import frc.robot.Constants.PhysicalConstants.ShooterMotorConstantsauto;
    public class TaxiCenter extends SequentialCommandGroup {
        public TaxiCenter() {
               addCommands(
            new SequentialCommandGroup(
              new TimedDrive(1, -0.15, 0,0),
              new ParallelCommandGroup(
                new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmPos),
                new TimedShoot(2, PhysicalConstants.ShooterMotorConstants.shootingVel)),
              new ParallelCommandGroup(
                  new TimedShoot(6, PhysicalConstants.ShooterMotorConstants.shootingVel),
                  new TimedFeed(6, PhysicalConstants.ShooterFeederConstants.feedingPos))
        )
        )
        
            
        //     new ParallelCommandGroup(     
        //    new TimedIntake(12,PhysicalConstants.IntakeConstants.intakeNeg),
        //            new SequentialCommandGroup(          
        //       new TimedDrive(4, -2, 0,2), 
        //        new TimedDrive(1.5, 0, -2,0), 
        //          new TimedDrive(1.5, 0, 2,0), 
        //                         new SequentialCommandGroup(
        //                 new TimedShoot(3, PhysicalConstants.ShooterMotorConstants2.shootingVel)),
        //             new ParallelCommandGroup(
        //                   new TimedDrive(1, 0, 0,6), 
        //                 new TimedShoot(3, PhysicalConstants.ShooterMotorConstants2.shootingVel),
        //                 new TimedFeed(3, PhysicalConstants.ShooterFeederConstants.feedingauto))
        //        )

           
           // )
           ;
        
    }
}
