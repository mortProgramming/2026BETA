package frc.robot.subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.PIDConstants.ClimberConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.spark.SparkBase.ControlType;

public class Climber extends SubsystemBase {
    public static Climber climber;
    private SparkClosedLoopController closedLoopController2;
    public SparkFlex climbMotor; 
    public SparkFlexConfig climbMotorConfig;
    public double position2;
    private Climber() {  
        climbMotor = new SparkFlex(20, MotorType.kBrushless);
        climbMotorConfig = new SparkFlexConfig();
        climbMotor.configure(climbMotorConfig, com.revrobotics.ResetMode.kResetSafeParameters, 
        com.revrobotics.PersistMode.kPersistParameters);
                climbMotorConfig.closedLoop
                .p(ClimberConstants.kP2)
                .i(ClimberConstants.kI2)
                .d(ClimberConstants.kD2);
        closedLoopController2=climbMotor.getClosedLoopController();
    }
            public void stop() {
            climbMotor.set(0);
        }
    public void periodic(){
            updatePosition();
            SmartDashboard.putNumber("PositionClimb", position2);
            SmartDashboard.putNumber("SpeedClimb", climbMotor.get());
        }
        public double getPosition(){
            return position2;
        }
        public void updatePosition(){
            position2=climbMotor.getExternalEncoder().getPosition();
        }
        public void setSetpoint(double setpoint){
            closedLoopController2.setSetpoint(setpoint, ControlType.kPosition);
        }
    
        public static Climber getInstance() {
            if(climber==null)
            climber = new Climber();
            return climber;
    }
}
