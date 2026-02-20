package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterFeederConstants;

public class ShooterFeeder extends SubsystemBase {
    public static ShooterFeeder shooterFeeder;
    public SparkFlex feederMaster;
    public SparkFlexConfig feederMasterConfig;
    public SparkFlex feederFollower1;
    public SparkFlexConfig feederFollower1Config;
    public SparkFlex feederFollower2;
    public SparkFlexConfig feederFollower2Config;
    private ShooterFeeder() {
        feederMaster = new SparkFlex(16, MotorType.kBrushless);
        feederMasterConfig = new SparkFlexConfig();
        feederMaster.configure(feederMasterConfig, com.revrobotics.ResetMode.kResetSafeParameters, 
        com.revrobotics.PersistMode.kPersistParameters);
        
    //    feederFollower2 = new SparkFlex(19, MotorType.kBrushless);
 //       feederFollower2Config = new Spa0rkFlexConfig();
   //     feederFollower2.configure(feederFollower2Config, com.revrobotics.ResetMode.kResetSafeParameters,
      //   com.revrobotics.PersistMode.kPersistParameters);
    
     //   feederFollower2Config.follow(feederMaster, true);
    }
        public void setSpeed(double speed) {
            feederMaster.set(speed);
        }
        public void stop() {
            feederMaster.set(0);
        }

        public static ShooterFeeder getInstance() {
                if(shooterFeeder==null)
                shooterFeeder = new ShooterFeeder();
                return shooterFeeder;
    }
    }
