package frc.robot.subsystems;

import java.time.Period;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PIDConstants.IntakeArmConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.spark.SparkBase.ControlType;
public class IntakeArm extends SubsystemBase  {
        private SparkClosedLoopController ClosedLoopController;
        public static IntakeArm intakeArm;
        private SparkMax intakeRotate;
        private SparkMaxConfig intakeConfigRotate;
        public double position;
        private IntakeArm() {
            intakeRotate = new SparkMax(15, MotorType.kBrushless);  
            intakeConfigRotate = new SparkMaxConfig();

            intakeRotate.configure(intakeConfigRotate, com.revrobotics.ResetMode.kResetSafeParameters, 
                com.revrobotics.PersistMode.kPersistParameters);

            intakeConfigRotate.closedLoop
                .p(IntakeArmConstants.kP)
                .i(IntakeArmConstants.kI)
                .d(IntakeArmConstants.kD);

            ClosedLoopController=intakeRotate.getClosedLoopController();
        }

    //    public void setSpeed(double speed) {
      //      intakeRotate.set(1);
     // }
        
        public void periodic(){
            updatePosition();
            SmartDashboard.putNumber("Position", position);
            SmartDashboard.putNumber("Speed", intakeRotate.get());
        }
        public double getPosition(){
            return position;
        }
        public void updatePosition(){
            position=intakeRotate.getEncoder().getPosition();
        }
        public void setSetpoint(double setpoint){
            ClosedLoopController.setSetpoint(setpoint, ControlType.kPosition);
        }
    
        public static IntakeArm getInstance() {
            if(intakeArm==null)
            intakeArm = new IntakeArm();
            return intakeArm;
    }
        public void stop() {
    intakeRotate.set(PhysicalConstants.IntakeArmConstants.inPosition);
        }
}
