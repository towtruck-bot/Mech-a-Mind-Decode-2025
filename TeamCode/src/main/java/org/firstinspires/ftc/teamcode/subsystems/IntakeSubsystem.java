package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants.ElevatorConstants;

public class IntakeSubsystem {
    private DcMotor intakeMotor;
    private double intakePower = 0;
    public IntakeSubsystem(HardwareMap hmap) {
        intakeMotor = hmap.get(DcMotor.class, "intakeMotor");

    }

    public void setSpeed(double speed) {
        intakeMotor.setMode(RunMode.RUN_USING_ENCODER);
        intakeMotor.setPower(ElevatorConstants.MAX_MOTOR_SPEED * speed);
    }
    public void run(){
        intakePower = 1;
        intakeMotor.setPower(intakePower);

    }

    public void stop() {
        intakePower = 0;
        intakeMotor.setPower(intakePower);
    }
    public void purge(){
        intakePower = -1;
        intakeMotor.setPower(intakePower);
    }

    public double getIntakePower(){
        return intakePower;
    }
}
