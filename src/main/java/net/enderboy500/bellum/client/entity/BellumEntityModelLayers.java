package net.enderboy500.bellum.client.entity;

import com.google.common.collect.Sets;
import net.enderboy500.bellum.Bellum;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ArmorModelSet;

import java.util.Set;

public class BellumEntityModelLayers {
    private static final String MAIN = "main";
    private static final Set<ModelLayerLocation> LAYERS = Sets.newHashSet();
    public static final ModelLayerLocation TRAINING_DUMMY = registerMain("training_dummy");

    private static ModelLayerLocation registerMain(String id) {
        return register(id, "main");
    }

    private static ModelLayerLocation register(String id, String layer) {
        ModelLayerLocation entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(Bellum.id(id), layer);
    }
}
