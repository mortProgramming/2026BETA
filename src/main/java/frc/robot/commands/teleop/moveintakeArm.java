package frc.robot.commands.teleop;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.subsystems.IntakeArm;
import edu.wpi.first.wpilibj2.command.Command;


public class MoveIntakeArm extends Command {
        private double m_speed;
        private IntakeArm m_intakeArm;
    public MoveIntakeArm(double speed) {
        m_speed = speed;
        m_intakeArm = IntakeArm.getInstance();
        addRequirements(m_intakeArm);

    }
    public void initialize() {
    }
    public void execute() {
        m_intakeArm.setSetpoint(PhysicalConstants.IntakeArmConstants.outPosition);
    }
    public void end(boolean interrupted) {
        m_intakeArm.setSetpoint(PhysicalConstants.IntakeArmConstants.inPosition);
    }    
    public boolean isFinished() {
        return false;
    }
    public static MoveIntakeArm maxSpeed(){
        return new MoveIntakeArm(1.0);
    }
}