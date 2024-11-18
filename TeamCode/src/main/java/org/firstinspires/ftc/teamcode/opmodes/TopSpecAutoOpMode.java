package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

@Autonomous(name="TopSpecAutoOpMode", preselectTeleOp = "MainOpMode")
public class TopSpecAutoOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap);
        GrabberSubsystem grabberSubsystem = new GrabberSubsystem(hardwareMap);

        waitForStart();

        grabberSubsystem.setGrabberPosition(0.7);
        sleep(100);

        elevatorSubsystem.setAutoExtension(2500);
        sleep(100);

        grabberSubsystem.setAngle(0.35);
        sleep(500);

        driveSubsystem.driveRobotCentric(0, 0.25, 0);
        sleep(2000);

        driveSubsystem.stop();
        sleep(1000);

        grabberSubsystem.setGrabberPosition(0.3);
    }
}
