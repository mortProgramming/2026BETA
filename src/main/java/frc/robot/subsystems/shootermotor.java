package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;

public class ShooterMotor extends SubsystemBase {
    public static ShooterMotor shootermotor; 
    private SparkFlex shooter;
    public SparkFlex shooterFollower;
    public SparkFlexConfig shooterFollowerConfig;
    private SparkFlexConfig shooterConfig; // Defining variables  
    private ShooterMotor() {
        shooter = new SparkFlex(17, MotorType.kBrushless);    // SparkFlex Creation
        shooterConfig = new SparkFlexConfig();     
        shooter.configure(shooterConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);
                 
        shooterFollower= new SparkFlex(18, MotorType.kBrushless);
        shooterFollowerConfig = new SparkFlexConfig();
        shooterFollower.configure(shooterFollowerConfig, com.revrobotics.ResetMode.kResetSafeParameters,
         com.revrobotics.PersistMode.kPersistParameters);

         shooterFollowerConfig.follow(17, true);
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
