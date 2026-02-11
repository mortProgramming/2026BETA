package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooterFeeder extends SubsystemBase {
    public static shooterFeeder shooterFeeder;
    public SparkMax feeder;
    public SparkMaxConfig feederConfig;
    public shooterFeeder() {
        feeder = new SparkMax(17, MotorType.kBrushless);
        feederConfig = new SparkMaxConfig();
        feeder.configure(feederConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
    }
        public void setSpeed(double speed) {
            feeder.set(speed);
        }
        public void stop() {
            feeder.set(0);
        }

        public static shooterFeeder getInstance() {
                if(shooterFeeder==null)
                shooterFeeder = new shooterFeeder();
                return shooterFeeder;
    }
    }
