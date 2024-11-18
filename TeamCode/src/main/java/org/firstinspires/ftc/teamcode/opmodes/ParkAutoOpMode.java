package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

@Autonomous(name="ParkAutoOpMode", preselectTeleOp = "MainOpMode")
public class ParkAutoOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap);
        ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        GrabberSubsystem grabberSubsystem = new GrabberSubsystem(hardwareMap);

        waitForStart();

        grabberSubsystem.setGrabberPosition(0.7);
        sleep(100);

        elevatorSubsystem.setAutoExtension(Constants.ElevatorConstants.ELEVATOR_LEVELS[1]);
        sleep(100);

        driveSubsystem.driveRobotCentric(0.5, 0, 0);

        sleep(1000);

        driveSubsystem.stop();

    }
}
