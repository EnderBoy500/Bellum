package net.enderboy500.bellum.data.providers;

import io.github.ciph3rj.cipherlib.data.ParticleResourceProvider;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumParticleTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BellumParticleProvider extends ParticleResourceProvider {
    public BellumParticleProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    protected void generate(Output output) {
        output.accept(BellumParticleTypes.ANCHOR_SWEEP, getNumberedList(Bellum.id("sweeps/anchor_sweep"), 7));
        output.accept(BellumParticleTypes.CIPHERED_SWEEP, getNumberedList(Bellum.id("sweeps/ciphered_sweep"), 7));
        output.accept(BellumParticleTypes.SOUL_SWEEP, getNumberedList(Bellum.id("sweeps/soul_sweep"), 7));
        output.accept(BellumParticleTypes.SHOCKWAVE, getNumberedList(Bellum.id("shockwave"), 7));
    }

    public List<Identifier> getNumberedList(Identifier base, int i) {
        List<Identifier> list = new ArrayList<>();
        for (int j = 0; j < i + 1; j++) {
            list.add(base.withSuffix("_" + String.valueOf(j)));
        }
        return list;
    }

    @Override
    public String getName() {
        return "";
    }
}
