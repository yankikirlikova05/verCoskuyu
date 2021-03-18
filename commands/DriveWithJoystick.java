package frc.robot.commands;

import java.sql.Driver;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.driveTrain;

public class DriveWithJoystick extends CommandBase {
  private final driveTrain drivetrain;
  /**
   * Creates a new DriveWithJoystick.
   */
  public DriveWithJoystick(driveTrain dt) {
    drivetrain = dt;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.driveWithJoystick(RobotContainer.driverJoystick, Constants.DRIVETRAINSPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
