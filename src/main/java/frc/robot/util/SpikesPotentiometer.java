package frc.robot.util;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class SpikesPotentiometer extends AnalogPotentiometer{

    public SpikesPotentiometer(int channel) {
        super(channel);
    }

    public SpikesPotentiometer(int channel, double fullRange, double offset) {
        super(channel, fullRange, offset);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Analog Input");
        builder.addDoubleProperty("Value", this::get, null);
    }
}
