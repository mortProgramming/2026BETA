package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
    private static Intake intake;
    private SparkFlex intakeMaster;
    private SparkFlexConfig intakeConfigMaster; // Defining variables
    //private SparkFlex intakeFollower;
    //private SparkFlexConfig intakeConfigFollower;

    private Intake() {
        intakeMaster = new SparkFlex(14, MotorType.kBrushless);      // SparkFlex Creation
       // intakeFollower = new SparkFlex(15, MotorType.kBrushless);
        intakeConfigMaster = new SparkFlexConfig();
       // intakeConfigFollower = new SparkFlexConfig();
        intakeMaster.configure(intakeConfigMaster, ResetMode.kResetSafeParameters, 
        PersistMode.kPersistParameters);
       // intakeFollower.configure(intakeConfigFollower, ResetMode.kResetSafeParameters, 
      //  PersistMode.kPersistParameters);
      //  intakeConfigFollower.follow(14, false);

        }

            public void setSpeed(double speed){
            intakeMaster.set(speed);           
        }

            public void stop() {
            intakeMaster.set(0);        
        }
            public static Intake getInstance() {
                if(intake==null)
                intake = new Intake();
                return intake;
    }

}