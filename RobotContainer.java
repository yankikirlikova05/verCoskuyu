package frc.robot;

import javax.naming.spi.DirObjectFactory;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.VerCoskuyu;
import frc.robot.commands.VerKuskuyu;
import frc.robot.subsystems.driveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private driveTrain drivetrain;
  private final DriveWithJoystick driveWithJoystick;
  public static Joystick driverJoystick;
  //ver coskuyu adlı command tanımlıcaz
  public  VerCoskuyu ece;
  public  VerKuskuyu sea;

  public AHRS gyro;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    drivetrain = new driveTrain(gyro);
    driveWithJoystick = new DriveWithJoystick(drivetrain);
    driveWithJoystick.addRequirements(drivetrain);
    drivetrain.setDefaultCommand(driveWithJoystick);
    driverJoystick = new Joystick(Constants.JOYSTICK_NUMBER);
    ece = new VerCoskuyu(drivetrain);
    sea = new VerKuskuyu(drivetrain);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(
      new RunCommand(
        () -> drivetrain.driveWithJoystick(
          driverJoystick, 1), drivetrain )
    );

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return sea; 
  }
}
