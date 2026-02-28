package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command; 
import frc.robot.subsystems.IntakeArm;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.IntakeArmConstants;

public class SetIntakeArm extends Command {
    public IntakeArm intakeArm;
    public double setpoint;
    public SetIntakeArm(double position) {
        intakeArm = IntakeArm.getInstance();
        setpoint=position;
    }
    public void initialize(){

    }
    public void execute(){
        intakeArm.setSetpoint(setpoint);
    }
    public void end(boolean interupted) {
        intakeArm.stop();
    }
    public boolean isFinished() {
        return intakeArm.atSetpoint();
    }
}
