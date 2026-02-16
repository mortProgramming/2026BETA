package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class SetClimber extends Command {
    public Climber climber;
    public double setpoint2;
    public SetClimber(double position2) {
        climber= Climber.getInstance();
        setpoint2=position2;
    }
    public void initialize(){

    }
    public void execute(){
        climber.setSetpoint(setpoint2);
    }
    public void end(boolean interupted) {
        climber.stop();
    }
    public boolean isFinished() {
        return false;
    }
}

