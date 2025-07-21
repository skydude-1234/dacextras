package com.skydude.dacextras;


import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class AttributeModifiers {
    public static final UUID HP_MODIFIER_UUID = UUID.fromString("c4e5bda5-6b2e-4027-b20f-ec89cf3f5e81");
    public static final String HP_MODIFIER_NAME = "rogue_max_health_boost";

    // Helper to generate a modifier with custom amount
    public static AttributeModifier HpModifier(double amount) {
        return new AttributeModifier(
                HP_MODIFIER_UUID,
                HP_MODIFIER_NAME,
                amount,
                AttributeModifier.Operation.ADDITION
        );
    }
}
