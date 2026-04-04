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
    public class TaxiRCollectHalf extends SequentialCommandGroup {
        public TaxiRCollectHalf() {
               addCommands(
          new ParallelCommandGroup(
            new TimedIntake(6,PhysicalConstants.IntakeConstants.intakeNeg),
            new SequentialCommandGroup(
                new ParallelCommandGroup(
              new TimedDrive(2.1, 0, -2.5,0),
              new TimedIntakeArm(2, PhysicalConstants.IntakeArmConstants.intakeArmPos)
                ),
                new TimedDrive(0.5, 0, 0,-0.1),
                new ParallelCommandGroup(
                  new TimedDrive(1.8, 1.1, 0,0), 
                  new TimedIntakeArm(2, PhysicalConstants.IntakeArmConstants.intakeArmPosauto)
                ),
                new ParallelCommandGroup(
                new TimedIntakeArm(1.2, PhysicalConstants.IntakeArmConstants.intakeArmNeg),
                new TimedDrive(0.87, -2.2, 0,0)
                ),
            new TimedDrive(2.1, 0, 2.3,-0),
              new TimedDrive(1, 0, 2,-0),
              new ParallelCommandGroup(
                new TimedIntakeArm(0.6, PhysicalConstants.IntakeArmConstants.intakeArmPos),
                new TimedDrive(1, 0, 0,-1.225),
                new TimedShoot(2, PhysicalConstants.ShooterMotorConstantsauto3.shootingVel)
                ),
                new ParallelCommandGroup(
                new ParallelCommandGroup(
                  new TimedShoot(10, PhysicalConstants.ShooterMotorConstantsauto3.shootingVel),
                  new TimedFeed(10, PhysicalConstants.ShooterFeederConstants.feedingauto2))
                ,
                  new SequentialCommandGroup(
                    new WaitCommand(4),
                    new TimedIntakeArm(0.9, PhysicalConstants.IntakeArmConstants.intakeArmNeg),
                      new TimedIntakeArm(0.9, PhysicalConstants.IntakeArmConstants.intakeArmPos)
                  )
            ))
            )
            );
          
        
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
          //               new TimedFeed(3, PhysicalConstants.ShooterFeederConstants.feedingauto))
              // )

         //  )
           // )
           ;
        
    }
}
