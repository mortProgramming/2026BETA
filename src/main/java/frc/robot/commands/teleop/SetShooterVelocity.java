package frc.robot.commands.teleop;
import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.wpilibj2.command.Command; 
import frc.robot.subsystems.ShooterMotor;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.IntakeArmConstants;

public class SetShooterVelocity extends Command {
    public ShooterMotor shooter;
    public double velocity;
    public double RPM; 
    public SetShooterVelocity(double velocity) {
        shooter = ShooterMotor.getInstance();
        RPM=velocity;
    }
    public void initialize(){
        shooter.getPidController().reset();
    }
    public void execute(){
        shooter.setShooterSpeedRPM(RPM);
    }
    public void end(boolean interupted) { 
        shooter.stop();
    }
    public boolean isFinished() {
        return false;
    }
}