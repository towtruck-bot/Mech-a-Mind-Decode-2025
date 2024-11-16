package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

@TeleOp(name="MainOpMode")
public class MainOpMode extends LinearOpMode {
    ElevatorSubsystem elevatorSubsystem;
    GrabberSubsystem grabberSubsystem;
    DriveSubsystem driveSubsystem;

    private int currentElevatorLevel;

    @Override
    public void runOpMode() throws InterruptedException {
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        grabberSubsystem = new GrabberSubsystem(hardwareMap);
        driveSubsystem = new DriveSubsystem(hardwareMap);

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            driveControls();
            driveTelemetry();

            elevatorControls();
//            elevatorManual();
            elevatorTelemetry();

//            grabberControls();
            grabberManual();
            grabberTelemetry();

            telemetry.update();
        }
    }

    private void driveControls() {
        if(driveSubsystem == null) return;

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        driveSubsystem.driveRobotCentric(x, y, rx);

        if(gamepad1.options) {
            driveSubsystem.resetHeading();
        }
    }

    public void driveTelemetry() {
        if(driveSubsystem == null) {
            telemetry.addData("Drive Status: ", "Disabled");
            return;
        }
    }

    private void elevatorControls() {
        if(elevatorSubsystem == null) return;

        boolean override = gamepad2.left_bumper;

        double speed = -gamepad2.left_stick_y * Constants.ElevatorConstants.MAX_MOTOR_SPEED;

        if(speed > 0 && elevatorSubsystem.getPosition() > 3000 && !override) {
            speed = 0;
        } else if(speed < 0 && elevatorSubsystem.getPosition() <= 0 && !override) {
            speed = 0;
        }

        elevatorSubsystem.setSpeed(speed);
    }

    private void elevatorManual() {
        if(elevatorSubsystem == null) return;

        elevatorSubsystem.setSpeed(-gamepad2.left_stick_y * Constants.ElevatorConstants.MAX_MOTOR_SPEED);
    }

    private void elevatorTelemetry() {
        if(elevatorSubsystem == null) {
            telemetry.addData("Elevator Status: ", "Disabled");
            return;
        }

        telemetry.addData("Elevator Position: ", elevatorSubsystem.getPosition());
    }

    private void grabberControls() {
        if(grabberSubsystem == null) return;
    }

    private void grabberManual() {
        if(grabberSubsystem == null) return;

        if(gamepad2.dpad_up) {
            grabberSubsystem.setAngle(grabberSubsystem.getGrabberPosition() + 0.1);
            sleep(100);
        } else if(gamepad2.dpad_down) {
            grabberSubsystem.setAngle(grabberSubsystem.getGrabberPosition() - 0.1);
            sleep(100);
        }

        if(gamepad2.triangle) {
            grabberSubsystem.setGrabberPosition(0.7);
            sleep(100);
        } else if(gamepad2.square) {
            grabberSubsystem.setGrabberPosition(0.35);
            sleep(100);
        }

        grabberSubsystem.setExtendPower(-gamepad2.right_stick_y * Constants.GrabberConstants.MAX_EXTEND_SPEED);
    }

    private void grabberTelemetry() {
        if(grabberSubsystem == null) {
            telemetry.addData("Grabber Status: ", "Disabled");
            return;
        }

        telemetry.addData("Grabber Position: ", grabberSubsystem.getGrabberPosition());
        telemetry.addData("Angle Position: ", grabberSubsystem.getAngle());
    }
}
