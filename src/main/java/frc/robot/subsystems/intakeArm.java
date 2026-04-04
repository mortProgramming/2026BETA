package frc.robot.subsystems;

import java.time.Period;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PIDConstants.IntakeArmConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
public class IntakeArm extends SubsystemBase  {
        private SparkClosedLoopController pidController;
        private ProfiledPIDController posController;
        public static IntakeArm intakeArm;
        private SparkMax intakeRotate;
        private SparkMaxConfig intakeConfigRotate;
        public double position;
        private double motorSpeed = 0;
        private IntakeArm() {
            intakeRotate = new SparkMax(15, MotorType.kBrushless);  
            intakeConfigRotate = new SparkMaxConfig();

            intakeConfigRotate.closedLoop
                .p(PIDConstants.IntakeArmConstants.kP)
                .i(PIDConstants.IntakeArmConstants.kI)
                .d(PIDConstants.IntakeArmConstants.kD);
                            
            intakeRotate.configure(intakeConfigRotate, com.revrobotics.ResetMode.kResetSafeParameters, 
                com.revrobotics.PersistMode.kPersistParameters);
            
            pidController=intakeRotate.getClosedLoopController();
        }


       public void setSpeed(double speed) {
           intakeRotate.set(speed);
     }
        public double getSpeed(){
            return intakeRotate.get();
        }
        
        public void periodic(){
            updatePosition();
            SmartDashboard.putNumber("Intake Arm Position", updatePosition());
            SmartDashboard.putNumber("Intake Arm Speed", intakeRotate.get());
        }

        public double updatePosition(){
           return intakeRotate.getEncoder().getPosition();
        }

        public void setSetpoint(double position){
           this.position = position;
        }
        public void setMotorPercent(double motorSpeed) {
            this.motorSpeed=motorSpeed +PIDConstants.IntakeArmConstants.kG;
        }
        public static IntakeArm getInstance() {
            if(intakeArm==null)
            intakeArm = new IntakeArm();
            return intakeArm;
    }
        public void setArmPosition(double targetPosition){
        pidController.setSetpoint(targetPosition, SparkMax.ControlType.kPosition, ClosedLoopSlot.kSlot0, 0);
    }
        public double getMotorRotationRPM() {
        return intakeRotate.getEncoder().getVelocity();
    }
       // public boolean atSetpoint(){
          //  return ClosedLoopController.isAtSetpoint();
        //}
            public ProfiledPIDController getPidController() {
            return posController;
        }
        public void stop() {
    intakeRotate.set(0);
        }
}
