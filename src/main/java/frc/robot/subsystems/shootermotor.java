package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;

public class ShooterMotor extends SubsystemBase {
    public static ShooterMotor shootermotor; 
    private SparkMax shooter;
    public SparkMax shooterFollower;
    public SparkMaxConfig shooterFollowerConfig;
    private SparkMaxConfig shooterConfig; // Defining variables  
    private ShooterMotor() {
        shooter = new SparkMax(17, MotorType.kBrushless);    // Sparkmax Creation
        shooterConfig = new SparkMaxConfig();     
        shooter.configure(shooterConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
                 
        shooterFollower= new SparkMax(18, MotorType.kBrushless);
        shooterFollowerConfig = new SparkMaxConfig();
        shooterFollower.configure(shooterFollowerConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);

         shooterFollowerConfig.follow(17, true);
    }
            public void setSpeed(double speed){
                shooter.set(PhysicalConstants.ShooterMotorConstants.shootingSpeed);
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
