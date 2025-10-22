package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        double shootingx = -11.75;
        double shootingy = -12;
        double shootingangle = 225;
        double ballstack1 = -11.75;
        double ballstack2 = 11;
        double ballstack3 = 34.75;


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(63, -12, Math.toRadians(180)))
                .lineToXLinearHeading(ballstack1,Math.toRadians(shootingangle))
                .turnTo(Math.toRadians(270))
                .strafeTo(new Vector2d(ballstack1,-52))
                .strafeTo(new Vector2d(shootingx,shootingy))
                .turnTo(Math.toRadians(225))
                .turnTo(Math.toRadians(270))
                .strafeTo(new Vector2d(ballstack2,-12))
                .strafeTo(new Vector2d(12,-60))
                        .strafeTo(new Vector2d(ballstack2,-50))
                .strafeTo(new Vector2d(shootingx,shootingy))
                .turnTo(Math.toRadians(225))
                .turnTo(Math.toRadians(270))
                .strafeTo(new Vector2d(ballstack3,-12))
                .strafeTo(new Vector2d(ballstack3,-60))
                .strafeTo(new Vector2d(shootingx,shootingy))
                .turnTo(Math.toRadians(225))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}