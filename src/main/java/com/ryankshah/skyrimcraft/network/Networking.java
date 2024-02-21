package com.ryankshah.skyrimcraft.network;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.network.character.*;
import com.ryankshah.skyrimcraft.network.skill.AddXpToSkill;
import com.ryankshah.skyrimcraft.network.spell.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Networking
{
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(Skyrimcraft.MODID);

        registrar.play(AddToLevelUpdates.ID, AddToLevelUpdates::new, payload -> payload.server(AddToLevelUpdates::handleServer).client(AddToLevelUpdates::handleClient));
        registrar.play(AddXpToSkill.ID, AddXpToSkill::new, payload -> payload.server(AddXpToSkill::handleServer).client(AddXpToSkill::handleClient));

        registrar.play(AddToTargetingEntities.ID, AddToTargetingEntities::new, payload -> payload.server(AddToTargetingEntities::handleServer).client(AddToTargetingEntities::handleClient));
        registrar.play(RemoveFromTargetingEntities.ID, RemoveFromTargetingEntities::new, payload -> payload.server(RemoveFromTargetingEntities::handleServer).client(RemoveFromTargetingEntities::handleClient));
        registrar.play(UpdateCurrentTarget.ID, UpdateCurrentTarget::new, payload -> payload.server(UpdateCurrentTarget::handleServer).client(UpdateCurrentTarget::handleClient));

        registrar.play(AddToCompassFeatures.ID, AddToCompassFeatures::new, payload -> payload.server(AddToCompassFeatures::handleServer).client(AddToCompassFeatures::handleClient));

        registrar.play(AddToKnownSpells.ID, AddToKnownSpells::new, payload -> payload.server(AddToKnownSpells::handleServer).client(AddToKnownSpells::handleClient));
        registrar.play(UpdateSelectedSpell.ID, UpdateSelectedSpell::new, payload -> payload.server(UpdateSelectedSpell::handleServer).client(UpdateSelectedSpell::handleClient));
        registrar.play(UpdateShoutCooldown.ID, UpdateShoutCooldown::new, payload -> payload.server(UpdateShoutCooldown::handleServer).client(UpdateShoutCooldown::handleClient));
        registrar.play(CastSpell.ID, CastSpell::new, payload -> payload.server(CastSpell::handle));

        registrar.play(ReplenishMagicka.ID, ReplenishMagicka::new, payload -> payload.server(ReplenishMagicka::handleServer).client(ReplenishMagicka::handleClient));
        registrar.play(ConsumeMagicka.ID, ConsumeMagicka::new, payload -> payload.server(ConsumeMagicka::handleServer).client(ConsumeMagicka::handleClient));

        registrar.play(OpenCharacterCreationScreen.ID, OpenCharacterCreationScreen::new, payload -> payload.server(OpenCharacterCreationScreen::handleServer).client(OpenCharacterCreationScreen::handleClient));
        registrar.play(CreateCharacter.ID, CreateCharacter::new, payload -> payload.server(CreateCharacter::handleServer).client(CreateCharacter::handleClient));
        registrar.play(UpdateCharacter.ID, UpdateCharacter::new, payload -> payload.client(UpdateCharacter::handleClient)); //.server(UpdateCharacter::handleServer)
    }
}