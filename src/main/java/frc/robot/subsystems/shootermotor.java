package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shootermotor extends SubsystemBase {
    public shootermotor shootermotor; 
    private SparkMax shooter;
    private SparkMaxConfig shooterConfig; // Defining variables  
    public shootermotor() {
        shooter = new SparkMax(15, MotorType.kBrushless);    // Sparkmax Creation
        shooterConfig = new SparkMaxConfig();     // Config

        shooter.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
            public void setSpeed(double speed){
            shooter.set(speed);
            }

            public void stop() {
            shooter.set(0);

    }

}