package com.ryankshah.skyrimcraft.network;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.network.character.*;
import com.ryankshah.skyrimcraft.network.recipe.FinishAlchemyRecipe;
import com.ryankshah.skyrimcraft.network.recipe.FinishForgeRecipe;
import com.ryankshah.skyrimcraft.network.recipe.FinishOvenRecipe;
import com.ryankshah.skyrimcraft.network.skill.AddXpToSkill;
import com.ryankshah.skyrimcraft.network.skill.HandlePickpocket;
import com.ryankshah.skyrimcraft.network.spell.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Skyrimcraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Networking
{
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Skyrimcraft.MODID);

        registrar.playBidirectional(AddToLevelUpdates.TYPE, AddToLevelUpdates.CODEC, new DirectionalPayloadHandler<>(
                AddToLevelUpdates::handleClient, AddToLevelUpdates::handleServer
        ));
        registrar.playBidirectional(AddXpToSkill.TYPE, AddXpToSkill.CODEC, new DirectionalPayloadHandler<>(
                AddXpToSkill::handleClient, AddXpToSkill::handleServer
        ));
        registrar.playBidirectional(HandlePickpocket.TYPE, HandlePickpocket.CODEC, new DirectionalPayloadHandler<>(
                HandlePickpocket::handleClient, HandlePickpocket::handleServer
        ));
        registrar.playBidirectional(AddToTargetingEntities.TYPE, AddToTargetingEntities.CODEC, new DirectionalPayloadHandler<>(
                AddToTargetingEntities::handleClient, AddToTargetingEntities::handleServer
        ));
        registrar.playBidirectional(RemoveFromTargetingEntities.TYPE, RemoveFromTargetingEntities.CODEC, new DirectionalPayloadHandler<>(
                RemoveFromTargetingEntities::handleClient, RemoveFromTargetingEntities::handleServer
        ));
        registrar.playBidirectional(UpdateCurrentTarget.TYPE, UpdateCurrentTarget.CODEC, new DirectionalPayloadHandler<>(
                UpdateCurrentTarget::handleClient, UpdateCurrentTarget::handleServer
        ));
        registrar.playBidirectional(AddToCompassFeatures.TYPE, AddToCompassFeatures.CODEC, new DirectionalPayloadHandler<>(
                AddToCompassFeatures::handleClient, AddToCompassFeatures::handleServer
        ));
        registrar.playBidirectional(AddToKnownSpells.TYPE, AddToKnownSpells.CODEC, new DirectionalPayloadHandler<>(
                AddToKnownSpells::handleClient, AddToKnownSpells::handleServer
        ));
        registrar.playBidirectional(UpdateSelectedSpell.TYPE, UpdateSelectedSpell.CODEC, new DirectionalPayloadHandler<>(
                UpdateSelectedSpell::handleClient, UpdateSelectedSpell::handleServer
        ));
        registrar.playBidirectional(UpdateShoutCooldown.TYPE, UpdateShoutCooldown.CODEC, new DirectionalPayloadHandler<>(
                UpdateShoutCooldown::handleClient, UpdateShoutCooldown::handleServer
        ));
        registrar.playToServer(CastSpell.TYPE, CastSpell.CODEC, CastSpell::handle);
        registrar.playToClient(DetectLife.TYPE, DetectLife.CODEC, DetectLife::handle);
        registrar.playBidirectional(ReplenishMagicka.TYPE, ReplenishMagicka.CODEC, new DirectionalPayloadHandler<>(
                ReplenishMagicka::handleClient, ReplenishMagicka::handleServer
        ));
        registrar.playBidirectional(ConsumeMagicka.TYPE, ConsumeMagicka.CODEC, new DirectionalPayloadHandler<>(
                ConsumeMagicka::handleClient, ConsumeMagicka::handleServer
        ));
        registrar.playBidirectional(UpdateMagicka.TYPE, UpdateMagicka.CODEC, new DirectionalPayloadHandler<>(
                UpdateMagicka::handleClient, UpdateMagicka::handleServer
        ));

        registrar.playBidirectional(OpenCharacterCreationScreen.TYPE, OpenCharacterCreationScreen.CODEC, new DirectionalPayloadHandler<>(
                OpenCharacterCreationScreen::handleClient, OpenCharacterCreationScreen::handleServer
        ));
        registrar.playBidirectional(CreateCharacter.TYPE, CreateCharacter.CODEC, new DirectionalPayloadHandler<>(
                CreateCharacter::handleClient, CreateCharacter::handleServer
        ));
        registrar.playBidirectional(UpdateCharacter.TYPE, UpdateCharacter.CODEC, new DirectionalPayloadHandler<>(
                UpdateCharacter::handleClient, UpdateCharacter::handleServer
        ));
        registrar.playBidirectional(UpdateExtraCharacter.TYPE, UpdateExtraCharacter.CODEC, new DirectionalPayloadHandler<>(
                UpdateExtraCharacter::handleClient, UpdateExtraCharacter::handleServer
        ));
        registrar.playBidirectional(UpdateLevelUpdates.TYPE, UpdateLevelUpdates.CODEC, new DirectionalPayloadHandler<>(
                UpdateLevelUpdates::handleClient, UpdateLevelUpdates::handleServer
        ));
        registrar.playBidirectional(UpdateStatIncreases.TYPE, UpdateStatIncreases.CODEC, new DirectionalPayloadHandler<>(
                UpdateStatIncreases::handleClient, UpdateStatIncreases::handleServer
        ));
        registrar.playToServer(FinishAlchemyRecipe.TYPE, FinishAlchemyRecipe.CODEC, FinishAlchemyRecipe::handleServer);
        registrar.playToServer(FinishOvenRecipe.TYPE, FinishOvenRecipe.CODEC, FinishOvenRecipe::handleServer);
        registrar.playToServer(FinishForgeRecipe.TYPE, FinishForgeRecipe.CODEC, FinishForgeRecipe::handleServer);
    }
}