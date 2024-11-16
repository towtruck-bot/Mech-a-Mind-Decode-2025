package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@Autonomous(name="AutoTestOpMode")
public class AutoTestOpMode extends LinearOpMode {
    DriveSubsystem driveSubsystem;

    @Override
    public void runOpMode() throws InterruptedException {
        driveSubsystem = new DriveSubsystem(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {

        }
    }
}
