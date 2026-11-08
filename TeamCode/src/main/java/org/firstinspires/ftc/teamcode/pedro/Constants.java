package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FrontLeft");
        c.frontRightName.set("FrontRight");
        c.backLeftName.set("BackLeft");
        c.backRightName.set("BackRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-5.940980085237759);
        c.yPodOffset.set(-6.499421727938915);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.3177913272639355);
                Controller secondaryTranslationalForward = Controller.proportional(0.11741540388336871);
                Controller primaryTranslationalLateral = Controller.proportional(0.6453354606595281);
                Controller secondaryTranslationalLateral = Controller.proportional(0.2384342090326054);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.013346985862892506));
                c.brake.set(Controller.proportionalFeedforward(0.01134493798345863));

                c.headingFeedback.set(Controller.proportional(5.899809465280119));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04956897666572184, 0.006326697086438316));

                c.linearBrakeCoefficients.set(Matrix.diag(0.10329816470418801, 0.0769751186142648));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0011277072646867542, 0.0013972758561318387));

                c.maxAchievableForwardVelocity.set(75.58953139301813);
                c.maxAchievableStrafeVelocity.set(58.1195861579199);
                c.naturalForwardDeceleration.set(30.89682894118931);
                c.naturalStrafeDeceleration.set(73.06090956377858);
            }
    );
}