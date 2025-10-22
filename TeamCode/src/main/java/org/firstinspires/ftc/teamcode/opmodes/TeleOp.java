package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOp extends LinearOpMode {

    private DcMotor backLeftMotor, backRightMotor, frontLeftMotor, frontRightMotor, intake,shooter;
    private IMU imu;

    double shooterpower = 0.5;
    // PID constants (for heading hold later)
    private double kP = 0.02;
    private double kI = 0.0;
    private double kD = 0.001;
    private Servo angleServo, shooterServo;

    private double targetHeading = 0; // target heading in degrees

    @Override
    public void runOpMode() {
        // Hardware map

        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        intake = hardwareMap.get(DcMotor.class, "intakeMotor");
        shooter = hardwareMap.get(DcMotor.class,"shooterMotor");
        angleServo = hardwareMap.get(Servo.class, "angleServo");
        shooterServo = hardwareMap.get(Servo.class, "shooterServo");


        // Reverse right side motors if needed
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // IMU setup (new SDK format)
        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters imuParams = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        );

        imu.initialize(imuParams);

        telemetry.addLine("Initializing IMU...");
        telemetry.update();

        telemetry.addLine("IMU Initialized!");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Joystick inputs
            double y = gamepad1.left_stick_y*0.75; // forward/back
            double x = -gamepad1.left_stick_x*0.75;  // strafe
            double rx = -gamepad1.right_stick_x*0.75; // turn

            // Mecanum formulas
            double frontLeftPower  = y + x + rx;
            double backLeftPower   = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower  = y + x - rx;

            // Normalize so no value exceeds 1.0
            double max = Math.max(y+x+rx,0.7);
            frontLeftPower  /= max;
            backLeftPower   /= max;
            frontRightPower /= max;
            backRightPower  /= max;


            // Set motor powers
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Intake controls
            if (gamepad1.left_bumper) {
                intake.setPower(1);   // intake forward
            } else if (gamepad1.a) {
                intake.setPower(-1);  // purge
            } else {
                intake.setPower(0);   // stop
            }

            if (gamepad1.right_bumper){
                shooter.setPower(shooterpower);
            } else {
                shooter.setPower(0);
            }

            if (gamepad1.y) {
                shooterpower += 0.025;
                sleep(100);
            }

            if (gamepad1.x){
                shooterpower -= 0.025;
                sleep(100);
            }
            // Telemetry for debugging
            telemetry.addData("FL", frontLeftPower);
            telemetry.addData("FR", frontRightPower);
            telemetry.addData("BL", backLeftPower);
            telemetry.addData("BR", backRightPower);
            telemetry.addData("Shooter power:", shooterpower*100+"%");
            telemetry.addData("Heading", getHeading());
            telemetry.update();
        }
    }

    // Get yaw (heading)
    private double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    // Wrap angle to [-180, 180]
    private double angleWrap(double degrees) {
        while (degrees > 180) degrees -= 360;
        while (degrees < -180) degrees += 360;
        return degrees;
    }
}
