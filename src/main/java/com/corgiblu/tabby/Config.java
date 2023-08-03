package com.corgiblu.tabby;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static ForgeConfigSpec.ConfigValue<Long> maxCount;
    public static ForgeConfigSpec.IntValue maxRows;
    public static ForgeConfigSpec.BooleanValue adaptive;
    public static ForgeConfigSpec.IntValue adaptiveDivisor;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        CONFIG_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        maxCount = builder
                .comment("The maximum players that can be rendered, set to 0 for the max to always be the current online player count")
                .define("max_count", 0L);
        maxRows = builder
                .comment("The maximum rows that can be rendered, this value is not used if adaptive is set to true")
                .defineInRange("max_rows", 40, 1, 100);
        adaptive = builder
                .comment("Set to true for the amount of rows to adapt based off how many players there are")
                .define("adaptive", false);
        adaptiveDivisor = builder
                .comment("The amount to divide the players online by to determine how many rows will be rendered when adaptive is set to true\nFormula: x / y = maxRows\nWhere x is the value of maxCount and y is the adaptiveDivisor value")
                .defineInRange("adaptive_divisor", 5, 1, 10);
    }
}
