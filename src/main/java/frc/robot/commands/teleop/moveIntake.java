package frc.robot.commands.teleop;
import com.ctre.phoenix6.SignalLogger;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
public class MoveIntake extends Command {
    public Intake intake;
    public double m_speed;
    public MoveIntake(Intake intake ,double speed) {
        m_speed = speed;
        intake = Intake.getInstance();
        addRequirements(intake);
    }
    public void initialize() {
        SignalLogger.stop();
        
    }
    public void execute() {
        intake.setSpeed(m_speed);
    }
    public void end(boolean interrupted) {
        intake.stop();
    }    
    public boolean isFinished() {
        return false;
    }
    // public static MoveIntake maxSpeed(){
    //     return new MoveIntake(1.0);
    // }
}