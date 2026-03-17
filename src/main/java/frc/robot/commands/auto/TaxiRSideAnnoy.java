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
    public class TaxiRSideAnnoy extends SequentialCommandGroup {
        public TaxiRSideAnnoy() {
               addCommands(
          new ParallelCommandGroup(
            new TimedIntake(20,PhysicalConstants.IntakeConstants.intakePos),
            new SequentialCommandGroup(
              new TimedDrive(2, 0, 2,0),
              new ParallelCommandGroup(
                new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmPos),
                new SequentialCommandGroup(
                new TimedDrive(2, 0, 0,1), 
                  new TimedDrive(2, -2.5, 0,0), 
                    new TimedDrive(2, 0, 0,-1), 
                new ParallelCommandGroup(
                new TimedShoot(3, PhysicalConstants.ShooterMotorConstants3.shootingVel)))
              ),
              new ParallelCommandGroup(
                  new TimedShoot(10, PhysicalConstants.ShooterMotorConstants3.shootingVel),
                  new TimedFeed(10, PhysicalConstants.ShooterFeederConstants.feedingPos))
        )
        )
        )
          //   new ParallelCommandGroup(     
            
          //          new SequentialCommandGroup(          
          //     new TimedDrive(4, 0, -0,8), 
          //      new TimedDrive(1.5, 0, -2,0), 
          //        new TimedDrive(1.5, 0, 2,0), 
          //                       new SequentialCommandGroup(
          //               new TimedShoot(3, PhysicalConstants.ShooterMotorConstants2.shootingVel)),
          //           new ParallelCommandGroup(
          //                 new TimedDrive(1, 0, 0,6), 
          //               new TimedShoot(3, PhysicalConstants.ShooterMotorConstants2.shootingVel),
          //               new TimedFeed(3, PhysicalConstants.ShooterFeederConstants.feedingPos))
              // )

         //  )
           // )
           ;
        
    }
}
