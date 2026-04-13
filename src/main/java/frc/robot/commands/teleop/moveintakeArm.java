package frc.robot.commands.teleop;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.subsystems.IntakeArm;
import edu.wpi.first.wpilibj2.command.Command;


public class MoveIntakeArm extends Command {
        private double m_speed;
        private IntakeArm intakeArm;

    public MoveIntakeArm(IntakeArm intakeArm, double speed) {
        m_speed = speed;
        intakeArm = IntakeArm.getInstance();
        addRequirements(intakeArm);

    }
    public void initialize() {
    }
    public void execute() {
        intakeArm.setSpeed(m_speed);
    }
    public void end(boolean interrupted) {
        intakeArm.setSpeed(0);
    }    
    public boolean isFinished() {
        return false;
    }
    // public static MoveIntakeArm maxSpeed(){
    //     return new MoveIntakeArm(1.0);
    // }
}