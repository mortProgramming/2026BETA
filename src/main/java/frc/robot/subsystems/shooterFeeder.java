package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterFeeder extends SubsystemBase {
    public static ShooterFeeder shooterFeeder;
    public SparkMax feederMaster;
    public SparkMaxConfig feederMasterConfig;
    public SparkMax feederFollower1;
    public SparkMaxConfig feederFollower1Config;
    public SparkMax feederFollower2;
    public SparkMaxConfig feederFollower2Config;
    private ShooterFeeder() {
        feederMaster = new SparkMax(17, MotorType.kBrushless);
        feederMasterConfig = new SparkMaxConfig();
        feederMaster.configure(feederMasterConfig, com.revrobotics.ResetMode.kResetSafeParameters, 
        com.revrobotics.PersistMode.kPersistParameters);
        
        feederFollower1 = new SparkMax(18, MotorType.kBrushless);
        feederFollower1Config = new SparkMaxConfig();
        feederFollower1.configure(feederFollower1Config, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
        
        feederFollower2 = new SparkMax(19, MotorType.kBrushless);
        feederFollower2Config = new SparkMaxConfig();
        feederFollower2.configure(feederFollower2Config, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
    
        feederFollower1Config.follow(feederMaster, true);
        feederFollower2Config.follow(feederMaster, true);
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
