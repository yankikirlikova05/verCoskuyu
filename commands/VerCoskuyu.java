package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveTrain;

public class VerCoskuyu extends CommandBase {
  private final driveTrain drivetrain;
  /** Creates a new VerCoskuyu. */
  private double initTime;

  public VerCoskuyu(driveTrain dt) {
    drivetrain = dt;
    addRequirements(drivetrain);
  
    // Use addRequirements() here to declare subsystem dependencies.
  }
   
//int, string, boolean

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initTime = Timer.getFPGATimestamp();
    
    // başladığı saniyeyi bulmamamız lazım (referans noktası)
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      
    if(Timer.getFPGATimestamp() - initTime < 0.3) {
      drivetrain.verCoskuyu(0.6);
      // System.out.print("intakereverse");
    }
    else{
      drivetrain.verCoskuyu(0);
    }
  }

   // Referans noktasına göre kaç sn geçti.
   // Referans noktasına geçen saniye 3ten küçükse robotu /60 hzında ileri 


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.verCoskuyu(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
