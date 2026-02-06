package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooterMotor extends SubsystemBase {
    public static shooterMotor shootermotor; 
    private SparkMax shooter;
    private SparkMaxConfig shooterConfig; // Defining variables  
    public shooterMotor() {
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
    public static shooterMotor getInstance() {
        if(shootermotor==null)
        shootermotor = new shooterMotor();
        return shootermotor;
    }
}