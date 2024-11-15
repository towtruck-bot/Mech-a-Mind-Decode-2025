package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants.ElevatorConstants;

public class ElevatorSubsystem {
    private DcMotor leftMotor, rightMotor, centerMotor;

    private int setpoint;

    public ElevatorSubsystem(HardwareMap hmap) {
        leftMotor = hmap.get(DcMotor.class, ElevatorConstants.LEFT_MOTOR_ID);
        rightMotor = hmap.get(DcMotor.class, ElevatorConstants.RIGHT_MOTOR_ID);
        centerMotor = hmap.get(DcMotor.class, ElevatorConstants.CENTER_MOTOR_ID);

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        centerMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setSpeed(double speed) {
        leftMotor.setMode(RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(RunMode.RUN_USING_ENCODER);
        centerMotor.setMode(RunMode.RUN_USING_ENCODER);

        leftMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED * speed);
        rightMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED * speed);
        centerMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED * speed);
    }

    public void setExtension(int extension) {
        if(extension > ElevatorConstants.kMaxExtensionCounts) {
            extension = ElevatorConstants.kMaxExtensionCounts;
        } else if(extension < ElevatorConstants.kMinExtensionCounts) {
            extension = ElevatorConstants.kMinExtensionCounts;
        }

        setpoint = extension;

        leftMotor.setTargetPosition(extension);
        rightMotor.setTargetPosition(extension);
        centerMotor.setTargetPosition(extension);

        leftMotor.setMode(RunMode.RUN_TO_POSITION);
        rightMotor.setMode(RunMode.RUN_TO_POSITION);
        centerMotor.setMode(RunMode.RUN_TO_POSITION);

        leftMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED);
        rightMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED);
        centerMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED);
    }

    public boolean atSetpoint() {
        return Math.abs(getPosition() - setpoint) < ElevatorConstants.kSetpointTolerance;
    }

    public int getPosition() {
        return leftMotor.getCurrentPosition();
    }
}
