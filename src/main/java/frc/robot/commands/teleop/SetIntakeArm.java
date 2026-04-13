package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command; 
import frc.robot.subsystems.IntakeArm;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.IntakeArmConstants;

public class SetIntakeArm extends Command {
    public IntakeArm intakeArm;
    public double targetPosition;
    public SetIntakeArm(IntakeArm intakeArm ,double position) {
        intakeArm = IntakeArm.getInstance();
        targetPosition=position;
        addRequirements(intakeArm);
    }
    public void initialize(){
        intakeArm.getPidController();
    }
    public void execute(){
        intakeArm.setArmPosition(targetPosition);
    }
    public void end(boolean interupted) {
        intakeArm.stop();
    }

    public boolean isFinished() {
        return false;
    }
}
