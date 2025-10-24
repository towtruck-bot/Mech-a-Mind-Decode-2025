package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

@TeleOp(name="MainOpMode")
public class

MainOpMode extends LinearOpMode {
    IntakeSubsystem intakeSubsystem;
    ShooterSubsystem shooterSubsystem;
    DriveSubsystem driveSubsystem;

    private int currentElevatorLevel;

    @Override
    public void runOpMode() throws InterruptedException {
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        shooterSubsystem = new ShooterSubsystem(hardwareMap);
        driveSubsystem = new DriveSubsystem(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive()) {
            driveControls();
            driveTelemetry();

            intakeControls();
//            elevatorManual();
            intakeTelemetry();

            shooterControls();
//            grabberManual();
            shooterTelemetry();

            telemetry.update();
        }
    }



    private void driveControls() {
        if (driveSubsystem == null) return;

        double x = Constants.DriveConstants.DRIVE_SPEED * Math.signum(gamepad1.left_stick_x) * Math.pow(Math.abs(gamepad1.left_stick_x), Constants.DriveConstants.DRIVE_SENSITIVITY);
        double y = Constants.DriveConstants.DRIVE_SPEED * Math.signum(-gamepad1.left_stick_y) * Math.pow(Math.abs(gamepad1.left_stick_y), Constants.DriveConstants.DRIVE_SENSITIVITY);
        double rx = Constants.DriveConstants.DRIVE_SPEED * Math.signum(gamepad1.right_stick_x) * Math.pow(Math.abs(gamepad1.right_stick_x), Constants.DriveConstants.DRIVE_SENSITIVITY);

        driveSubsystem.driveRobotCentric(x, y, rx);

        if (gamepad1.options) {
            driveSubsystem.resetHeading();
        }
    }

    public void driveTelemetry() {
        if (driveSubsystem == null) {
            telemetry.addData("Drive Status: ", "Disabled");
        }
    }

    private void intakeControls() {
        if (gamepad2.left_bumper) {
            intakeSubsystem.run();
            }
        if (gamepad2.right_bumper) {
            intakeSubsystem.stop();
            }
        }
        private void intakeTelemetry() {
            if (intakeSubsystem == null) {
                telemetry.addData("Intake Status: ", "Disabled");
            } else{
            telemetry.addData("Intake Power: ", intakeSubsystem.getIntakePower());
            }
        }

        private void shooterControls () {
            if (gamepad2.dpad_up && shooterSubsystem.getShooterAngle() < 1) {
                shooterSubsystem.setShooterAngle(shooterSubsystem.getShooterAngle() + 0.05);
            }
            if (gamepad2.dpad_down && shooterSubsystem.getShooterAngle() > 0) {
                shooterSubsystem.setShooterAngle(shooterSubsystem.getShooterAngle() - 0.05);
            }
            if (gamepad2.y && shooterSubsystem.getShooterPower() < 1) {
                shooterSubsystem.setShooterPower(shooterSubsystem.getShooterPower() + 0.05);
            }
            if (gamepad2.x && shooterSubsystem.getShooterPower() > 0) {
                shooterSubsystem.setShooterPower(shooterSubsystem.getShooterPower() - 0.05);
            }
            if (gamepad2.left_trigger>0) {
                shooterSubsystem.spin();
            }
            if (gamepad2.right_trigger>0) {
                shooterSubsystem.stop();
            }
        }

        private void shooterTelemetry () {
            if (shooterSubsystem == null) {
                telemetry.addData("Shooter Status: ", "Disabled");
            } else{
                telemetry.addData("Shooter Status: ", "Enabled");
                telemetry.addData("Shooter Angle: ", shooterSubsystem.getShooterAngle());
                telemetry.addData("Shooter Power: ", shooterSubsystem.getShooterPower());
            }
    }
}
