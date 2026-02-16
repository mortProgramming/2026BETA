package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
    private static Intake intake;
    private SparkMax intakeMaster;
    private SparkMaxConfig intakeConfigMaster; // Defining variables
    private SparkMax intakeFollower;
    private SparkMaxConfig intakeConfigFollower;



    private Intake() {
        intakeMaster = new SparkMax(13, MotorType.kBrushless);      // Sparkmax Creation
        intakeFollower = new SparkMax(14, MotorType.kBrushless);
        intakeConfigMaster = new SparkMaxConfig();
        intakeConfigFollower = new SparkMaxConfig();
        intakeMaster.configure(intakeConfigMaster, ResetMode.kResetSafeParameters, 
        PersistMode.kPersistParameters);
        intakeFollower.configure(intakeConfigFollower, ResetMode.kResetSafeParameters, 
        PersistMode.kPersistParameters);
        intakeConfigFollower.follow(13, false);

    }

            public void setSpeed(double speed){
            intakeMaster.set(speed);           }
            public void stop() {
            intakeMaster.set(0);        
    }
            public static Intake getInstance() {
                if(intake==null)
                intake = new Intake();
                return intake;
    }

}