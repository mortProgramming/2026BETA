package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intakeArm extends SubsystemBase  {
        public static intakeArm intakeArm;
        private SparkMax intakeRotate;
        private SparkMaxConfig intakeConfigRotate;
        public intakeArm() {
            intakeRotate = new SparkMax(15, MotorType.kBrushless);  
            intakeConfigRotate = new SparkMaxConfig();  
            intakeRotate.configure(intakeConfigRotate, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
        }

        public void setSpeed(double speed) {
            intakeRotate.set(speed);
        }
        public void stop() {
            intakeRotate.set(0);
        }

        public static intakeArm getInstance() {
                if(intakeArm==null)
                intakeArm = new intakeArm();
                return intakeArm;
    }
}
