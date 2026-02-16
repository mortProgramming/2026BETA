package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterMotor extends SubsystemBase {
    public static ShooterMotor shootermotor; 
    private SparkMax shooter;
    private SparkMaxConfig shooterConfig; // Defining variables  
    private ShooterMotor() {
        shooter = new SparkMax(16, MotorType.kBrushless);    // Sparkmax Creation
        shooterConfig = new SparkMaxConfig();     
        shooter.configure(shooterConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
    }
            public void setSpeed(double speed){
                shooter.set(speed);
            }

            public void stop() {
            shooter.set(0);
            }

    public static ShooterMotor getInstance() {
        if(shootermotor==null) 
            shootermotor = new ShooterMotor();
        return shootermotor;

        //v₀ = √[(33.37·x²)/(1.036x - 4)] 
        //Shooter Formula

}
}
