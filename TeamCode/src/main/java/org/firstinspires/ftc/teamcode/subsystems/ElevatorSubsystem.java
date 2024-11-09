package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants.ElevatorConstants;

public class ElevatorSubsystem {
    private DcMotor leftMotor, rightMotor;

    private int setpoint;

    public ElevatorSubsystem(HardwareMap hmap) {
        leftMotor = hmap.get(DcMotor.class, ElevatorConstants.LEFT_MOTOR_ID);
        rightMotor = hmap.get(DcMotor.class, ElevatorConstants.RIGHT_MOTOR_ID);

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setSpeed(double speed) {
        leftMotor.setMode(RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(RunMode.RUN_USING_ENCODER);

        leftMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED * speed);
        rightMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED * speed);
    }

    public void setExtension(int extension) {
        if(extension > ElevatorConstants.kMaxExtensionCounts) {
            extension = ElevatorConstants.kMaxExtensionCounts;
        } else if(extension < ElevatorConstants.kMinExtensionCounts) {
            extension = ElevatorConstants.kMinExtensionCounts;
        }

        setpoint = extension;

        leftMotor.setTargetPosition(leftMotor.getCurrentPosition() + extension);
        rightMotor.setTargetPosition(rightMotor.getCurrentPosition() + extension);

        leftMotor.setMode(RunMode.RUN_TO_POSITION);
        rightMotor.setMode(RunMode.RUN_TO_POSITION);

        leftMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED);
        rightMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED);
    }

    public boolean atSetpoint() {
        return Math.abs(getPosition() - setpoint) < ElevatorConstants.kSetpointTolerance;
    }

    public int getPosition() {
        return leftMotor.getCurrentPosition();
    }
}
