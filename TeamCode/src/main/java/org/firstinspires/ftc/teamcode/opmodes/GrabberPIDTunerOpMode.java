package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

@TeleOp(name="GrabberPIDTuner")
public class GrabberPIDTunerOpMode extends LinearOpMode {
    private GrabberSubsystem grabberSubsystem;

    @Override
    public void runOpMode() throws InterruptedException {
        grabberSubsystem = new GrabberSubsystem(hardwareMap);

        waitForStart();

        if(isStopRequested()) return;

        double power = 0;

        while(opModeIsActive()) {
//            if(power < 1 && gamepad2.dpad_up) {
//                power += 0.001;
//                sleep(200);
//            } else if(power > 0 && gamepad2.dpad_down) {
//                power -= 0.001;
//                sleep(200);
//            }
            power = -gamepad2.left_stick_y * 0.1;

            grabberSubsystem.setAngle_raw(power);

            telemetry.addData("Grabber Power: ", power);
            telemetry.update();
        }
    }
}
